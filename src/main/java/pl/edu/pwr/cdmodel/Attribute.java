package pl.edu.pwr.cdmodel;

public class Attribute {
	private String name;
	private AtrType type;
	private String definition;
	private String lemma;

	public Attribute(String name, AtrType type){
		this.name = name;
		this.type = type;
	}

	public AtrType getType() { return type; }
	public String getName() { return name; }
	public String getDefinition() { return definition; }
	public String getLemma(){ return lemma; }
	public void setLemma(String lemma){ this.lemma = lemma;}

	public void setDefinition(String definition) { this.definition = definition; }

	public String plantUML(){
		StringBuffer sb = new StringBuffer();
		sb.append(name);
		sb.append(":");
		sb.append(type);
		sb.append("\n");

		return sb.toString();
	}
}
