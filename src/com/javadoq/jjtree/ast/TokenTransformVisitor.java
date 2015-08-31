
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

import com.javadoq.jjtree.Token;

/**
 * <p>An abstract base class for JJTree visitors to transform JJTree node at token level.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public abstract class TokenTransformVisitor extends TokenVisitor
{
	/**
	 * <p>Visits a token before transformation.</p>
	 * @param t The JJTree token to visit.
	 * @since 1.0
	 */
	protected abstract void start(Token t);
	/**
	 * <p>Visits a token after transformation.</p>
	 * @param t The JJTree token to visit.
	 * @since 1.0
	 */
	protected abstract void close(Token t);
	/**
	 * <p>Transforms a token.</p>
	 * @param t The JJTree token to output.
	 * @since 1.0
	 */
	protected abstract void write(Token t);
	/**
	 * <p>Transforms a string.</p>
	 * @param s The string to output.
	 * @since 1.0
	 */
	protected abstract void write(String s);

	private Token visited = null;

	/**
	 * <p>Visits a token.</p>
	 * @param t The JJTree token to visit.
	 * @since 1.0
	 */
	@Override
	protected void visit(Token t) {
		if (t == visited) {
			return;
		}
		visitSpecial(t.specialToken);
		visitToken(t);
	}

	/**
	 * <p>Transforms a special token.</p>
	 * @param t The JJTree special token to transform.
	 * @since 1.0
	 */
	protected void visitSpecial(Token t) {
		if (t != null) {
			while (t.specialToken != null) {
				t = t.specialToken;
			}
			while (t != null) {
				start(t);
				write(t);
				close(t);
				t = t.next;
			}
		}
	}

	/**
	 * <p>Transforms a token.</p>
	 * @param t The JJTree token to transform.
	 * @since 1.0
	 */
	protected void visitToken(Token t) {
		if (t == visited) {
			return;
		}
		visited = t;
		start(t);
		write(t);
		close(t);
	}
}
