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

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "custom", defaultPhase = LifecyclePhase.PACKAGE)
public class CustomMojo extends AbstractJarHeaderMojo {
	
	/**
	 * command to execute
	 */
	@Parameter(property = "command", required = true)
	private String command;
	
	
	@Override
	protected String getCommand() {
		return this.command;
	}
	
}
