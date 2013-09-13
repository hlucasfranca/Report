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

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author @nuboat
 */
public class HomeloanFacadeNGTest {

	final HomeloanFacade instance = new HomeloanFacade();
	final List<InterestRate> rateList = new LinkedList<>();
	final List<InterestRate> failList = new LinkedList<>();

	public HomeloanFacadeNGTest() {
		rateList.add(new InterestRate("1 - 12", new BigDecimal("1.25")));
		rateList.add(new InterestRate("13", new BigDecimal("5.25")));

		failList.add(new InterestRate("1 - 12", new BigDecimal("1.25")));
		failList.add(new InterestRate("13 - 24", new BigDecimal("5.25")));
	}

	@Test
	public void testCalcCaseSuccess() {
		final Date startdate = new Date();
		final BigDecimal total = new BigDecimal("2400000");
		final BigDecimal paid = new BigDecimal("16000");

		final List result = instance.calc(startdate, total, paid, rateList);
		assertNotNull(result);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCalcCasePaidNotCover() {
		final Date startdate = new Date();
		final BigDecimal total = new BigDecimal("2400000");
		final BigDecimal paid = new BigDecimal("6000");

		final List result = instance.calc(startdate, total, paid, rateList);
		assertNotNull(result);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCalcCaseRateFail() {
		final Date startdate = new Date();
		final BigDecimal total = new BigDecimal("2400000");
		final BigDecimal paid = new BigDecimal("16000");

		final List result = instance.calc(startdate, total, paid, failList);
		assertNotNull(result);
	}

	@Test
	public void testGetInterest() {
		final int year  = 2013;
		final int month = 9;

		final BigDecimal rate = new BigDecimal("1");
		final BigDecimal amount = new BigDecimal("100000");

		final BigDecimal expResult = new BigDecimal("82.19");
		final BigDecimal result = instance.getInterest(year, month, rate, amount);

		assertEquals(result, expResult);
	}

	@Test
	public void testGetRateCaseBetween() {
		final int count = 12;

		final BigDecimal expResult = new BigDecimal("1.25");
		final BigDecimal result = instance.getRate(count, rateList);
		assertEquals(result, expResult);
	}

	@Test
	public void testGetRateCaseMorethan() {
		final int count = 56;

		final BigDecimal expResult = new BigDecimal("5.25");
		final BigDecimal result = instance.getRate(count, rateList);
		assertEquals(result, expResult);
	}

	@Test
	public void testInLengthCaseSuccess() {
		final int count = 12;
		final String month = "1 - 13";

		final boolean expResult = true;
		final boolean result = instance.inLength(count, month);
		assertEquals(result, expResult);
	}

	@Test
	public void testInLengthCaseBelow() {
		final int count = 6;
		final String month = "13 - 24";

		final boolean expResult = false;
		final boolean result = instance.inLength(count, month);
		assertEquals(result, expResult);
	}

	@Test
	public void testInLengthCaseUpper() {
		final int count = 26;
		final String month = "13 - 24";

		final boolean expResult = false;
		final boolean result = instance.inLength(count, month);
		assertEquals(result, expResult);
	}

}