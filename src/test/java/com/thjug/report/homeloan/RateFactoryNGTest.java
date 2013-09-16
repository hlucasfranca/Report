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

import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author @nuboat
 */
public final class RateFactoryNGTest {

	@Test
	public void testGetRate() throws Exception {
		final String bankname = "Oomsin";
		final List<InterestRate> result = RateFactory.getRate(bankname);
		assertNotNull(result);
	}

	@Test
	public void testGetInstance() throws Exception {
		assertNotNull(RateFactory.getInstance());
	}

}