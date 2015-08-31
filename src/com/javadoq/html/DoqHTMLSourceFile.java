
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

import java.io.Reader;

import com.javadoq.SourceFile;
import com.javadoq.jjtree.JavaCharStream;
import com.javadoq.jjtree.Token;

/**
 * <p>An abstract base class of transformers that perform transformation of source
 * files to HTML files.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public abstract class DoqHTMLSourceFile extends DoqHTMLBody
{
	/**
	 * <p>The source file to transform.</p>
	 * @since 1.0
	 */
	public final SourceFile file;

	private static final int TAB_SPACES = 4;

	/**
	 * <p>Constructs a {@link DoqHTMLSourceFile} transformer.</p>
	 * <p>This constructor invokes its super constructor to create or open the target file,
	 * open HTML and HTML body, and write the header.</p>
	 * @param file The source file to transform.
	 * @since 1.0
	 */
	public DoqHTMLSourceFile(SourceFile file) {
		super(
				file.pckg.jdoq,
				file.getTarget(),
				file.getFullName().text,
				file.pckg.getRoot() + STYLE_SHEET
		);
		this.file = file;
		writeLine("<table id=\"header\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
		writeLine("<tr>");
		writeLine("<td colspan=\"2\" width=\"20%\">&nbsp;</td>");
		writeLine("<td align=\"center\" colspan=\"2\" width=\"60%\">");
		writeLine("<font size=\"4\"><a href=\"http://www.javadoq.com\" target=\"_blank\">JavaDoq</a>: " + file.name + "</font>");
		writeLine("</td>");
		writeLine("<td align=\"right\" colspan=\"2\" width=\"20%\">&nbsp;</td>");
		writeLine("</tr>");
		writeLine("</table>");
		writeLine("<pre id=\"java-source\">");
	}

	/**
	 * <p>Closes the target HTML body.</p>
	 * <p>This method writes the footer and invokes its super method to close HTML body, HTML 
	 * and the file.</p>
	 * @since 1.0
	 */
	@Override
	public void close() {
		writeLine("</pre>");
		writeLine("<table id=\"footer\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
		writeLine("<tr>");
		writeLine("<td colspan=\"2\" width=\"20%\">&nbsp;</td>");
		writeLine("<td align=\"center\" colspan=\"2\" width=\"60%\">");
		writeLine("<font size=\"4\"><a href=\"http://www.javadoq.com\" target=\"_blank\">JavaDoq</a>: " + file.name + "</font>");
		writeLine("</td>");
		writeLine("<td align=\"right\" colspan=\"2\" width=\"20%\">&nbsp;</td>");
		writeLine("</tr>");
		writeLine("</table>");
		super.close();
	}

	/**
	 * <p>The format of line numbers.</p>
	 * @since 1.0
	 */
	protected String format = "%1$05d ";

	// Stores the current line number.
	private int lines = 0;

	/**
	 * <p>Gets the current line number.</p>
	 * @return The HTML text for the current line number.
	 * @since 1.0
	 */
	protected String getLineNumber() {
		lines++;
		return "<font id=\"line-number\">" + String.format(format, lines) + 
				"</font><a name=\"" + lines + "\"></a>";
	}

	// Temporary variables for writeHTML(char).
	private int skips = 0, chars = 0;

	/**
	 * <p>Writes a char to the target file as HTML text.</p>
	 * @param c The char to parse and write.
	 * @since 1.0
	 */
	public void writeHTML(char c) {
		switch (c) {
			case '&':
				write("&amp;");
				chars++;
				break;
			case '<':
				write("&lt;");
				chars++;
				break;
			case '>':
				write("&gt;");
				chars++;
				break;
			case '\r':
				chars = 0;
				write(LINE_SEPARATOR);
				write(getLineNumber());
				skips = 2;
				break;
			case '\n':
				chars = 0;
				if (skips == 0) {
					write(LINE_SEPARATOR);
					write(getLineNumber());
				}
				break;
			case '\t':
				int t = TAB_SPACES - chars % TAB_SPACES;
				while (t > 0) {
					write(' '); t--; chars++;
				}
				break;
			default:
				write(c);
				chars++;
				break;
		}
		if (skips > 0) {
			skips--;
		}
	}

	/**
	 * <p>Writes a string to the target file as HTML text.</p>
	 * @param str The string to parse and write.
	 * @since 1.0
	 */
	public void writeHTML(String str) {
		for (int i = 0; i < str.length(); i++) {
			writeHTML(str.charAt(i));
		}
	}

	/**
	 * <p>Writes a {@link Token} to the target file as HTML text.</p>
	 * @param t The {@link Token} to parse and write.
	 * @since 1.0
	 */
	public void writeHTML(Token t) {
		int j = t.beginColumn + 5;
		for (int i = 0; i < t.image.length(); i++, j++) {
			char ch = t.image.charAt(i);
			String s = escapes.get(j + "," + t.beginLine);
			if (s != null) {
				writeHTML(s);
				j += 5;
			}
			else {
				writeHTML(ch);
			}
		}
	}

	//A map of escapable unicodes.
	private final java.util.Map<String, String> escapes = new java.util.HashMap<String, String>();

	/**
	 * <p>A {@link JavaCharStream} filter to escape unicodes.</p>
	 * 
	 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
	 */
	protected class CharStream extends JavaCharStream
	{
		/**
		 * <p>Constructs a {@link CharStream}.</p>
		 * @param reader A reader for input.
		 * @since 1.0
		 */
		public CharStream(Reader reader) {
			super(reader);
		}

		/**
		 * <p>Reads a byte from the input reader for the char stream.</p>
		 * <p>This method invokes its super method to read a byte and then 
		 * performs necessary filtering.</p>
		 * @return The read byte as a char.
		 * @since 1.0
		 */
		@Override
		protected char ReadByte() throws java.io.IOException {
			char c = super.ReadByte();
			if (buf.length() > 5) {
				buf.setLength(0);
			}
			int len = buf.length();
			if (len == 0 && c != '\\') {
				return c;
			}
			if (len == 1) {
				if (c == '\\') {
					return c;
				}
				if (c != 'u') {
					buf.setLength(0);
					return c;
				}
			}
			buf.append(c);
			return c;
		}

		// A string buffer.
		private final StringBuilder buf = new StringBuilder();

		/**
		 * <p>Reads a char from the char stream.</p>
		 * <p>This method invokes its super method to read a char and then 
		 * performs necessary filtering.</p>
		 * @return The read char.
		 * @since 1.0
		 */
		@Override
		public char readChar() throws java.io.IOException {
			int n = column;
			char c = super.readChar();
			if (column > n + 5) {
				String s = buf.toString();
				if (s.length() == 6) {
					escapes.put(column + "," + line, s);
				}
			}
			return c;
		}
	}
}
