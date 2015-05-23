package de.taimos.daemon.plugin;

/*
 * #%L
 * Maven plugin for daemon framework
 * %%
 * Copyright (C) 2015 Taimos GmbH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

public abstract class AbstractJarHeaderMojo extends AbstractMojo {
	
	/**
	 * jar file full name
	 */
	@Parameter(defaultValue = "${project.build.directory}/${project.build.finalName}.jar", property = "artifact", required = true)
	protected String artifact;
	
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		File artifactFile = new File(this.artifact);
		if (!artifactFile.exists()) {
			throw new MojoExecutionException("artifact does not exist: " + this.artifact);
		}
		File artifactTmpFile = new File(this.artifact + ".tmp");
		
		try (FileOutputStream fos = new FileOutputStream(artifactTmpFile); FileInputStream fis = new FileInputStream(artifactFile)) {
			StringBuilder header = new StringBuilder();
			header.append("#!/bin/bash");
			header.append('\n');
			header.append(this.getCommand());
			header.append('\n');
			header.append("exit");
			header.append('\n');
			
			fos.write(header.toString().getBytes());
			IOUtils.copy(fis, fos);
			
			artifactTmpFile.setExecutable(true, false);
		} catch (Exception e) {
			throw new MojoExecutionException("Error writing stream", e);
		}
		
		artifactFile.delete();
		artifactTmpFile.renameTo(artifactFile);
	}
	
	protected abstract String getCommand();
	
}
