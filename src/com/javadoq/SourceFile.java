
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
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Represents a source file in a Java package.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public class SourceFile
{
	/**
	 * <p>The absolute path name of the source file.</p>
	 * @since 1.0
	 */
	public final String source;
	/**
	 * <p>The package where the source file is located.</p>
	 * @since 1.0
	 */
	public final JavaPackage pckg;
	/**
	 * <p>The simple name of the source file.</p>
	 * @since 1.0
	 */
	public final String name;
	/**
	 * <p>Whether the file is a Java source file.</p>
	 * @since 1.0
	 */
	public final boolean isJava;
	/**
	 * <p>Whether the file is a JavaCC source file.</p>
	 * @since 1.0
	 */
	public final boolean isJavaCC;
	/**
	 * <p>Whether the file is a JJTree source file.</p>
	 * @since 1.0
	 */
	public final boolean isJJTree;

	/**
	 * <p>Constructs a {@link SourceFile}.</p>
	 * @param source The absolute path name for the source file.
	 * @param pckg The package where the source file is located.
	 * @param name The simple name for the source file.
	 * @since 1.0
	 */
	public SourceFile(String source, JavaPackage pckg, String name) {
		this.source = source;
		this.pckg = pckg;
		this.name = name;
		isJava = name.endsWith(".java");
		isJavaCC = name.endsWith(".jj");
		isJJTree = name.endsWith(".jjt");
	}

	private JavaName fqdn;

	/**
	 * <p>Gets the fully qualified name of the file.</p>
	 * @return The fully qualified name of the file.
	 * @since 1.0
	 */
	public final JavaName getFullName() {
		if (fqdn == null) {
			fqdn = pckg.name.addLast(name);
		}
		return fqdn;
	}

	/**
	 * <p>Gets the target name of the file for hyper linking.</p>
	 * @return The target name of the file.
	 * @since 1.0
	 */
	public String getLinkName() {
		return isJava || isJavaCC || isJJTree ? name + ".html" : name;
	}

	protected String link;

	/**
	 * <p>Gets the hyper link to the file from the root.</p>
	 * @return The hyper link to the file from the root.
	 * @since 1.0
	 */
	public final String getLink() {
		if (link == null) {
			link = getLinkName();
			if (!pckg.isDefault) {
				link = pckg.name.path() + '/' + link;
			}
		}
		return link;
	}

	private String target;

	/**
	 * <p>Gets the target path name from the root to the file.</p>
	 * @return The target path name from the root to the file.
	 * @since 1.0
	 */
	public final String getTarget() {
		if (target == null) {
			target = pckg.jdoq.dst + File.separator + getLink().replace('/', File.separatorChar);
		}
		return target;
	}

	/**
	 * <p>Imported types in the file.</p>
	 * @since 1.0
	 */
	public final List<JavaType> imports = new ArrayList<JavaType>();

	/**
	 * <p>Finds a Java type in the scope of this file.</p>
	 * @param name The name for the type being found.
	 * @return The matched Java type or <tt>null</tt> if it is not found.
	 * @since 1.0
	 */
	public final JavaType findType(JavaName name) {
		for (JavaType t : imports) {
			if (t.qname.endsWith(name)) {
				return t;
			}
		}
		JavaType t = pckg.findType(name);
		if (t == null) {
			t = pckg.jdoq.findType(name);
		}
		return t;
	}
}
