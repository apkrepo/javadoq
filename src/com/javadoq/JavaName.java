
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
 * <p>Represents fully qualified names in Java source code.</p>
 * <p>Note that a {@link JavaName} is read-only like a Java string.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public final class JavaName
{
	/**
	 * <p>An empty {@link JavaName}.</p>
	 * @since 1.0
	 */
	public final static JavaName EMPTY = new JavaName("");

	/**
	 * <p>Text of the {@link JavaName}.</p>
	 * @since 1.0
	 */
	public final String text;
	/**
	 * <p>Index of the first dot in the text.</p>
	 * @since 1.0
	 */
	public final int firstDot;
	/**
	 * <p>Index of the last dot in the text.</p>
	 * @since 1.0
	 */
	public final int lastDot;
	/**
	 * <p>Length of the text.</p>
	 * @since 1.0
	 */
	public final int length;
	/**
	 * <p>Whether the {@link JavaName} is simple.</p>
	 * @since 1.0
	 */
	public final boolean isSimple;

	/**
	 * <p>Constructs a {@link JavaName} from a text string.</p>
	 * @param text The text for the {@link JavaName} being constructed.
	 * @since 1.0
	 */
	public JavaName(String text) {
		this.text = text;
		firstDot = text.indexOf('.');
		lastDot = text.lastIndexOf('.');
		isSimple = (firstDot == -1 || lastDot == -1);
		length = text.length();
	}

	// Stores path
	private String path;

	/**
	 * <p>Converts the {@link JavaName} to a directory path.</p>
	 * @return A string of the directory path.
	 * @since 1.0
	 */
	public final String path() {
		if (path == null) {
			path = length == 0 ? "." : text.replace('.', '/');
		}
		return path;
	}

	/**
	 * <p>Converts the {@link JavaName} to string.</p>
	 * @return The string representation of the {@link JavaName}.
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return text;
	}

	/**
	 * <p>Determines if the current {@link JavaName} equals another object.</p>
	 * @param o An object.
	 * @return <tt>true</tt> if the given object is an instance of {@link JavaName} and its
	 * {@link #text} is equal to that of the current one.
	 * @since 1.0
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof JavaName && ((JavaName)o).text.equals(text);
	}


	/**
	 * <p>Returns a hash code of the current {@link JavaName}.</p>
	 * @return A hash code of the current {@link JavaName} that is same as that of its {@link #text}.
	 * @since 1.0
	 */
	@Override
	public int hashCode() {
		return text.hashCode();
	}

	/**
	 * <p>Gets the first part of the {@link JavaName}.</p>
	 * @return The first part of the {@link JavaName} or <tt>null</tt> if 
	 * the {@link JavaName} is simple.
	 * @since 1.0
	 */
	public final String getFirst() {
		return isSimple ? null : text.substring(0, firstDot);
	}

	/**
	 * <p>Gets the last part of the {@link JavaName}.</p>
	 * @return The last part of the {@link JavaName} or <tt>null</tt> if 
	 * the {@link JavaName} is simple.
	 * @since 1.0
	 */
	public final String getLast() {
		return isSimple ? null : text.substring(lastDot + 1);
	}

	/**
	 * <p>Chops the first part of the {@link JavaName}.</p>
	 * @return The result {@link JavaName} or the current one itself if 
	 * it is simple.
	 * @since 1.0
	 */
	public final JavaName chopFirst() {
		if (isSimple) {
			return this;
		}
		return new JavaName(text.substring(firstDot + 1));
	}

	/**
	 * <p>Chops the last part of the {@link JavaName}.</p>
	 * @return The result {@link JavaName} or the current one itself if 
	 * it is simple.
	 * @since 1.0
	 */
	public final JavaName chopLast() {
		if (isSimple) {
			return this;
		}
		return new JavaName(text.substring(0, lastDot));
	}

	/**
	 * <p>Adds a simple name to the beginning of the current {@link JavaName}.</p>
	 * @param name A simple name to add.
	 * @return The result {@link JavaName}.
	 * @since 1.0
	 */
	public final JavaName addFirst(String name) {
		return name.length() == 0 ? this : new JavaName(
				length == 0 ? name : name + '.' + text
		);
	}

	/**
	 * <p>Adds a simple name to the end of the current {@link JavaName}.</p>
	 * @param name A simple name to add.
	 * @return The result {@link JavaName}.
	 * @since 1.0
	 */
	public final JavaName addLast(String name) {
		return name.length() == 0 ? this : new JavaName(
				length == 0 ? name : text + '.' + name
		);
	}

	/**
	 * <p>Determines if the current {@link JavaName} starts with a simple name.</p>
	 * @param simple A simple name.
	 * @return <tt>true</tt> if if the current {@link JavaName} starts with the
	 * specified simple name; <tt>false</tt>, otherwise.
	 * @since 1.0
	 */
	public final boolean startsWith(String simple) {
		return isSimple ? text.equals(simple) : getFirst().equals(simple);
	}

	/**
	 * <p>Determines if the current {@link JavaName} starts with another name.</p>
	 * @param name Another {@link JavaName}.
	 * @return <tt>true</tt> if if the current {@link JavaName} starts with the
	 * specified {@link JavaName}; <tt>false</tt>, otherwise.
	 * @since 1.0
	 */
	public final boolean startsWith(JavaName name) {
		JavaName t = this;
		while (!name.isSimple) {
			String s = name.getFirst();
			if (!t.startsWith(s)) {
				return false;
			}
			t = t.chopFirst(); name = name.chopFirst();
		}
		return t.startsWith(name.text);
	}

	/**
	 * <p>Determines if the current {@link JavaName} ends with a simple name.</p>
	 * @param simple A simple name.
	 * @return <tt>true</tt> if if the current {@link JavaName} ends with the
	 * specified simple name; <tt>false</tt>, otherwise.
	 * @since 1.0
	 */
	public final boolean endsWith(String simple) {
		return isSimple ? text.equals(simple) : getLast().equals(simple);
	}

	/**
	 * <p>Determines if the current {@link JavaName} ends with another name.</p>
	 * @param name Another {@link JavaName}.
	 * @return <tt>true</tt> if if the current {@link JavaName} ends with the
	 * specified {@link JavaName}; <tt>false</tt>, otherwise.
	 * @since 1.0
	 */
	public final boolean endsWith(JavaName name) {
		JavaName t = this;
		while (!name.isSimple) {
			String s = name.getLast();
			if (!t.endsWith(s)) {
				return false;
			}
			t = t.chopLast(); name = name.chopLast();
		}
		return t.endsWith(name.text);
	}

	/**
	 * <p>Chops the specified head of the {@link JavaName}.</p>
	 * @param head A head for the current name.
	 * @return The result {@link JavaName} or the current one does not starts 
	 * with the specified head.
	 * @since 1.0
	 */
	public final JavaName chopFirst(JavaName head) {
		if (head.length == 0 || !startsWith(head)) {
			return this;
		}
		return new JavaName(text.substring(head.length +1));
	}

	/**
	 * <p>Merges the specified {@link JavaName} to the end of the current one.</p>
	 * @param name A {@link JavaName}.
	 * @return The result {@link JavaName}.
	 * @since 1.0
	 */
	public final JavaName merge(JavaName name) {
		JavaName head = new JavaName(name.text);
		JavaName tail = JavaName.EMPTY;
		while (!endsWith(head)) {
			if (head.isSimple) {
				return addLast(name.text);
			}
			tail = tail.addFirst(head.getLast());
			head = head.chopLast();
		}
		return addLast(tail.text);
	}
}
