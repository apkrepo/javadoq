
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

package com.javadoq.html;

import java.io.FileReader;
import java.io.IOException;

import com.javadoq.SourceFile;
import com.javadoq.jjtree.ASTNode;
import com.javadoq.jjtree.JJTreeParser;
import com.javadoq.jjtree.JJTreeParserTokenManager;
import com.javadoq.jjtree.ParseException;
import com.javadoq.jjtree.ast.HTMLConversionVisitor;
import com.javadoq.jjtree.ast.SuperTypeVisitor;

/**
 * <p>Transforms Java, JavaCC or JJTree source files to HTML files.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public final class DoqHTMLJavaFile extends DoqHTMLSourceFile
{
	/**
	 * <p>Constructs a {@link DoqHTMLJavaFile} transformer.</p>
	 * <p>This constructor invokes its super constructor to create or open the target file,
	 * open HTML and HTML body, and write the header.</p>
	 * @param file The source file to transform.
	 * @since 1.0
	 */
	public DoqHTMLJavaFile(SourceFile file) {
		super(file);
	}

	/**
	 * <p>Parses the source file and converts it to an HTML file.</p>
	 * @throws IOException A java.io.IOException.
	 * @throws ParseException A {@link ParseException}.
	 * @since 1.0
	 */
	public final void parse() throws IOException, ParseException {
		FileReader reader = new FileReader(file.source);
		JJTreeParser parser = new JJTreeParser(
				new JJTreeParserTokenManager(this.new CharStream(reader))
		);
		ASTNode node = null;
		if (file.isJava) {
			node = parser.CompilationUnit();
			// Need scan this twice.
			new SuperTypeVisitor(file).visit(node, null);
		} else if (file.isJavaCC || file.isJJTree) {
			node = parser.javacc_input();
		}
		format = "%1$0" + new Integer(
				node.lastToken.endLine).toString().length() + "d ";
		write(getLineNumber());
		new HTMLConversionVisitor(this).visit(node, null);
		reader.close();
	}
}
