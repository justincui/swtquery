// $ANTLR 3.4 P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g 2011-10-27 22:39:46

package kr.or.eclipse.swt.query.internal.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SWTQuerySelectorLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int ASTERIK=4;
    public static final int ATTR_OP=5;
    public static final int BSLASH=6;
    public static final int CLOSE_BR=7;
    public static final int COLON=8;
    public static final int COMMA=9;
    public static final int DQUOTE=10;
    public static final int EXCLAMATION=11;
    public static final int ID=12;
    public static final int LITERAL=13;
    public static final int LT=14;
    public static final int NUMBER=15;
    public static final int OPEN_BR=16;
    public static final int SQUOTE=17;
    public static final int WHITESPACE=18;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public SWTQuerySelectorLexer() {} 
    public SWTQuerySelectorLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SWTQuerySelectorLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g"; }

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:6:7: ( '(' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:6:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:7:7: ( ')' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:7:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "OPEN_BR"
    public final void mOPEN_BR() throws RecognitionException {
        try {
            int _type = OPEN_BR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:58:9: ( '[' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:59:3: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPEN_BR"

    // $ANTLR start "CLOSE_BR"
    public final void mCLOSE_BR() throws RecognitionException {
        try {
            int _type = CLOSE_BR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:61:10: ( ']' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:62:3: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CLOSE_BR"

    // $ANTLR start "ASTERIK"
    public final void mASTERIK() throws RecognitionException {
        try {
            int _type = ASTERIK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:64:9: ( '*' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:65:3: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASTERIK"

    // $ANTLR start "SQUOTE"
    public final void mSQUOTE() throws RecognitionException {
        try {
            int _type = SQUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:67:8: ( '\\'' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:68:3: '\\''
            {
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SQUOTE"

    // $ANTLR start "DQUOTE"
    public final void mDQUOTE() throws RecognitionException {
        try {
            int _type = DQUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:70:8: ( '\"' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:71:3: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DQUOTE"

    // $ANTLR start "BSLASH"
    public final void mBSLASH() throws RecognitionException {
        try {
            int _type = BSLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:73:8: ( '\\\\' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:74:3: '\\\\'
            {
            match('\\'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BSLASH"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:76:4: ( '>' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:77:3: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:79:7: ( ':' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:80:3: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "EXCLAMATION"
    public final void mEXCLAMATION() throws RecognitionException {
        try {
            int _type = EXCLAMATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:82:13: ( '!' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:83:3: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXCLAMATION"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:85:7: ( ',' )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:86:3: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:88:4: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )* )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:89:3: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:93:3: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='-'||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "ATTR_OP"
    public final void mATTR_OP() throws RecognitionException {
        try {
            int _type = ATTR_OP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:101:9: ( ( '=' | '!=' | '*=' ) )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:102:3: ( '=' | '!=' | '*=' )
            {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:102:3: ( '=' | '!=' | '*=' )
            int alt2=3;
            switch ( input.LA(1) ) {
            case '=':
                {
                alt2=1;
                }
                break;
            case '!':
                {
                alt2=2;
                }
                break;
            case '*':
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:103:5: '='
                    {
                    match('='); 

                    }
                    break;
                case 2 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:104:7: '!='
                    {
                    match("!="); 



                    }
                    break;
                case 3 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:105:7: '*='
                    {
                    match("*="); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ATTR_OP"

    // $ANTLR start "LITERAL"
    public final void mLITERAL() throws RecognitionException {
        try {
            int _type = LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:108:9: ( SQUOTE (~ SQUOTE | BSLASH SQUOTE )* SQUOTE | DQUOTE (~ DQUOTE | BSLASH DQUOTE )* DQUOTE )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\'') ) {
                alt5=1;
            }
            else if ( (LA5_0=='\"') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:109:3: SQUOTE (~ SQUOTE | BSLASH SQUOTE )* SQUOTE
                    {
                    mSQUOTE(); 


                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:110:3: (~ SQUOTE | BSLASH SQUOTE )*
                    loop3:
                    do {
                        int alt3=3;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0=='\\') ) {
                            int LA3_2 = input.LA(2);

                            if ( (LA3_2=='\'') ) {
                                int LA3_4 = input.LA(3);

                                if ( ((LA3_4 >= '\u0000' && LA3_4 <= '\uFFFF')) ) {
                                    alt3=2;
                                }

                                else {
                                    alt3=1;
                                }


                            }
                            else if ( ((LA3_2 >= '\u0000' && LA3_2 <= '&')||(LA3_2 >= '(' && LA3_2 <= '\uFFFF')) ) {
                                alt3=1;
                            }


                        }
                        else if ( ((LA3_0 >= '\u0000' && LA3_0 <= '&')||(LA3_0 >= '(' && LA3_0 <= '[')||(LA3_0 >= ']' && LA3_0 <= '\uFFFF')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:111:5: ~ SQUOTE
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\u0010')||(input.LA(1) >= '\u0012' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:112:7: BSLASH SQUOTE
                    	    {
                    	    mBSLASH(); 


                    	    mSQUOTE(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    mSQUOTE(); 


                    }
                    break;
                case 2 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:115:5: DQUOTE (~ DQUOTE | BSLASH DQUOTE )* DQUOTE
                    {
                    mDQUOTE(); 


                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:116:3: (~ DQUOTE | BSLASH DQUOTE )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            int LA4_2 = input.LA(2);

                            if ( (LA4_2=='\"') ) {
                                int LA4_4 = input.LA(3);

                                if ( ((LA4_4 >= '\u0000' && LA4_4 <= '\uFFFF')) ) {
                                    alt4=2;
                                }

                                else {
                                    alt4=1;
                                }


                            }
                            else if ( ((LA4_2 >= '\u0000' && LA4_2 <= '!')||(LA4_2 >= '#' && LA4_2 <= '\uFFFF')) ) {
                                alt4=1;
                            }


                        }
                        else if ( ((LA4_0 >= '\u0000' && LA4_0 <= '!')||(LA4_0 >= '#' && LA4_0 <= '[')||(LA4_0 >= ']' && LA4_0 <= '\uFFFF')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:117:5: ~ DQUOTE
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:118:7: BSLASH DQUOTE
                    	    {
                    	    mBSLASH(); 


                    	    mDQUOTE(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    mDQUOTE(); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LITERAL"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:122:8: ( ( '+' | '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:123:3: ( '+' | '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
            {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:123:3: ( '+' | '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='+'||LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:127:3: ( '0' .. '9' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:127:13: ( '.' ( '0' .. '9' )+ )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='.') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:127:14: '.' ( '0' .. '9' )+
                    {
                    match('.'); 

                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:127:18: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:129:12: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:130:3: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:130:3: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0 >= '\t' && LA10_0 <= '\n')||(LA10_0 >= '\f' && LA10_0 <= '\r')||LA10_0==' ') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:8: ( T__19 | T__20 | OPEN_BR | CLOSE_BR | ASTERIK | SQUOTE | DQUOTE | BSLASH | LT | COLON | EXCLAMATION | COMMA | ID | ATTR_OP | LITERAL | NUMBER | WHITESPACE )
        int alt11=17;
        switch ( input.LA(1) ) {
        case '(':
            {
            alt11=1;
            }
            break;
        case ')':
            {
            alt11=2;
            }
            break;
        case '[':
            {
            alt11=3;
            }
            break;
        case ']':
            {
            alt11=4;
            }
            break;
        case '*':
            {
            int LA11_5 = input.LA(2);

            if ( (LA11_5=='=') ) {
                alt11=14;
            }
            else {
                alt11=5;
            }
            }
            break;
        case '\'':
            {
            int LA11_6 = input.LA(2);

            if ( ((LA11_6 >= '\u0000' && LA11_6 <= '\uFFFF')) ) {
                alt11=15;
            }
            else {
                alt11=6;
            }
            }
            break;
        case '\"':
            {
            int LA11_7 = input.LA(2);

            if ( ((LA11_7 >= '\u0000' && LA11_7 <= '\uFFFF')) ) {
                alt11=15;
            }
            else {
                alt11=7;
            }
            }
            break;
        case '\\':
            {
            alt11=8;
            }
            break;
        case '>':
            {
            alt11=9;
            }
            break;
        case ':':
            {
            alt11=10;
            }
            break;
        case '!':
            {
            int LA11_11 = input.LA(2);

            if ( (LA11_11=='=') ) {
                alt11=14;
            }
            else {
                alt11=11;
            }
            }
            break;
        case ',':
            {
            alt11=12;
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt11=13;
            }
            break;
        case '=':
            {
            alt11=14;
            }
            break;
        case '+':
        case '-':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt11=16;
            }
            break;
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
            {
            alt11=17;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 11, 0, input);

            throw nvae;

        }

        switch (alt11) {
            case 1 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:10: T__19
                {
                mT__19(); 


                }
                break;
            case 2 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:16: T__20
                {
                mT__20(); 


                }
                break;
            case 3 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:22: OPEN_BR
                {
                mOPEN_BR(); 


                }
                break;
            case 4 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:30: CLOSE_BR
                {
                mCLOSE_BR(); 


                }
                break;
            case 5 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:39: ASTERIK
                {
                mASTERIK(); 


                }
                break;
            case 6 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:47: SQUOTE
                {
                mSQUOTE(); 


                }
                break;
            case 7 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:54: DQUOTE
                {
                mDQUOTE(); 


                }
                break;
            case 8 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:61: BSLASH
                {
                mBSLASH(); 


                }
                break;
            case 9 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:68: LT
                {
                mLT(); 


                }
                break;
            case 10 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:71: COLON
                {
                mCOLON(); 


                }
                break;
            case 11 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:77: EXCLAMATION
                {
                mEXCLAMATION(); 


                }
                break;
            case 12 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:89: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 13 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:95: ID
                {
                mID(); 


                }
                break;
            case 14 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:98: ATTR_OP
                {
                mATTR_OP(); 


                }
                break;
            case 15 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:106: LITERAL
                {
                mLITERAL(); 


                }
                break;
            case 16 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:114: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 17 :
                // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:1:121: WHITESPACE
                {
                mWHITESPACE(); 


                }
                break;

        }

    }


 

}