# BussinessRulesChecker

Program for compatibility checking of bussiness rules against domain specification.
Its executable version is available under the link:
https://drive.google.com/drive/folders/12knuK1992WzsL9HHqPkFqNOcZiH6lwnN?usp=sharing

Business rules are defined in English followed by a list of propositions. There is an empty line between the rules.
Examplary business rules are given in config folder (e.g. bank_rules.txt)

Domain specification is defined in plant UML format (files with puml extension) with some simplifications:
- classes can have attributes only
- association multiplicity is required at both ends
- association name can bo followed by its reverse name, e.g. mangages#is_managed

Examples of business rules and domain specification are placed in config folder.
The config folder contains some configuration files influencing the tool behavior, e.g.
- aggregate_verbs - verbs used for "navigation" through aggregations
- composition_verbs - verbs used for "navigation" through compositions
- possession_verbs - verbs used for properties in the context of class instance
- words_to_delete - stop words removed from the propositions
- synonyms - synonyms for classes/roles

Execution environment: Java 8
Dependencies (defined in pom.xml): 
- antl4
- standford tagger
