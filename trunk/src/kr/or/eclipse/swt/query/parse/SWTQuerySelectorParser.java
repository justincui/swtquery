// $ANTLR 3.4 P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g 2011-10-22 00:30:13

package kr.or.eclipse.swt.query.parse;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class SWTQuerySelectorParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASTERIK", "ATTR_OP", "BSLASH", "CLOSE_BR", "COLON", "DQUOTE", "EXCLAMATION", "ID", "LITERAL", "LT", "OPEN_BR", "SQUOTE", "WHITESPACE", "'('", "')'", "','"
    };

    public static final int EOF=-1;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int ASTERIK=4;
    public static final int ATTR_OP=5;
    public static final int BSLASH=6;
    public static final int CLOSE_BR=7;
    public static final int COLON=8;
    public static final int DQUOTE=9;
    public static final int EXCLAMATION=10;
    public static final int ID=11;
    public static final int LITERAL=12;
    public static final int LT=13;
    public static final int OPEN_BR=14;
    public static final int SQUOTE=15;
    public static final int WHITESPACE=16;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public SWTQuerySelectorParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public SWTQuerySelectorParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return SWTQuerySelectorParser.tokenNames; }
    public String getGrammarFileName() { return "P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g"; }


    public static class selectors_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectors"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:33:1: selectors : ( selector )+ ;
    public final SWTQuerySelectorParser.selectors_return selectors() throws RecognitionException {
        SWTQuerySelectorParser.selectors_return retval = new SWTQuerySelectorParser.selectors_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        SWTQuerySelectorParser.selector_return selector1 =null;



        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:33:11: ( ( selector )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:34:2: ( selector )+
            {
            root_0 = (Object)adaptor.nil();


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:34:2: ( selector )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ASTERIK||LA1_0==ID||LA1_0==LT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:34:2: selector
            	    {
            	    pushFollow(FOLLOW_selector_in_selectors242);
            	    selector1=selector();

            	    state._fsp--;

            	    adaptor.addChild(root_0, selector1.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selectors"


    public static class selector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selector"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:37:1: selector : ( LT )? ( ID | ASTERIK ) ^ ( psudo )? ( attributes )? ;
    public final SWTQuerySelectorParser.selector_return selector() throws RecognitionException {
        SWTQuerySelectorParser.selector_return retval = new SWTQuerySelectorParser.selector_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LT2=null;
        Token set3=null;
        SWTQuerySelectorParser.psudo_return psudo4 =null;

        SWTQuerySelectorParser.attributes_return attributes5 =null;


        Object LT2_tree=null;
        Object set3_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:37:10: ( ( LT )? ( ID | ASTERIK ) ^ ( psudo )? ( attributes )? )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:38:2: ( LT )? ( ID | ASTERIK ) ^ ( psudo )? ( attributes )?
            {
            root_0 = (Object)adaptor.nil();


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:38:2: ( LT )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LT) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:38:2: LT
                    {
                    LT2=(Token)match(input,LT,FOLLOW_LT_in_selector255); 
                    LT2_tree = 
                    (Object)adaptor.create(LT2)
                    ;
                    adaptor.addChild(root_0, LT2_tree);


                    }
                    break;

            }


            set3=(Token)input.LT(1);

            set3=(Token)input.LT(1);

            if ( input.LA(1)==ASTERIK||input.LA(1)==ID ) {
                input.consume();
                root_0 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(set3)
                , root_0);
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:38:20: ( psudo )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==COLON) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:38:20: psudo
                    {
                    pushFollow(FOLLOW_psudo_in_selector265);
                    psudo4=psudo();

                    state._fsp--;

                    adaptor.addChild(root_0, psudo4.getTree());

                    }
                    break;

            }


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:38:27: ( attributes )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==OPEN_BR) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:38:27: attributes
                    {
                    pushFollow(FOLLOW_attributes_in_selector268);
                    attributes5=attributes();

                    state._fsp--;

                    adaptor.addChild(root_0, attributes5.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selector"


    public static class idList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "idList"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:40:1: idList : ID ( ',' ID )* -> ( ID )+ ;
    public final SWTQuerySelectorParser.idList_return idList() throws RecognitionException {
        SWTQuerySelectorParser.idList_return retval = new SWTQuerySelectorParser.idList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID6=null;
        Token char_literal7=null;
        Token ID8=null;

        Object ID6_tree=null;
        Object char_literal7_tree=null;
        Object ID8_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:40:9: ( ID ( ',' ID )* -> ( ID )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:41:2: ID ( ',' ID )*
            {
            ID6=(Token)match(input,ID,FOLLOW_ID_in_idList280);  
            stream_ID.add(ID6);


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:41:5: ( ',' ID )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==19) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:41:6: ',' ID
            	    {
            	    char_literal7=(Token)match(input,19,FOLLOW_19_in_idList283);  
            	    stream_19.add(char_literal7);


            	    ID8=(Token)match(input,ID,FOLLOW_ID_in_idList285);  
            	    stream_ID.add(ID8);


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            // AST REWRITE
            // elements: ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 41:15: -> ( ID )+
            {
                if ( !(stream_ID.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ID.hasNext() ) {
                    adaptor.addChild(root_0, 
                    stream_ID.nextNode()
                    );

                }
                stream_ID.reset();

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "idList"


    public static class psudo_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "psudo"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:45:1: psudo : ( COLON ^ ID | COLON ^ '(' ! idList ')' !);
    public final SWTQuerySelectorParser.psudo_return psudo() throws RecognitionException {
        SWTQuerySelectorParser.psudo_return retval = new SWTQuerySelectorParser.psudo_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COLON9=null;
        Token ID10=null;
        Token COLON11=null;
        Token char_literal12=null;
        Token char_literal14=null;
        SWTQuerySelectorParser.idList_return idList13 =null;


        Object COLON9_tree=null;
        Object ID10_tree=null;
        Object COLON11_tree=null;
        Object char_literal12_tree=null;
        Object char_literal14_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:45:8: ( COLON ^ ID | COLON ^ '(' ! idList ')' !)
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==COLON) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==ID) ) {
                    alt6=1;
                }
                else if ( (LA6_1==17) ) {
                    alt6=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:46:2: COLON ^ ID
                    {
                    root_0 = (Object)adaptor.nil();


                    COLON9=(Token)match(input,COLON,FOLLOW_COLON_in_psudo303); 
                    COLON9_tree = 
                    (Object)adaptor.create(COLON9)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(COLON9_tree, root_0);


                    ID10=(Token)match(input,ID,FOLLOW_ID_in_psudo306); 
                    ID10_tree = 
                    (Object)adaptor.create(ID10)
                    ;
                    adaptor.addChild(root_0, ID10_tree);


                    }
                    break;
                case 2 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:47:2: COLON ^ '(' ! idList ')' !
                    {
                    root_0 = (Object)adaptor.nil();


                    COLON11=(Token)match(input,COLON,FOLLOW_COLON_in_psudo311); 
                    COLON11_tree = 
                    (Object)adaptor.create(COLON11)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(COLON11_tree, root_0);


                    char_literal12=(Token)match(input,17,FOLLOW_17_in_psudo314); 

                    pushFollow(FOLLOW_idList_in_psudo317);
                    idList13=idList();

                    state._fsp--;

                    adaptor.addChild(root_0, idList13.getTree());

                    char_literal14=(Token)match(input,18,FOLLOW_18_in_psudo319); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "psudo"


    public static class attributes_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "attributes"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:52:1: attributes : OPEN_BR attribute ( ',' attribute )* CLOSE_BR -> ( attribute )+ ;
    public final SWTQuerySelectorParser.attributes_return attributes() throws RecognitionException {
        SWTQuerySelectorParser.attributes_return retval = new SWTQuerySelectorParser.attributes_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token OPEN_BR15=null;
        Token char_literal17=null;
        Token CLOSE_BR19=null;
        SWTQuerySelectorParser.attribute_return attribute16 =null;

        SWTQuerySelectorParser.attribute_return attribute18 =null;


        Object OPEN_BR15_tree=null;
        Object char_literal17_tree=null;
        Object CLOSE_BR19_tree=null;
        RewriteRuleTokenStream stream_CLOSE_BR=new RewriteRuleTokenStream(adaptor,"token CLOSE_BR");
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_OPEN_BR=new RewriteRuleTokenStream(adaptor,"token OPEN_BR");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:52:12: ( OPEN_BR attribute ( ',' attribute )* CLOSE_BR -> ( attribute )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:53:2: OPEN_BR attribute ( ',' attribute )* CLOSE_BR
            {
            OPEN_BR15=(Token)match(input,OPEN_BR,FOLLOW_OPEN_BR_in_attributes331);  
            stream_OPEN_BR.add(OPEN_BR15);


            pushFollow(FOLLOW_attribute_in_attributes333);
            attribute16=attribute();

            state._fsp--;

            stream_attribute.add(attribute16.getTree());

            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:53:20: ( ',' attribute )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:53:21: ',' attribute
            	    {
            	    char_literal17=(Token)match(input,19,FOLLOW_19_in_attributes336);  
            	    stream_19.add(char_literal17);


            	    pushFollow(FOLLOW_attribute_in_attributes338);
            	    attribute18=attribute();

            	    state._fsp--;

            	    stream_attribute.add(attribute18.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            CLOSE_BR19=(Token)match(input,CLOSE_BR,FOLLOW_CLOSE_BR_in_attributes342);  
            stream_CLOSE_BR.add(CLOSE_BR19);


            // AST REWRITE
            // elements: attribute
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 53:46: -> ( attribute )+
            {
                if ( !(stream_attribute.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_attribute.hasNext() ) {
                    adaptor.addChild(root_0, stream_attribute.nextTree());

                }
                stream_attribute.reset();

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "attributes"


    public static class attribute_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "attribute"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:58:1: attribute : ( ID ATTR_OP ^ ( attributeValue )? | EXCLAMATION ID ^);
    public final SWTQuerySelectorParser.attribute_return attribute() throws RecognitionException {
        SWTQuerySelectorParser.attribute_return retval = new SWTQuerySelectorParser.attribute_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID20=null;
        Token ATTR_OP21=null;
        Token EXCLAMATION23=null;
        Token ID24=null;
        SWTQuerySelectorParser.attributeValue_return attributeValue22 =null;


        Object ID20_tree=null;
        Object ATTR_OP21_tree=null;
        Object EXCLAMATION23_tree=null;
        Object ID24_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:58:11: ( ID ATTR_OP ^ ( attributeValue )? | EXCLAMATION ID ^)
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ID) ) {
                alt9=1;
            }
            else if ( (LA9_0==EXCLAMATION) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:59:2: ID ATTR_OP ^ ( attributeValue )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID20=(Token)match(input,ID,FOLLOW_ID_in_attribute360); 
                    ID20_tree = 
                    (Object)adaptor.create(ID20)
                    ;
                    adaptor.addChild(root_0, ID20_tree);


                    ATTR_OP21=(Token)match(input,ATTR_OP,FOLLOW_ATTR_OP_in_attribute362); 
                    ATTR_OP21_tree = 
                    (Object)adaptor.create(ATTR_OP21)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(ATTR_OP21_tree, root_0);


                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:59:14: ( attributeValue )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( ((LA8_0 >= ID && LA8_0 <= LITERAL)) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:59:14: attributeValue
                            {
                            pushFollow(FOLLOW_attributeValue_in_attribute365);
                            attributeValue22=attributeValue();

                            state._fsp--;

                            adaptor.addChild(root_0, attributeValue22.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:60:2: EXCLAMATION ID ^
                    {
                    root_0 = (Object)adaptor.nil();


                    EXCLAMATION23=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_attribute371); 
                    EXCLAMATION23_tree = 
                    (Object)adaptor.create(EXCLAMATION23)
                    ;
                    adaptor.addChild(root_0, EXCLAMATION23_tree);


                    ID24=(Token)match(input,ID,FOLLOW_ID_in_attribute373); 
                    ID24_tree = 
                    (Object)adaptor.create(ID24)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(ID24_tree, root_0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "attribute"


    public static class attributeValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "attributeValue"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:65:1: attributeValue : ( LITERAL | ID );
    public final SWTQuerySelectorParser.attributeValue_return attributeValue() throws RecognitionException {
        SWTQuerySelectorParser.attributeValue_return retval = new SWTQuerySelectorParser.attributeValue_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set25=null;

        Object set25_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:65:16: ( LITERAL | ID )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:
            {
            root_0 = (Object)adaptor.nil();


            set25=(Token)input.LT(1);

            if ( (input.LA(1) >= ID && input.LA(1) <= LITERAL) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set25)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "attributeValue"

    // Delegated rules


 

    public static final BitSet FOLLOW_selector_in_selectors242 = new BitSet(new long[]{0x0000000000002812L});
    public static final BitSet FOLLOW_LT_in_selector255 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_set_in_selector258 = new BitSet(new long[]{0x0000000000004102L});
    public static final BitSet FOLLOW_psudo_in_selector265 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_attributes_in_selector268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_idList280 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_idList283 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ID_in_idList285 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_COLON_in_psudo303 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ID_in_psudo306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_psudo311 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_psudo314 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_idList_in_psudo317 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_psudo319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BR_in_attributes331 = new BitSet(new long[]{0x0000000000000C00L});
    public static final BitSet FOLLOW_attribute_in_attributes333 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_19_in_attributes336 = new BitSet(new long[]{0x0000000000000C00L});
    public static final BitSet FOLLOW_attribute_in_attributes338 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_CLOSE_BR_in_attributes342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attribute360 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ATTR_OP_in_attribute362 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_attributeValue_in_attribute365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_attribute371 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ID_in_attribute373 = new BitSet(new long[]{0x0000000000000002L});

}