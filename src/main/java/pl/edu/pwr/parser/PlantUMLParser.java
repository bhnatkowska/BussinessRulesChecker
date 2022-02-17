// Generated from C:/Users/BHnat/IdeaProjects/RestApi/src/main/antlr4/pl/edu/pwr/plantUMLParser\PlantUML.g4 by ANTLR 4.9.2
package pl.edu.pwr.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PlantUMLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, GEN=13, AGR=14, COMP=15, ASS=16, LEFT=17, 
		RIGHT=18, CLASS=19, ENUM=20, START=21, END=22, NUMBER=23, STRING=24, ID=25, 
		ABSTRACT=26, COMMENT=27, LINE_COMMENT=28, WS=29;
	public static final int
		RULE_diagram = 0, RULE_relation_def = 1, RULE_generalization = 2, RULE_relation = 3, 
		RULE_class_name = 4, RULE_mult = 5, RULE_rel_details = 6, RULE_ass_class = 7, 
		RULE_class_def = 8, RULE_enum_def = 9, RULE_literal_list = 10, RULE_field_list = 11, 
		RULE_field_def = 12, RULE_field_name = 13, RULE_field_type = 14, RULE_id = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"diagram", "relation_def", "generalization", "relation", "class_name", 
			"mult", "rel_details", "ass_class", "class_def", "enum_def", "literal_list", 
			"field_list", "field_def", "field_name", "field_type", "id"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\"1\"'", "'\"0..1\"'", "'\"*\"'", "'\"1..*\"'", "'\"0..*\"'", 
			"':'", "'('", "','", "')'", "'..'", "'{'", "'}'", "'<|--'", "'o--'", 
			"'*--'", "'--'", "'<'", "'>'", "'class'", "'enum'", null, null, null, 
			null, null, "'abstract'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "GEN", "AGR", "COMP", "ASS", "LEFT", "RIGHT", "CLASS", "ENUM", 
			"START", "END", "NUMBER", "STRING", "ID", "ABSTRACT", "COMMENT", "LINE_COMMENT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PlantUML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PlantUMLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class DiagramContext extends ParserRuleContext {
		public TerminalNode START() { return getToken(PlantUMLParser.START, 0); }
		public TerminalNode END() { return getToken(PlantUMLParser.END, 0); }
		public List<Class_defContext> class_def() {
			return getRuleContexts(Class_defContext.class);
		}
		public Class_defContext class_def(int i) {
			return getRuleContext(Class_defContext.class,i);
		}
		public List<Enum_defContext> enum_def() {
			return getRuleContexts(Enum_defContext.class);
		}
		public Enum_defContext enum_def(int i) {
			return getRuleContext(Enum_defContext.class,i);
		}
		public List<Relation_defContext> relation_def() {
			return getRuleContexts(Relation_defContext.class);
		}
		public Relation_defContext relation_def(int i) {
			return getRuleContext(Relation_defContext.class,i);
		}
		public DiagramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_diagram; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterDiagram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitDiagram(this);
		}
	}

	public final DiagramContext diagram() throws RecognitionException {
		DiagramContext _localctx = new DiagramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_diagram);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(START);
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CLASS) | (1L << ENUM) | (1L << STRING) | (1L << ID))) != 0)) {
				{
				setState(36);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(33);
					class_def();
					}
					break;
				case ENUM:
					{
					setState(34);
					enum_def();
					}
					break;
				case STRING:
				case ID:
					{
					setState(35);
					relation_def();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Relation_defContext extends ParserRuleContext {
		public GeneralizationContext generalization() {
			return getRuleContext(GeneralizationContext.class,0);
		}
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public Relation_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterRelation_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitRelation_def(this);
		}
	}

	public final Relation_defContext relation_def() throws RecognitionException {
		Relation_defContext _localctx = new Relation_defContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_relation_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(43);
				generalization();
				}
				break;
			case 2:
				{
				setState(44);
				relation();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GeneralizationContext extends ParserRuleContext {
		public List<Class_nameContext> class_name() {
			return getRuleContexts(Class_nameContext.class);
		}
		public Class_nameContext class_name(int i) {
			return getRuleContext(Class_nameContext.class,i);
		}
		public TerminalNode GEN() { return getToken(PlantUMLParser.GEN, 0); }
		public GeneralizationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generalization; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterGeneralization(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitGeneralization(this);
		}
	}

	public final GeneralizationContext generalization() throws RecognitionException {
		GeneralizationContext _localctx = new GeneralizationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_generalization);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			class_name();
			setState(48);
			match(GEN);
			setState(49);
			class_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationContext extends ParserRuleContext {
		public List<Class_nameContext> class_name() {
			return getRuleContexts(Class_nameContext.class);
		}
		public Class_nameContext class_name(int i) {
			return getRuleContext(Class_nameContext.class,i);
		}
		public List<MultContext> mult() {
			return getRuleContexts(MultContext.class);
		}
		public MultContext mult(int i) {
			return getRuleContext(MultContext.class,i);
		}
		public Rel_detailsContext rel_details() {
			return getRuleContext(Rel_detailsContext.class,0);
		}
		public TerminalNode AGR() { return getToken(PlantUMLParser.AGR, 0); }
		public TerminalNode COMP() { return getToken(PlantUMLParser.COMP, 0); }
		public TerminalNode ASS() { return getToken(PlantUMLParser.ASS, 0); }
		public Ass_classContext ass_class() {
			return getRuleContext(Ass_classContext.class,0);
		}
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitRelation(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_relation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			class_name();
			setState(52);
			mult();
			setState(53);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AGR) | (1L << COMP) | (1L << ASS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(54);
			mult();
			setState(55);
			class_name();
			setState(56);
			rel_details();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(57);
				ass_class();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_nameContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Class_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterClass_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitClass_name(this);
		}
	}

	public final Class_nameContext class_name() throws RecognitionException {
		Class_nameContext _localctx = new Class_nameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_class_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PlantUMLParser.STRING, 0); }
		public MultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mult; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterMult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitMult(this);
		}
	}

	public final MultContext mult() throws RecognitionException {
		MultContext _localctx = new MultContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_mult);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				match(T__2);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(68);
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(70);
				match(T__4);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(72);
				match(STRING);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rel_detailsContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Rel_detailsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel_details; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterRel_details(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitRel_details(this);
		}
	}

	public final Rel_detailsContext rel_details() throws RecognitionException {
		Rel_detailsContext _localctx = new Rel_detailsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_rel_details);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(75);
				match(T__5);
				setState(76);
				id();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ass_classContext extends ParserRuleContext {
		public List<Class_nameContext> class_name() {
			return getRuleContexts(Class_nameContext.class);
		}
		public Class_nameContext class_name(int i) {
			return getRuleContext(Class_nameContext.class,i);
		}
		public Ass_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ass_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterAss_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitAss_class(this);
		}
	}

	public final Ass_classContext ass_class() throws RecognitionException {
		Ass_classContext _localctx = new Ass_classContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ass_class);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__6);
			setState(80);
			class_name();
			setState(81);
			match(T__7);
			setState(82);
			class_name();
			setState(83);
			match(T__8);
			setState(84);
			match(T__9);
			setState(85);
			class_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_defContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(PlantUMLParser.CLASS, 0); }
		public Class_nameContext class_name() {
			return getRuleContext(Class_nameContext.class,0);
		}
		public Field_listContext field_list() {
			return getRuleContext(Field_listContext.class,0);
		}
		public Class_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterClass_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitClass_def(this);
		}
	}

	public final Class_defContext class_def() throws RecognitionException {
		Class_defContext _localctx = new Class_defContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_class_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(CLASS);
			setState(88);
			class_name();
			setState(89);
			match(T__10);
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(90);
				field_list();
				}
				break;
			}
			setState(93);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enum_defContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(PlantUMLParser.ENUM, 0); }
		public Literal_listContext literal_list() {
			return getRuleContext(Literal_listContext.class,0);
		}
		public Enum_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterEnum_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitEnum_def(this);
		}
	}

	public final Enum_defContext enum_def() throws RecognitionException {
		Enum_defContext _localctx = new Enum_defContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_enum_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(ENUM);
			setState(96);
			match(T__10);
			setState(97);
			literal_list();
			setState(98);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_listContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(PlantUMLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PlantUMLParser.ID, i);
		}
		public Literal_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterLiteral_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitLiteral_list(this);
		}
	}

	public final Literal_listContext literal_list() throws RecognitionException {
		Literal_listContext _localctx = new Literal_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_literal_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(100);
				match(ID);
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_listContext extends ParserRuleContext {
		public List<Field_defContext> field_def() {
			return getRuleContexts(Field_defContext.class);
		}
		public Field_defContext field_def(int i) {
			return getRuleContext(Field_defContext.class,i);
		}
		public Field_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterField_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitField_list(this);
		}
	}

	public final Field_listContext field_list() throws RecognitionException {
		Field_listContext _localctx = new Field_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_field_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING || _la==ID) {
				{
				{
				setState(106);
				field_def();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_defContext extends ParserRuleContext {
		public Field_nameContext field_name() {
			return getRuleContext(Field_nameContext.class,0);
		}
		public Field_typeContext field_type() {
			return getRuleContext(Field_typeContext.class,0);
		}
		public Field_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterField_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitField_def(this);
		}
	}

	public final Field_defContext field_def() throws RecognitionException {
		Field_defContext _localctx = new Field_defContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_field_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			field_name();
			setState(113);
			match(T__5);
			setState(114);
			field_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_nameContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Field_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterField_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitField_name(this);
		}
	}

	public final Field_nameContext field_name() throws RecognitionException {
		Field_nameContext _localctx = new Field_nameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_field_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_typeContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Field_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterField_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitField_type(this);
		}
	}

	public final Field_typeContext field_type() throws RecognitionException {
		Field_typeContext _localctx = new Field_typeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_field_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PlantUMLParser.ID, 0); }
		public TerminalNode STRING() { return getToken(PlantUMLParser.STRING, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUMLListener ) ((PlantUMLListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37}\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3\2\3\2"+
		"\7\2\'\n\2\f\2\16\2*\13\2\3\2\3\2\3\3\3\3\5\3\60\n\3\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\5\7L\n\7\3\b\3\b\5\bP\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\5\n^\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\7"+
		"\fh\n\f\f\f\16\fk\13\f\3\r\7\rn\n\r\f\r\16\rq\13\r\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \2\4\3\2\20\22\3\2\32\33\2\177\2\"\3\2\2\2\4/\3\2\2\2\6\61"+
		"\3\2\2\2\b\65\3\2\2\2\n>\3\2\2\2\fK\3\2\2\2\16O\3\2\2\2\20Q\3\2\2\2\22"+
		"Y\3\2\2\2\24a\3\2\2\2\26i\3\2\2\2\30o\3\2\2\2\32r\3\2\2\2\34v\3\2\2\2"+
		"\36x\3\2\2\2 z\3\2\2\2\"(\7\27\2\2#\'\5\22\n\2$\'\5\24\13\2%\'\5\4\3\2"+
		"&#\3\2\2\2&$\3\2\2\2&%\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3\2\2"+
		"\2*(\3\2\2\2+,\7\30\2\2,\3\3\2\2\2-\60\5\6\4\2.\60\5\b\5\2/-\3\2\2\2/"+
		".\3\2\2\2\60\5\3\2\2\2\61\62\5\n\6\2\62\63\7\17\2\2\63\64\5\n\6\2\64\7"+
		"\3\2\2\2\65\66\5\n\6\2\66\67\5\f\7\2\678\t\2\2\289\5\f\7\29:\5\n\6\2:"+
		"<\5\16\b\2;=\5\20\t\2<;\3\2\2\2<=\3\2\2\2=\t\3\2\2\2>?\5 \21\2?\13\3\2"+
		"\2\2@L\7\3\2\2AL\3\2\2\2BL\7\4\2\2CL\3\2\2\2DL\7\5\2\2EL\3\2\2\2FL\7\6"+
		"\2\2GL\3\2\2\2HL\7\7\2\2IL\3\2\2\2JL\7\32\2\2K@\3\2\2\2KA\3\2\2\2KB\3"+
		"\2\2\2KC\3\2\2\2KD\3\2\2\2KE\3\2\2\2KF\3\2\2\2KG\3\2\2\2KH\3\2\2\2KI\3"+
		"\2\2\2KJ\3\2\2\2L\r\3\2\2\2MN\7\b\2\2NP\5 \21\2OM\3\2\2\2OP\3\2\2\2P\17"+
		"\3\2\2\2QR\7\t\2\2RS\5\n\6\2ST\7\n\2\2TU\5\n\6\2UV\7\13\2\2VW\7\f\2\2"+
		"WX\5\n\6\2X\21\3\2\2\2YZ\7\25\2\2Z[\5\n\6\2[]\7\r\2\2\\^\5\30\r\2]\\\3"+
		"\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\16\2\2`\23\3\2\2\2ab\7\26\2\2bc\7\r\2\2"+
		"cd\5\26\f\2de\7\16\2\2e\25\3\2\2\2fh\7\33\2\2gf\3\2\2\2hk\3\2\2\2ig\3"+
		"\2\2\2ij\3\2\2\2j\27\3\2\2\2ki\3\2\2\2ln\5\32\16\2ml\3\2\2\2nq\3\2\2\2"+
		"om\3\2\2\2op\3\2\2\2p\31\3\2\2\2qo\3\2\2\2rs\5\34\17\2st\7\b\2\2tu\5\36"+
		"\20\2u\33\3\2\2\2vw\5 \21\2w\35\3\2\2\2xy\5 \21\2y\37\3\2\2\2z{\t\3\2"+
		"\2{!\3\2\2\2\13&(/<KO]io";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}