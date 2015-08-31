
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

import java.io.File;

import com.javadoq.JavaDoq;

/**
 * <p>Creates "index.html" file.</p>
 * <p>Note that a transformer of this type needs to close to close its target HTML file.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public final class DoqHTMLIndex extends DoqHTMLFile
{
	/**
	 * <p>Constructs a {@link DoqHTMLIndex} transformer.</p>
	 * <p>This constructor invokes its super constructor to create and open the target file
	 * and HTML body.</p>
	 * @param jdoq The {@link JavaDoq} environment for the transformer.
	 * @since 1.0
	 */
	public DoqHTMLIndex(JavaDoq jdoq) {
		super(jdoq, jdoq.dst + File.separator + "index.html", jdoq.title, null);
		writeLine("<frameset cols=\"30%, 70%\">");
		writeLine("<frameset rows=\"30%, 70%\">");
		writeLine("<frame src=\"packages.html\" name=\"packages-frame\">");
		writeLine("<frame src=\"allfiles.html\" name=\"package-frame\">");
		writeLine("</frameset>");
		writeLine("<frame src=\"overview.html\" name=\"source-frame\">");
		writeLine("</frameset>");
		writeLine("<noframes>");
		writeLine("<h2>Frame Alert</h2>");
		writeLine("<p>This document is designed to be viewed using the frames feature. If you see this message, you are using a non-frame-capable web client.");
		writeLine("</noframes>");
	}
}
