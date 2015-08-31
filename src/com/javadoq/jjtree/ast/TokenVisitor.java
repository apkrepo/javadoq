
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

import com.javadoq.jjtree.ASTCompilationUnit;
import com.javadoq.jjtree.ASTGrammar;
import com.javadoq.jjtree.ASTImportName;
import com.javadoq.jjtree.ASTName;
import com.javadoq.jjtree.ASTNewName;
import com.javadoq.jjtree.ASTNewType;
import com.javadoq.jjtree.ASTNode;
import com.javadoq.jjtree.ASTPackageName;
import com.javadoq.jjtree.ASTSuperList;
import com.javadoq.jjtree.JJTreeParserVisitor;
import com.javadoq.jjtree.SimpleNode;
import com.javadoq.jjtree.Token;

/**
 * <p>An abstract base class for JJTree visitor to visit JJTree nodes and tokens.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public abstract class TokenVisitor implements JJTreeParserVisitor
{
	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(SimpleNode node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTCompilationUnit node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTGrammar node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTImportName node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTName node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTNewType node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTNewName node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTPackageName node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTSuperList node, Object data) {
		return visit((ASTNode)node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	protected Object visit(ASTNode node, Object data) {
		return visitChildren(node, data);
	}

	/**
	 * <p>Visits all children nodes and tokens of a JJTree node.</p>
	 * @param node The JJTree node to visit children and tokens.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	protected Object visitChildren(ASTNode node, Object data) {
		Token t = node.firstToken;
		ASTNode n;
		for (int i = 0; i < node.jjtGetNumChildren(); i++) {
			n = (ASTNode)node.jjtGetChild(i);
			do {
				if (t == n.firstToken) {
					break;
				}
				visit(t);
				t = t.next;
			} while (true);
			n.jjtAccept(this, data);
			t = n.lastToken;
		}
		if (t != node.lastToken) do {
			visit(t);
			if (t == node.lastToken) {
				break;
			}
			t = t.next;
		} while (true);
		return data;
	}

	/**
	 * <p>Visits a token.</p>
	 * @param t The JJTree token to visit.
	 * @since 1.0
	 */
	protected abstract void visit(Token t);
}
