
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

import java.util.ArrayList;
import java.util.List;

import com.javadoq.JavaNestedType;
import com.javadoq.JavaType;
import com.javadoq.SourceFile;
import com.javadoq.jjtree.ASTCompilationUnit;
import com.javadoq.jjtree.ASTGrammar;
import com.javadoq.jjtree.ASTImportName;
import com.javadoq.jjtree.ASTName;
import com.javadoq.jjtree.ASTNewName;
import com.javadoq.jjtree.ASTNewType;
import com.javadoq.jjtree.ASTPackageName;
import com.javadoq.jjtree.ASTSuperList;
import com.javadoq.jjtree.JJTreeParserVisitor;
import com.javadoq.jjtree.SimpleNode;

/**
 * <p>A JJTree visitor to collect new Java types defined in a source file.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public class NewTypeVisitor implements JJTreeParserVisitor
{
	/**
	 * <p>The source file where this visitor is collecting new Java types.</p>
	 * @since 1.0
	 */
	public final SourceFile file;

	/**
	 * <p>Constructs a {@link NewTypeVisitor}.</p>
	 * @param file The source file where this visitor is collecting new Java types.
	 * @since 1.0
	 */
	public NewTypeVisitor(SourceFile file) {
		this.file = file;
	}

	private List<JavaType> stack = new ArrayList<JavaType>();

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTNewType node, Object data) {
		JavaType t;
		if (stack.isEmpty()) {
			t = new JavaType(file, node.name);
		} else {
			JavaType outer = stack.get(stack.size() - 1);
			t = new JavaNestedType(outer, node.name);
			outer.inners.put(t.name, (JavaNestedType)t);
			
		}
		stack.add(t);
		data = node.childrenAccept(this, data);
		stack.remove(stack.size() - 1);
		return data;
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(SimpleNode node, Object data) {
		if (node instanceof ASTCompilationUnit) {
			return visit((ASTCompilationUnit)node, data);
		} else if (node instanceof ASTNewType) {
			return visit((ASTNewType)node, data);
		}
		return data;
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTCompilationUnit node, Object data) {
		if (node.packageName.equals(file.pckg.name.text)) {
			return node.childrenAccept(this, data);
		}
		return data;
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method simply throws an UnsupportedOperationException.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTName node, Object data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method simply throws an UnsupportedOperationException.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTImportName node, Object data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method simply throws an UnsupportedOperationException.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTNewName node, Object data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method simply throws an UnsupportedOperationException.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTPackageName node, Object data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method simply throws an UnsupportedOperationException.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTSuperList node, Object data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method simply throws an UnsupportedOperationException.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	public Object visit(ASTGrammar node, Object data) {
		throw new UnsupportedOperationException();
	}
}
