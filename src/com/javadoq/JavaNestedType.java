
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

/**
 * <p>Represents an enclosed Java type, either a class, an interface or enum.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public class JavaNestedType extends JavaType
{
	/**
	 * <p>The enclosing type.</p>
	 * @since 1.0
	 */
	public final JavaType outer;

	/**
	 * <p>Constructs a {@link JavaNestedType}.</p>
	 * @param outer The enclosing type.
	 * @param name The simple name for the type.
	 * @since 1.0
	 */
	public JavaNestedType(JavaType outer, String name) {
		super(outer.file, name, outer.qname.addLast(name));
		this.outer = outer;
	}

	/**
	 * <p>Finds a Java type locally defined within this type or its super types.</p>
	 * @param name The simple name for the type being found.
	 * @return The matched Java type or <tt>null</tt> if it is not found.
	 * @since 1.0
	 */
	@Override
	protected JavaType findLocalType(JavaName name) {
		JavaType t = super.findLocalType(name);
		if (t == null) {
			t = outer.findLocalType(name);
		}
		return t;
	}
}
