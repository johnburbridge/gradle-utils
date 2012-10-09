package org.metabuild.gradle.tasks

import java.io.File
import java.util.Map

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException

/**
 * Replace the values in a property file
 * 
 * @author jburbridge
 * @since 9/29/2012
 */
class PropertyReplacer extends DefaultTask {
	
	@OutputFile
	File propertyFile
	
	@Input
	Map propertiesMap
	
	@TaskAction
	def void replaceContents() {
		if (propertyFile.isFile() && propertyFile.exists()) {
			def lines = propertyFile.readLines()
			def newLines = []
			lines.each { line ->
				logger.debug("${this} processing line: ${line}")
				// find properties to replace
				def matcher = line =~ /^(.*)\s*\=\s*(.*)$/
				if (matcher.size() != 0 && matcher[0] != null) {
					newLines.add((propertiesMap[matcher[0][1]] != null) ?
						"${matcher[0][1]}=${propertiesMap[matcher[0][1]]}" : line)
				// if we don't find a match, just add the line back as-is
				} else {
					newLines.add(line)
				}
			}
			propertyFile.write(newLines.join("\n"))
			logger.warn("Wrote file: ${propertyFile}")
		} else {
			throw new TaskExecutionException(this, new RuntimeException("Could not find property file ${propertyFile}!"))
		}
	}
}
