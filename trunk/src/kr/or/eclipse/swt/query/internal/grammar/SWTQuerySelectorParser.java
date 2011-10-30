// $ANTLR 3.4 P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g 2011-10-31 00:59:35

package kr.or.eclipse.swt.query.internal.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class SWTQuerySelectorParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASTERIK", "ATTR_OP", "BSLASH", "CLOSE_BR", "COLON", "COMMA", "DQUOTE", "EXCLAMATION", "ID", "LITERAL", "LT", "NUMBER", "OPEN_BR", "SQUOTE", "WHITESPACE", "'('", "')'"
    };

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
    public String getGrammarFileName() { return "P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g"; }


    public static class selectors_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectors"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:19:1: selectors : selector ( COMMA selector )* ;
    public final SWTQuerySelectorParser.selectors_return selectors() throws RecognitionException {
        SWTQuerySelectorParser.selectors_return retval = new SWTQuerySelectorParser.selectors_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA2=null;
        SWTQuerySelectorParser.selector_return selector1 =null;

        SWTQuerySelectorParser.selector_return selector3 =null;


        Object COMMA2_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:19:11: ( selector ( COMMA selector )* )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:20:3: selector ( COMMA selector )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selector_in_selectors47);
            selector1=selector();

            state._fsp--;

            adaptor.addChild(root_0, selector1.getTree());

            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:20:12: ( COMMA selector )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==COMMA) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:20:13: COMMA selector
            	    {
            	    COMMA2=(Token)match(input,COMMA,FOLLOW_COMMA_in_selectors50); 
            	    COMMA2_tree = 
            	    (Object)adaptor.create(COMMA2)
            	    ;
            	    adaptor.addChild(root_0, COMMA2_tree);


            	    pushFollow(FOLLOW_selector_in_selectors52);
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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:22:1: selector : selectorSegment ( selectorSegment )* -> ( selectorSegment )+ ;
    public final SWTQuerySelectorParser.selector_return selector() throws RecognitionException {
        SWTQuerySelectorParser.selector_return retval = new SWTQuerySelectorParser.selector_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        SWTQuerySelectorParser.selectorSegment_return selectorSegment4 =null;

        SWTQuerySelectorParser.selectorSegment_return selectorSegment5 =null;


        RewriteRuleSubtreeStream stream_selectorSegment=new RewriteRuleSubtreeStream(adaptor,"rule selectorSegment");
        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:22:10: ( selectorSegment ( selectorSegment )* -> ( selectorSegment )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:23:3: selectorSegment ( selectorSegment )*
            {
            pushFollow(FOLLOW_selectorSegment_in_selector64);
            selectorSegment4=selectorSegment();

            state._fsp--;

            stream_selectorSegment.add(selectorSegment4.getTree());

            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:23:19: ( selectorSegment )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==ASTERIK||LA2_0==ID||LA2_0==LT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:23:20: selectorSegment
            	    {
            	    pushFollow(FOLLOW_selectorSegment_in_selector67);
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
            // 24:5: -> ( selectorSegment )+
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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:28:1: selectorSegment : ( LT )? ( ID | ASTERIK ) ^ ( styleFilter )? ( attributeFilters )? ;
    public final SWTQuerySelectorParser.selectorSegment_return selectorSegment() throws RecognitionException {
        SWTQuerySelectorParser.selectorSegment_return retval = new SWTQuerySelectorParser.selectorSegment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LT6=null;
        Token set7=null;
        SWTQuerySelectorParser.styleFilter_return styleFilter8 =null;

        SWTQuerySelectorParser.attributeFilters_return attributeFilters9 =null;


        Object LT6_tree=null;
        Object set7_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:28:17: ( ( LT )? ( ID | ASTERIK ) ^ ( styleFilter )? ( attributeFilters )? )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:29:3: ( LT )? ( ID | ASTERIK ) ^ ( styleFilter )? ( attributeFilters )?
            {
            root_0 = (Object)adaptor.nil();


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:29:3: ( LT )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LT) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:29:3: LT
                    {
                    LT6=(Token)match(input,LT,FOLLOW_LT_in_selectorSegment90); 
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


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:34:3: ( styleFilter )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==COLON) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:34:3: styleFilter
                    {
                    pushFollow(FOLLOW_styleFilter_in_selectorSegment118);
                    styleFilter8=styleFilter();

                    state._fsp--;

                    adaptor.addChild(root_0, styleFilter8.getTree());

                    }
                    break;

            }


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:34:16: ( attributeFilters )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==OPEN_BR) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:34:16: attributeFilters
                    {
                    pushFollow(FOLLOW_attributeFilters_in_selectorSegment121);
                    attributeFilters9=attributeFilters();

                    state._fsp--;

                    adaptor.addChild(root_0, attributeFilters9.getTree());

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
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:36:1: idList : ID ( ',' ID )* -> ( ID )+ ;
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
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:36:8: ( ID ( ',' ID )* -> ( ID )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:37:3: ID ( ',' ID )*
            {
            ID10=(Token)match(input,ID,FOLLOW_ID_in_idList132);  
            stream_ID.add(ID10);


            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:37:6: ( ',' ID )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COMMA) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:37:7: ',' ID
            	    {
            	    char_literal11=(Token)match(input,COMMA,FOLLOW_COMMA_in_idList135);  
            	    stream_COMMA.add(char_literal11);


            	    ID12=(Token)match(input,ID,FOLLOW_ID_in_idList137);  
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
            // 38:5: -> ( ID )+
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


    public static class styleFilter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "styleFilter"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:40:1: styleFilter : ( COLON ^ ID | COLON ^ '(' ! idList ')' !);
    public final SWTQuerySelectorParser.styleFilter_return styleFilter() throws RecognitionException {
        SWTQuerySelectorParser.styleFilter_return retval = new SWTQuerySelectorParser.styleFilter_return();
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
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:40:13: ( COLON ^ ID | COLON ^ '(' ! idList ')' !)
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==COLON) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==ID) ) {
                    alt7=1;
                }
                else if ( (LA7_1==19) ) {
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
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:41:3: COLON ^ ID
                    {
                    root_0 = (Object)adaptor.nil();


                    COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_styleFilter158); 
                    COLON13_tree = 
                    (Object)adaptor.create(COLON13)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(COLON13_tree, root_0);


                    ID14=(Token)match(input,ID,FOLLOW_ID_in_styleFilter161); 
                    ID14_tree = 
                    (Object)adaptor.create(ID14)
                    ;
                    adaptor.addChild(root_0, ID14_tree);


                    }
                    break;
                case 2 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:42:5: COLON ^ '(' ! idList ')' !
                    {
                    root_0 = (Object)adaptor.nil();


                    COLON15=(Token)match(input,COLON,FOLLOW_COLON_in_styleFilter167); 
                    COLON15_tree = 
                    (Object)adaptor.create(COLON15)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(COLON15_tree, root_0);


                    char_literal16=(Token)match(input,19,FOLLOW_19_in_styleFilter170); 

                    pushFollow(FOLLOW_idList_in_styleFilter173);
                    idList17=idList();

                    state._fsp--;

                    adaptor.addChild(root_0, idList17.getTree());

                    char_literal18=(Token)match(input,20,FOLLOW_20_in_styleFilter175); 

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
    // $ANTLR end "styleFilter"


    public static class attributeFilters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "attributeFilters"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:44:1: attributeFilters : OPEN_BR attributeFilter ( ',' attributeFilter )* CLOSE_BR -> ( attributeFilter )+ ;
    public final SWTQuerySelectorParser.attributeFilters_return attributeFilters() throws RecognitionException {
        SWTQuerySelectorParser.attributeFilters_return retval = new SWTQuerySelectorParser.attributeFilters_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token OPEN_BR19=null;
        Token char_literal21=null;
        Token CLOSE_BR23=null;
        SWTQuerySelectorParser.attributeFilter_return attributeFilter20 =null;

        SWTQuerySelectorParser.attributeFilter_return attributeFilter22 =null;


        Object OPEN_BR19_tree=null;
        Object char_literal21_tree=null;
        Object CLOSE_BR23_tree=null;
        RewriteRuleTokenStream stream_CLOSE_BR=new RewriteRuleTokenStream(adaptor,"token CLOSE_BR");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_OPEN_BR=new RewriteRuleTokenStream(adaptor,"token OPEN_BR");
        RewriteRuleSubtreeStream stream_attributeFilter=new RewriteRuleSubtreeStream(adaptor,"rule attributeFilter");
        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:44:18: ( OPEN_BR attributeFilter ( ',' attributeFilter )* CLOSE_BR -> ( attributeFilter )+ )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:45:3: OPEN_BR attributeFilter ( ',' attributeFilter )* CLOSE_BR
            {
            OPEN_BR19=(Token)match(input,OPEN_BR,FOLLOW_OPEN_BR_in_attributeFilters186);  
            stream_OPEN_BR.add(OPEN_BR19);


            pushFollow(FOLLOW_attributeFilter_in_attributeFilters188);
            attributeFilter20=attributeFilter();

            state._fsp--;

            stream_attributeFilter.add(attributeFilter20.getTree());

            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:45:27: ( ',' attributeFilter )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==COMMA) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:45:28: ',' attributeFilter
            	    {
            	    char_literal21=(Token)match(input,COMMA,FOLLOW_COMMA_in_attributeFilters191);  
            	    stream_COMMA.add(char_literal21);


            	    pushFollow(FOLLOW_attributeFilter_in_attributeFilters193);
            	    attributeFilter22=attributeFilter();

            	    state._fsp--;

            	    stream_attributeFilter.add(attributeFilter22.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            CLOSE_BR23=(Token)match(input,CLOSE_BR,FOLLOW_CLOSE_BR_in_attributeFilters197);  
            stream_CLOSE_BR.add(CLOSE_BR23);


            // AST REWRITE
            // elements: attributeFilter
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 46:5: -> ( attributeFilter )+
            {
                if ( !(stream_attributeFilter.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_attributeFilter.hasNext() ) {
                    adaptor.addChild(root_0, stream_attributeFilter.nextTree());

                }
                stream_attributeFilter.reset();

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
    // $ANTLR end "attributeFilters"


    public static class attributeFilter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "attributeFilter"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:48:1: attributeFilter : ( ID ATTR_OP ^ ( operand )? | EXCLAMATION ^ ID | ID ^);
    public final SWTQuerySelectorParser.attributeFilter_return attributeFilter() throws RecognitionException {
        SWTQuerySelectorParser.attributeFilter_return retval = new SWTQuerySelectorParser.attributeFilter_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID24=null;
        Token ATTR_OP25=null;
        Token EXCLAMATION27=null;
        Token ID28=null;
        Token ID29=null;
        SWTQuerySelectorParser.operand_return operand26 =null;


        Object ID24_tree=null;
        Object ATTR_OP25_tree=null;
        Object EXCLAMATION27_tree=null;
        Object ID28_tree=null;
        Object ID29_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:48:17: ( ID ATTR_OP ^ ( operand )? | EXCLAMATION ^ ID | ID ^)
            int alt10=3;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==ID) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==ATTR_OP) ) {
                    alt10=1;
                }
                else if ( (LA10_1==CLOSE_BR||LA10_1==COMMA) ) {
                    alt10=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;

                }
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
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:49:3: ID ATTR_OP ^ ( operand )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID24=(Token)match(input,ID,FOLLOW_ID_in_attributeFilter216); 
                    ID24_tree = 
                    (Object)adaptor.create(ID24)
                    ;
                    adaptor.addChild(root_0, ID24_tree);


                    ATTR_OP25=(Token)match(input,ATTR_OP,FOLLOW_ATTR_OP_in_attributeFilter218); 
                    ATTR_OP25_tree = 
                    (Object)adaptor.create(ATTR_OP25)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(ATTR_OP25_tree, root_0);


                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:49:15: ( operand )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0 >= ID && LA9_0 <= LITERAL)||LA9_0==NUMBER) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:49:15: operand
                            {
                            pushFollow(FOLLOW_operand_in_attributeFilter221);
                            operand26=operand();

                            state._fsp--;

                            adaptor.addChild(root_0, operand26.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:50:5: EXCLAMATION ^ ID
                    {
                    root_0 = (Object)adaptor.nil();


                    EXCLAMATION27=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_attributeFilter228); 
                    EXCLAMATION27_tree = 
                    (Object)adaptor.create(EXCLAMATION27)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(EXCLAMATION27_tree, root_0);


                    ID28=(Token)match(input,ID,FOLLOW_ID_in_attributeFilter231); 
                    ID28_tree = 
                    (Object)adaptor.create(ID28)
                    ;
                    adaptor.addChild(root_0, ID28_tree);


                    }
                    break;
                case 3 :
                    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:51:5: ID ^
                    {
                    root_0 = (Object)adaptor.nil();


                    ID29=(Token)match(input,ID,FOLLOW_ID_in_attributeFilter237); 
                    ID29_tree = 
                    (Object)adaptor.create(ID29)
                    ;
                    root_0 = (Object)adaptor.becomeRoot(ID29_tree, root_0);


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
    // $ANTLR end "attributeFilter"


    public static class operand_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operand"
    // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:53:1: operand : ( LITERAL | ID | NUMBER );
    public final SWTQuerySelectorParser.operand_return operand() throws RecognitionException {
        SWTQuerySelectorParser.operand_return retval = new SWTQuerySelectorParser.operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set30=null;

        Object set30_tree=null;

        try {
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:53:9: ( LITERAL | ID | NUMBER )
            // P:\\Jeeeyul\\workspaces\\cba\\kr.or.eclipse.swt.query\\src\\kr\\or\\eclipse\\swt\\query\\internal\\grammar\\SWTQuerySelector.g:
            {
            root_0 = (Object)adaptor.nil();


            set30=(Token)input.LT(1);

            if ( (input.LA(1) >= ID && input.LA(1) <= LITERAL)||input.LA(1)==NUMBER ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set30)
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
    // $ANTLR end "operand"

    // Delegated rules


 

    public static final BitSet FOLLOW_selector_in_selectors47 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_COMMA_in_selectors50 = new BitSet(new long[]{0x0000000000005010L});
    public static final BitSet FOLLOW_selector_in_selectors52 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_selectorSegment_in_selector64 = new BitSet(new long[]{0x0000000000005012L});
    public static final BitSet FOLLOW_selectorSegment_in_selector67 = new BitSet(new long[]{0x0000000000005012L});
    public static final BitSet FOLLOW_LT_in_selectorSegment90 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_set_in_selectorSegment95 = new BitSet(new long[]{0x0000000000010102L});
    public static final BitSet FOLLOW_styleFilter_in_selectorSegment118 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_attributeFilters_in_selectorSegment121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_idList132 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_COMMA_in_idList135 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_idList137 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_COLON_in_styleFilter158 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_styleFilter161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_styleFilter167 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_styleFilter170 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_idList_in_styleFilter173 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_styleFilter175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BR_in_attributeFilters186 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_attributeFilter_in_attributeFilters188 = new BitSet(new long[]{0x0000000000000280L});
    public static final BitSet FOLLOW_COMMA_in_attributeFilters191 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_attributeFilter_in_attributeFilters193 = new BitSet(new long[]{0x0000000000000280L});
    public static final BitSet FOLLOW_CLOSE_BR_in_attributeFilters197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attributeFilter216 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ATTR_OP_in_attributeFilter218 = new BitSet(new long[]{0x000000000000B002L});
    public static final BitSet FOLLOW_operand_in_attributeFilter221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_attributeFilter228 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_attributeFilter231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attributeFilter237 = new BitSet(new long[]{0x0000000000000002L});

}