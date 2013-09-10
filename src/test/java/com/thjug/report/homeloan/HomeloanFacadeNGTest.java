/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 * @author nuboat
 */
public class HomeloanFacadeNGTest {

	final HomeloanFacade instance = new HomeloanFacade();
	final List<InterestRate> rateList = new LinkedList<>();
	final List<InterestRate> failList = new LinkedList<>();

	public HomeloanFacadeNGTest() {
		rateList.add(new InterestRate("1 - 6", new BigDecimal("0")));
		rateList.add(new InterestRate("7 - 12", new BigDecimal("4.0")));
		rateList.add(new InterestRate("13 - 36", new BigDecimal("6.82")));
		rateList.add(new InterestRate("37", new BigDecimal("6.42")));

		failList.add(new InterestRate("1 - 12", new BigDecimal("1.25")));
		failList.add(new InterestRate("13 - 26", new BigDecimal("5.25")));
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
	public void testCalcCasePaidRateFail() {
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
		final BigDecimal result = instance.getRate(count, failList);
		assertEquals(result, expResult);
	}

	@Test
	public void testGetRateCaseMorethan() {
		final int count = 56;

		final BigDecimal expResult = new BigDecimal("5.25");
		final BigDecimal result = instance.getRate(count, failList);
		assertEquals(result, expResult);
	}

	@Test
	public void testInLengthCaseTrue() {
		final int count = 12;
		final String month = "1 - 13";

		final boolean expResult = true;
		final boolean result = instance.inLength(count, month);
		assertEquals(result, expResult);
	}

	@Test
	public void testInLengthCaseFasle() {
		final int count = 16;
		final String month = "1 - 13";

		final boolean expResult = false;
		final boolean result = instance.inLength(count, month);
		assertEquals(result, expResult);
	}

}