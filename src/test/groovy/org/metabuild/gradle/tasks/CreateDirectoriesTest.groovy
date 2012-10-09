package org.metabuild.gradle.tasks

import static org.junit.Assert.*
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test
import org.gradle.api.Project

/**
 * @author jburbrid
 *
 */
class CreateDirectoriesTest {

	@Test
	public void canAddTaskToProject() {
		Project project = ProjectBuilder.builder().build()
		def task = project.task('initDirs', type: CreateDirectories)
		assertTrue(task instanceof CreateDirectories)
	}
}
