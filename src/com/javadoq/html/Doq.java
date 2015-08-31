
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

import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>An abstract base class for Java source code transformers.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public abstract class Doq
{
	/**
	 * <p>A line-separator.</p>
	 * @since 1.0
	 */
	protected static final String LINE_SEPARATOR = System.getProperty("line.separator");
	/**
	 * <p>Cascade style sheet.</p>
	 * @since 1.0
	 */
	protected static final String STYLE_SHEET = "stylesheet.css";

	/**
	 * <p>Absolute path of the source file to transform.</p>
	 * @since 1.0
	 */
	protected final String fileName;
	/**
	 * <p>A FileWriter to the target file.</p>
	 * @since 1.0
	 */
	protected final FileWriter fileWriter;

	/**
	 * <p>Constructs a {@link Doq} transformer, creates and opens the target file.</p>
	 * @param fileName The absolute path of the source file to transform.
	 * @since 1.0
	 */
	protected Doq(String fileName) {
		this.fileName = fileName;
		try {
			System.out.println("Creating: " + fileName);
			this.fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Closes the target file.</p>
	 * @since 1.0
	 */
	public void close() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Writes a char to the target file.</p>
	 * @param c A char
	 * @since 1.0
	 */
	public final void write(char c) {
		try {
			fileWriter.write(c);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Writes a string to the target file.</p>
	 * @param str A string
	 * @since 1.0
	 */
	public final void write(String str) {
		try {
			fileWriter.write(str);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Writes a line to the target file.</p>
	 * @param str A line of string
	 * @since 1.0
	 */
	public final void writeLine(String str) {
		write(str + LINE_SEPARATOR);
	}
}
