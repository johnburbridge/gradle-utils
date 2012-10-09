package org.metabuild.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException

class PropertyReader extends DefaultTask {

	@Input
	File propertyFile
	Boolean overWriteExisting = false

	@TaskAction
	def void readFile() {
		if (propertyFile.isFile() && propertyFile.exists()) {
			logger.warn("Loading properties: ${propertyFile}")
		} else {
			throw new TaskExecutionException(this, new RuntimeException("Could not find property file ${propertyFile}!"))
		}
	}	
}
