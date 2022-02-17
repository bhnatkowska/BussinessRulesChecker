package pl.edu.pwr.grammarreader;

import pl.edu.pwr.cdmodel.Class;
import pl.edu.pwr.cdmodel.ClassDiagram;
import pl.edu.pwr.parser.PlantUMLBaseListener;
import pl.edu.pwr.parser.PlantUMLParser;
import pl.edu.pwr.cdmodel.*;

import java.util.Locale;
import java.util.Optional;

public class PlantUMLWalker extends PlantUMLBaseListener {
    private ClassDiagram diagram = new ClassDiagram();
    private Class lastClass = null;

    public ClassDiagram getDiagram() {
        return diagram;
    }

    private Class findOrCreate(String name){
        Optional<Class> cl = diagram.classes.stream().filter(c -> c.getName().equals(name)).findAny();
        if (cl.isPresent())
            return cl.get();

        Class c = new Class(name);
        diagram.classes.add(c);
        return c;
    }

    public void enterClass_name(PlantUMLParser.Class_nameContext ctx) {
        // System.out.println("Class name:" + ctx.getText());
        String name = ctx.getText();
        lastClass = findOrCreate(name);
    }

    @Override public void enterGeneralization(PlantUMLParser.GeneralizationContext ctx) {
        String parent = ctx.class_name(0).getText();
        String child = ctx.class_name(1).getText();

        Class parClass = diagram.findClassByName(parent);
        Class childClass = diagram.findClassByName(child);

        Generalization gen = new Generalization(parClass, childClass);
        diagram.generalizations.add(gen);
        // System.out.println("Generalization:" + ctx.getText());
    }


    private Multiplicity translateMultiplicity(String type){
        if (type.equals("1"))
            return Multiplicity.ONE;
        if (type.equals("0..1"))
            return Multiplicity.ZERO_TO_ONE;
        if (type.equals("1..*"))
            return Multiplicity.ONE_TO_MANY;

        return Multiplicity.MANY;
    }

    @Override public void enterRelation(PlantUMLParser.RelationContext ctx) {
        String leftClassName = ctx.class_name(0).getText();
        String rightClassName = ctx.class_name(1).getText();

        String leftMult = ctx.mult(0).getText();
        String leftRole = "";
        String[] leftMultParsed = leftMult.split(" ");
        if (leftMultParsed.length == 1) {
            leftMult = leftMult.substring(1, leftMult.length() - 1);
        }
        else {
            leftRole = leftMultParsed[0].substring(1).toLowerCase(Locale.ROOT);
            leftMult = leftMultParsed[1].substring(0, leftMultParsed[1].length() - 1);
        }

        String rightMult = ctx.mult(1).getText();
        String rightRole = "";
        String[] rightMultParsed = rightMult.split(" ");
        if (rightMultParsed.length == 1) {
            rightMult = rightMult.substring(1, rightMult.length() - 1);
        }
        else {  // * uniw
            rightRole = rightMultParsed[1].substring(0, rightMultParsed[1].length() - 1).toLowerCase(Locale.ROOT);
            rightMult = rightMultParsed[0].substring(1);
        }

        Class leftClass = diagram.findClassByName(leftClassName);
        Class rightClass = diagram.findClassByName(rightClassName);

        String relName = null;
        if (ctx.rel_details().getText() != null)
            relName = ctx.rel_details().getText();

        Association ass = null;
        if (ctx.ass_class() != null) {
            String assClassName = ctx.ass_class().class_name(2).getText();
            Class assClass = diagram.findClassByName(assClassName);
            ass = new Association(leftClass, rightClass, assClass);
        }
        else {
            String[] assNameParts = relName.split("#");
            if (assNameParts.length == 1) {
                String name = relName.length() > 1 ? assNameParts[0].substring(1) : "";
                ass = new Association(name, "", leftClass, rightClass);
            }
            else {
                String name = relName.length() > 1 ? assNameParts[0].substring(1) : "";
                ass = new Association(name, assNameParts[1], leftClass, rightClass);
            }
        }

        if (ctx.AGR() != null && ctx.AGR().getText().equals("o--"))
            ass.setAggregation(true);
        if (ctx.COMP() != null && ctx.COMP().getText().equals("*--"))
            ass.setComposition(true);
        ass.setMultiplicity(translateMultiplicity(leftMult), 0);
        ass.setMultiplicity(translateMultiplicity(rightMult), 1);
        ass.setRole(0, leftRole);
        ass.setRole(1, rightRole);

        diagram.associations.add(ass);
    }

    private AtrType translate(String type){
        String lowerType = type.toLowerCase();
        if (lowerType.equals("int") || type.equals("integer"))
            return AtrType.Integer;
        if (lowerType.equals("real") || type.equals("float") || type.equals("double"))
            return AtrType.Real;
        if (lowerType.equals("bool") || type.equals("boolean"))
            return AtrType.Boolean;
        if (lowerType.equals("date"))
            return AtrType.Date;
        return AtrType.String;
    }

    @Override public void enterField_def(PlantUMLParser.Field_defContext ctx) {
        String name = ctx.getChild(0).getText();
        String type = ctx.getChild(2).getText();

        Attribute atr = new Attribute(name, translate(type));
        lastClass.addAttribute(atr);
        diagram.attributes.add(atr);

        // System.out.println("Field def:" + ctx.getText() + " " + ctx.getChild(0).getText() + " " + ctx.getChild(2).getText());
    }

}
