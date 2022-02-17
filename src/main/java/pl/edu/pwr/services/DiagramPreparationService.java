package pl.edu.pwr.services;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import pl.edu.pwr.grammarreader.PlantUMLWalker;
import pl.edu.pwr.cdmodel.*;
import pl.edu.pwr.cdmodel.Class;
import pl.edu.pwr.parser.PlantUMLLexer;
import pl.edu.pwr.parser.PlantUMLParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DiagramPreparationService {
    public ClassDiagram processDiagram(String path){
        ClassDiagram m1 = parseDiagram(path);  // diagram
        replaceAssClasses(m1);
        replaceGeneralizations(m1);
        return m1;
    }

    private ClassDiagram parseDiagram(String path){
        String input = "";
        try {
            input = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e)
        {
            System.out.println("Problem with file: " + path);
            System.exit(0);
        }

        PlantUMLLexer lexer = new PlantUMLLexer( CharStreams.fromString(input) );
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        PlantUMLParser parser = new PlantUMLParser( tokens );
        ParseTree tree = parser.diagram();
        ParseTreeWalker walker = new ParseTreeWalker();
        PlantUMLWalker myWalker = new PlantUMLWalker();
        walker.walk( myWalker, tree );

        return myWalker.getDiagram();
    }

    private void replaceGeneralizations(ClassDiagram m){
        List<Generalization> copy = new ArrayList<>(m.generalizations);
        while (!m.generalizations.isEmpty()) {
            List<Generalization> parents = m.generalizations.stream()
                    .filter(g -> m.generalizations.stream().filter(g2 -> g2.getChild() == g.getParent()).count() == 0).collect(Collectors.toList());

            for (Generalization gen : parents) {
                Class parent = gen.getParent();
                Class child = gen.getChild();

                parent.getAttributes().forEach(a ->
                        {
                            if (child.getAttributes().stream().filter(atr -> atr.getName().equals(a.getName())).count() == 0)
                                child.getAttributes().add(a);
                        }
                );

                List<Association> assOfParent = m.associations.stream()
                        .filter(a -> a.getEnds().contains(parent)).collect(Collectors.toList());
                for(Association a: assOfParent) {
                    Class left = a.getEnd(0);
                    Class right = a.getEnd(1);
                    String roleLeft = a.getRole(0);
                    String roleRight = a.getRole(1);
                    Multiplicity mulLeft = a.getMultiplicity(0);
                    Multiplicity mulRight = a.getMultiplicity(1);

                    if (right == parent){
                        right = left;
                        left = parent;
                        mulLeft = mulRight;
                        mulRight = a.getMultiplicity(0);
                        roleLeft = roleRight;
                        roleRight = a.getRole(0);
                    }

                    Association ass = new Association(a.getName(), a.getReverseName(), child, right);
                    ass.setMultiplicity(mulLeft, 0);
                    ass.setMultiplicity(mulRight, 1);
                    ass.setRole(0, roleLeft);
                    ass.setRole(1, roleRight);
                    m.associations.add(ass);

                    // dodatkowa self-asocjacja
                    if (right == child){
                        Association ass2 = new Association(a.getName(), a.getReverseName(), parent, parent);
                        ass.setMultiplicity(mulLeft, 0);
                        ass.setMultiplicity(mulRight, 1);
                        ass.setRole(0, roleLeft);
                        ass.setRole(1, roleRight);
                     }
                   }
            }
            m.generalizations.removeAll(parents);
        }

        m.generalizations = copy;
    }

    private void replaceAssClasses(ClassDiagram m){
        List<Association> assClasses = m.associations.stream().filter(a -> a.isAssociationClass()).collect(Collectors.toList());
        for(Association ass: assClasses){
            if (ass.getEnds().size() == 2){
                Class left = ass.getEnd(0);
                Multiplicity leftM = ass.getMultiplicity(0);
                Class right = ass.getEnd(1);
                Multiplicity rightM = ass.getMultiplicity(1);
                Class assC = ass.getAssociationClass();

                Association first = new Association("derived-1", "", left, assC);
                first.setMultiplicity(Multiplicity.ONE, 0);
                first.setMultiplicity(leftM, 1);
                m.associations.add(first);

                Association second = new Association("derived-2", "", assC, right);
                second.setMultiplicity(rightM, 0);
                second.setMultiplicity(Multiplicity.ONE, 1);
                m.associations.add(second);
            }
        }

        m.associations.removeAll(assClasses);
    }

}
