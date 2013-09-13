/*
 * Attribution
 * CC BY
 * This license lets others distribute, remix, tweak,
 * and build upon your work, even commercially,
 * as long as they credit you for the original creation.
 * This is the most accommodating of licenses offered.
 * Recommended for maximum dissemination and use of licensed materials.
 *
 * http://creativecommons.org/licenses/by/3.0/
 * http://creativecommons.org/licenses/by/3.0/legalcode
 */
package com.thjug.report.homeloan;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author @nuboat
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