
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

package com.javadoq;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javadoq.html.DoqCopy;
import com.javadoq.html.DoqHTMLAllFiles;
import com.javadoq.html.DoqHTMLIndex;
import com.javadoq.html.DoqHTMLJavaFile;
import com.javadoq.html.DoqHTMLPackage;
import com.javadoq.html.DoqHTMLPackages;
import com.javadoq.html.DoqStyleSheet;
import com.javadoq.jjtree.ASTCompilationUnit;
import com.javadoq.jjtree.JJTreeParser;
import com.javadoq.jjtree.ast.ImportNameVisitor;
import com.javadoq.jjtree.ast.NewTypeVisitor;
import com.javadoq.jjtree.ast.SuperTypeVisitor;

/**
 * <p>Converts Java source code to HTML documents.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public final class JavaDoq
{
	/**
	 * <p>Version of JavaDoq.</p>
	 * @since 1.0
	 */
	public final static String VERSION = "JavaDoq 1.0 - DOCUment JAVA In Source";
	private final static String COPYRIGHT =
		"Copyright (c) 2008-2011 J.J.Liu <jianjunliu@126.com> http://www.javadoq.com";

	/**
	 * <p>Title for target HTML documents.</p>
	 * @since 1.0
	 */
	public String title = "Java Source";
	/**
	 * <p>Source directory name.</p>
	 * @since 1.0
	 */
	public String src = "src";
	/**
	 * <p>Target directory name.</p>
	 * @since 1.0
	 */
	public String dst = "docs/src";

	/**
	 * <p>All packages of source code to convert.</p>
	 * @since 1.0
	 */
	public final Map<String, JavaPackage> packages = new HashMap<String, JavaPackage>();

	private List<SourceFile> allFiles = null;

	/**
	 * <p>Gets all files of source code to convert.</p>
	 * @return All files of source code to convert.
	 * @since 1.0
	 */
	public final List<SourceFile> getAllFiles() {
		if (allFiles == null) {
			allFiles = new ArrayList<SourceFile>();
			for (JavaPackage pkg : packages.values()) {
				allFiles.addAll(pkg.files.values());
			}
		}
		return allFiles;
	}

	/**
	 * <p>Gets a {@link JavaPackage}.</p>
	 * @param qname The fully qualified name of the package.
	 * @return A {@link JavaPackage} that has <tt>qname</tt>.
	 * @since 1.0
	 */
	public final JavaPackage findPackage(JavaName qname) {
		do {
			JavaPackage p = packages.get(qname.text);
			if (p != null) {
				return p;
			}
			if (qname.isSimple) {
				return packages.get("");
			} else {
				qname = qname.chopLast();
			}
		} while (true);
	}

	/**
	 * <p>Gets a {@link JavaType}.</p>
	 * @param qname The fully qualified name of the Java type.
	 * @return A {@link JavaType} that has <tt>qname</tt>.
	 * @since 1.0
	 */
	public final JavaType findType(JavaName qname) {
		JavaPackage p = findPackage(qname);
		if (p == null) {
			return null;
		}
		return p.findType(qname.chopFirst(p.name));
	}

	/**
	 * <p>Builds HTML documents by the definitions.</p>
	 * @since 1.0
	 */
	public final void build() {
		try {
			System.out.println(VERSION);
			System.out.println(COPYRIGHT);

			if (src == null) {
				src = ".";
			}

			new File(dst).mkdirs();
			new DoqStyleSheet(this).close();
			new DoqCopy(this, "overview.html").close();
			new DoqHTMLIndex(this).close();

			buildSource(src, new JavaPackage(this, JavaName.EMPTY));
 
			for (String k : new ArrayList<String>(packages.keySet())) {
				JavaPackage pkg = packages.get(k);
				if (pkg.files.isEmpty()) {
					packages.remove(k);
				}
			}

			new DoqHTMLAllFiles(this).close();
			new DoqHTMLPackages(this).close();
			for (JavaPackage pkg : packages.values()) {
				new File(dst + File.separator + pkg.name.path()).mkdirs();
				new DoqHTMLPackage(pkg).close();
			}

			for (SourceFile f : getAllFiles()) {
				if (f.isJava) {
					FileReader reader = new FileReader(f.source);
					JJTreeParser parser = new JJTreeParser(reader);
					ASTCompilationUnit node = parser.CompilationUnit();

					new NewTypeVisitor(f).visit(node, null);
					reader.close();
				}
			}

			for (SourceFile f : getAllFiles()) {
				if (f.isJava) {
					FileReader reader = new FileReader(f.source);
					JJTreeParser parser = new JJTreeParser(reader);
					ASTCompilationUnit node = parser.CompilationUnit();

					new ImportNameVisitor(f).visit(node, null);
					new SuperTypeVisitor(f).visit(node, null);
					reader.close();
				}
			}

			for (SourceFile f : getAllFiles()) {
				if (f.isJava || f.isJavaCC || f.isJJTree) {
					DoqHTMLJavaFile doqFile = new DoqHTMLJavaFile(f);
					doqFile.parse();
					doqFile.close();
				} else {
					new DoqCopy(this, f.pckg.name.path() + File.separator + f.name).close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Scans source files in a package.
	private final void buildSource(String dir, JavaPackage pkg) {
		packages.put(pkg.name.text, pkg);
		File d = new File(dir);
		String[] a = d.list();
		if (a == null) return;
		for (String s : a) {
			if ("package.html".equals(s)) {
				pkg.hasDescription = true;
			}
			String fn = dir + File.separator + s;
			if (new File(fn).isFile()) {
				pkg.files.put(s, new SourceFile(fn, pkg, s));
			} else {
				buildSource(fn, new JavaPackage(
						this,
						pkg.name.addLast(s)
				));
			}
		}
	}

	/**
	 * <p>Builds HTML documents from a console.</p>
	 * @param args Arguments
	 * @since 1.0
	 */
	public static void main(String[] args) {
		JavaDoq jdoq = new JavaDoq();
		if (args.length < 3) {
			System.out.println("Usage: java -jar javadoq.jar srcdir destdir title");
		} else {
			jdoq.src = new File(args[0]).getAbsolutePath();
			jdoq.dst = new File(args[1]).getAbsolutePath();
			jdoq.title = args[2];
			jdoq.build();
		}
	}
}
