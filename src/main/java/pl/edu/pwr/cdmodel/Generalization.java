package pl.edu.pwr.cdmodel;

public class Generalization {
    private Class parent;
    private Class child;

    public Generalization(Class parent, Class child){
        this.parent = parent;
        this.child = child;
    }

    public Class getParent(){ return parent; }
    public Class getChild() { return child; }
    public void setParent(Class parent){ this.parent = parent; }
    public void setChild(Class child) { this.child = child; }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("======= GENERALIZATION ======= \n");
        sb.append(parent.getName() + " <|-- " + child.getName());
        sb.append("\n=================== \n");

        return sb.toString();
    }

    public String plantUML() {
        // Class01 <|-- Class02
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append(parent.getName());
        sb.append("<|-- ");
        sb.append(child.getName());
        sb.append("\n");

        return sb.toString();
    }

}
