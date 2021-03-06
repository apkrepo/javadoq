
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
 * <p>JJTree node for new Java types.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public class ASTNewType extends ASTNode 
{
	/**
	 * <p>Constructs a {@link ASTNewType} with a JJTreeParser.</p>
	 * @param p The JJTreeParser
	 * @param id The id.
	 * @since 1.0
	 */
	public ASTNewType(JJTreeParser p, int id) {
		super(p, id);
	}

	/**
	 * <p>The simple name of the new type.</p>
	 * @since 1.0
	 */
	public String name;
}
