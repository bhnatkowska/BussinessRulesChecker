package pl.edu.pwr.cdmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Association {
    private ArrayList<Class> ends;
    private ArrayList<String> roles;
    private ArrayList<String> role_lemas;
    private ArrayList<Multiplicity> multiplicities;

    private Set<String> alternativeNames = new HashSet<>();
    private Set<String> alternativeReverseNames = new HashSet<>();

    private Class assClass;
    private String name;
    private String reverseName;
    private String lemma;
    private String lemma_reverse;

    private boolean isComposition = false;
    private boolean isAggregation = false;
    private boolean isAssociationClass = false;

    public Association(String name, String reverseName, Class c1, Class c2) {
        this.name = name;
        this.reverseName = reverseName;
        this.roles = new ArrayList<>();
        this.role_lemas = new ArrayList<>();
        this.ends = new ArrayList<>();
        ends.add(c1);
        ends.add(c2);

        multiplicities = new ArrayList<>(ends.size());
        for (int i = 0; i < ends.size(); i++) {
            multiplicities.add(Multiplicity.MANY);
            roles.add("");
            role_lemas.add("");
        }
    }

    public void setAlternativeNames(Set<String> alternativeNames){
        this.alternativeNames = alternativeNames;
    }

    public Set<String> getAlternativeNames(){
        return alternativeNames;
    }

    public Set<String> getAlternativeReverseNames(){
        return alternativeReverseNames;
    }

    public void setAlternativeReverseNames(Set<String> alternativeReverseNames){
        this.alternativeReverseNames = alternativeReverseNames;
    }

    public Association(String name, String reverseName, Class c1, Class c2, boolean isComposition) {
        this(name, reverseName, c1, c2);   // c1 - composite (0), c2 - part (1)
        this.isComposition = true;
    }

    public Association(Class left, Class right, Class assClass) {
        this(assClass.getName(), "", left, right);
        this.assClass = assClass;
        isAssociationClass = true;
    }

    public Association(ArrayList<Class> ends) {
        this.roles = new ArrayList<>();
        this.role_lemas = new ArrayList<>();
        this.ends = ends;
        this.name = "";
        this.reverseName = "";
        multiplicities = new ArrayList<>(ends.size());
        for (int i = 0; i < ends.size(); i++) {
            multiplicities.add(Multiplicity.MANY);
            roles.add("");
            role_lemas.add("");
        }
    }

    public Association(ArrayList<Class> ends, Class assClass) {
        this(ends);
        this.assClass = assClass;
        this.name = assClass.getName();
        this.reverseName = "";
        isAssociationClass = true;
    }

    public String getName(){ return name; }
    public String getLemma(boolean isReverse){
        return (isReverse) ? lemma_reverse : lemma;
    }
    public void setLemma(String lemma, boolean isReverse){
        if (isReverse) this.lemma_reverse = lemma; else this.lemma = lemma;
    }

    public Class getEnd(int i) {
        return ends.get(i);
    }
    public void setEnd(int i, Class c) {
        ends.set(i, c);
    }

    public ArrayList<Class> getEnds() {
        return ends;
    }
    public boolean isAssociationClass() {
        return isAssociationClass;
    }
    public Class getAssociationClass() {
        return assClass;
    }

    public String getRole(int i) {
        return roles.get(i);
    }
    public void setRole(int i, String role) {
        roles.set(i, role);
    }

    public String getRoleLemma(int i) {
        return role_lemas.get(i);
    }
    public void setRoleLemma(int i, String role) {
        role_lemas.set(i, role);
    }

    public void setMultiplicity(Multiplicity mul, int i) {
        multiplicities.set(i, mul);
    }
    private String getMultiplicity(Multiplicity mul) {
        switch (mul) {
            case ZERO_TO_ONE:
                return "0..1";
            case ONE:
                return "1";
            case MANY:
                return "*";
            case ONE_TO_MANY:
                return "1..*";
        }
        return "";
    }

    public String getReverseName(){ return reverseName; }
    public Multiplicity getMultiplicity(int i) {
        return multiplicities.get(i);
    }
    public String getMultiplicityAsString(int i) {
        return getMultiplicity(getMultiplicity(i));
    }


    public boolean isAggregation() {return isAggregation;}
    public void setAggregation(boolean aggregation) {isAggregation = aggregation;}

    public boolean isComposition(){ return isComposition; }
    public void setComposition(boolean composition){isComposition = composition;}

    public String plantUML() {
        if (isComposition)
            return plantUMLComposition();

        if (ends.size() == 2)
            return plantUMLBinary();

        return plantUMLAnary();
    }

    public String plantUMLComposition() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append(ends.get(0).getName());
        sb.append(" *-- ");
        sb.append(" \"1..*\" ");
        sb.append(ends.get(1).getName());
        sb.append("\n");

        return sb.toString();
    }

    public String plantUMLAnary() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");

        for (int i = 0; i < ends.size(); i++) {
            sb.append(ends.get(i).getName());
            sb.append(" ");
            sb.append("\"");
            sb.append(getMultiplicityAsString(i));
            sb.append("\"");
            sb.append(" ");
            sb.append("--");
            sb.append(" ");
            sb.append(name);
            sb.append("\n");
        }


        // classA .. classB
        if (isAssociationClass) {
            sb.append("\n");
            sb.append(name);
            sb.append(" .. ");
            sb.append(assClass.getName());
            sb.append("\n");
        }

        return sb.toString();
    }

    public String plantUMLBinary() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append(ends.get(0).getName());
        sb.append(" ");
        sb.append("\"");
        sb.append(getRole(0));
        if (getRole(0).length() > 0) sb.append(" ");
        sb.append(getMultiplicityAsString(0));
        sb.append("\"");
        sb.append(" ");
        sb.append("--");
        sb.append(" ");
        sb.append("\"");
        sb.append(getRole(1));
        if (getRole(1).length() > 0) sb.append(" ");
        sb.append(getMultiplicityAsString(1));
        sb.append("\"");
        sb.append(" ");
        sb.append(ends.get(1).getName());
        if (!getName().equals(""))
            sb.append(getName());
        if (!getReverseName().equals("")){
            sb.append("#");
            sb.append(getReverseName());
        }

        if (isAssociationClass){
            sb.append("\n");
            sb.append("(");
            sb.append(ends.get(0).getName());
            sb.append(",");
            sb.append(ends.get(1).getName());
            sb.append(") .. ");
            sb.append(assClass.getName());
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString(){
        if (isComposition)
            return toStringComposition();

        if (isAssociationClass)
            return toStringAssociationClass() + toStringAssociation();

        return toStringAssociation();
    }

    public String toStringComposition(){
        StringBuffer sb = new StringBuffer();
        sb.append("======= COMPOSITION ======= \n");
        sb.append("Composite: " + ends.get(0).getName() + "<>-- " + ends.get(1).getName());
        sb.append("\n=================== \n");

        return sb.toString();
    }

    public String toStringAssociationClass(){
        StringBuffer sb = new StringBuffer();
        sb.append("======= ASS - CLASS  ======= \n");
        sb.append(this.assClass);
        sb.append("\n =================== \n");

        return sb.toString();
    }

    public String toStringAssociation(){
        StringBuffer sb = new StringBuffer();
        sb.append("======= N-ary ASS ======= \n");
        sb.append(name);
        sb.append("  between: ");
        for(int i = 0; i < ends.size(); i++){
            sb.append("  ");
            sb.append(ends.get(i).getName());
            sb.append("\n");
        }
        sb.append("\n=================== \n");

        return sb.toString();
    }
}
