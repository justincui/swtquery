// $ANTLR 3.4 C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g 2011-10-20 17:01:15

package kr.or.eclipse.swt.query.parse;


import org.antlr.runtime.CharStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SWTQuerySelectorLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__14=14;
    public static final int ASTERIK=4;
    public static final int BSLASH=5;
    public static final int CLOSE_BR=6;
    public static final int ID=7;
    public static final int LITERAL=8;
    public static final int LT=9;
    public static final int OP=10;
    public static final int OPEN_BR=11;
    public static final int SQUOTE=12;
    public static final int WHITESPACE=13;

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
    public String getGrammarFileName() { return "C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g"; }

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:6:7: ( ',' )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:6:9: ','
            {
                match(',');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "SQUOTE"
    public final void mSQUOTE() throws RecognitionException {
        try {
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:17:17: ( '\\'' )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:17:19: '\\''
            {
                match('\'');

            }


        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SQUOTE"

    // $ANTLR start "BSLASH"
    public final void mBSLASH() throws RecognitionException {
        try {
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:18:17: ( '\\\\' )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:18:19: '\\\\'
            {
                match('\\');

            }


        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BSLASH"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:20:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:20:7: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
                if ( this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'||this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z' ) {
                    this.input.consume();
                }
                else {
                    MismatchedSetException mse = new MismatchedSetException(null,this.input);
                    recover(mse);
                    throw mse;
                }


                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:20:26: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
                loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = this.input.LA(1);

                        if ( LA1_0 >= '0' && LA1_0 <= '9'||LA1_0 >= 'A' && LA1_0 <= 'Z'||LA1_0 >= 'a' && LA1_0 <= 'z' ) {
                            alt1=1;
                        }


                        switch (alt1) {
                        case 1 :
                            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:
                        {
                            if ( this.input.LA(1) >= '0' && this.input.LA(1) <= '9'||this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'||this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z' ) {
                                this.input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,this.input);
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

            this.state.type = _type;
            this.state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "OPEN_BR"
    public final void mOPEN_BR() throws RecognitionException {
        try {
            int _type = OPEN_BR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:21:10: ( '[' )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:21:12: '['
            {
                match('[');

            }

            this.state.type = _type;
            this.state.channel = _channel;
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
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:22:10: ( ']' )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:22:12: ']'
            {
                match(']');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "CLOSE_BR"

    // $ANTLR start "OP"
    public final void mOP() throws RecognitionException {
        try {
            int _type = OP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:23:5: ( ( '=' | '~=' | '!=' ) )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:23:7: ( '=' | '~=' | '!=' )
            {
                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:23:7: ( '=' | '~=' | '!=' )
                int alt2=3;
                switch ( this.input.LA(1) ) {
                case '=':
                {
                    alt2=1;
                }
                break;
                case '~':
                {
                    alt2=2;
                }
                break;
                case '!':
                {
                    alt2=3;
                }
                break;
                default:
                    NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, this.input);

                    throw nvae;

                }

                switch (alt2) {
                case 1 :
                    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:23:8: '='
                {
                    match('=');

                }
                break;
                case 2 :
                    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:23:12: '~='
                {
                    match("~=");



                }
                break;
                case 3 :
                    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:23:17: '!='
                {
                    match("!=");



                }
                break;

                }


            }

            this.state.type = _type;
            this.state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "OP"

    // $ANTLR start "ASTERIK"
    public final void mASTERIK() throws RecognitionException {
        try {
            int _type = ASTERIK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:24:10: ( '*' )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:24:12: '*'
            {
                match('*');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ASTERIK"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:25:12: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:25:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:25:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
                int cnt3=0;
                loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = this.input.LA(1);

                        if ( LA3_0 >= '\t' && LA3_0 <= '\n'||LA3_0 >= '\f' && LA3_0 <= '\r'||LA3_0==' ' ) {
                            alt3=1;
                        }


                        switch (alt3) {
                        case 1 :
                            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:
                        {
                            if ( this.input.LA(1) >= '\t' && this.input.LA(1) <= '\n'||this.input.LA(1) >= '\f' && this.input.LA(1) <= '\r'||this.input.LA(1)==' ' ) {
                                this.input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,this.input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default :
                            if ( cnt3 >= 1 ) {
                                break loop3;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(3, this.input);
                            throw eee;
                        }
                        cnt3++;
                    } while (true);


                _channel = HIDDEN;

            }

            this.state.type = _type;
            this.state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "LITERAL"
    public final void mLITERAL() throws RecognitionException {
        try {
            int _type = LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:26:10: ( SQUOTE ( (~ SQUOTE | BSLASH . )* ) SQUOTE )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:26:12: SQUOTE ( (~ SQUOTE | BSLASH . )* ) SQUOTE
            {
                mSQUOTE();


                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:26:19: ( (~ SQUOTE | BSLASH . )* )
                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:26:20: (~ SQUOTE | BSLASH . )*
                {
                    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:26:20: (~ SQUOTE | BSLASH . )*
                    loop4:
                        do {
                            int alt4=3;
                            int LA4_0 = this.input.LA(1);

                            if ( LA4_0=='\\' ) {
                                int LA4_2 = this.input.LA(2);

                                if ( LA4_2=='\'' ) {
                                    int LA4_4 = this.input.LA(3);

                                    if ( LA4_4 >= '\u0000' && LA4_4 <= '\uFFFF' ) {
                                        alt4=2;
                                    }

                                    else {
                                        alt4=1;
                                    }


                                }
                                else if ( LA4_2=='\\' ) {
                                    alt4=1;
                                }
                                else if ( LA4_2 >= '\u0000' && LA4_2 <= '&'||LA4_2 >= '(' && LA4_2 <= '['||LA4_2 >= ']' && LA4_2 <= '\uFFFF' ) {
                                    alt4=1;
                                }


                            }
                            else if ( LA4_0 >= '\u0000' && LA4_0 <= '&'||LA4_0 >= '(' && LA4_0 <= '['||LA4_0 >= ']' && LA4_0 <= '\uFFFF' ) {
                                alt4=1;
                            }


                            switch (alt4) {
                            case 1 :
                                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:26:21: ~ SQUOTE
                            {
                                if ( this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '\u000B'||this.input.LA(1) >= '\r' && this.input.LA(1) <= '\uFFFF' ) {
                                    this.input.consume();
                                }
                                else {
                                    MismatchedSetException mse = new MismatchedSetException(null,this.input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;
                            case 2 :
                                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:26:29: BSLASH .
                            {
                                mBSLASH();


                                matchAny();

                            }
                            break;

                            default :
                                break loop4;
                            }
                        } while (true);


                }


                mSQUOTE();


            }

            this.state.type = _type;
            this.state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LITERAL"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:27:5: ( '>' )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:27:7: '>'
            {
                match('>');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LT"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:8: ( T__14 | ID | OPEN_BR | CLOSE_BR | OP | ASTERIK | WHITESPACE | LITERAL | LT )
        int alt5=9;
        switch ( this.input.LA(1) ) {
        case ',':
        {
            alt5=1;
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
            alt5=2;
        }
        break;
        case '[':
        {
            alt5=3;
        }
        break;
        case ']':
        {
            alt5=4;
        }
        break;
        case '!':
        case '=':
        case '~':
        {
            alt5=5;
        }
        break;
        case '*':
        {
            alt5=6;
        }
        break;
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
        {
            alt5=7;
        }
        break;
        case '\'':
        {
            alt5=8;
        }
        break;
        case '>':
        {
            alt5=9;
        }
        break;
        default:
            NoViableAltException nvae =
            new NoViableAltException("", 5, 0, this.input);

            throw nvae;

        }

        switch (alt5) {
        case 1 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:10: T__14
        {
            mT__14();


        }
        break;
        case 2 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:16: ID
        {
            mID();


        }
        break;
        case 3 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:19: OPEN_BR
        {
            mOPEN_BR();


        }
        break;
        case 4 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:27: CLOSE_BR
        {
            mCLOSE_BR();


        }
        break;
        case 5 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:36: OP
        {
            mOP();


        }
        break;
        case 6 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:39: ASTERIK
        {
            mASTERIK();


        }
        break;
        case 7 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:47: WHITESPACE
        {
            mWHITESPACE();


        }
        break;
        case 8 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:58: LITERAL
        {
            mLITERAL();


        }
        break;
        case 9 :
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:1:66: LT
        {
            mLT();


        }
        break;

        }

    }




}