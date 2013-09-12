/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.report.homeloan;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author PeerapatAsoktummarun
 */
public class AbstractBeanNGTest {

	@Test
	public void testMesssages() {
		final AbstractBean instance = new AbstractBeanImpl();

		instance.addMessage("NB");
		assertEquals(instance.getMesssages().size(), 1);

		instance.clear();
		assertEquals(instance.getMesssages().size(), 0);
	}

	public class AbstractBeanImpl extends AbstractBean { }
	
}