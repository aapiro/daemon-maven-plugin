# Daemon Plugin for Maven

[![Build Status](https://travis-ci.org/taimos/daemon-maven-plugin.png?branch=master)](https://travis-ci.org/taimos/daemon-maven-plugin)

Writes bash script headers to jar files to make them executable  

```
<plugins>
	<plugin>
		<groupId>de.taimos</groupId>
		<artifactId>daemon-maven-plugin</artifactId>
		<version>0.1-SNAPSHOT</version>
		<configuration>
		</configuration>
	</plugin>
</plugins>
```

## Goals

### standalone

writes the following command to the header ``"java -jar "$0" "$@"``

### daemon

writes the following command to the header

``java -cp '<jarFile>.jar:lib/*' -Xmx<maxMem> -XX:OnOutOfMemoryError='kill -9 %p' -DstartupMode=<startupMode> <javaOpts> <mainClass> "$@"``

``javaOpts`` defaults to empty string

``startupMode`` defaults to ``run``

``mainClass`` defaults to ``${mainClass}``

``maxMem`` defaults to ``256m``

``jarFile`` defaults to ``${project.build.finalName}``

### custom

``command`` the command to write to the header
