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
  styleFilter? attributeFilters?;

idList :
  ID (',' ID)*
    -> ID+;

styleFilter :
  COLON^ ID
  | COLON^ '('! idList ')'!;

attributeFilters :
  OPEN_BR attributeFilter (',' attributeFilter)* CLOSE_BR
    -> attributeFilter+;

attributeFilter :
  ID ATTR_OP^ operand?
  | EXCLAMATION^ ID
  | ID^;

operand :
  LITERAL
  | ID
  | NUMBER;

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

NUMBER :
  (
    '+'
    | '-'
  )?
  '0'..'9'+ ('.' '0'..'9'+)?;

WHITESPACE :
  (
    '\t'
    | ' '
    | '\r'
    | '\n'
    | '\u000C'
  )+
   { $channel = HIDDEN; };
