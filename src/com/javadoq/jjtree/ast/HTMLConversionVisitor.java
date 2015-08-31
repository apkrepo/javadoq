
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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.javadoq.JavaName;
import com.javadoq.JavaNestedType;
import com.javadoq.JavaPackage;
import com.javadoq.JavaType;
import com.javadoq.html.DoqHTMLSourceFile;
import com.javadoq.jjtree.ASTCompilationUnit;
import com.javadoq.jjtree.ASTGrammar;
import com.javadoq.jjtree.ASTName;
import com.javadoq.jjtree.ASTNewName;
import com.javadoq.jjtree.ASTNewType;
import com.javadoq.jjtree.ASTNode;
import com.javadoq.jjtree.ASTPackageName;
import com.javadoq.jjtree.Token;

/**
 * <p>Transforms JJTree nodes to HTML at token level.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public class HTMLConversionVisitor extends HTMLColorizeVisitor
{
	/**
	 * <p>The transformer.</p>
	 * @since 1.0
	 */
	public final DoqHTMLSourceFile doq;

	/**
	 * <p>Constructs a {@link HTMLConversionVisitor}.</p>
	 * @param doq The transformer for this visitor.
	 * @since 1.0
	 */
	public HTMLConversionVisitor(DoqHTMLSourceFile doq) {
		this.doq = doq;
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method visits all the children nodes and tokens.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	@Override
	public Object visit(ASTGrammar node, Object data) {
		return visitChildren(node, data);
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method visits all the children nodes and tokens.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	@Override
	public Object visit(ASTCompilationUnit node, Object data) {
		if (!node.packageName.equals(doq.file.pckg.name.text)) {
			return data;
		}
		return visitChildren(node, data);
	}

	private List<JavaType> stack = new ArrayList<JavaType>();

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method pushes the found new type into the stack before visiting children nodes and
	 * pop it after visiting the children nodes and tokens.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	@Override
	public Object visit(ASTNewType node, Object data) {
		JavaType t;
		if (stack.isEmpty()) {
			t = doq.file.pckg.types.get(node.name);
		} else {
			JavaType outer = stack.get(stack.size() - 1);
			t = outer.inners.get(node.name);
			
		}
		stack.add(t);
		data = visitChildren(node, data);
		stack.remove(stack.size() - 1);
		return data;
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method visits all the children nodes and tokens.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	@Override
	protected Object visit(ASTNode node, Object data) {
		if (node instanceof ASTCompilationUnit) {
			return visit((ASTCompilationUnit)node, data);
		} else if (node instanceof ASTGrammar) {
			return visit((ASTGrammar)node, data);
		} else if (node instanceof ASTName) {
			return visit((ASTName)node, data);
		} else if (node instanceof ASTNewType) {
			return visit((ASTNewType)node, data);
		} else {
			return visitChildren(node, data);
		}
	}

	/**
	 * <p>Visits a JJTree node with the specific type.</p>
	 * <p>This method create hyper links for the found name.</p>
	 * @param node The JJTree node to visit.
	 * @param data Visitor data.
	 * @return A visitor data.
	 * @since 1.0
	 */
	@Override
	public Object visit(ASTName node, Object data) {
		Token t = node.firstToken;
		visitSpecial(t.specialToken);
		String link = null;
		JavaPackage pckg = doq.file.pckg;

		if (node instanceof ASTNewName) {
			write("<a name=\"" + stack.get(stack.size() - 1).qname.merge(new JavaName(node.text)) + "\"></a>");
		} else if (node instanceof ASTPackageName) {
			link = pckg.getLink(pckg.jdoq.findPackage(new JavaName(node.text)));
			if (link != null) {
				link += "\" target=\"package-frame";
			}
		} else if (node.text.endsWith(".*")) {
			JavaPackage p = pckg.jdoq.findPackage(new JavaName(node.text).chopLast());
			if (p != null && !p.isDefault) {
				link = pckg.getLink(p) + "\" target=\"package-frame";
			}
		} else {
			JavaType current = stack.isEmpty() ? null : stack.get(stack.size() - 1);
			JavaName name = new JavaName(node.text);
			JavaType type = current != null ? current.searchType(name):
					doq.file.findType(name);
			if (type != null) {
				name = type.qname.merge(name);
				if (!name.equals(type.qname)) {
					link = "#" + name;
					if (!type.equals(current)) {
						link = pckg.getLink(type.file) + link;
					}
				} else {
					if (type.equals(current)) {
						link = "#";
						if (type instanceof JavaNestedType) {
							link += type.qname;
						}
					} else {
						link = pckg.getLink(type.file);					}
					if (type instanceof JavaNestedType) {
						link += "#" + type.qname;
					}
				}
				link += "\" target=\"source-frame";
			}
		}

		if (link != null) {
			write("<a href=\"" + link + "\">");
		}

		visitToken(t);

		if (t == node.lastToken) {
			if (link != null) {
				write("</a>");
			}
			return data;
		}
		t = t.next;

		while (t != node.lastToken) {
			visit(t);
			t = t.next;
		}
		visit(t);
		if (link != null) {
			write("</a>");
		}
		return data;
	}

	/**
	 * <p>Transforms a string{@link  DoqHTMLSourceFile#write(String)  }.</p>
	 * <p>This method invokes {@link DoqHTMLSourceFile#write(String)} to write the string to 
	 * the target HTML file.</p>
	 * @param s The string to output.
	 * @since 1.0
	 */
	@Override
	protected void write(String s) {
		doq.write(s);
	}

	/**
	 * <p>Transforms a token.</p>
	 * <p>This method invokes {@link DoqHTMLSourceFile#writeHTML(Token)} to write the token to 
	 * the target HTML file.</p>
	 * @param t The JJTree token to output.
	 * @since 1.0
	 */
	@Override
	protected void write(Token t) {
		if (t.kind != FORMAL_COMMENT) {
			doq.writeHTML(t);
		} else {
			StringReader reader = new StringReader(t.image);
			com.javadoq.javadoc.JavadocParser parser = new com.javadoq.javadoc.JavadocParser(reader);
			try {
				com.javadoq.javadoc.ASTCompilationUnit node = parser.CompilationUnit();
				for (com.javadoq.javadoc.Token tt = node.firstToken; tt != null; tt = tt.next) {
					switch (tt.kind) {
						case com.javadoq.javadoc.JavadocParserConstants.ANCHOR:
							doq.write("<a href=\"" + tt.image + "\" target=\"_blank\">");
							doq.writeHTML(tt.image);
							doq.write("</a>");
							break;
						case com.javadoq.javadoc.JavadocParserConstants.LINK:
						case com.javadoq.javadoc.JavadocParserConstants.SEE:
							JavaType current = stack.isEmpty() ? null : stack.get(stack.size() - 1);
							String[] names = tt.image.split("#");
							String link = null;
							if (names.length < 1) {
								// Flow through to default
							} else if (names.length < 2) {
								JavaName name = new JavaName(names[0]);
								JavaType type = current != null ? current.findType(name) :
									doq.file.findType(name);
								if (type != null) {
									if (type.equals(current)) {
										link = "#";
										if (type instanceof JavaNestedType) {
											link += type.qname;
										}
									} else {
										link = doq.file.pckg.getLink(type.file);
										if (type instanceof JavaNestedType) {
											link += "#" + type.qname;
										}
									}
								}
							} else if (names[0].length() > 0) {
								JavaName name = new JavaName(names[0]);
								JavaType type = current != null ? current.findType(name) :
									doq.file.findType(name);
								if (type != null) {
									if (names[1].length() > 0) {
										link = "#" + type.qname.addLast(names[1]);
										if (!type.equals(current)) {
											link = doq.file.pckg.getLink(type.file) + link;
										}
									} else {
										if (type.equals(current)) {
											link = "#";
											if (type instanceof JavaNestedType) {
												link += type.qname;
											}
										} else {
											link = doq.file.pckg.getLink(type.file);
											if (type instanceof JavaNestedType) {
												link += "#" + type.qname;
											}
										}
									}
								}
							} else if (current != null) {
								link = "#";
								if (names[1].length() > 0) {
									link += current.qname.addLast(names[1]);
								} else if (current instanceof JavaNestedType) {
									link += current.qname;
								}
							}
							if (link != null) {
								doq.write("<a href=\"" + link + "\" target=\"source-frame\">");
								doq.writeHTML(tt.image);
								doq.write("</a>");
								break; // Skip default
							}
						default:
							doq.writeHTML(tt.image);
					}
				}
			} catch (Throwable thr) {
				doq.writeHTML(t);
				doq.close();
				throw new RuntimeException(thr);
			}
			reader.close();
		}
	}
}
