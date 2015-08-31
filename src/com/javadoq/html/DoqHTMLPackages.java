
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.javadoq.JavaDoq;
import com.javadoq.JavaPackage;

/**
 * <p>Creates "packages.html" file.</p>
 * <p>Note that a transformer of this type needs to close to close its target HTML file.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public class DoqHTMLPackages extends DoqHTMLBody
{
	/**
	 * <p>Constructs a {@link DoqHTMLPackages} transformer.</p>
	 * <p>This constructor invokes its super constructor to create and open the target file
	 * and HTML body.</p>
	 * @param jdoq The {@link JavaDoq} environment for the transformer.
	 * @since 1.0
	 */
	public DoqHTMLPackages(JavaDoq jdoq) {
		super(jdoq, jdoq.dst + File.separator + "packages.html", jdoq.title);
		writeLine("<font size=\"+1\"><a href=\"overview.html\" target=\"source-frame\">" + jdoq.title + "</a></font>");
		writeLine("<br><a href=\"allfiles.html\" target=\"package-frame\">All Files</a>");
		writeLine("<br><font size=\"+1\">Packages</font>");
		List<String> list = new ArrayList<String>(jdoq.packages.keySet());
		Collections.sort(list);
		for (String text : list) {
			JavaPackage pkg = jdoq.packages.get(text);
			writeFrameItem(pkg.getLink(), "package-frame", pkg.title());
		}
	}
}
