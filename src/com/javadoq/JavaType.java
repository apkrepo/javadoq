
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
 * <p>Represents a Java type, either a class, an interface or enum.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public class JavaType
{
	/**
	 * <p>The inner type map with the simple names as the keys.</p>
	 * @since 1.0
	 */
	public final Map<String, JavaNestedType> inners = new HashMap<String, JavaNestedType>();
	/**
	 * <p>The super type map with the fully qualified names as the keys.</p>
	 * @since 1.0
	 */
	public final Map<String, JavaType> supers = new HashMap<String, JavaType>();
	/**
	 * <p>The source file that defines this type.</p>
	 * @since 1.0
	 */
	public final SourceFile file;
	/**
	 * <p>The simple name of this type.</p>
	 * @since 1.0
	 */
	public final String name;
	/**
	 * <p>The fully qualified name of this type.</p>
	 * @since 1.0
	 */
	public final JavaName qname;

	/**
	 * <p>Constructs a {@link JavaType}.</p>
	 * @param file The source file that defines the type being constructed.
	 * @param name The simple name for the type.
	 * @param qname The fully qualified name of the type.
	 * @since 1.0
	 */
	protected JavaType(SourceFile file, String name, JavaName qname) {
		this.file = file;
		this.name = name;
		this.qname = qname;
	}

	/**
	 * <p>Constructs a {@link JavaType}.</p>
	 * @param file The source file that defines the type being constructed.
	 * @param name The simple name for the type.
	 * @since 1.0
	 */
	public JavaType(SourceFile file, String name) {
		this.file = file;
		this.name = name;
		qname = file.pckg.name.addLast(name);
		file.pckg.types.put(name, this);
	}

	/**
	 * <p>Finds a Java type nested in this type.</p>
	 * @param name The simple name for the type being found.
	 * @return The matched Java type or <tt>null</tt> if it is not found.
	 * @since 1.0
	 */
	protected final JavaType findNestedType(JavaName name) {
		JavaType t = inners.get(name.text);
		if (!name.isSimple) {
			t = inners.get(name.getFirst());
			if (t != null) {
				t = t.findNestedType(name.chopFirst());
			}
		}
		return t;
	}

	/**
	 * <p>Finds a Java type locally defined within this type or its super types.</p>
	 * @param name The simple name for the type being found.
	 * @return The matched Java type or <tt>null</tt> if it is not found.
	 * @since 1.0
	 */
	protected JavaType findLocalType(JavaName name) {
		JavaType t = findNestedType(name);
		if (t == null) {
			for (JavaType s : supers.values()) {
				t = s.findLocalType(name);
				if (t != null) {
					break;
				}
			}
		}
		return t;
	}

	/**
	 * <p>Finds a Java type in the scope of this type.</p>
	 * @param name The name for the type being found.
	 * @return The matched Java type or <tt>null</tt> if it is not found.
	 * @since 1.0
	 */
	public final JavaType findType(JavaName name) {
		JavaType t = findLocalType(name);
		return t != null ? t : file.findType(name);
	}

	/**
	 * <p>Searches for a Java type from the scope of this type.</p>
	 * @param name The name for the type being searched for.
	 * @return The matched Java type or <tt>null</tt> if it is not found.
	 * @since 1.0
	 */
	public final JavaType searchType(JavaName name) {
		while (true) {
			JavaType t = findType(name);
			if (t != null) {
				return t;
			}
			t = file.findType(name);
			if (t != null) {
				return t;
			}
			if (name.isSimple) {
				return null;
			}
			name = name.chopLast();
		}
	}

	/**
	 * <p>Determines if the current {@link JavaType} equals another object.</p>
	 * @param o An object.
	 * @return <tt>true</tt> if the given object is an instance of {@link JavaType} and its
	 * {@link #qname} is equal to that of the current one.
	 * @since 1.0
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof JavaType && ((JavaType)o).qname.equals(qname);
	}


	/**
	 * <p>Returns a hash code of the current {@link JavaType}.</p>
	 * @return A hash code of the current {@link JavaType} that is same as that of its {@link #qname}.
	 * @since 1.0
	 */
	@Override
	public int hashCode() {
		return qname.hashCode();
	}

	/**
	 * <p>Returns a string representation of this type.</p>
	 * @return The string representation of this type, that is, the fully
	 * qualified name of the type.
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return qname.toString();
	}
}
