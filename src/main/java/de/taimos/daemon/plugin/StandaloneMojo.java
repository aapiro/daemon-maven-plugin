package de.taimos.daemon.plugin;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "standalone", defaultPhase = LifecyclePhase.PACKAGE)
public class StandaloneMojo extends AbstractJarHeaderMojo {
	
	@Override
	protected String getCommand() {
		return "java -jar \"$0\" \"$@\"";
	}
	
}
