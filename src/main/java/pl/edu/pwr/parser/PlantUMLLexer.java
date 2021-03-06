// Generated from C:/Users/BHnat/IdeaProjects/RestApi/src/main/antlr4/pl/edu/pwr/plantUMLParser\PlantUML.g4 by ANTLR 4.9.2
package pl.edu.pwr.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PlantUMLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, GEN=13, AGR=14, COMP=15, ASS=16, LEFT=17, 
		RIGHT=18, CLASS=19, ENUM=20, START=21, END=22, NUMBER=23, STRING=24, ID=25, 
		ABSTRACT=26, COMMENT=27, LINE_COMMENT=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "GEN", "AGR", "COMP", "ASS", "LEFT", "RIGHT", 
			"CLASS", "ENUM", "START", "END", "NUMBER", "DIGIT", "STRING", "ID", "ABSTRACT", 
			"LETTER", "COMMENT", "LINE_COMMENT", "WS"
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


	public PlantUMLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PlantUML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u010e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\5\26\u009f\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\5\27\u00af\n\27\3\30\5\30\u00b2\n\30\3\30\3\30\6"+
		"\30\u00b6\n\30\r\30\16\30\u00b7\3\30\6\30\u00bb\n\30\r\30\16\30\u00bc"+
		"\3\30\3\30\7\30\u00c1\n\30\f\30\16\30\u00c4\13\30\5\30\u00c6\n\30\5\30"+
		"\u00c8\n\30\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u00d0\n\32\f\32\16\32\u00d3"+
		"\13\32\3\32\3\32\3\33\3\33\3\33\7\33\u00da\n\33\f\33\16\33\u00dd\13\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\7\36\u00ee\n\36\f\36\16\36\u00f1\13\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\7\37\u00fc\n\37\f\37\16\37\u00ff\13\37\3\37\5\37"+
		"\u0102\n\37\3\37\3\37\3\37\3\37\3 \6 \u0109\n \r \16 \u010a\3 \3 \5\u00d1"+
		"\u00ef\u00fd\2!\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\2\63\32\65\33"+
		"\67\349\2;\35=\36?\37\3\2\5\3\2\62;\7\2%%C\\aac|\u00d5\u017e\5\2\13\f"+
		"\17\17\"\"\2\u011b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5E\3\2\2\2\7L\3\2\2\2\tP\3\2\2\2\13W\3"+
		"\2\2\2\r^\3\2\2\2\17`\3\2\2\2\21b\3\2\2\2\23d\3\2\2\2\25f\3\2\2\2\27i"+
		"\3\2\2\2\31k\3\2\2\2\33m\3\2\2\2\35r\3\2\2\2\37v\3\2\2\2!z\3\2\2\2#}\3"+
		"\2\2\2%\177\3\2\2\2\'\u0081\3\2\2\2)\u0087\3\2\2\2+\u009e\3\2\2\2-\u00ae"+
		"\3\2\2\2/\u00b1\3\2\2\2\61\u00c9\3\2\2\2\63\u00cb\3\2\2\2\65\u00d6\3\2"+
		"\2\2\67\u00de\3\2\2\29\u00e7\3\2\2\2;\u00e9\3\2\2\2=\u00f7\3\2\2\2?\u0108"+
		"\3\2\2\2AB\7$\2\2BC\7\63\2\2CD\7$\2\2D\4\3\2\2\2EF\7$\2\2FG\7\62\2\2G"+
		"H\7\60\2\2HI\7\60\2\2IJ\7\63\2\2JK\7$\2\2K\6\3\2\2\2LM\7$\2\2MN\7,\2\2"+
		"NO\7$\2\2O\b\3\2\2\2PQ\7$\2\2QR\7\63\2\2RS\7\60\2\2ST\7\60\2\2TU\7,\2"+
		"\2UV\7$\2\2V\n\3\2\2\2WX\7$\2\2XY\7\62\2\2YZ\7\60\2\2Z[\7\60\2\2[\\\7"+
		",\2\2\\]\7$\2\2]\f\3\2\2\2^_\7<\2\2_\16\3\2\2\2`a\7*\2\2a\20\3\2\2\2b"+
		"c\7.\2\2c\22\3\2\2\2de\7+\2\2e\24\3\2\2\2fg\7\60\2\2gh\7\60\2\2h\26\3"+
		"\2\2\2ij\7}\2\2j\30\3\2\2\2kl\7\177\2\2l\32\3\2\2\2mn\7>\2\2no\7~\2\2"+
		"op\7/\2\2pq\7/\2\2q\34\3\2\2\2rs\7q\2\2st\7/\2\2tu\7/\2\2u\36\3\2\2\2"+
		"vw\7,\2\2wx\7/\2\2xy\7/\2\2y \3\2\2\2z{\7/\2\2{|\7/\2\2|\"\3\2\2\2}~\7"+
		">\2\2~$\3\2\2\2\177\u0080\7@\2\2\u0080&\3\2\2\2\u0081\u0082\7e\2\2\u0082"+
		"\u0083\7n\2\2\u0083\u0084\7c\2\2\u0084\u0085\7u\2\2\u0085\u0086\7u\2\2"+
		"\u0086(\3\2\2\2\u0087\u0088\7g\2\2\u0088\u0089\7p\2\2\u0089\u008a\7w\2"+
		"\2\u008a\u008b\7o\2\2\u008b*\3\2\2\2\u008c\u008d\7B\2\2\u008d\u008e\7"+
		"u\2\2\u008e\u008f\7v\2\2\u008f\u0090\7c\2\2\u0090\u0091\7t\2\2\u0091\u0092"+
		"\7v\2\2\u0092\u0093\7W\2\2\u0093\u0094\7O\2\2\u0094\u009f\7N\2\2\u0095"+
		"\u0096\7B\2\2\u0096\u0097\7u\2\2\u0097\u0098\7v\2\2\u0098\u0099\7c\2\2"+
		"\u0099\u009a\7t\2\2\u009a\u009b\7v\2\2\u009b\u009c\7w\2\2\u009c\u009d"+
		"\7o\2\2\u009d\u009f\7n\2\2\u009e\u008c\3\2\2\2\u009e\u0095\3\2\2\2\u009f"+
		",\3\2\2\2\u00a0\u00a1\7B\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7p\2\2\u00a3"+
		"\u00a4\7f\2\2\u00a4\u00a5\7W\2\2\u00a5\u00a6\7O\2\2\u00a6\u00af\7N\2\2"+
		"\u00a7\u00a8\7B\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab"+
		"\7f\2\2\u00ab\u00ac\7w\2\2\u00ac\u00ad\7o\2\2\u00ad\u00af\7n\2\2\u00ae"+
		"\u00a0\3\2\2\2\u00ae\u00a7\3\2\2\2\u00af.\3\2\2\2\u00b0\u00b2\7/\2\2\u00b1"+
		"\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00c7\3\2\2\2\u00b3\u00b5\7\60"+
		"\2\2\u00b4\u00b6\5\61\31\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00c8\3\2\2\2\u00b9\u00bb\5\61"+
		"\31\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00c5\3\2\2\2\u00be\u00c2\7\60\2\2\u00bf\u00c1\5"+
		"\61\31\2\u00c0\u00bf\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00be\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00b3\3\2\2\2\u00c7"+
		"\u00ba\3\2\2\2\u00c8\60\3\2\2\2\u00c9\u00ca\t\2\2\2\u00ca\62\3\2\2\2\u00cb"+
		"\u00d1\7$\2\2\u00cc\u00cd\7^\2\2\u00cd\u00d0\7$\2\2\u00ce\u00d0\13\2\2"+
		"\2\u00cf\u00cc\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00d2"+
		"\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4"+
		"\u00d5\7$\2\2\u00d5\64\3\2\2\2\u00d6\u00db\59\35\2\u00d7\u00da\59\35\2"+
		"\u00d8\u00da\5\61\31\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\u00dd"+
		"\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\66\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00de\u00df\7c\2\2\u00df\u00e0\7d\2\2\u00e0\u00e1\7u\2"+
		"\2\u00e1\u00e2\7v\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4\7c\2\2\u00e4\u00e5"+
		"\7e\2\2\u00e5\u00e6\7v\2\2\u00e68\3\2\2\2\u00e7\u00e8\t\3\2\2\u00e8:\3"+
		"\2\2\2\u00e9\u00ea\7\61\2\2\u00ea\u00eb\7,\2\2\u00eb\u00ef\3\2\2\2\u00ec"+
		"\u00ee\13\2\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00f0\3"+
		"\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2"+
		"\u00f3\7,\2\2\u00f3\u00f4\7\61\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\b\36"+
		"\2\2\u00f6<\3\2\2\2\u00f7\u00f8\7\61\2\2\u00f8\u00f9\7\61\2\2\u00f9\u00fd"+
		"\3\2\2\2\u00fa\u00fc\13\2\2\2\u00fb\u00fa\3\2\2\2\u00fc\u00ff\3\2\2\2"+
		"\u00fd\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd"+
		"\3\2\2\2\u0100\u0102\7\17\2\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2"+
		"\u0102\u0103\3\2\2\2\u0103\u0104\7\f\2\2\u0104\u0105\3\2\2\2\u0105\u0106"+
		"\b\37\2\2\u0106>\3\2\2\2\u0107\u0109\t\4\2\2\u0108\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\3\2"+
		"\2\2\u010c\u010d\b \3\2\u010d@\3\2\2\2\23\2\u009e\u00ae\u00b1\u00b7\u00bc"+
		"\u00c2\u00c5\u00c7\u00cf\u00d1\u00d9\u00db\u00ef\u00fd\u0101\u010a\4\b"+
		"\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}