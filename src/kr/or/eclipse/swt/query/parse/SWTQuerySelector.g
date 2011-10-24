grammar SWTQuerySelector;

options {
    output=AST;
}

@lexer::header{
package kr.or.eclipse.swt.query.parse;
}

@parser::header{
package kr.or.eclipse.swt.query.parse;
}


fragment SQUOTE : '\'';
fragment DQUOTE : '"';
fragment BSLASH	: '\\';

ID		: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'-'|'_')*;
OPEN_BR		: '[';
CLOSE_BR		: ']';
ATTR_OP		: ('='|'~='|'!=');
ASTERIK		: '*';
WHITESPACE 	: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ { $channel = HIDDEN; };
LITERAL		: SQUOTE  ((~SQUOTE|BSLASH SQUOTE )*) SQUOTE | DQUOTE  ((~DQUOTE|BSLASH DQUOTE )*) DQUOTE;
LT		: '>';
COLON		: ':';
EXCLAMATION	: '!';
COMMA		: ',';

// Main Rule
selectors	: 
	selector (COMMA selector)*;

selector:	
	selectorSegment (selectorSegment)* -> selectorSegment+;
	
	
// Selector Segment
selectorSegment:	
	LT? (ID|ASTERIK)^ psudo? attributes?;

idList 	:	
	ID (',' ID)* -> ID+;
/*
 * ��Ÿ�� ����
 */
psudo		:
	COLON^ ID |
	COLON^ '('! idList ')'!;

/**
 * �Ӽ� ����.
 */
attributes	:
	OPEN_BR attribute (',' attribute)* CLOSE_BR -> attribute+;

/*
 * ������ �Ӽ�.
 */	
attribute	:	
	ID ATTR_OP^ attributeValue? |
	EXCLAMATION ID^;
	
/*
 * �Ӽ� ��.
 */
attributeValue	:
	LITERAL | ID;

