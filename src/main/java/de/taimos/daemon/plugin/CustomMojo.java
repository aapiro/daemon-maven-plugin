package de.taimos.daemon.plugin;

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
