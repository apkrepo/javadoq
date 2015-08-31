
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.javadoq.JavaDoq;

/**
 * <p>A transformer that simply copies a file.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public final class DoqCopy extends Doq
{
	/**
	 * <p>Constructs a {@link DoqCopy} transformer copying a file.</p>
	 * @param jdoq A {@link JavaDoq} environment.
	 * @param path The relative path to the source file and the target file.
	 * @since 1.0
	 */
	public DoqCopy(JavaDoq jdoq, String path) {
		super(jdoq.dst + File.separator + path);
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(jdoq.src + File.separator + path)
			);
			String line = null;
			while ((line = reader.readLine()) != null) {
				writeLine(line);
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
