
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
 * <p>Creates "stylesheet.css" file.</p>
 * <p>Note that a transformer of this type needs to close to close its target file.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */
public final class DoqStyleSheet extends Doq
{
	/**
	 * <p>Constructs a {@link DoqStyleSheet} transformer.</p>
	 * <p>This constructor invokes its super constructor to create and open the target file.</p>
	 * @param jdoq The {@link JavaDoq} environment for the transformer.
	 * @since 1.0
	 */
	public DoqStyleSheet(JavaDoq jdoq) {
		super(jdoq.dst + File.separator + STYLE_SHEET);
		writeLine("/* JavaDoq - Style Definitions*/");
		writeLine("body { background-color: #ffffff; color: #000000}");
		writeLine("#header { font-family: Arial, Helvetica, sans-serif; color: #0000a0;  background-color:#ccccee}");
		writeLine("#footer { font-family: Arial, Helvetica, sans-serif; color: #0000a0;  background-color:#ccccee }");
		writeLine("#line-number { color: #0000a0;  background-color:#ffffff }");
		writeLine("#java-source a:link		{ color: #000000; }");
		writeLine("#java-source a:visited	{ color: #000000; }");
		writeLine("#java-source pre			{ color: #000000; }");
		writeLine("#abstract		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#boolean			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#break			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#byte			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#case			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#catch			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#char			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#class			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#const			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#continue		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#default			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#do				{ color: #a00a00 ; font-weight: bold }");
		writeLine("#double			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#else			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#extends			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#false			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#final			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#finally			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#float			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#for				{ color: #a00a00 ; font-weight: bold }");
		writeLine("#goto			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#if				{ color: #a00a00 ; font-weight: bold }");
		writeLine("#implements		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#import			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#instanceof		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#int				{ color: #a00a00 ; font-weight: bold }");
		writeLine("#interface		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#long			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#native			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#new				{ color: #a00a00 ; font-weight: bold }");
		writeLine("#package			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#private			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#protected		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#public			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#return			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#short			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#static			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#strictfp		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#super			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#switch			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#synchronized	{ color: #a00a00 ; font-weight: bold }");
		writeLine("#this			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#throw			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#throws			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#transient		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#true			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#try				{ color: #a00a00 ; font-weight: bold }");
		writeLine("#void			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#volatile		{ color: #a00a00 ; font-weight: bold }");
		writeLine("#while			{ color: #a00a00 ; font-weight: bold }");
		writeLine("#single-line-comment					{ color: #00a000; }");
		writeLine("#formal-comment						{ color: #0000a0; }");
		writeLine("#multi-line-comment					{ color: #00a000; }");
		writeLine("#characer-literal					{ color: #0000a0; }");
		writeLine("#string-literal						{ color: #0000a0; }");
		writeLine("#decimal-literal						{ color: #000000 }");
		writeLine("#floating-point-literal				{ color: #000000 }");
		writeLine("#hex-literal							{ color: #000000 }");
		writeLine("#integer-literal						{ color: #000000 }");
		writeLine("#octal-literal						{ color: #000000 }");
		writeLine("#decimal-floating-point-literal		{ color: #000000 }");
		writeLine("#decimal-exponent					{ color: #000000 }");
		writeLine("#hexa-decimal-floating-point-literal	{ color: #000000 }");
		writeLine("#hexa-decimal-exponent				{ color: #000000 }");
		writeLine("#lookahead							{ color: #f000f0 }");
		writeLine("#ignore-case							{ color: #f000f0 }");
		writeLine("#parser-begin						{ color: #f000f0 }");
		writeLine("#parser-end							{ color: #f000f0 }");
		writeLine("#javacode							{ color: #f000f0 }");
		writeLine("#token								{ color: #f000f0 }");
		writeLine("#special-token						{ color: #f000f0 }");
		writeLine("#more								{ color: #f000f0 }");
		writeLine("#skip								{ color: #f000f0 }");
		writeLine("#token-mgr-decls						{ color: #f000f0 }");
		writeLine("#eof									{ color: #f000f0 }");
		writeLine("#left-paren				{ color: #000000 }");
		writeLine("#right-paren				{ color: #000000 }");
		writeLine("#left-brace				{ color: #000000 }");
		writeLine("#right-brace				{ color: #000000 }");
		writeLine("#left-bracket			{ color: #000000 }");
		writeLine("#right-bracket			{ color: #000000 }");
		writeLine("#semi-colon				{ color: #000000 }");
		writeLine("#comma					{ color: #000000 }");
		writeLine("#dot						{ color: #000000 }");
		writeLine("#at						{ color: #000000 }");
		writeLine("#assign					{ color: #000000 }");
		writeLine("#lt						{ color: #000000 }");
		writeLine("#bang					{ color: #000000 }");
		writeLine("#tilde					{ color: #000000 }");
		writeLine("#hook					{ color: #000000 }");
		writeLine("#colon					{ color: #000000 }");
		writeLine("#eq						{ color: #000000 }");
		writeLine("#le						{ color: #000000 }");
		writeLine("#ge						{ color: #000000 }");
		writeLine("#ne						{ color: #000000 }");
		writeLine("#sc-or					{ color: #f000f0 }");
		writeLine("#sc-and					{ color: #f000f0 }");
		writeLine("#incr					{ color: #f000f0 }");
		writeLine("#decr					{ color: #f000f0 }");
		writeLine("#plus					{ color: #000000 }");
		writeLine("#minus					{ color: #000000 }");
		writeLine("#star					{ color: #000000 }");
		writeLine("#slash					{ color: #000000 }");
		writeLine("#bit-and					{ color: #000000 }");
		writeLine("#bit-or					{ color: #000000 }");
		writeLine("#xor						{ color: #000000 }");
		writeLine("#rem						{ color: #000000 }");
		writeLine("#plus-assign				{ color: #000000 }");
		writeLine("#minus-assign			{ color: #000000 }");
		writeLine("#star-assign				{ color: #000000 }");
		writeLine("#slash-assign			{ color: #000000 }");
		writeLine("#and-assign				{ color: #000000 }");
		writeLine("#or-assign				{ color: #000000 }");
		writeLine("#xor-assign				{ color: #000000 }");
		writeLine("#rem-assign				{ color: #000000 }");
		writeLine("#ellipsis				{ color: #000000 }");
		writeLine("#right-unsigned-shift	{ color: #000000 }");
		writeLine("#right-signed-shift		{ color: #000000 }");
		writeLine("#gt						{ color: #000000 }");
		writeLine("#identifier				{ color: #000000 }");
		writeLine("#letter					{ color: #f000f0 }");
		writeLine("#part-letter				{ color: #f000f0 }");
	}
}
