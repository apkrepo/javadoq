
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

package com.javadoq.jjtree;

/**
 * <p>An abstract base class for JJTree nodes.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public abstract class ASTNode extends SimpleNode
{
	/**
	 * <p>Constructs a {@link ASTNode} without a JJTreeParser.</p>
	 * @param i The id.
	 * @since 1.0
	 */
	protected ASTNode(int i) {
		super(i);
	}

	/**
	 * <p>Constructs a {@link ASTNode} with a JJTreeParser.</p>
	 * @param p The JJTreeParser
	 * @param i The id.
	 * @since 1.0
	 */
	protected ASTNode(JJTreeParser p, int i) {
		super(p, i);
	}

	/**
	 * <p>The first token of this node.</p>
	 * @since 1.0
	 */
	public Token firstToken;
	/**
	 * <p>The last token of this node.</p>
	 * @since 1.0
	 */
	public Token lastToken;
}
