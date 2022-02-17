import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import pl.edu.pwr.cdmodel.Association;
import pl.edu.pwr.cdmodel.Attribute;
import pl.edu.pwr.cdmodel.Class;
import pl.edu.pwr.cdmodel.ClassDiagram;
import pl.edu.pwr.services.DiagramPreparationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class BusinessRuleValidator {
    // Dane czytane
    // private static ClassDiagram diagram;
    private static List<BusinessRule> rules;
    private static Set<String> wordsToRemove = new HashSet<>();
    private static Set<String> compositionVb = new HashSet<>();
    private static Set<String> aggregationVb = new HashSet<>();
    private static Set<String> compositionRevVb = new HashSet<>();
    private static Set<String> aggregationRevVb = new HashSet<>();

    private static Set<String> possessionVb = new HashSet<>();
    private static Map<String,Set<String>> synonyms = new HashMap<>();

    // Słowniki budowane przy tagowaniu diagramu klas
    private static Set<String> classNames = new HashSet<>();
    private static Set<String> attrNames = new HashSet<>();
    private static Set<String> assNames = new HashSet<>();
    private static Set<String> glossary = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2){
            System.out.println("BusinessRuleValidator folder diagram rules");
            System.out.println("e.g. BusinessRuleValidator config bank bank_rules");
            System.out.println("Note: diagram.puml and rules.txt should be placed in the folder");

            System.exit(0);
        }

        String path = args[0];
        String class_diagram_name = args[1] + ".puml";
        readAndTagVbList(path);

        ClassDiagram diagram = readClassDiagram(path, class_diagram_name);
        tagDiagramElements(diagram);

        buildGlossaryFromClassDiagram();        // build glossary of all terms
        modifyGlossaryBasedOnAdditionalWords(diagram); // dodaje possession, aggregation etc. if necessary

        modifyClassDiagramBasedOnAdditionalWords(diagram); // possession, aggregations, class synonyms

        String rules_name = args[1] + "_rules.txt";
        String pathToRules = path + "\\" + rules_name;
        rules = readBusinessRules(pathToRules);

        tagBussinessRules(rules);
        // rules.forEach(System.out::println);
        removePropositionsWithTwoVerbs(rules);

        // System.out.println("------------ PO USUNIĘCIU DWÓCH CZASOWNIKÓW ---------- ");
        // rules.forEach(System.out::println);
        removePropositionsBeingSubstrings(rules);

        // System.out.println("------------ PO USUNIĘCIU PODSTRINGÓW ---------- ");
        // rules.forEach(System.out::println);

        replacePropositionsWithSynonyms(rules);

        processPropositions(rules);
        replacePOSwithOF(rules);

        checkBusinessRules(rules, diagram);
        // System.out.println("------------ OSTATECZNY WYNIK ---------- ");
        rules.forEach(System.out::println);
        // System.out.println(diagram.plantUML());
    }

    private static String  removeIdentifiers(String orginal_text){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < orginal_text.length(); i++){
            char ch = orginal_text.charAt(i);
            if (ch >= '0' && ch <= '9')
                continue;
            builder.append(ch);
        }
        return builder.toString();
    }

    private static void checkBusinessRules(List<BusinessRule> rules, ClassDiagram diagram){
        rules.forEach(r -> {
            boolean checkResult = checkRule(r, diagram);
            if (checkResult)
                r.setCorrect();
        });
    }

    private static boolean checkRule(BusinessRule rule, ClassDiagram diagram){
        for(Proposition p: rule.propositions){
            if (!p.isConsistent(diagram)) {
                // System.out.println(p);
                return false;
            }

            p.setCorrect(true);
            // System.out.println(p);
        }
        return true;
    }

    private static void replacePOSwithOF(List<BusinessRule> rules){
        rules.forEach(r -> r.propositions.forEach(p ->
                p.parts = p.addPartWithPOSReplacedByOF()));
    }

    private static void processPropositions(List<BusinessRule> rules){
        rules.forEach(r -> r.propositions.forEach( p ->
                p.parts = p.splitIntoGlossaryParts(p.tokens.size())));
    }

    private static ClassDiagram readClassDiagram(String path, String class_diagram) throws FileNotFoundException {
        DiagramPreparationService service = new DiagramPreparationService();
        return service.processDiagram(path + "\\" + class_diagram);
    }

    private static void readAndTagVbList(String path) throws FileNotFoundException {
        String pathToWords = path + "\\" + "words_to_delete.txt";
        wordsToRemove = readWordsToDelete(pathToWords);

        possessionVb = readWords(path + "\\" + "possession_verbs.txt", false);

        aggregationVb = readWords(path + "\\" + "aggregate_verbs.txt", false);
        compositionVb = readWords(path + "\\" + "composition_verbs.txt", false);

        aggregationRevVb = readWords(path + "\\" + "aggregate_verbs.txt", true);
        compositionRevVb = readWords(path + "\\" + "composition_verbs.txt", true);

        synonyms = readSynonyms(path + "\\" + "synonyms.txt");
    }

    private static Map<String, Set<String>> readSynonyms(String pathToFile) throws FileNotFoundException {
        Map<String, Set<String>> result = new HashMap<>();

        File file = new File(pathToFile);
        Scanner sc = new Scanner(file);
        Set<String> words = new HashSet<>();

        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().equals("")) {
                continue;
            }

            String[] assNameParts = line.split("#");
            if (assNameParts.length >= 2) {
                String term = assNameParts[0].trim();
                term = normalize(term);
                term = removeStoppedWords(term);
                term = getLemma(pipeline, term);

                Set<String> syn = new HashSet<>();
                for(int i = 1; i < assNameParts.length; i++) {
                    String txt = assNameParts[1].trim();
                    txt = normalize(txt);
                    txt = removeStoppedWords(txt);
                    txt = getLemma(pipeline, txt);
                    syn.add(txt);
                }
                result.put(term, syn);
            }
        }
        return result;
    }

    private static Set<String> readWords(String pathToFile, boolean reverse) throws FileNotFoundException {
        File file = new File(pathToFile);
        Scanner sc = new Scanner(file);
        Set<String> words = new HashSet<>();

        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().equals("")) {
                continue;
            }

            String[] assNameParts = line.split("#");
            if (assNameParts.length == 1 || !reverse) {
                String txt = assNameParts[0].trim();
                txt = normalize(txt);
                txt = removeStoppedWords(txt);
                txt = getLemma(pipeline, txt);
                words.add(txt);
            }
            else {
                if (assNameParts.length == 2) {
                    String txt = assNameParts[1].trim();
                    txt = normalize(txt);
                    txt = removeStoppedWords(txt);
                    txt = getLemma(pipeline, txt);
                    words.add(txt);
                }
            }
        }

        return words;
    }

    private static void buildGlossaryFromClassDiagram(){
        glossary.addAll(classNames);
        glossary.addAll(assNames);
        glossary.addAll(attrNames);
    }

    private static void modifyClassDiagramBasedOnAdditionalWords(ClassDiagram diagram){
        diagram.associations.stream().filter(a -> a.isComposition()).forEach(a ->
        {
            a.setAlternativeNames(compositionVb);
            a.setAlternativeReverseNames(compositionRevVb);
        });
        diagram.associations.stream().filter(a -> a.isAggregation()).forEach(a ->
        {
            a.setAlternativeNames(aggregationVb);
            a.setAlternativeReverseNames(aggregationRevVb);
        });
    }

    private static void modifyGlossaryBasedOnAdditionalWords(ClassDiagram diagram){
        if (diagram.associations.stream().anyMatch(ass -> ass.isComposition())) {
            glossary.addAll(compositionVb);
            glossary.addAll(compositionRevVb);
            assNames.addAll(compositionVb);
            assNames.addAll(compositionRevVb);
        }

        if (diagram.associations.stream().anyMatch(ass -> ass.isAggregation())) {
            glossary.addAll(aggregationVb);
            glossary.addAll(aggregationRevVb);
            assNames.addAll(aggregationVb);
            assNames.addAll(aggregationRevVb);
        }

        if (diagram.classes.stream().anyMatch(cl -> cl.getAttributes().size() > 0)){
            glossary.addAll(possessionVb);
        }

        classNames.addAll(synonyms.keySet());
    }

    private static Set<String> readWordsToDelete(String pathToFile) throws FileNotFoundException {
        File file = new File(pathToFile);
        Scanner sc = new Scanner(file);

        Set<String> words = new HashSet<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().equals("")) {
                continue;
            }
            words.add(line.trim());
        }
        return words;
    }

    private static List<BusinessRule> readBusinessRules(String pathToRules) throws FileNotFoundException {
        File file = new File(pathToRules);
        Scanner sc = new Scanner(file);

        List<BusinessRule> rules = new ArrayList<>();
        boolean newRule = true;
        BusinessRule rule = null;

        while (sc.hasNextLine()) {
           String line = sc.nextLine();
           if (line.trim().equals("")) {
               newRule = true;
               continue;
           }

           if (newRule) {
               rule = new BusinessRule(line.trim());
               rules.add(rule);
               newRule = false;
               continue;
           }
           else { // part
               String[] parts = line.split(",");
               if (parts.length == 3) {
                   String s = normalize(parts[0]);
                   String r = normalize(parts[1]);
                   String o = normalize(parts[2]);

                   if (s.startsWith("that")){
                       if (rule != null) {
                           String subject = rule.findSubject(r);
                           List<CoreLabel> tokens = rule.findTokens(subject);
                           StringBuilder builder = new StringBuilder();
                           for (CoreLabel token : tokens) {
                               builder.append(token.word() + " ");
                           }
                           s = builder.toString().trim();
                       }
                   }

                   Proposition p = new Proposition(s, r, o);
                   if (rule != null)
                       rule.propositions.add(p);
               }
               else if (parts.length == 2) {
                   String s = normalize(parts[0]);
                   String r = normalize(parts[1]);

                   if (s.startsWith("that")){
                       if (rule != null) {
                           String subject = rule.findSubject(r);
                           List<CoreLabel> tokens = rule.findTokens(subject);
                           StringBuilder builder = new StringBuilder();
                           for (CoreLabel token : tokens) {
                               builder.append(token.word() + " ");
                           }
                           s = builder.toString().trim();
                       }
                   }

                   Proposition p = new Proposition(s, r, "");
                   if (rule != null)
                       rule.propositions.add(p);
               }
           }
        }

        return rules;
    }

    private static String normalize(String text){
        if (text.equals(""))
            return text;

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < text.length() - 1; i++){
           boolean isCapital = Character.isUpperCase(text.charAt(i));
           boolean isNextCapital = Character.isUpperCase(text.charAt(i+1));
           builder.append(text.charAt(i));
           if (!isCapital && isNextCapital) // zmiana liter z małych na duże
           {
               builder.append(" ");
           }
        }

        builder.append(text.charAt(text.length() - 1)); // dodać ostatni znak
        text = builder.toString();
        text = text.replaceAll("_", " ");
        text = text.replaceAll("-", " ");
        text = text.replaceAll("\"", "");
        while (text.indexOf("  ") >= 0) {
            text = text.replaceAll("  "," ");
        }
        text = text.replaceAll("[\\n\\t]", "");
        text = text.replaceAll("[0-9]","");
        text = text.toLowerCase(Locale.ROOT);
        return text.replaceAll("[,;?.]", "");
    }

    private static String getLemma(StanfordCoreNLP pipeline, String text){
        CoreDocument document = new CoreDocument(text);
        // annnotate the document
        pipeline.annotate(document);
        // examples

        // 10th token of the document
        StringBuilder builder = new StringBuilder();

        for(CoreLabel token : document.tokens()){
            builder.append(token.lemma());
            builder.append(" ");
        }

        // System.out.println(text + "   " + builder.toString().trim());
        return builder.toString().trim();
    }

    private static void tagBussinessRules(List<BusinessRule> rules){
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object

        rules.forEach(r ->
        {
            r.propositions.forEach(p ->
            {
                p.taggedProposition(pipeline);
            });
        });
    }

    private static void tagDiagramElements(ClassDiagram diagram){
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object

        diagram.classes.forEach(c ->
            {
                String cl_name = c.getName();
                cl_name = normalize(cl_name);
                cl_name = removeStoppedWords(cl_name);
                c.setLemma(getLemma(pipeline, cl_name));
                classNames.add(c.getLemma());

                c.getAttributes().forEach(a -> {
                    String at_name = a.getName();
                    at_name = normalize(at_name);
                    at_name = removeStoppedWords(at_name);
                    a.setLemma(getLemma(pipeline, at_name ));
                    attrNames.add(a.getLemma());
                });
            });

        diagram.associations.forEach(ass -> {
            String as_name = ass.getName();
            if (as_name == null || as_name.equals(""))
                ass.setLemma("", false);
            else {
                as_name = normalize(as_name);
                as_name = removeStoppedWords(as_name);
                ass.setLemma(getLemma(pipeline, as_name), false);
                assNames.add(ass.getLemma(false));
            }

            String as_rev_name = ass.getReverseName();
            if (as_rev_name == null || as_rev_name.equals(""))
                ass.setLemma("", true);
            else {
                as_rev_name = normalize(as_rev_name);
                as_rev_name = removeStoppedWords(as_rev_name);
                ass.setLemma(getLemma(pipeline,as_rev_name), true);
                assNames.add(ass.getLemma(true));
            }

            String left_role = ass.getRole(0);

            if (left_role == null || left_role.equals("")) {
                ass.setRoleLemma(0, ass.getEnd(0).getLemma());
            }
            else {
                left_role = normalize(left_role);
                left_role = removeStoppedWords(left_role);
                ass.setRoleLemma(0, getLemma(pipeline, left_role));
                classNames.add(ass.getRoleLemma(0));
            }

            String right_role = ass.getRole(1);

            if (right_role == null || right_role.equals("")) {
                ass.setRoleLemma(1, ass.getEnd(1).getLemma());
            }
            else {
                right_role = normalize(right_role);
                right_role = removeStoppedWords(right_role);
                ass.setRoleLemma(1, getLemma(pipeline, right_role));
                classNames.add(ass.getRoleLemma(1));
            }
        });
    }

    private static String removeStoppedWords(String text) {
        for(String word: wordsToRemove){
            int pos = word.length() > 4 ? text.indexOf(word + " ") : text.indexOf(" " + word + " ");
            while(pos != -1){
                text = text.substring(0, pos) + text.substring(pos + word.length() + 1);
                pos = word.length() > 4 ? text.indexOf(word + " ") : text.indexOf(" " + word + " ");
            }
        }

        StringBuilder builder = new StringBuilder();
        String[] words = text.split(" ");
        for(String w: words) {
            if (wordsToRemove.contains(w))
                continue;

            builder.append(w);
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    private static void replacePropositionsWithSynonyms(List<BusinessRule> rules){
        rules.forEach(r -> {
            r.propositions.forEach( p -> p.replaceTokenWithSynonym());
        });
    }
    private static void removePropositionsWithTwoVerbs(List<BusinessRule> rules){
        rules.forEach(r -> {
            List<Proposition> propositions = r.propositions.stream().filter(p -> p.hasMoreThanOneVerb()).collect(Collectors.toList());
            r.propositions.removeAll(propositions);
        });
    }

    private static void removePropositionsBeingSubstrings(List<BusinessRule> rules){
        List<Proposition> toRemove = new ArrayList<>();

        rules.forEach(r -> {
            Collections.sort(r.propositions, (a, b)->Integer.compare(a.sentence_orginal.length(), b.sentence_orginal.length()));
            toRemove.clear();
            for(int i = 0; i < r.propositions.size() - 1; i++){
                Proposition prop = r.propositions.get(i);
                if (r.propositions.stream().anyMatch(p ->
                   p.sentence_orginal.length() > prop.sentence_orginal.length()
                   && p.sentence_orginal.startsWith(prop.sentence_orginal))){
                    toRemove.add(prop);
                }
            }

            r.propositions.removeAll(toRemove);
        });
    }

    static class RuleDescription {
        String pattern;
        List<String> classes = new ArrayList<>();
        List<String> attributes = new ArrayList<>();
        String ass;
    }

    static class Proposition {
        String subject;
        String relation;
        String object;
        String sentence;
        String sentence_orginal;
        boolean isCorrect = false;

        public List<CoreLabel> tokens = new ArrayList<>();          // budowane ze skróconej listy
        public List<List<GlossaryPart>> parts = new ArrayList<>();

        void replaceTokenWithSynonym(){
            tokens.forEach(t -> {
                String elem = t.lemma();
                String key = partInSynonyms(elem, synonyms);
                if (key != null)
                    t.setLemma(key);
            });
        }

        List<List<GlossaryPart>> addPartsWithSynonyms() {
            List<List<GlossaryPart>> newParts = new ArrayList<>();
            for(List<GlossaryPart> split: parts){
                newParts.add(split);

                for (int first_idx = 0; first_idx < split.size(); first_idx ++) {
                        String elem = split.get(first_idx).part;
                        String key = partInSynonyms(elem, synonyms);
                        if (key != null) {
                            List<GlossaryPart> newSplit = new ArrayList<>(split);
                            GlossaryPart oldPart = split.get(first_idx);

                            GlossaryPart newPart = new GlossaryPart();
                            newPart.part = key;
                            newPart.start_token = oldPart.start_token;
                            newPart.end_token = oldPart.end_token;
                            newPart.type = oldPart.type;
                            newSplit.set(first_idx, newPart);
                            newParts.add(newSplit);
                        }
                }
            }
            return newParts;
        }

        String partInSynonyms(String syn, Map<String, Set<String>> synonyms){
            for(String key: synonyms.keySet()) {
               Set<String> val = synonyms.get(key);
               if (val.contains(syn))
                  return key;
            }

            return null;
        }

        List<List<GlossaryPart>> addPartWithPOSReplacedByOF(){
            List<List<GlossaryPart>> newParts = new ArrayList<>();
            for(List<GlossaryPart> split: parts){
                newParts.add(split);

                if (split.stream().anyMatch(p-> p.type == Type.POS)) {
                    int before = -1;
                    int after = -1;

                    for (int first_idx = 1; first_idx < split.size() - 1; first_idx ++) {
                        if (split.get(first_idx).type == Type.POS){
                            List<GlossaryPart> newSplit = new ArrayList<>(split);
                            before = first_idx - 1;
                            after = first_idx + 1;
                            newSplit.set(before, split.get(after));
                            newSplit.set(after, split.get(before));
                            GlossaryPart oldPart = split.get(first_idx);
                            GlossaryPart newPart = new GlossaryPart();
                            newPart.part = oldPart.part;
                            newPart.start_token = oldPart.start_token;
                            newPart.end_token = oldPart.end_token;
                            newPart.type = Type.OF;
                            newSplit.set(first_idx, newPart);
                            newParts.add(newSplit);
                        }
                    }
                }
            }
            return newParts;
        }

        Proposition(String s, String r, String o){
            subject = s;
            relation = r;
            object = o;
            sentence_orginal = normalize(s).trim() + " " + normalize(r).trim() + " " + normalize(o).trim();
            sentence = removeStoppedWords(sentence_orginal);
        }

        boolean isCorrect() {
            return isCorrect;
        }

        void setCorrect(boolean correct) {
            isCorrect = correct;
        }

        // maszyna stanow
        RuleDescription buildPattern(List<GlossaryPart> partition){
            RuleDescription description = new RuleDescription();
            if (partition.stream().anyMatch(p -> p.type == Type.ERR))
            {
                description.pattern = "ERR";
                return description;
            }

            StringBuilder builder = new StringBuilder();
            partition.forEach(p -> {
                if (p.type != Type.EMPTY){
                    builder.append(p.type.toString() + "_");
                }
            });

            String pattern = builder.toString();
            description.pattern = pattern;
            fillDescription(partition, description);
            return description;
        }

        void fillDescription(List<GlossaryPart> partition, RuleDescription description){
            for(GlossaryPart part: partition){
                if (part.type == Type.EMPTY || part.type == Type.POS || part.type == Type.COND)
                    continue;

                String name = sentenceFromTokens(part.start_token, part.end_token);
                if (part.type == Type.CL)
                    description.classes.add(name);
                else if (part.type == Type.ASS)
                    description.ass = name;
                else if (part.type == Type.ATTR)
                    description.attributes.add(name);
            }
        }

        boolean isAttributeInClass(String atrName, Class cl, ClassDiagram diagram){
            Optional<Attribute> at = cl.getAttributes().stream().filter(a -> a.getLemma().equals(atrName)).findAny();
            if (!at.isPresent())
                return false;
            return true;
        }

        boolean isAttributeInClass(RuleDescription description, ClassDiagram diagram){
            if (description.classes.isEmpty() || description.attributes.isEmpty())
                return false;

            String className = description.classes.get(0);
            Optional<Class> cl = diagram.classes.stream().filter(a -> a.getLemma().equals(className)).findAny();
            if (!cl.isPresent())
                return false;

            return isAttributeInClass(description.attributes.get(0), cl.get(), diagram);
        }

        Class findClassWithRoleNavigableFromClass(ClassDiagram diagram, String role, Class cl){
            Optional<Association> as = findAssConnectingRoleWithClass(diagram, role, cl);
            if (!as.isPresent())
                return null;
            return as.get().getEnd(0) == cl ? as.get().getEnd(1) : as.get().getEnd(0);
        }

        Optional<Association> findAssConnectingRoleWithClass(ClassDiagram diagram, String name, Class cl){
            return diagram.associations.stream()
                    .filter(a ->
                     (  (a.getRoleLemma(0).equals(name) && a.getEnd(1) == cl) || (a.getRoleLemma(1).equals(name) && a.getEnd(0) == cl))
                      || (a.getEnd(0).getLemma().equals(name) && a.getEnd(1) == cl) || (a.getEnd(1).getLemma().equals(name) && a.getEnd(0) == cl))
                    .findAny();
        }

        Optional<Class> findClassWithName(ClassDiagram diagram, String name){
            return diagram.classes.stream().filter(c ->
                    c.getLemma().equals(name)).findAny();
        }

        boolean isAssociationBetweenClasses(ClassDiagram diagram, Class cA, Class cB, String assName){
            Optional<Association> as = diagram.associations.stream().filter(a ->
                    ((a.getLemma(false).equals(assName) || a.getLemma(true).equals(assName)
                     || a.getAlternativeNames().contains(assName) || a.getAlternativeReverseNames().contains(assName))
                   && a.getEnds().contains(cA) && a.getEnds().contains(cB))).findAny();

            if (!as.isPresent())
                return false;
            return true;
        }

        Optional<Class> findClassWithRole(ClassDiagram diagram, String role){
            return diagram.associations.stream()
                    .filter(a -> a.getRoleLemma(0).equals(role) || a.getRoleLemma(1).equals(role))
                    .map(a -> (a.getRoleLemma(0).equals(role) ? a.getEnd(0) : a.getEnd(1))).findAny();
        }

        boolean checkAttributeOfPartOfNavExp(ClassDiagram diagram, RuleDescription description, int first_idx, int last_idx, int attr_idx) throws Exception {
            Class base = null;

            // atrybut bez kontekstu (bez klasy), na pewno istnieje
            if (first_idx == last_idx) {
                return true;
            }

            String className = description.classes.get(last_idx - 1);
            Optional<Class> cl = findClassWithName(diagram, className);
            if (cl.isPresent()) {
                base = cl.get();
            }
            else {
                Optional<Class> cl2 = findClassWithRole(diagram, className);
                if (cl2.isPresent()){
                    base = cl2.get();
                }
            }

            for(int i = last_idx-2; i >= first_idx; i--){
                String roleName =  description.classes.get(i);
                Class cl3 = findClassWithRoleNavigableFromClass(diagram, roleName, base);
                if (cl3 == null)
                    throw new Exception();
                base = cl3;
            }

            String attributeName = description.attributes.get(attr_idx);
            return isAttributeInClass(attributeName, base, diagram);
        }

        Class getTypeOfPartOfNavExp(ClassDiagram diagram, RuleDescription description, int first_idx, int last_idx) throws Exception {
            Class base = null;

            String className = description.classes.get(last_idx - 1);
            if (first_idx + 1 == last_idx) {
                Optional<Class> cl = findClassWithName(diagram, className);
                if (cl.isPresent()) {
                    base = cl.get();
                }
                else {  // szukamy klasy w takiej roli (jak jest dużo to na razie zwracamy jedną)
                   Optional<Class> cl2 = findClassWithRole(diagram, className);
                   if (cl2.isPresent()){
                       base = cl2.get();
                   }
                }
            } else {
                Optional<Class> cl = findClassWithName(diagram, className);
                if (cl.isPresent()) {
                    base = cl.get();
                }
                else {
                    Optional<Class> cl2 = findClassWithRole(diagram, className);
                    if (cl2.isPresent()){
                        base = cl2.get();
                    }
                }
            }

            for(int i = last_idx-2; i >= first_idx; i--){
                String roleName =  description.classes.get(i);
                Class cl = findClassWithRoleNavigableFromClass(diagram, roleName, base);
                if (cl == null)
                    throw new Exception();
                base = cl;
            }

            return base;
        }

        boolean AttributesOfNavigExpExists(ClassDiagram diagram, RuleDescription description){
            int idx = description.pattern.indexOf("COND_");
            String left = description.pattern.substring(0, idx);
            String right = description.pattern.substring(idx + 5);

            String[] leftp = left.split("OF_");
            String[] rightp = right.split("OF_");

            try {
                boolean leftAttrOK = checkAttributeOfPartOfNavExp(diagram, description, 0, leftp.length - 1, 0);
                boolean rightAttrOK = checkAttributeOfPartOfNavExp(diagram, description, leftp.length - 1, leftp.length + rightp.length - 2, 1);
                return leftAttrOK && rightAttrOK;
            } catch (Exception e){
                return false;
            }
        }

        boolean TypesOfNavigExpAreEqal(ClassDiagram diagram, RuleDescription description) {
            int idx = description.pattern.indexOf("COND_");
            String left = description.pattern.substring(0, idx);
            String right = description.pattern.substring(idx + 5);

            String[] leftp = left.split("OF_");
            String[] rightp = right.split("OF_");

            try {
                Class leftClass = getTypeOfPartOfNavExp(diagram, description, 0, leftp.length);
                Class rightClass = getTypeOfPartOfNavExp(diagram, description, leftp.length, leftp.length + rightp.length);
                return (leftClass == rightClass) || leftClass.getAllParents(diagram).contains(rightClass) || rightClass.getAllParents(diagram).contains(leftClass);
            } catch (Exception e){
                return false;
            }
        }

        boolean TypesOfNavigExpAreAssociated(ClassDiagram diagram, RuleDescription description) {
            int idx = description.pattern.indexOf("ASS_");
            String left = description.pattern.substring(0, idx);
            String right = description.pattern.substring(idx + 4);

            String[] leftp = left.split("OF_");
            String[] rightp = right.split("OF_");
            try {
                Class leftClass = getTypeOfPartOfNavExp(diagram, description, 0, leftp.length);
                Class rightClass = getTypeOfPartOfNavExp(diagram, description, leftp.length, leftp.length + rightp.length);
                return (isAssociationBetweenClasses(diagram, leftClass, rightClass, description.ass));
            } catch (Exception e){
                return false;
            }
        }

        int countSubstrings(String input, String text){
            int index = input.indexOf(text);
            int count = 0;
            while (index != -1) {
                count++;
                input = input.substring(index + 1);
                index = input.indexOf(text);
            }
            return count;
        }

        boolean twoInRow(String pattern, String value){
            String[] splitStr = pattern.split("_");

            String last = splitStr[0];
            boolean matched = last.equals(value);

            for(int i = 1; i < splitStr.length; i++){
                last = splitStr[i];

                if (last.equals("EMPTY"))
                    continue;

                if (last.equals(value) && matched)
                    return true;

                matched = last.equals(value);
            }
            return false;
        }

        boolean isConsistent(ClassDiagram diagram){
            boolean found = false;
            for(List<GlossaryPart> partition: parts){
                if (found)
                    break;

                RuleDescription description = buildPattern(partition);

                if (description.pattern.equals("ERR"))
                    continue;

                if (twoInRow(description.pattern, "CL") || twoInRow(description.pattern, "ATTR")
                 || twoInRow(description.pattern, "ASS") || twoInRow(description.pattern, "COND"))
                    continue;

                if (description.pattern.indexOf("COND_") >= 0 &&
                    description.pattern.indexOf("ASS_") >= 0)
                    continue;

                if (countSubstrings(description.pattern, "ATTR_") > 2)
                    continue;

                if (countSubstrings(description.pattern, "COND_") > 1)
                    continue;

                if (countSubstrings(description.pattern, "ASS_") > 1)
                    continue;

                // warunek dotyczący dwóch klas (powinny być tym samym)
                if (description.pattern.indexOf("COND_") >= 0
                    && description.pattern.indexOf("ASS") ==-1
                    && description.pattern.indexOf("POS") ==-1
                    && description.pattern.indexOf("ATTR") == -1){
                        found = TypesOfNavigExpAreEqal(diagram, description);
                }

                // dwie klasy połączone asocjacją
                if (description.pattern.indexOf("ASS_") >= 0
                    && description.pattern.indexOf("COND") ==-1
                    && description.pattern.indexOf("POS") ==-1
                    && description.pattern.indexOf("ATTR") == -1){
                    found = TypesOfNavigExpAreAssociated(diagram, description);
                }

                // pojedynczy atrybut
                if (description.pattern.indexOf("COND_") == -1
                    && description.pattern.indexOf("ASS") == -1
                    && description.pattern.indexOf("POS") == -1
                    && countSubstrings(description.pattern, "ATTR_") == 1){
                    found = isAttributeInClass(description,diagram);
                }

                // warunek dotyczy atrybutów (powinny być w odpowiednich klasach)
                // później można sprawdzić, czy są tych samych typów
                if (description.pattern.indexOf("COND_") >= 0
                    && description.pattern.indexOf("ASS") ==-1
                    && description.pattern.indexOf("POS") ==-1
                    && countSubstrings(description.pattern, "ATTR_") >= 1
                    && countSubstrings(description.pattern, "ATTR_") <= 2){
                    found = AttributesOfNavigExpExists(diagram, description);
                }
            }
            return found;
        }

        String sentenceFromTokens(){
            StringBuilder builder = new StringBuilder();
            tokens.forEach(t -> builder.append(t.lemma() + " "));
            return builder.toString().trim();
        }

        List<Type> findType(String part, List<String> tags){
            List<Type> types = new ArrayList<>();
            boolean found = false;

            if (classNames.contains(part)) {
                types.add(Type.CL);
                found = true;
            }

            if (assNames.contains(part)) {
                types.add(Type.ASS);
                found = true;
            }

            if (attrNames.contains(part)) {
                types.add(Type.ATTR);
                found = true;
            }

            if (part.equals("be")) {
                types.add(Type.COND);
                found = true;
            }

            if (part.equals("of") || part.equals("for")){
                types.add(Type.OF);
                found = true;
            }

            if (part.equals("'s")){
                types.add(Type.POS);
                found = true;
            }

            if (possessionVb.contains(part)) {
                types.add(Type.POS);
                found = true;
            }

            if (!found) {
                if (tags.stream().anyMatch(t -> t.equals("VB") || t.equals("NN")))
                    types.add(Type.ERR);
                else
                    types.add(Type.EMPTY);
            }
            return types;
        }

        // wszystkie części (o różnych długościach) z różnych kategorii, które zaczynają się na pozycji start
        List<GlossaryPart> nextPart(int start){
            List<GlossaryPart> parts = new ArrayList<>();

            boolean found = false;

            for(int toWhich = tokens.size(); toWhich > start; toWhich--){
                String partStr = sentenceFromTokens(start, toWhich);
                List<String> tags = tagsFromTokens(start, toWhich);

                Optional<String> elem = glossary.stream().filter(g -> g.equals(partStr)).findAny();
                if (elem.isPresent()){
                    List<Type> types = findType(partStr, tags);
                    for (Type t : types) {
                        GlossaryPart part = new GlossaryPart();
                        part.part = partStr;
                        part.start_token = start;
                        part.end_token = toWhich;
                        part.type = t;
                        parts.add(part);
                    }

                    found = true;
                    continue;
                    // break;
                }

                if (found)
                    break;

                if (toWhich == start + 1) { // empty
                    List<Type> types = findType(partStr, tags);
                    for(Type t: types) {
                        GlossaryPart part = new GlossaryPart();
                        part.part = partStr;
                        part.start_token = start;
                        part.end_token = toWhich;
                        part.type = t;
                        parts.add(part);
                    }
                    break;
                }
            }

            return parts;
        }

        // tokenNr - pierwszy token = 0
        List<List<GlossaryPart>> splitIntoGlossaryParts(int tokenNr){
            List<List<GlossaryPart>> result = new ArrayList<>();
            result.add(new ArrayList<>());

            while (notFinishedSplitExists(result, tokenNr)) {
                List<GlossaryPart> unfinished = findUnfinishedSplit(result, tokenNr);
                int lastToken = findLastToken(unfinished);
                List<GlossaryPart> parts = nextPart(lastToken);
                for(GlossaryPart part: parts) {
                    List<GlossaryPart> copy = unfinished.stream().collect(Collectors.toList());
                    copy.add(part);
                    result.add(copy);
                }
                result.remove(unfinished);
            }

            return result;
        }

        int findLastToken(List<GlossaryPart> parts){
            if (parts.isEmpty())
                return 0;
            return parts.get(parts.size()-1).end_token;
        }

        boolean notFinishedSplitExists(List<List<GlossaryPart>> split, int tokenNr){
            boolean result = split.stream().anyMatch(l -> findLastToken(l) < tokenNr);
            return result;
        }

        List<GlossaryPart> findUnfinishedSplit(List<List<GlossaryPart>> split, int tokenNr){
            return split.stream().filter(s -> findLastToken(s) < tokenNr).findAny().get();
        }

        List<String> tagsFromTokens(int startToken, int endToken){
            List<String> tags = new ArrayList<>();
            for(int i = 0; i < tokens.size(); i++){
                if (i >= startToken && i < endToken)
                    tags.add(tokens.get(i).tag());
            }
            return tags;
        }

        String sentenceFromTokens(int startToken, int endToken) {
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < tokens.size(); i++){
                if (i >= startToken && i < endToken)
                    builder.append(tokens.get(i).lemma() + " ");
            }
            return builder.toString().trim();
        }

        void taggedProposition(StanfordCoreNLP pipeline){
            CoreDocument document = new CoreDocument(sentence);
            pipeline.annotate(document);
            tokens = document.tokens();
        }

        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("\nSentence: " + sentence);
            builder.append("\nCORRECT: " + isCorrect);

            // builder.append("Sentence from tokens: " + sentenceFromTokens() + "\n");
            //tokens.forEach(t -> {
            //    builder.append(t.lemma() + ":" + t.tag() + " ");
            //});
            /*
            builder.append("\n ------ PARTS ------");
            parts.forEach(l -> {
                builder.append("\n");
                l.forEach(e -> {
                    builder.append(e.part + ":" + e.type + " ");
                });
                builder.append("\n");
            });
            builder.append("\n ------------------");
            */
            return builder.toString();
        }

        boolean hasMoreThanOneVerb(){
            return tokens.stream().filter(t -> t.tag().startsWith("VBD") || t.tag().startsWith("VBZ") || t.tag().equals("VB")).count() > 1;
        }
    }

    static enum Type {
        CL, ATTR, ASS, COND, POS, OF, EMPTY, ERR
    }

    static class GlossaryPart {
        String part;
        Type type;
        int start_token;
        int end_token;
    }

    static class BusinessRule {
        String orginal_text;
        boolean isCorrect = false;
        List<Proposition> propositions = new ArrayList<>();

        BusinessRule(String text){
            orginal_text = normalize(text);
        }

        void setCorrect(){
            isCorrect = true;
        }
        boolean getIsCorrect(){ return isCorrect; }

        void replaceTokenWithSynonym(){

        }
        String findSubject(String part){    // part = (that) is included
            // szukamy najdluzszego fragmentu ze slownika, ktory poprzedza te fraze
            String found = "";
            String[] parts = orginal_text.split("that");
            for(int i = 1; i < parts.length; i++){
                if (parts[i].startsWith(part)){
                    String[] words = parts[i-1].split(" ");
                    String toCheck = "";

                    for(int j = words.length-1; j >= 0; j--){
                        toCheck = words[j] + " " + toCheck;
                        toCheck = toCheck.trim();
                        if (glossary.contains(toCheck)){
                            found = toCheck;
                            continue;
                        }
                    }
                }
            }

            return found;
        }

        List<CoreLabel> findTokens(String subject){ // szuka tokenów dla car movement
            // set up pipeline properties
            Properties props = new Properties();
            // set the list of annotators to run
            props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
            // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
            props.setProperty("coref.algorithm", "neural");
            // build pipeline
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
            // create a document object
            CoreDocument document = new CoreDocument(subject);
            pipeline.annotate(document);
            return document.tokens();
        }

        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("\n=========================");
            builder.append("\nRule: " + orginal_text + "\n");
            builder.append("CORRECT: " + isCorrect + "\n");

            propositions.forEach(p -> {
                builder.append(p);
                builder.append("\n");
            });
            builder.append("\n=========================");
            return builder.toString();
        }
    }
}
