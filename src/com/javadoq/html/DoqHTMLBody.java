
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

import com.javadoq.JavaDoq;

/**
 * <p>An abstract base class of transformers that create target HTML files with bodies.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public abstract class DoqHTMLBody extends DoqHTMLFile
{
	/**
	 * <p>Constructs a {@link DoqHTMLBody} transformer without style sheet.</p>
	 * <p>This constructor invokes its super constructor to create and open the target file
	 * and HTML body.</p>
	 * @param jdoq The {@link JavaDoq} environment for the transformer.
	 * @param fileName The absolute path of the source file to transform.
	 * @param title The title for the target HTML file.
	 * @since 1.0
	 */
	protected DoqHTMLBody(JavaDoq jdoq, String fileName, String title) {
		super(jdoq, fileName, title);
	}

	/**
	 * <p>Constructs a {@link DoqHTMLBody} transformer with a given style sheet.</p>
	 * <p>This constructor invokes its super constructor to create and open the target file
	 * and HTML body.</p>
	 * @param jdoq The {@link JavaDoq} environment for the transformer.
	 * @param fileName The absolute path of the source file to transform.
	 * @param title The title for the target HTML file.
	 * @param css The name of the cascade style sheet file.
	 * @since 1.0
	 */
	protected DoqHTMLBody(JavaDoq jdoq, String fileName, String title, String css) {
		super(jdoq, fileName, title, css);
		writeLine("<body>");
	}

	/**
	 * <p>Closes the target HTML body.</p>
	 * <p>This method closes HTML body and invokes its super method to close HTML 
	 * and the file.</p>
	 * @since 1.0
	 */
	@Override
	public void close() {
		writeLine("</body>");
		super.close();
	}
}
