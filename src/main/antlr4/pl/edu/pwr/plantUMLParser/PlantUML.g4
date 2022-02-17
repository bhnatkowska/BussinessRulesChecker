// define a grammar called PlantUML

grammar PlantUML;

diagram
   : START (class_def | enum_def | relation_def)* END
   ;

relation_def
   : (generalization | relation)
   ;

generalization
   : class_name GEN class_name
   ;

relation 
   : class_name mult (AGR | COMP | ASS) mult class_name rel_details ass_class?
   ;

class_name 
   : id
   ;

mult
   : '"1"' || '"0..1"' || '"*"' || '"1..*"' || '"0..*"' || STRING
   ;

rel_details
   : ( ':' id )?
   ;

ass_class
   : '(' class_name ',' class_name ')' '..' class_name
   ;

class_def
   : CLASS class_name '{' field_list? '}'
   ;

enum_def
   : ENUM '{' literal_list '}'
   ;

literal_list
   : (ID)*
   ;

field_list
   : ( field_def)*
   ; 

field_def : field_name ':' field_type;
field_name : id;
field_type : id;

id
   : ID | STRING 
   ;

// "The keywords node, edge, graph, digraph, subgraph, and strict are
// case-independent"

GEN
   :
   '<|--';

AGR
   : 'o--'
   ;

COMP
   : '*--'
   ;

ASS
   : '--'
   ;

LEFT
   : '<'
   ;

RIGHT
   : '>'
   ;

CLASS
   : 'class'
   ;

ENUM
   : 'enum'
   ;

START
   : '@startUML' | '@startuml'
   ;

END
   : '@endUML' | '@enduml'
   ;


/** "a numeral [-]?(.[0-9]+ | [0-9]+(.[0-9]*)? )" */ 
NUMBER
   : '-'? ( '.' DIGIT+ | DIGIT+ ( '.' DIGIT* )? )
   ;


fragment DIGIT
   : [0-9]
   ;


/** "any double-quoted string ("...") possibly containing escaped quotes" */ 
STRING
   : '"' ( '\\"' | . )*? '"'
   ;


/** "Any string of alphabetic ([a-zA-Z\200-\377]) characters, underscores
 *  ('_') or digits ([0-9]), not beginning with a digit"
 */ 
ID
   : LETTER ( LETTER | DIGIT )*
   ;


ABSTRACT
   : 'abstract'
   ;


fragment LETTER
   : [a-zA-Z\u00D3-\u017C_#]
   ;


COMMENT
   : '/*' .*? '*/' -> skip
   ;


LINE_COMMENT
   : '//' .*? '\r'? '\n' -> skip
   ;


WS
   : [ \t\r\n]+ -> channel(HIDDEN)
   ;