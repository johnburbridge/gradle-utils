package org.metabuild.gradle.tasks

import java.util.logging.Logger

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.OutputDirectories

/**
 * Create a list of directories if they don't already exist<br>
 * <br>
 * Usage:<br>
 * <pre>
 * task initializeDirs(type: CreateDirectories) {
 *     directories = [ sourceSets*.java.srcDirs, sourceSets*.resources.srcDirs ]
 * }
 * </pre>
 * @author jburbridge
 *
 */
class CreateDirectories extends DefaultTask {

	Logger logger = Logger.getLogger("CreateDirectoriesTask")
	
	@OutputDirectories
	List<File> directories

	@TaskAction
	def createDirectories() {
		directories.each { File directory ->
			if (!directory.exists()) {
				logger.warn("Creating ${directory}")
				directory.mkdirs()
			}
		}
	}
}
