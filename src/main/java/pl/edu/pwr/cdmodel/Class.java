package pl.edu.pwr.cdmodel;

import java.util.HashSet;
import java.util.Set;

public class Class {
    private String name;
    private String definition;
    private String lemma;
    // private Set<String> synonyms = new HashSet<>();
    private Set<Attribute> attributes = new HashSet<>();

    private boolean isAssociationClass = false;

    public Class(String name){
        this.name = name;
    }

    public void addAttribute(Attribute atr){
        attributes.add(atr);
    }
    public Set<Attribute> getAttributes(){ return attributes; }
    public String getName(){ return name; }
    public void setAssociationClass(){ isAssociationClass = true; }
    public boolean getAssociationClass(){ return isAssociationClass; }
    public String getDefinition() { return definition; }
    public void setDefinition(String definition) { this.definition = definition; }
    public String getLemma(){ return lemma; }
    public void setLemma(String lemma){ this.lemma = lemma;}

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("===== " + name + " =====\n");
        sb.append(definition + "\n");
        for(Attribute col: attributes){
            sb.append("  " + col.toString() + "\n");
        }
        sb.append("===============\n");
        return sb.toString();
    }

    public Set<Class> getAllParents(ClassDiagram diagram){
        Set<Class> parents = new HashSet<>();
        getAllParents(diagram, parents);
        return parents;
    }

    private void getAllParents(ClassDiagram diagram, Set<Class> result){
        for(Generalization g: diagram.generalizations){
            if (g.getChild() == this){
                Class parent = g.getParent();
                result.add(parent);
                parent.getAllParents(diagram, result);
            }
        }
    }
    public String plantUML(){
        StringBuffer sb = new StringBuffer();
        sb.append("class ");
        sb.append(name);
        sb.append(" ");
        sb.append("{\n");
        for(Attribute col: getAttributes()){
            sb.append(col.plantUML());
        }
        sb.append("}\n");

        return sb.toString();
    }
}
