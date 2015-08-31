
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

import com.javadoq.JavaPackage;
import com.javadoq.SourceFile;

/**
 * <p>Creates HTML package files.</p>
 * <p>Note that a transformer of this type needs to close to close its target HTML file.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public class DoqHTMLPackage extends DoqHTMLBody
{
	/**
	 * <p>Constructs a {@link DoqHTMLPackage} transformer.</p>
	 * <p>This constructor invokes its super constructor to create and open the target file
	 * and HTML body.</p>
	 * @param pkg The {@link JavaPackage} for the transformer.
	 * @since 1.0
	 */
	public DoqHTMLPackage(JavaPackage pkg) {
		super(pkg.jdoq, pkg.jdoq.dst + File.separator + pkg.getLink(), "Package " + pkg.title());
		if (pkg.hasDescription) {
			writeLine("<font size=\"+1\">Package <a href=\"" + pkg.name.path() + "/package.html\" target=\"source-frame\">" + pkg.title() + "</a></font>");
		} else {
			writeLine("<font size=\"+1\">Package " + pkg.title() + "</font>");
		}
		writeLine("<br/>");

		List<String> list = new ArrayList<String>(pkg.files.keySet());
		Collections.sort(list);
		for (String k : list) {
			SourceFile f = pkg.files.get(k);
			writeFrameItem(f.getLink(), "source-frame", f.name);
		}
	}
}
