package de.taimos.daemon.plugin;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "daemon", defaultPhase = LifecyclePhase.PACKAGE)
public class DaemonMojo extends AbstractJarHeaderMojo {
	
	/**
	 * javaOpts
	 */
	@Parameter(defaultValue = "", property = "javaOpts")
	private String javaOpts;
	
	/**
	 * startupMode
	 */
	@Parameter(defaultValue = "run", property = "startupMode", required = true)
	private String startupMode;
	
	/**
	 * mainClass
	 */
	@Parameter(defaultValue = "${mainClass}", property = "mainClass", required = true)
	private String mainClass;
	
	/**
	 * maxMem
	 */
	@Parameter(defaultValue = "256m", property = "maxMem", required = true)
	private String maxMem;
	
	/**
	 * jar file name
	 */
	@Parameter(defaultValue = "${project.build.finalName}", property = "jarFile", required = true)
	private String jarFile;
	
	
	@Override
	protected String getCommand() {
		String opts = this.javaOpts != null ? this.javaOpts : "";
		
		return String.format("java -cp '%s.jar:lib/*' -Xmx%s -XX:OnOutOfMemoryError='kill -9 %%p' -DstartupMode=%s -Djava.net.preferIPv4Stack=true %s %s \"$@\"", this.jarFile, this.maxMem, this.startupMode, opts, this.mainClass);
	}
	
}
