package pl.edu.pwr.cdmodel;

import java.util.ArrayList;
import java.util.List;

public class ClassDiagram {
	public List<Attribute> attributes = new ArrayList<>();
	public List<Class> classes = new ArrayList<>();
	public List<Generalization> generalizations = new ArrayList<>();
	public List<Association> associations = new ArrayList<>();

	public Class findClassByName(String name){
		// if (classes.stream().anyMatch((c -> c.getName().equals(name))))
			return classes.stream().filter(c -> c.getName().equals(name)).findAny().get();
		//else
		//	return null;
	}

	public Association findAssociationByName(String name){
		return associations.stream().filter(c -> c.getName().equals(name)).findAny().get();
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		classes.forEach(cl->sb.append(cl.toString()));
		return sb.toString();
	}

	public String plantUML(){
		StringBuffer sb = new StringBuffer();
		sb.append("@startuml\n");

		for(Class cl: classes){
			sb.append(cl.plantUML());
		}

		for(Generalization gen: generalizations){
			sb.append(gen.plantUML());
		}


		for(Association na: associations){
			sb.append(na.plantUML());
		}

		// sb.append(note());


		sb.append("\n@enduml");
		return sb.toString();
	}
}
