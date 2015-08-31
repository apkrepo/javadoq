
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

import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;

import java.io.File;

/**
 * <p>A task for an Ant builder to run JavaDoq.</p>
 * 
 * @author <a href="mailto:jianjunliu@126.com">J.J.Liu (Jianjun Liu)</a> at <a href="http://www.javadoq.com" target="_blank">http://www.javadoq.com</a>
 */

public class JavaDoqTask extends Task {

	private JavaDoq jdoq = new JavaDoq();

	/**
	 * <p>Executes the task.</p>
	 * @since 1.0
	 */
	@Override
	public void execute() throws BuildException {
		try {
			jdoq.build();
		}
		catch (Exception e) {
			throw new BuildException(e);
		}
	}

	/**
	 * <p>Sets source code directory for JavaDoq.</p>
	 * @param src The directory file of the source code.
	 * @since 1.0
	 */
	public void setSource(File src) {
		jdoq.src = src.getAbsolutePath();
	}

	/**
	 * <p>Sets target document directory for JavaDoq.</p>
	 * @param dst The directory file for the target documents.
	 * @since 1.0
	 */
	public void setDestination(File dst) {
		jdoq.dst = dst.getAbsolutePath();
	}

	/**
	 * <p>Sets title for JavaDoq.</p>
	 * @param title The title for the target documents.
	 * @since 1.0
	 */
	public void setTitle(String title) {
		jdoq.title = title;
	}
}
