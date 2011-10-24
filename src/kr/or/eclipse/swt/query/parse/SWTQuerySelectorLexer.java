// $ANTLR 3.4 P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g 2011-10-24 22:28:30

package kr.or.eclipse.swt.query.parse;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({ "all", "warnings", "unchecked" })
public class SWTQuerySelectorLexer extends Lexer {
	public static final int EOF = -1;
	public static final int T__18 = 18;
	public static final int T__19 = 19;
	public static final int ASTERIK = 4;
	public static final int ATTR_OP = 5;
	public static final int BSLASH = 6;
	public static final int CLOSE_BR = 7;
	public static final int COLON = 8;
	public static final int COMMA = 9;
	public static final int DQUOTE = 10;
	public static final int EXCLAMATION = 11;
	public static final int ID = 12;
	public static final int LITERAL = 13;
	public static final int LT = 14;
	public static final int OPEN_BR = 15;
	public static final int SQUOTE = 16;
	public static final int WHITESPACE = 17;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public SWTQuerySelectorLexer() {
	}

	public SWTQuerySelectorLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}

	public SWTQuerySelectorLexer(CharStream input, RecognizerSharedState state) {
		super(input, state);
	}

	public String getGrammarFileName() {
		return "P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g";
	}

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:6:7:
			// ( '(' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:6:9:
			// '('
			{
				match('(');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:7:7:
			// ( ')' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:7:9:
			// ')'
			{
				match(')');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__19"

	// $ANTLR start "SQUOTE"
	public final void mSQUOTE() throws RecognitionException {
		try {
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:16:17:
			// ( '\\'' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:16:19:
			// '\\''
			{
				match('\'');

			}

		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "SQUOTE"

	// $ANTLR start "DQUOTE"
	public final void mDQUOTE() throws RecognitionException {
		try {
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:17:17:
			// ( '\"' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:17:19:
			// '\"'
			{
				match('\"');

			}

		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "DQUOTE"

	// $ANTLR start "BSLASH"
	public final void mBSLASH() throws RecognitionException {
		try {
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:18:17:
			// ( '\\\\' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:18:19:
			// '\\\\'
			{
				match('\\');

			}

		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "BSLASH"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:20:5:
			// ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' ..
			// '9' | '-' | '_' )* )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:20:7:
			// ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' ..
			// '9' | '-' | '_' )*
			{
				if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z')
						|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
					input.consume();
				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:20:26:
				// ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )*
				loop1: do {
					int alt1 = 2;
					int LA1_0 = input.LA(1);

					if ((LA1_0 == '-' || (LA1_0 >= '0' && LA1_0 <= '9')
							|| (LA1_0 >= 'A' && LA1_0 <= 'Z') || LA1_0 == '_' || (LA1_0 >= 'a' && LA1_0 <= 'z'))) {
						alt1 = 1;
					}

					switch (alt1) {
					case 1:
					// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:
					{
						if (input.LA(1) == '-'
								|| (input.LA(1) >= '0' && input.LA(1) <= '9')
								|| (input.LA(1) >= 'A' && input.LA(1) <= 'Z')
								|| input.LA(1) == '_'
								|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
							input.consume();
						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop1;
					}
				} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "ID"

	// $ANTLR start "OPEN_BR"
	public final void mOPEN_BR() throws RecognitionException {
		try {
			int _type = OPEN_BR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:21:10:
			// ( '[' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:21:12:
			// '['
			{
				match('[');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "OPEN_BR"

	// $ANTLR start "CLOSE_BR"
	public final void mCLOSE_BR() throws RecognitionException {
		try {
			int _type = CLOSE_BR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:22:11:
			// ( ']' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:22:13:
			// ']'
			{
				match(']');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "CLOSE_BR"

	// $ANTLR start "ATTR_OP"
	public final void mATTR_OP() throws RecognitionException {
		try {
			int _type = ATTR_OP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:23:10:
			// ( ( '=' | '~=' | '!=' ) )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:23:12:
			// ( '=' | '~=' | '!=' )
			{
				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:23:12:
				// ( '=' | '~=' | '!=' )
				int alt2 = 3;
				switch (input.LA(1)) {
				case '=': {
					alt2 = 1;
				}
					break;
				case '~': {
					alt2 = 2;
				}
					break;
				case '!': {
					alt2 = 3;
				}
					break;
				default:
					NoViableAltException nvae = new NoViableAltException("", 2,
							0, input);

					throw nvae;

				}

				switch (alt2) {
				case 1:
				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:23:13:
				// '='
				{
					match('=');

				}
					break;
				case 2:
				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:23:17:
				// '~='
				{
					match("~=");

				}
					break;
				case 3:
				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:23:22:
				// '!='
				{
					match("!=");

				}
					break;

				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "ATTR_OP"

	// $ANTLR start "ASTERIK"
	public final void mASTERIK() throws RecognitionException {
		try {
			int _type = ASTERIK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:24:10:
			// ( '*' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:24:12:
			// '*'
			{
				match('*');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "ASTERIK"

	// $ANTLR start "WHITESPACE"
	public final void mWHITESPACE() throws RecognitionException {
		try {
			int _type = WHITESPACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:25:13:
			// ( ( '\\t' | ' ' | '\\r' | '\\n' | '\ ' )+ )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:25:15:
			// ( '\\t' | ' ' | '\\r' | '\\n' | '\ ' )+
			{
				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:25:15:
				// ( '\\t' | ' ' | '\\r' | '\\n' | '\ ' )+
				int cnt3 = 0;
				loop3: do {
					int alt3 = 2;
					int LA3_0 = input.LA(1);

					if (((LA3_0 >= '\t' && LA3_0 <= '\n')
							|| (LA3_0 >= '\f' && LA3_0 <= '\r') || LA3_0 == ' ')) {
						alt3 = 1;
					}

					switch (alt3) {
					case 1:
					// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:
					{
						if ((input.LA(1) >= '\t' && input.LA(1) <= '\n')
								|| (input.LA(1) >= '\f' && input.LA(1) <= '\r')
								|| input.LA(1) == ' ') {
							input.consume();
						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						if (cnt3 >= 1)
							break loop3;
						EarlyExitException eee = new EarlyExitException(3,
								input);
						throw eee;
					}
					cnt3++;
				} while (true);

				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "WHITESPACE"

	// $ANTLR start "LITERAL"
	public final void mLITERAL() throws RecognitionException {
		try {
			int _type = LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:10:
			// ( SQUOTE ( (~ SQUOTE | BSLASH SQUOTE )* ) SQUOTE | DQUOTE ( (~
			// DQUOTE | BSLASH DQUOTE )* ) DQUOTE )
			int alt6 = 2;
			int LA6_0 = input.LA(1);

			if ((LA6_0 == '\'')) {
				alt6 = 1;
			} else if ((LA6_0 == '\"')) {
				alt6 = 2;
			} else {
				NoViableAltException nvae = new NoViableAltException("", 6, 0,
						input);

				throw nvae;

			}
			switch (alt6) {
			case 1:
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:12:
			// SQUOTE ( (~ SQUOTE | BSLASH SQUOTE )* ) SQUOTE
			{
				mSQUOTE();

				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:20:
				// ( (~ SQUOTE | BSLASH SQUOTE )* )
				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:21:
				// (~ SQUOTE | BSLASH SQUOTE )*
				{
					// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:21:
					// (~ SQUOTE | BSLASH SQUOTE )*
					loop4: do {
						int alt4 = 3;
						int LA4_0 = input.LA(1);

						if ((LA4_0 == '\\')) {
							int LA4_2 = input.LA(2);

							if ((LA4_2 == '\'')) {
								int LA4_4 = input.LA(3);

								if (((LA4_4 >= '\u0000' && LA4_4 <= '\uFFFF'))) {
									alt4 = 2;
								}

								else {
									alt4 = 1;
								}

							} else if (((LA4_2 >= '\u0000' && LA4_2 <= '&') || (LA4_2 >= '(' && LA4_2 <= '\uFFFF'))) {
								alt4 = 1;
							}

						} else if (((LA4_0 >= '\u0000' && LA4_0 <= '&')
								|| (LA4_0 >= '(' && LA4_0 <= '[') || (LA4_0 >= ']' && LA4_0 <= '\uFFFF'))) {
							alt4 = 1;
						}

						switch (alt4) {
						case 1:
						// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:22:
						// ~ SQUOTE
						{
							if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\u000F')
									|| (input.LA(1) >= '\u0011' && input.LA(1) <= '\uFFFF')) {
								input.consume();
							} else {
								MismatchedSetException mse = new MismatchedSetException(
										null, input);
								recover(mse);
								throw mse;
							}

						}
							break;
						case 2:
						// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:30:
						// BSLASH SQUOTE
						{
							mBSLASH();

							mSQUOTE();

						}
							break;

						default:
							break loop4;
						}
					} while (true);

				}

				mSQUOTE();

			}
				break;
			case 2:
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:57:
			// DQUOTE ( (~ DQUOTE | BSLASH DQUOTE )* ) DQUOTE
			{
				mDQUOTE();

				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:65:
				// ( (~ DQUOTE | BSLASH DQUOTE )* )
				// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:66:
				// (~ DQUOTE | BSLASH DQUOTE )*
				{
					// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:66:
					// (~ DQUOTE | BSLASH DQUOTE )*
					loop5: do {
						int alt5 = 3;
						int LA5_0 = input.LA(1);

						if ((LA5_0 == '\\')) {
							int LA5_2 = input.LA(2);

							if ((LA5_2 == '\"')) {
								int LA5_4 = input.LA(3);

								if (((LA5_4 >= '\u0000' && LA5_4 <= '\uFFFF'))) {
									alt5 = 2;
								}

								else {
									alt5 = 1;
								}

							} else if (((LA5_2 >= '\u0000' && LA5_2 <= '!') || (LA5_2 >= '#' && LA5_2 <= '\uFFFF'))) {
								alt5 = 1;
							}

						} else if (((LA5_0 >= '\u0000' && LA5_0 <= '!')
								|| (LA5_0 >= '#' && LA5_0 <= '[') || (LA5_0 >= ']' && LA5_0 <= '\uFFFF'))) {
							alt5 = 1;
						}

						switch (alt5) {
						case 1:
						// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:67:
						// ~ DQUOTE
						{
							if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
									|| (input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF')) {
								input.consume();
							} else {
								MismatchedSetException mse = new MismatchedSetException(
										null, input);
								recover(mse);
								throw mse;
							}

						}
							break;
						case 2:
						// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:26:75:
						// BSLASH DQUOTE
						{
							mBSLASH();

							mDQUOTE();

						}
							break;

						default:
							break loop5;
						}
					} while (true);

				}

				mDQUOTE();

			}
				break;

			}
			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "LITERAL"

	// $ANTLR start "LT"
	public final void mLT() throws RecognitionException {
		try {
			int _type = LT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:27:5:
			// ( '>' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:27:7:
			// '>'
			{
				match('>');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "LT"

	// $ANTLR start "COLON"
	public final void mCOLON() throws RecognitionException {
		try {
			int _type = COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:28:8:
			// ( ':' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:28:10:
			// ':'
			{
				match(':');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "COLON"

	// $ANTLR start "EXCLAMATION"
	public final void mEXCLAMATION() throws RecognitionException {
		try {
			int _type = EXCLAMATION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:29:13:
			// ( '!' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:29:15:
			// '!'
			{
				match('!');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "EXCLAMATION"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:30:8:
			// ( ',' )
			// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:30:10:
			// ','
			{
				match(',');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "COMMA"

	public void mTokens() throws RecognitionException {
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:8:
		// ( T__18 | T__19 | ID | OPEN_BR | CLOSE_BR | ATTR_OP | ASTERIK |
		// WHITESPACE | LITERAL | LT | COLON | EXCLAMATION | COMMA )
		int alt7 = 13;
		switch (input.LA(1)) {
		case '(': {
			alt7 = 1;
		}
			break;
		case ')': {
			alt7 = 2;
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
		case 'z': {
			alt7 = 3;
		}
			break;
		case '[': {
			alt7 = 4;
		}
			break;
		case ']': {
			alt7 = 5;
		}
			break;
		case '=':
		case '~': {
			alt7 = 6;
		}
			break;
		case '!': {
			int LA7_7 = input.LA(2);

			if ((LA7_7 == '=')) {
				alt7 = 6;
			} else {
				alt7 = 12;
			}
		}
			break;
		case '*': {
			alt7 = 7;
		}
			break;
		case '\t':
		case '\n':
		case '\f':
		case '\r':
		case ' ': {
			alt7 = 8;
		}
			break;
		case '\"':
		case '\'': {
			alt7 = 9;
		}
			break;
		case '>': {
			alt7 = 10;
		}
			break;
		case ':': {
			alt7 = 11;
		}
			break;
		case ',': {
			alt7 = 13;
		}
			break;
		default:
			NoViableAltException nvae = new NoViableAltException("", 7, 0,
					input);

			throw nvae;

		}

		switch (alt7) {
		case 1:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:10:
		// T__18
		{
			mT__18();

		}
			break;
		case 2:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:16:
		// T__19
		{
			mT__19();

		}
			break;
		case 3:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:22:
		// ID
		{
			mID();

		}
			break;
		case 4:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:25:
		// OPEN_BR
		{
			mOPEN_BR();

		}
			break;
		case 5:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:33:
		// CLOSE_BR
		{
			mCLOSE_BR();

		}
			break;
		case 6:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:42:
		// ATTR_OP
		{
			mATTR_OP();

		}
			break;
		case 7:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:50:
		// ASTERIK
		{
			mASTERIK();

		}
			break;
		case 8:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:58:
		// WHITESPACE
		{
			mWHITESPACE();

		}
			break;
		case 9:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:69:
		// LITERAL
		{
			mLITERAL();

		}
			break;
		case 10:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:77:
		// LT
		{
			mLT();

		}
			break;
		case 11:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:80:
		// COLON
		{
			mCOLON();

		}
			break;
		case 12:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:86:
		// EXCLAMATION
		{
			mEXCLAMATION();

		}
			break;
		case 13:
		// P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:1:98:
		// COMMA
		{
			mCOMMA();

		}
			break;

		}

	}

}