
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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Represents a Java package.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public final class JavaPackage
{
	/**
	 * <p>The type map with the simple names as the keys.</p>
	 * @since 1.0
	 */
	public final Map<String, JavaType> types = new HashMap<String, JavaType>();
	/**
	 * <p>The source file map with the simple file names as the keys.</p>
	 * @since 1.0
	 */
	public final Map<String, SourceFile> files = new HashMap<String, SourceFile>();

	/**
	 * <p>The JavaDoq environment of the package.</p>
	 * @since 1.0
	 */
	public final JavaDoq jdoq;
	/**
	 * <p>The fully qualified name of the package.</p>
	 * @since 1.0
	 */
	public final JavaName name;
	/**
	 * <p>Whether the package is default.</p>
	 * @since 1.0
	 */
	public final boolean isDefault;
	/**
	 * <p>Whether the package has a description file.</p>
	 * @since 1.0
	 */
	public boolean hasDescription;

	/**
	 * <p>Constructs a {@link JavaPackage}.</p>
	 * @param jdoq The JavaDoq environment for the package being constructed.
	 * @param name The fully qualified name for the package.
	 * @since 1.0
	 */
	public JavaPackage(JavaDoq jdoq, JavaName name) {
		this.jdoq = jdoq;
		this.name = name;
		isDefault = name.length == 0;
	}

	/**
	 * <p>Gets the title for the {@link JavaPackage}.</p>
	 * @return The title for target documents.
	 * @since 1.0
	 */
	public final String title() {
		return isDefault ? "[default]" : name.text;
	}

	private String link;

	/**
	 * <p>Gets the hyper link to the target package file.</p>
	 * @return The hyper link to the target package file.
	 * @since 1.0
	 */
	public final String getLink() {
		if (link == null) {
			link = "package." + (isDefault ? "default" : name) + ".html";
		}
		return link;
	}

	private String root;

	/**
	 * <p>Gets the hyper link to the root of the source from the location of the
	 * current package.</p>
	 * @return The relative hyper link to the source root.
	 * @since 1.0
	 */
	public final String getRoot() {
		if (root == null) {
			if (isDefault) {
				root = "";
			} else {
				StringBuilder sb = new StringBuilder("../");
				int i = 0;
				while (true) {
					i = name.text.indexOf('.', i);
					if (i == -1) {
						break;
					}
					sb.append("../");
					i++;
				}
				root = sb.toString();
			}
		}
		return root;
	}

	/**
	 * <p>Gets the hyper link to the specified source file from the location of the
	 * current package.</p>
	 * @param f A source file.
	 * @return The relative hyper link to the source file.
	 * @since 1.0
	 */
	public final String getLink(SourceFile f) {
		if (f == null) {
			return null;
		}
		return f.pckg.equals(this) ? f.getLinkName() :
			getRoot() + f.getLink();
	}

	/**
	 * <p>Gets the hyper link to the specified package from the location of the
	 * current package.</p>
	 * @param p A package.
	 * @return The relative hyper link to the specified package.
	 * @since 1.0
	 */
	public final String getLink(JavaPackage p) {
		if (p == null) {
			return null;
		}
		return getRoot() + p.getLink();
	}

	/**
	 * <p>Finds a Java type in the scope of this package.</p>
	 * @param name The name for the type being found.
	 * @return The matched Java type or <tt>null</tt> if it is not found.
	 * @since 1.0
	 */
	public final JavaType findType(JavaName name) {
		if (name.isSimple) {
			return types.get(name.text);
		}
		JavaType t = types.get(name.getFirst());
		return t == null ? t : t.findNestedType(name.chopFirst());
	}

	/**
	 * <p>Returns a string representation of this package.</p>
	 * @return The string representation of this package, that is, the fully
	 * qualified name of the package.
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return name.toString();
	}
}
