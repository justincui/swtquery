grammar SWTQuerySelector;


options {
    output=AST;
}

@lexer::header{
package kr.or.eclipse.cedt.swtquery.parse;
}

@parser::header{
package kr.or.eclipse.cedt.swtquery.parse;
}


fragment SQUOTE : '\'';
fragment BSLASH	: '\\';

ID		:	('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')*;
OPEN_BR		:	'[';
CLOSE_BR	:	']';
OP		:	('='|'~='|'!=');
ASTERIK		:	'*';
WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ { $channel = HIDDEN; };
LITERAL		:	SQUOTE ((~SQUOTE|BSLASH .)*) SQUOTE;
LT		: '>';

selectors	: selector ((LT)? selector)* ;

selector	:	
	ID -> ^(ID) | 
	ID attributes -> ^(ID attributes)| 
	ASTERIK attributes -> ^(ASTERIK attributes);
	
attributes	:
	OPEN_BR attribute (',' attribute)* CLOSE_BR -> attribute+;
	
attribute:	
	ID OP LITERAL -> ^(ID LITERAL)
	;
	
