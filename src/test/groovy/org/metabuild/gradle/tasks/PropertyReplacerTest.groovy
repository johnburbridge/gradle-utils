package org.metabuild.gradle.tasks;

import static org.junit.Assert.*;
import org.junit.Test;

import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project

/**
 * @author jburbrid
 * @since 10/8/2012
 */
class PropertyReplacerTest {

	/**
	 * Test method for {@link org.metabuild.gradle.tasks.PropertyReplacer#replaceContents()}.
	 */
	@Test
	public void testReplaceContents() {
		def x = 0
		def date = new Date().toString()
		def replacementMap = [ 
			'replacer.text1' : "${date}-${x++}",
			'replacer.text2' : "${date}-${x++}",
			'replacer.text3' : "${date}-${x++}",
			'replacer.text4' : "${date}-${x++}",
			'replacer.text5' : "${date}-${x++}"
		]
		def propFile = new File('replacer.properties')
		Project project = ProjectBuilder.builder().build()
		
		def task = project.task('replaceProps', type: PropertyReplacer)
		task.setProperty('propertyFile', propFile)
		task.setProperty('propertiesMap', replacementMap)
		
		assertTrue(task instanceof PropertyReplacer)
		// TODO: need to improve tests
	}

}
