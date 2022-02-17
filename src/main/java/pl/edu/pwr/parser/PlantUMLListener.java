// Generated from C:/Users/BHnat/IdeaProjects/RestApi/src/main/antlr4/pl/edu/pwr/plantUMLParser\PlantUML.g4 by ANTLR 4.9.2
package pl.edu.pwr.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PlantUMLParser}.
 */
public interface PlantUMLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#diagram}.
	 * @param ctx the parse tree
	 */
	void enterDiagram(PlantUMLParser.DiagramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#diagram}.
	 * @param ctx the parse tree
	 */
	void exitDiagram(PlantUMLParser.DiagramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#relation_def}.
	 * @param ctx the parse tree
	 */
	void enterRelation_def(PlantUMLParser.Relation_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#relation_def}.
	 * @param ctx the parse tree
	 */
	void exitRelation_def(PlantUMLParser.Relation_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#generalization}.
	 * @param ctx the parse tree
	 */
	void enterGeneralization(PlantUMLParser.GeneralizationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#generalization}.
	 * @param ctx the parse tree
	 */
	void exitGeneralization(PlantUMLParser.GeneralizationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(PlantUMLParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(PlantUMLParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#class_name}.
	 * @param ctx the parse tree
	 */
	void enterClass_name(PlantUMLParser.Class_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#class_name}.
	 * @param ctx the parse tree
	 */
	void exitClass_name(PlantUMLParser.Class_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#mult}.
	 * @param ctx the parse tree
	 */
	void enterMult(PlantUMLParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#mult}.
	 * @param ctx the parse tree
	 */
	void exitMult(PlantUMLParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#rel_details}.
	 * @param ctx the parse tree
	 */
	void enterRel_details(PlantUMLParser.Rel_detailsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#rel_details}.
	 * @param ctx the parse tree
	 */
	void exitRel_details(PlantUMLParser.Rel_detailsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#ass_class}.
	 * @param ctx the parse tree
	 */
	void enterAss_class(PlantUMLParser.Ass_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#ass_class}.
	 * @param ctx the parse tree
	 */
	void exitAss_class(PlantUMLParser.Ass_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#class_def}.
	 * @param ctx the parse tree
	 */
	void enterClass_def(PlantUMLParser.Class_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#class_def}.
	 * @param ctx the parse tree
	 */
	void exitClass_def(PlantUMLParser.Class_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#enum_def}.
	 * @param ctx the parse tree
	 */
	void enterEnum_def(PlantUMLParser.Enum_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#enum_def}.
	 * @param ctx the parse tree
	 */
	void exitEnum_def(PlantUMLParser.Enum_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#literal_list}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_list(PlantUMLParser.Literal_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#literal_list}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_list(PlantUMLParser.Literal_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#field_list}.
	 * @param ctx the parse tree
	 */
	void enterField_list(PlantUMLParser.Field_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#field_list}.
	 * @param ctx the parse tree
	 */
	void exitField_list(PlantUMLParser.Field_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#field_def}.
	 * @param ctx the parse tree
	 */
	void enterField_def(PlantUMLParser.Field_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#field_def}.
	 * @param ctx the parse tree
	 */
	void exitField_def(PlantUMLParser.Field_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#field_name}.
	 * @param ctx the parse tree
	 */
	void enterField_name(PlantUMLParser.Field_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#field_name}.
	 * @param ctx the parse tree
	 */
	void exitField_name(PlantUMLParser.Field_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#field_type}.
	 * @param ctx the parse tree
	 */
	void enterField_type(PlantUMLParser.Field_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#field_type}.
	 * @param ctx the parse tree
	 */
	void exitField_type(PlantUMLParser.Field_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUMLParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(PlantUMLParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUMLParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(PlantUMLParser.IdContext ctx);
}