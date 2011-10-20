// $ANTLR 3.4 C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g 2011-10-20 17:01:15

package kr.or.eclipse.swt.query.parse;


import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class SWTQuerySelectorParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASTERIK", "BSLASH", "CLOSE_BR", "ID", "LITERAL", "LT", "OP", "OPEN_BR", "SQUOTE", "WHITESPACE", "','"
    };

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
        return this.adaptor;
    }
    public String[] getTokenNames() { return SWTQuerySelectorParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g"; }


    public static class selectors_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return this.tree; }
    };


    // $ANTLR start "selectors"
    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:29:1: selectors : selector ( ( LT )? selector )* ;
    public final SWTQuerySelectorParser.selectors_return selectors() throws RecognitionException {
        SWTQuerySelectorParser.selectors_return retval = new SWTQuerySelectorParser.selectors_return();
        retval.start = this.input.LT(1);


        Object root_0 = null;

        Token LT2=null;
        SWTQuerySelectorParser.selector_return selector1 =null;

        SWTQuerySelectorParser.selector_return selector3 =null;


        Object LT2_tree=null;

        try {
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:29:11: ( selector ( ( LT )? selector )* )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:29:13: selector ( ( LT )? selector )*
            {
                root_0 = (Object)this.adaptor.nil();


                pushFollow(FOLLOW_selector_in_selectors188);
                selector1=selector();

                this.state._fsp--;

                this.adaptor.addChild(root_0, selector1.getTree());

                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:29:22: ( ( LT )? selector )*
                loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = this.input.LA(1);

                        if ( LA2_0==ASTERIK||LA2_0==ID||LA2_0==LT ) {
                            alt2=1;
                        }


                        switch (alt2) {
                        case 1 :
                            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:29:23: ( LT )? selector
                        {
                            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:29:23: ( LT )?
                            int alt1=2;
                            int LA1_0 = this.input.LA(1);

                            if ( LA1_0==LT ) {
                                alt1=1;
                            }
                            switch (alt1) {
                            case 1 :
                                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:29:24: LT
                            {
                                LT2=(Token)match(this.input,LT,FOLLOW_LT_in_selectors192);
                                LT2_tree =
                                        (Object)this.adaptor.create(LT2)
                                        ;
                                this.adaptor.addChild(root_0, LT2_tree);


                            }
                            break;

                            }


                            pushFollow(FOLLOW_selector_in_selectors196);
                            selector3=selector();

                            this.state._fsp--;

                            this.adaptor.addChild(root_0, selector3.getTree());

                        }
                        break;

                        default :
                            break loop2;
                        }
                    } while (true);


            }

            retval.stop = this.input.LT(-1);


            retval.tree = (Object)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(this.input,re);
            retval.tree = (Object)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selectors"


    public static class selector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return this.tree; }
    };


    // $ANTLR start "selector"
    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:31:1: selector : ( ID -> ^( ID ) | ID attributes -> ^( ID attributes ) | ASTERIK attributes -> ^( ASTERIK attributes ) );
    public final SWTQuerySelectorParser.selector_return selector() throws RecognitionException {
        SWTQuerySelectorParser.selector_return retval = new SWTQuerySelectorParser.selector_return();
        retval.start = this.input.LT(1);


        Object root_0 = null;

        Token ID4=null;
        Token ID5=null;
        Token ASTERIK7=null;
        SWTQuerySelectorParser.attributes_return attributes6 =null;

        SWTQuerySelectorParser.attributes_return attributes8 =null;


        Object ID4_tree=null;
        Object ID5_tree=null;
        Object ASTERIK7_tree=null;
        RewriteRuleTokenStream stream_ASTERIK=new RewriteRuleTokenStream(this.adaptor,"token ASTERIK");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(this.adaptor,"token ID");
        RewriteRuleSubtreeStream stream_attributes=new RewriteRuleSubtreeStream(this.adaptor,"rule attributes");
        try {
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:31:10: ( ID -> ^( ID ) | ID attributes -> ^( ID attributes ) | ASTERIK attributes -> ^( ASTERIK attributes ) )
            int alt3=3;
            int LA3_0 = this.input.LA(1);

            if ( LA3_0==ID ) {
                int LA3_1 = this.input.LA(2);

                if ( LA3_1==EOF||LA3_1==ASTERIK||LA3_1==ID||LA3_1==LT ) {
                    alt3=1;
                }
                else if ( LA3_1==OPEN_BR ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 3, 1, this.input);

                    throw nvae;

                }
            }
            else if ( LA3_0==ASTERIK ) {
                alt3=3;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 3, 0, this.input);

                throw nvae;

            }
            switch (alt3) {
            case 1 :
                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:32:2: ID
            {
                ID4=(Token)match(this.input,ID,FOLLOW_ID_in_selector209);
                stream_ID.add(ID4);


                // AST REWRITE
                // elements: ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(this.adaptor,"rule retval",retval!=null?retval.tree:null);

                root_0 = (Object)this.adaptor.nil();
                // 32:5: -> ^( ID )
                {
                    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:32:8: ^( ID )
                    {
                        Object root_1 = (Object)this.adaptor.nil();
                        root_1 = (Object)this.adaptor.becomeRoot(
                                stream_ID.nextNode()
                                , root_1);

                        this.adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }
            break;
            case 2 :
                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:33:2: ID attributes
            {
                ID5=(Token)match(this.input,ID,FOLLOW_ID_in_selector221);
                stream_ID.add(ID5);


                pushFollow(FOLLOW_attributes_in_selector223);
                attributes6=attributes();

                this.state._fsp--;

                stream_attributes.add(attributes6.getTree());

                // AST REWRITE
                // elements: ID, attributes
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(this.adaptor,"rule retval",retval!=null?retval.tree:null);

                root_0 = (Object)this.adaptor.nil();
                // 33:16: -> ^( ID attributes )
                {
                    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:33:19: ^( ID attributes )
                    {
                        Object root_1 = (Object)this.adaptor.nil();
                        root_1 = (Object)this.adaptor.becomeRoot(
                                stream_ID.nextNode()
                                , root_1);

                        this.adaptor.addChild(root_1, stream_attributes.nextTree());

                        this.adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }
            break;
            case 3 :
                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:34:2: ASTERIK attributes
            {
                ASTERIK7=(Token)match(this.input,ASTERIK,FOLLOW_ASTERIK_in_selector236);
                stream_ASTERIK.add(ASTERIK7);


                pushFollow(FOLLOW_attributes_in_selector238);
                attributes8=attributes();

                this.state._fsp--;

                stream_attributes.add(attributes8.getTree());

                // AST REWRITE
                // elements: ASTERIK, attributes
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(this.adaptor,"rule retval",retval!=null?retval.tree:null);

                root_0 = (Object)this.adaptor.nil();
                // 34:21: -> ^( ASTERIK attributes )
                {
                    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:34:24: ^( ASTERIK attributes )
                    {
                        Object root_1 = (Object)this.adaptor.nil();
                        root_1 = (Object)this.adaptor.becomeRoot(
                                stream_ASTERIK.nextNode()
                                , root_1);

                        this.adaptor.addChild(root_1, stream_attributes.nextTree());

                        this.adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }
            break;

            }
            retval.stop = this.input.LT(-1);


            retval.tree = (Object)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(this.input,re);
            retval.tree = (Object)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selector"


    public static class attributes_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return this.tree; }
    };


    // $ANTLR start "attributes"
    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:36:1: attributes : OPEN_BR attribute ( ',' attribute )* CLOSE_BR -> ( attribute )+ ;
    public final SWTQuerySelectorParser.attributes_return attributes() throws RecognitionException {
        SWTQuerySelectorParser.attributes_return retval = new SWTQuerySelectorParser.attributes_return();
        retval.start = this.input.LT(1);


        Object root_0 = null;

        Token OPEN_BR9=null;
        Token char_literal11=null;
        Token CLOSE_BR13=null;
        SWTQuerySelectorParser.attribute_return attribute10 =null;

        SWTQuerySelectorParser.attribute_return attribute12 =null;


        Object OPEN_BR9_tree=null;
        Object char_literal11_tree=null;
        Object CLOSE_BR13_tree=null;
        RewriteRuleTokenStream stream_CLOSE_BR=new RewriteRuleTokenStream(this.adaptor,"token CLOSE_BR");
        RewriteRuleTokenStream stream_OPEN_BR=new RewriteRuleTokenStream(this.adaptor,"token OPEN_BR");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(this.adaptor,"token 14");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(this.adaptor,"rule attribute");
        try {
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:36:12: ( OPEN_BR attribute ( ',' attribute )* CLOSE_BR -> ( attribute )+ )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:37:2: OPEN_BR attribute ( ',' attribute )* CLOSE_BR
            {
                OPEN_BR9=(Token)match(this.input,OPEN_BR,FOLLOW_OPEN_BR_in_attributes256);
                stream_OPEN_BR.add(OPEN_BR9);


                pushFollow(FOLLOW_attribute_in_attributes258);
                attribute10=attribute();

                this.state._fsp--;

                stream_attribute.add(attribute10.getTree());

                // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:37:20: ( ',' attribute )*
                loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = this.input.LA(1);

                        if ( LA4_0==14 ) {
                            alt4=1;
                        }


                        switch (alt4) {
                        case 1 :
                            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:37:21: ',' attribute
                        {
                            char_literal11=(Token)match(this.input,14,FOLLOW_14_in_attributes261);
                            stream_14.add(char_literal11);


                            pushFollow(FOLLOW_attribute_in_attributes263);
                            attribute12=attribute();

                            this.state._fsp--;

                            stream_attribute.add(attribute12.getTree());

                        }
                        break;

                        default :
                            break loop4;
                        }
                    } while (true);


                CLOSE_BR13=(Token)match(this.input,CLOSE_BR,FOLLOW_CLOSE_BR_in_attributes267);
                stream_CLOSE_BR.add(CLOSE_BR13);


                // AST REWRITE
                // elements: attribute
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(this.adaptor,"rule retval",retval!=null?retval.tree:null);

                root_0 = (Object)this.adaptor.nil();
                // 37:46: -> ( attribute )+
                {
                    if ( !stream_attribute.hasNext() ) {
                        throw new RewriteEarlyExitException();
                    }
                    while ( stream_attribute.hasNext() ) {
                        this.adaptor.addChild(root_0, stream_attribute.nextTree());

                    }
                    stream_attribute.reset();

                }


                retval.tree = root_0;

            }

            retval.stop = this.input.LT(-1);


            retval.tree = (Object)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(this.input,re);
            retval.tree = (Object)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "attributes"


    public static class attribute_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return this.tree; }
    };


    // $ANTLR start "attribute"
    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:39:1: attribute : ID OP LITERAL -> ^( ID LITERAL ) ;
    public final SWTQuerySelectorParser.attribute_return attribute() throws RecognitionException {
        SWTQuerySelectorParser.attribute_return retval = new SWTQuerySelectorParser.attribute_return();
        retval.start = this.input.LT(1);


        Object root_0 = null;

        Token ID14=null;
        Token OP15=null;
        Token LITERAL16=null;

        Object ID14_tree=null;
        Object OP15_tree=null;
        Object LITERAL16_tree=null;
        RewriteRuleTokenStream stream_LITERAL=new RewriteRuleTokenStream(this.adaptor,"token LITERAL");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(this.adaptor,"token ID");
        RewriteRuleTokenStream stream_OP=new RewriteRuleTokenStream(this.adaptor,"token OP");

        try {
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:39:10: ( ID OP LITERAL -> ^( ID LITERAL ) )
            // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:40:2: ID OP LITERAL
            {
                ID14=(Token)match(this.input,ID,FOLLOW_ID_in_attribute282);
                stream_ID.add(ID14);


                OP15=(Token)match(this.input,OP,FOLLOW_OP_in_attribute284);
                stream_OP.add(OP15);


                LITERAL16=(Token)match(this.input,LITERAL,FOLLOW_LITERAL_in_attribute286);
                stream_LITERAL.add(LITERAL16);


                // AST REWRITE
                // elements: LITERAL, ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(this.adaptor,"rule retval",retval!=null?retval.tree:null);

                root_0 = (Object)this.adaptor.nil();
                // 40:16: -> ^( ID LITERAL )
                {
                    // C:\\Users\\Jeeeyul\\workspaces\\exerd\\kr.or.eclipse.cedt\\src\\kr\\or\\eclipse\\cedt\\swtquery\\parse\\SWTQuerySelector.g:40:19: ^( ID LITERAL )
                    {
                        Object root_1 = (Object)this.adaptor.nil();
                        root_1 = (Object)this.adaptor.becomeRoot(
                                stream_ID.nextNode()
                                , root_1);

                        this.adaptor.addChild(root_1,
                                stream_LITERAL.nextNode()
                                );

                        this.adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }

            retval.stop = this.input.LT(-1);


            retval.tree = (Object)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(this.input,re);
            retval.tree = (Object)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "attribute"

    // Delegated rules




    public static final BitSet FOLLOW_selector_in_selectors188 = new BitSet(new long[]{0x0000000000000292L});
    public static final BitSet FOLLOW_LT_in_selectors192 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_selector_in_selectors196 = new BitSet(new long[]{0x0000000000000292L});
    public static final BitSet FOLLOW_ID_in_selector209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_selector221 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_attributes_in_selector223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERIK_in_selector236 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_attributes_in_selector238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BR_in_attributes256 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_attribute_in_attributes258 = new BitSet(new long[]{0x0000000000004040L});
    public static final BitSet FOLLOW_14_in_attributes261 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_attribute_in_attributes263 = new BitSet(new long[]{0x0000000000004040L});
    public static final BitSet FOLLOW_CLOSE_BR_in_attributes267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attribute282 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_OP_in_attribute284 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_LITERAL_in_attribute286 = new BitSet(new long[]{0x0000000000000002L});

}