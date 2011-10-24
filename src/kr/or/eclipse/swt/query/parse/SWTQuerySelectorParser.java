// $ANTLR 3.4 P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g 2011-10-24 22:28:29

package kr.or.eclipse.swt.query.parse;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class SWTQuerySelectorParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASTERIK", "ATTR_OP", "BSLASH", "CLOSE_BR", "COLON", "COMMA", "DQUOTE", "EXCLAMATION", "ID", "LITERAL", "LT", "OPEN_BR", "SQUOTE", "WHITESPACE", "'('", "')'"
    };

    public static final int EOF=-1;
    public static final int T__18=18;
    public static final int T__19=19;
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
    public static final int OPEN_BR=15;
    public static final int SQUOTE=16;
    public static final int WHITESPACE=17;

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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:33:1: selectors : selector ( COMMA selector )* ;
    public final SWTQuerySelectorParser.selectors_return selectors() throws RecognitionException {
        SWTQuerySelectorParser.selectors_return retval = new SWTQuerySelectorParser.selectors_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA2=null;
        SWTQuerySelectorParser.selector_return selector1 =null;

        SWTQuerySelectorParser.selector_return selector3 =null;


        Object COMMA2_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:33:11: ( selector ( COMMA selector )* )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:34:2: selector ( COMMA selector )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selector_in_selectors250);
            selector1=selector();

            state._fsp--;

            adaptor.addChild(root_0, selector1.getTree());

            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:34:11: ( COMMA selector )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==COMMA) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:34:12: COMMA selector
            	    {
            	    COMMA2=(Token)match(input,COMMA,FOLLOW_COMMA_in_selectors253); 
            	    COMMA2_tree = 
            	    (Object)adaptor.create(COMMA2)
            	    ;
            	    adaptor.addChild(root_0, COMMA2_tree);


            	    pushFollow(FOLLOW_selector_in_selectors255);
            	    selector3=selector();

            	    state._fsp--;

            	    adaptor.addChild(root_0, selector3.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:36:1: selector : selectorSegment ( selectorSegment )* -> ( selectorSegment )+ ;
    public final SWTQuerySelectorParser.selector_return selector() throws RecognitionException {
        SWTQuerySelectorParser.selector_return retval = new SWTQuerySelectorParser.selector_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        SWTQuerySelectorParser.selectorSegment_return selectorSegment4 =null;

        SWTQuerySelectorParser.selectorSegment_return selectorSegment5 =null;


        RewriteRuleSubtreeStream stream_selectorSegment=new RewriteRuleSubtreeStream(adaptor,"rule selectorSegment");
        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:36:9: ( selectorSegment ( selectorSegment )* -> ( selectorSegment )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:37:2: selectorSegment ( selectorSegment )*
            {
            pushFollow(FOLLOW_selectorSegment_in_selector266);
            selectorSegment4=selectorSegment();

            state._fsp--;

            stream_selectorSegment.add(selectorSegment4.getTree());

            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:37:18: ( selectorSegment )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==ASTERIK||LA2_0==ID||LA2_0==LT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:37:19: selectorSegment
            	    {
            	    pushFollow(FOLLOW_selectorSegment_in_selector269);
            	    selectorSegment5=selectorSegment();

            	    state._fsp--;

            	    stream_selectorSegment.add(selectorSegment5.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            // AST REWRITE
            // elements: selectorSegment
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 37:37: -> ( selectorSegment )+
            {
                if ( !(stream_selectorSegment.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_selectorSegment.hasNext() ) {
                    adaptor.addChild(root_0, stream_selectorSegment.nextTree());

                }
                stream_selectorSegment.reset();

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
    // $ANTLR end "selector"


    public static class selectorSegment_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectorSegment"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:41:1: selectorSegment : ( LT )? ( ID | ASTERIK ) ^ ( psudo )? ( attributes )? ;
    public final SWTQuerySelectorParser.selectorSegment_return selectorSegment() throws RecognitionException {
        SWTQuerySelectorParser.selectorSegment_return retval = new SWTQuerySelectorParser.selectorSegment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LT6=null;
        Token set7=null;
        SWTQuerySelectorParser.psudo_return psudo8 =null;

        SWTQuerySelectorParser.attributes_return attributes9 =null;


        Object LT6_tree=null;
        Object set7_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:41:16: ( ( LT )? ( ID | ASTERIK ) ^ ( psudo )? ( attributes )? )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:42:2: ( LT )? ( ID | ASTERIK ) ^ ( psudo )? ( attributes )?
            {
            root_0 = (Object)adaptor.nil();


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:42:2: ( LT )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LT) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:42:2: LT
                    {
                    LT6=(Token)match(input,LT,FOLLOW_LT_in_selectorSegment289); 
                    LT6_tree = 
                    (Object)adaptor.create(LT6)
                    ;
                    adaptor.addChild(root_0, LT6_tree);


                    }
                    break;

            }


            set7=(Token)input.LT(1);

            set7=(Token)input.LT(1);

            if ( input.LA(1)==ASTERIK||input.LA(1)==ID ) {
                input.consume();
                root_0 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(set7)
                , root_0);
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:42:20: ( psudo )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==COLON) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:42:20: psudo
                    {
                    pushFollow(FOLLOW_psudo_in_selectorSegment299);
                    psudo8=psudo();

                    state._fsp--;

                    adaptor.addChild(root_0, psudo8.getTree());

                    }
                    break;

            }


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:42:27: ( attributes )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==OPEN_BR) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:42:27: attributes
                    {
                    pushFollow(FOLLOW_attributes_in_selectorSegment302);
                    attributes9=attributes();

                    state._fsp--;

                    adaptor.addChild(root_0, attributes9.getTree());

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
    // $ANTLR end "selectorSegment"


    public static class idList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "idList"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:44:1: idList : ID ( ',' ID )* -> ( ID )+ ;
    public final SWTQuerySelectorParser.idList_return idList() throws RecognitionException {
        SWTQuerySelectorParser.idList_return retval = new SWTQuerySelectorParser.idList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID10=null;
        Token char_literal11=null;
        Token ID12=null;

        Object ID10_tree=null;
        Object char_literal11_tree=null;
        Object ID12_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:44:9: ( ID ( ',' ID )* -> ( ID )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:45:2: ID ( ',' ID )*
            {
            ID10=(Token)match(input,ID,FOLLOW_ID_in_idList314);  
            stream_ID.add(ID10);


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:45:5: ( ',' ID )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COMMA) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:45:6: ',' ID
            	    {
            	    char_literal11=(Token)match(input,COMMA,FOLLOW_COMMA_in_idList317);  
            	    stream_COMMA.add(char_literal11);


            	    ID12=(Token)match(input,ID,FOLLOW_ID_in_idList319);  
            	    stream_ID.add(ID12);


            	    }
            	    break;

            	default :
            	    break loop6;
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
            // 45:15: -> ( ID )+
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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:49:1: psudo : ( COLON ^ ID | COLON ^ '(' ! idList ')' !);
    public final SWTQuerySelectorParser.psudo_return psudo() throws RecognitionException {
        SWTQuerySelectorParser.psudo_return retval = new SWTQuerySelectorParser.psudo_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COLON13=null;
        Token ID14=null;
        Token COLON15=null;
        Token char_literal16=null;
        Token char_literal18=null;
        SWTQuerySelectorParser.idList_return idList17 =null;


        Object COLON13_tree=null;
        Object ID14_tree=null;
        Object COLON15_tree=null;
        Object char_literal16_tree=null;
        Object char_literal18_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:49:8: ( COLON ^ ID | COLON ^ '(' ! idList ')' !)
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==COLON) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==ID) ) {
                    alt7=1;
                }
                else if ( (LA7_1==18) ) {
                    alt7=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:50:2: COLON ^ ID
                    {
                    root_0 = (Object)adaptor.nil();


                    COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_psudo337); 
                    COLON13_tree = 
                    (Object)adaptor.create(COLON13)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(COLON13_tree, root_0);


                    ID14=(Token)match(input,ID,FOLLOW_ID_in_psudo340); 
                    ID14_tree = 
                    (Object)adaptor.create(ID14)
                    ;
                    adaptor.addChild(root_0, ID14_tree);


                    }
                    break;
                case 2 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:51:2: COLON ^ '(' ! idList ')' !
                    {
                    root_0 = (Object)adaptor.nil();


                    COLON15=(Token)match(input,COLON,FOLLOW_COLON_in_psudo345); 
                    COLON15_tree = 
                    (Object)adaptor.create(COLON15)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(COLON15_tree, root_0);


                    char_literal16=(Token)match(input,18,FOLLOW_18_in_psudo348); 

                    pushFollow(FOLLOW_idList_in_psudo351);
                    idList17=idList();

                    state._fsp--;

                    adaptor.addChild(root_0, idList17.getTree());

                    char_literal18=(Token)match(input,19,FOLLOW_19_in_psudo353); 

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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:56:1: attributes : OPEN_BR attribute ( ',' attribute )* CLOSE_BR -> ( attribute )+ ;
    public final SWTQuerySelectorParser.attributes_return attributes() throws RecognitionException {
        SWTQuerySelectorParser.attributes_return retval = new SWTQuerySelectorParser.attributes_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token OPEN_BR19=null;
        Token char_literal21=null;
        Token CLOSE_BR23=null;
        SWTQuerySelectorParser.attribute_return attribute20 =null;

        SWTQuerySelectorParser.attribute_return attribute22 =null;


        Object OPEN_BR19_tree=null;
        Object char_literal21_tree=null;
        Object CLOSE_BR23_tree=null;
        RewriteRuleTokenStream stream_CLOSE_BR=new RewriteRuleTokenStream(adaptor,"token CLOSE_BR");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_OPEN_BR=new RewriteRuleTokenStream(adaptor,"token OPEN_BR");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:56:12: ( OPEN_BR attribute ( ',' attribute )* CLOSE_BR -> ( attribute )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:57:2: OPEN_BR attribute ( ',' attribute )* CLOSE_BR
            {
            OPEN_BR19=(Token)match(input,OPEN_BR,FOLLOW_OPEN_BR_in_attributes365);  
            stream_OPEN_BR.add(OPEN_BR19);


            pushFollow(FOLLOW_attribute_in_attributes367);
            attribute20=attribute();

            state._fsp--;

            stream_attribute.add(attribute20.getTree());

            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:57:20: ( ',' attribute )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==COMMA) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:57:21: ',' attribute
            	    {
            	    char_literal21=(Token)match(input,COMMA,FOLLOW_COMMA_in_attributes370);  
            	    stream_COMMA.add(char_literal21);


            	    pushFollow(FOLLOW_attribute_in_attributes372);
            	    attribute22=attribute();

            	    state._fsp--;

            	    stream_attribute.add(attribute22.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            CLOSE_BR23=(Token)match(input,CLOSE_BR,FOLLOW_CLOSE_BR_in_attributes376);  
            stream_CLOSE_BR.add(CLOSE_BR23);


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
            // 57:46: -> ( attribute )+
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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:62:1: attribute : ( ID ATTR_OP ^ ( attributeValue )? | EXCLAMATION ID ^);
    public final SWTQuerySelectorParser.attribute_return attribute() throws RecognitionException {
        SWTQuerySelectorParser.attribute_return retval = new SWTQuerySelectorParser.attribute_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID24=null;
        Token ATTR_OP25=null;
        Token EXCLAMATION27=null;
        Token ID28=null;
        SWTQuerySelectorParser.attributeValue_return attributeValue26 =null;


        Object ID24_tree=null;
        Object ATTR_OP25_tree=null;
        Object EXCLAMATION27_tree=null;
        Object ID28_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:62:11: ( ID ATTR_OP ^ ( attributeValue )? | EXCLAMATION ID ^)
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==ID) ) {
                alt10=1;
            }
            else if ( (LA10_0==EXCLAMATION) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:63:2: ID ATTR_OP ^ ( attributeValue )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID24=(Token)match(input,ID,FOLLOW_ID_in_attribute394); 
                    ID24_tree = 
                    (Object)adaptor.create(ID24)
                    ;
                    adaptor.addChild(root_0, ID24_tree);


                    ATTR_OP25=(Token)match(input,ATTR_OP,FOLLOW_ATTR_OP_in_attribute396); 
                    ATTR_OP25_tree = 
                    (Object)adaptor.create(ATTR_OP25)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(ATTR_OP25_tree, root_0);


                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:63:14: ( attributeValue )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0 >= ID && LA9_0 <= LITERAL)) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:63:14: attributeValue
                            {
                            pushFollow(FOLLOW_attributeValue_in_attribute399);
                            attributeValue26=attributeValue();

                            state._fsp--;

                            adaptor.addChild(root_0, attributeValue26.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:64:2: EXCLAMATION ID ^
                    {
                    root_0 = (Object)adaptor.nil();


                    EXCLAMATION27=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_attribute405); 
                    EXCLAMATION27_tree = 
                    (Object)adaptor.create(EXCLAMATION27)
                    ;
                    adaptor.addChild(root_0, EXCLAMATION27_tree);


                    ID28=(Token)match(input,ID,FOLLOW_ID_in_attribute407); 
                    ID28_tree = 
                    (Object)adaptor.create(ID28)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(ID28_tree, root_0);


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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:69:1: attributeValue : ( LITERAL | ID );
    public final SWTQuerySelectorParser.attributeValue_return attributeValue() throws RecognitionException {
        SWTQuerySelectorParser.attributeValue_return retval = new SWTQuerySelectorParser.attributeValue_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set29=null;

        Object set29_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:69:16: ( LITERAL | ID )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\parse\\SWTQuerySelector.g:
            {
            root_0 = (Object)adaptor.nil();


            set29=(Token)input.LT(1);

            if ( (input.LA(1) >= ID && input.LA(1) <= LITERAL) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set29)
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


 

    public static final BitSet FOLLOW_selector_in_selectors250 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_COMMA_in_selectors253 = new BitSet(new long[]{0x0000000000005010L});
    public static final BitSet FOLLOW_selector_in_selectors255 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_selectorSegment_in_selector266 = new BitSet(new long[]{0x0000000000005012L});
    public static final BitSet FOLLOW_selectorSegment_in_selector269 = new BitSet(new long[]{0x0000000000005012L});
    public static final BitSet FOLLOW_LT_in_selectorSegment289 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_set_in_selectorSegment292 = new BitSet(new long[]{0x0000000000008102L});
    public static final BitSet FOLLOW_psudo_in_selectorSegment299 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_attributes_in_selectorSegment302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_idList314 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_COMMA_in_idList317 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_idList319 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_COLON_in_psudo337 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_psudo340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_psudo345 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_psudo348 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_idList_in_psudo351 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_psudo353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BR_in_attributes365 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_attribute_in_attributes367 = new BitSet(new long[]{0x0000000000000280L});
    public static final BitSet FOLLOW_COMMA_in_attributes370 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_attribute_in_attributes372 = new BitSet(new long[]{0x0000000000000280L});
    public static final BitSet FOLLOW_CLOSE_BR_in_attributes376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attribute394 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ATTR_OP_in_attribute396 = new BitSet(new long[]{0x0000000000003002L});
    public static final BitSet FOLLOW_attributeValue_in_attribute399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_attribute405 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_attribute407 = new BitSet(new long[]{0x0000000000000002L});

}