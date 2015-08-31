
/*
 *  JavaDoq 1.0 - DOCUment JAVA In Source
 *  Copyright (C) 2008-2011  J.J.Liu<jianjunliu@126.com> <http://www.javadoq.com>
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.javadoq.jjtree.ast;

import com.javadoq.jjtree.JJTreeParserConstants;
import com.javadoq.jjtree.Token;

/**
 * <p>An abstract base class for JJTree visitors to colorize JJTree node at token level.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public abstract class HTMLColorizeVisitor extends TokenTransformVisitor implements JJTreeParserConstants
{
	/**
	 * <p>Visits a token before transformation.</p>
	 * @param t The JJTree token to visit.
	 * @since 1.0
	 */
	@Override
	protected void start(Token t) {
		switch (t.kind) {
			case ABSTRACT:
				write("<font id=\"abstract\">");
				break;
			case BOOLEAN:
				write("<font id=\"boolean\">");
				break;
			case BREAK:
				write("<font id=\"break\">");
				break;
			case BYTE:
				write("<font id=\"byte\">");
				break;
			case CASE:
				write("<font id=\"case\">");
				break;
			case CATCH:
				write("<font id=\"catch\">");
				break;
			case CHAR:
				write("<font id=\"char\">");
				break;
			case CLASS:
				write("<font id=\"class\">");
				break;
			case CONST:
				write("<font id=\"const\">");
				break;
			case CONTINUE:
				write("<font id=\"continue\">");
				break;
			case _DEFAULT:
				write("<font id=\"default\">");
				break;
			case DO:
				write("<font id=\"do\">");
				break;
			case DOUBLE:
				write("<font id=\"double\">");
				break;
			case ELSE:
				write("<font id=\"else\">");
				break;
			case EXTENDS:
				write("<font id=\"extends\">");
				break;
			case FALSE:
				write("<font id=\"false\">");
				break;
			case FINAL:
				write("<font id=\"final\">");
				break;
			case FINALLY:
				write("<font id=\"finally\">");
				break;
			case FLOAT:
				write("<font id=\"float\">");
				break;
			case FOR:
				write("<font id=\"for\">");
				break;
			case GOTO:
				write("<font id=\"goto\">");
				break;
			case IF:
				write("<font id=\"if\">");
				break;
			case IMPLEMENTS:
				write("<font id=\"implements\">");
				break;
			case IMPORT:
				write("<font id=\"import\">");
				break;
			case INSTANCEOF:
				write("<font id=\"instanceof\">");
				break;
			case INT:
				write("<font id=\"int\">");
				break;
			case INTERFACE:
				write("<font id=\"interface\">");
				break;
			case LONG:
				write("<font id=\"long\">");
				break;
			case NATIVE:
				write("<font id=\"native\">");
				break;
			case NEW:
				write("<font id=\"new\">");
				break;
			case NULL:
				write("<font id=\"null\">");
				break;
			case PACKAGE:
				write("<font id=\"package\">");
				break;
			case PRIVATE:
				write("<font id=\"private\">");
				break;
			case PROTECTED:
				write("<font id=\"protected\">");
				break;
			case PUBLIC:
				write("<font id=\"public\">");
				break;
			case RETURN:
				write("<font id=\"return\">");
				break;
			case SHORT:
				write("<font id=\"short\">");
				break;
			case STATIC:
				write("<font id=\"static\">");
				break;
			case STRICTFP:
				write("<font id=\"strictfp\">");
				break;
			case SUPER:
				write("<font id=\"super\">");
				break;
			case SWITCH:
				write("<font id=\"switch\">");
				break;
			case SYNCHRONIZED:
				write("<font id=\"synchronized\">");
				break;
			case THIS:
				write("<font id=\"this\">");
				break;
			case THROW:
				write("<font id=\"throw\">");
				break;
			case THROWS:
				write("<font id=\"throws\">");
				break;
			case TRANSIENT:
				write("<font id=\"transient\">");
				break;
			case TRUE:
				write("<font id=\"true\">");
				break;
			case TRY:
				write("<font id=\"try\">");
				break;
			case VOID:
				write("<font id=\"void\">");
				break;
			case VOLATILE:
				write("<font id=\"volatile\">");
				break;
			case WHILE:
				write("<font id=\"while\">");
				break;
			case CHARACTER_LITERAL:
				write("<font id=\"characer-literal\">");
				break;
			case DECIMAL_LITERAL:
				write("<font id=\"decimal-literal\">");
				break;
			case FLOATING_POINT_LITERAL:
				write("<font id=\"floating-point-literal\">");
				break;
			case DECIMAL_FLOATING_POINT_LITERAL:
				write("<font id=\"decimal-floating-point-literal\">");
				break;
			case DECIMAL_EXPONENT:
				write("<font id=\"decimal-exponent\">");
				break;
			case HEXADECIMAL_FLOATING_POINT_LITERAL:
				write("<font id=\"hexa-decimal-floating-point-literal\">");
				break;
			case HEXADECIMAL_EXPONENT:
				write("<font id=\"hexa-decimal-exponent\">");
				break;
			case HEX_LITERAL:
				write("<font id=\"hex-literal\">");
				break;
			case INTEGER_LITERAL:
				write("<font id=\"integer-literal\">");
				break;
			case OCTAL_LITERAL:
				write("<font id=\"octal-literal\">");
				break;
			case STRING_LITERAL:
				write("<font id=\"string-literal\">");
				break;
			case SINGLE_LINE_COMMENT:
				write("<font id=\"single-line-comment\">");
				break;
			case FORMAL_COMMENT:
				write("<font id=\"formal-comment\">");
				break;
			case MULTI_LINE_COMMENT:
				write("<font id=\"multi-line-comment\">");
				break;
			case _LOOKAHEAD:
				write("<font id=\"lookahead\">");
				break;
			case _IGNORE_CASE:
				write("<font id=\"ignore-case\">");
				break;
			case _PARSER_BEGIN:
				write("<font id=\"parser-begin\">");
				break;
			case _PARSER_END:
				write("<font id=\"parser-end\">");
				break;
			case _JAVACODE:
				write("<font id=\"javacode\">");
				break;
			case _TOKEN:
				write("<font id=\"token\">");
				break;
			case _SPECIAL_TOKEN:
				write("<font id=\"special-token\">");
				break;
			case _MORE:
				write("<font id=\"more\">");
				break;
			case _SKIP:
				write("<font id=\"skip\">");
				break;
			case _TOKEN_MGR_DECLS:
				write("<font id=\"token-mgr-decls\">");
				break;
			case _EOF:
				write("<font id=\"eof\">");
				break;
			case LPAREN:
				write("<font id=\"left-paren\">");
				break;
			case RPAREN:
				write("<font id=\"right-paren\">");
				break;
			case LBRACE:
				write("<font id=\"left-brace\">");
				break;
			case RBRACE:
				write("<font id=\"right-brace\">");
				break;
			case LBRACKET:
				write("<font id=\"left-bracket\">");
				break;
			case RBRACKET:
				write("<font id=\"right-bracket\">");
				break;
			case SEMICOLON:
				write("<font id=\"semi-colon\">");
				break;
			case COMMA:
				write("<font id=\"comma\">");
				break;
			case DOT:
				write("<font id=\"dot\">");
				break;
			case AT:
				write("<font id=\"at\">");
				break;
			case ASSIGN:
				write("<font id=\"assign\">");
				break;
			case LT:
				write("<font id=\"lt\">");
				break;
			case BANG:
				write("<font id=\"bang\">");
				break;
			case TILDE:
				write("<font id=\"tilde\">");
				break;
			case HOOK:
				write("<font id=\"hook\">");
				break;
			case COLON:
				write("<font id=\"colon\">");
				break;
			case EQ:
				write("<font id=\"eq\">");
				break;
			case LE:
				write("<font id=\"le\">");
				break;
			case GE:
				write("<font id=\"ge\">");
				break;
			case NE:
				write("<font id=\"ne\">");
				break;
			case SC_OR:
				write("<font id=\"sc-or\">");
				break;
			case SC_AND:
				write("<font id=\"sc-and\">");
				break;
			case INCR:
				write("<font id=\"incr\">");
				break;
			case DECR:
				write("<font id=\"decr\">");
				break;
			case PLUS:
				write("<font id=\"plus\">");
				break;
			case MINUS:
				write("<font id=\"minus\">");
				break;
			case STAR:
				write("<font id=\"star\">");
				break;
			case SLASH:
				write("<font id=\"slash\">");
				break;
			case BIT_AND:
				write("<font id=\"bit-and\">");
				break;
			case BIT_OR:
				write("<font id=\"bit-or\">");
				break;
			case XOR:
				write("<font id=\"xor\">");
				break;
			case REM:
				write("<font id=\"rem\">");
				break;
			case PLUSASSIGN:
				write("<font id=\"plus-assign\">");
				break;
			case MINUSASSIGN:
				write("<font id=\"minus-assign\">");
				break;
			case STARASSIGN:
				write("<font id=\"star-assign\">");
				break;
			case SLASHASSIGN:
				write("<font id=\"slash-assign\">");
				break;
			case ANDASSIGN:
				write("<font id=\"and-assign\">");
				break;
			case ORASSIGN:
				write("<font id=\"or-assign\">");
				break;
			case XORASSIGN:
				write("<font id=\"xor-assign\">");
				break;
			case REMASSIGN:
				write("<font id=\"rem-assign\">");
				break;
			case ELLIPSIS:
				write("<font id=\"ellipsis\">");
				break;
			case RUNSIGNEDSHIFT:
				write("<font id=\"right-unsigned-shift\">");
				break;
			case RSIGNEDSHIFT:
				write("<font id=\"right-signed-shift\">");
				break;
			case GT:
				write("<font id=\"gt\">");
				break;
			case IDENTIFIER:
				write("<font id=\"identifier\">");
				break;
			case LETTER:
				write("<font id=\"letter\">");
				break;
			case PART_LETTER:
				write("<font id=\"part-letter\">");
				break;
			default:
				break;
		}
	}

	/**
	 * <p>Visits a token after transformation.</p>
	 * @param t The JJTree token to visit.
	 * @since 1.0
	 */
	@Override
	protected void close(Token t) {
		switch (t.kind) {
			case ABSTRACT:
			case BOOLEAN:
			case BREAK:
			case BYTE:
			case CASE:
			case CATCH:
			case CHAR:
			case CLASS:
			case CONST:
			case CONTINUE:
			case _DEFAULT:
			case DO:
			case DOUBLE:
			case ELSE:
			case EXTENDS:
			case FALSE:
			case FINAL:
			case FINALLY:
			case FLOAT:
			case FOR:
			case GOTO:
			case IF:
			case IMPLEMENTS:
			case IMPORT:
			case INSTANCEOF:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case NEW:
			case NULL:
			case PACKAGE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case RETURN:
			case SHORT:
			case STATIC:
			case SUPER:
			case SWITCH:
			case SYNCHRONIZED:
			case THIS:
			case THROW:
			case THROWS:
			case TRANSIENT:
			case TRUE:
			case TRY:
			case VOID:
			case VOLATILE:
			case WHILE:
			case STRICTFP:
			case DECIMAL_LITERAL:
			case CHARACTER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case DECIMAL_FLOATING_POINT_LITERAL:
			case DECIMAL_EXPONENT:
			case HEXADECIMAL_FLOATING_POINT_LITERAL:
			case HEXADECIMAL_EXPONENT:
			case HEX_LITERAL:
			case INTEGER_LITERAL:
			case OCTAL_LITERAL:
			case STRING_LITERAL:
			case SINGLE_LINE_COMMENT:
			case FORMAL_COMMENT:
			case MULTI_LINE_COMMENT:
			case _LOOKAHEAD:
			case _IGNORE_CASE:
			case _PARSER_BEGIN:
			case _PARSER_END:
			case _JAVACODE:
			case _TOKEN:
			case _SPECIAL_TOKEN:
			case _MORE:
			case _SKIP:
			case _TOKEN_MGR_DECLS:
			case _EOF:
			case LPAREN:
			case RPAREN:
			case LBRACE:
			case RBRACE:
			case LBRACKET:
			case RBRACKET:
			case SEMICOLON:
			case COMMA:
			case DOT:
			case AT:
			case ASSIGN:
			case LT:
			case BANG:
			case TILDE:
			case HOOK:
			case COLON:
			case EQ:
			case LE:
			case GE:
			case NE:
			case SC_OR:
			case SC_AND:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS:
			case STAR:
			case SLASH:
			case BIT_AND:
			case BIT_OR:
			case XOR:
			case REM:
			case PLUSASSIGN:
			case MINUSASSIGN:
			case STARASSIGN:
			case SLASHASSIGN:
			case ANDASSIGN:
			case ORASSIGN:
			case XORASSIGN:
			case REMASSIGN:
			case ELLIPSIS:
			case RUNSIGNEDSHIFT:
			case RSIGNEDSHIFT:
			case GT:
			case IDENTIFIER:
			case LETTER:
			case PART_LETTER:
				write("</font>");
			default:
				break;
		}
	}
}
