
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
 * <p>An abstract base class of transformers that create target HTML files.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public abstract class DoqHTMLFile extends Doq
{
	/**
	 * <p>The {@link JavaDoq} environment of this transformer.</p>
	 * @since 1.0
	 */
	public final JavaDoq jdoq;

	/**
	 * <p>Constructs a {@link DoqHTMLFile} transformer without style sheet.</p>
	 * <p>This constructor invokes its super constructor to create and open the target file.</p>
	 * @param jdoq The {@link JavaDoq} environment for the transformer.
	 * @param fileName The absolute path of the source file to transform.
	 * @param title The title for the target HTML file.
	 * @since 1.0
	 */
	protected DoqHTMLFile(JavaDoq jdoq, String fileName, String title) {
		this(jdoq, fileName, title, STYLE_SHEET);
	}

	/**
	 * <p>Constructs a {@link DoqHTMLFile} transformer with a given style sheet.</p>
	 * <p>This constructor invokes its super constructor to create and open the target file.</p>
	 * @param jdoq The {@link JavaDoq} environment for the transformer.
	 * @param fileName The absolute path of the source file to transform.
	 * @param title The title for the target HTML file.
	 * @param css The name of the cascade style sheet file.
	 * @since 1.0
	 */
	protected DoqHTMLFile(JavaDoq jdoq, String fileName, String title, String css) {
		super(fileName);
		this.jdoq = jdoq;
		writeLine("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//\">");
		writeLine("<html>");
		writeLine("<head>");
		writeLine("<meta name=\"generator\" content=\"" + JavaDoq.VERSION + "\" charset='utf-8'>");
		writeLine("<title>JavaDoq: " + title + "</title>");
		if (css != null) {
			writeLine("<link rel =\"stylesheet\" type=\"text/css\" href=\"" + css + "\" title=\"style\">");
		}
		writeLine("</head>");
	}

	/**
	 * <p>Writes a frame item to the target HTML file.</p>
	 * @param href The hyper link for the frame item.
	 * @param target The target for the frame item.
	 * @param text The text for the frame item.
	 * @since 1.0
	 */
	protected final void writeFrameItem(String href, String target, String text) {
		writeLine("<br><a href=\"" + href + "\" target=\"" + target + "\">" + text + "</a>");
	}

	/**
	 * <p>Closes the target HTML file.</p>
	 * <p>This method closes HTML and invokes its super method to close the file.</p>
	 * @since 1.0
	 */
	@Override
	public void close() {
		writeLine("</html>");
		super.close();
	}
}
