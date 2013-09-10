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
		final InterestRate rate1 = new InterestRate();
		rate1.setMonth("1 - 6");
		rate1.setRate(new BigDecimal("0"));
		rateList.add(rate1);

		final InterestRate rate2 = new InterestRate();
		rate2.setMonth("7 - 12");
		rate2.setRate(new BigDecimal("4.0"));
		rateList.add(rate2);

		final InterestRate rate3 = new InterestRate();
		rate3.setMonth("13-36");
		rate3.setRate(new BigDecimal("6.82"));
		rateList.add(rate3);

		final InterestRate rate4 = new InterestRate();
		rate4.setMonth("37");
		rate4.setRate(new BigDecimal("6.42"));
		rateList.add(rate4);

		final InterestRate failrate1 = new InterestRate();
		failrate1.setMonth("1 - 12");
		failrate1.setRate(new BigDecimal("1.25"));
		failList.add(failrate1);

		final InterestRate failrate2 = new InterestRate();
		failrate2.setMonth("13 - 26");
		failrate2.setRate(new BigDecimal("5.25"));
		failList.add(failrate2);
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