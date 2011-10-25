grammar SWTQuerySelector;

options {
  output = AST;
}

@lexer::header {
package kr.or.eclipse.swt.query.internal.grammar;
}

@parser::header {
package kr.or.eclipse.swt.query.internal.grammar;
}
/*------------------------------------------------------------------
 * PARSER RULES 
 *------------------------------------------------------------------*/


selectors :
  selector (COMMA selector)*;

selector :
  selectorSegment (selectorSegment)*
    -> selectorSegment+;

// Selector Segment

selectorSegment :
  LT?
  (
    ID
    | ASTERIK
  )^
  psudo? attributes?;

idList :
  ID (',' ID)*
    -> ID+;

psudo :
  COLON^ ID
  | COLON^ '('! idList ')'!;

attributes :
  OPEN_BR attribute (',' attribute)* CLOSE_BR
    -> attribute+;

attribute :
  ID ATTR_OP^ attributeValue?
  | EXCLAMATION ID^;

attributeValue :
  LITERAL
  | ID;

OPEN_BR :
  '[';

CLOSE_BR :
  ']';

ASTERIK :
  '*';

SQUOTE :
  '\'';

DQUOTE :
  '"';

BSLASH :
  '\\';

LT :
  '>';

COLON :
  ':';

EXCLAMATION :
  '!';

COMMA :
  ',';

ID :
  (
    'a'..'z'
    | 'A'..'Z'
  )
  (
    'a'..'z'
    | 'A'..'Z'
    | '0'..'9'
    | '-'
    | '_'
  )*;

ATTR_OP :
  (
    '='
    | '!='
    | '*='
  );

LITERAL :
  SQUOTE
  (
    ~SQUOTE
    | BSLASH SQUOTE
  )*
  SQUOTE
  | DQUOTE
  (
    ~DQUOTE
    | BSLASH DQUOTE
  )*
  DQUOTE;

WHITESPACE :
  (
    '\t'
    | ' '
    | '\r'
    | '\n'
    | '\u000C'
  )+ { $channel = HIDDEN; };
