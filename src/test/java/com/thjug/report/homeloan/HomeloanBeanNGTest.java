/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.report.homeloan;

import com.thjug.mock.ContextMocker;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.context.FacesContext;

import org.jboss.test.faces.mock.MockFacesEnvironment;
import org.primefaces.model.chart.CartesianChartModel;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author PeerapatAsoktummarun
 */
public class HomeloanBeanNGTest {

	private MockFacesEnvironment environment;

	@Test
	public void testGetter() {
		final HomeloanBean instance = new HomeloanBean();
		instance.setStartdate(new Date());
		instance.setTotal(new BigDecimal("2400000"));
		instance.setPaid(new BigDecimal("16000"));

		final CartesianChartModel result1 = instance.getHomeloanModel();
		assertNotNull(result1);

		final Date result2 = instance.getStartdate();
		assertNotNull(result2);

		final BigDecimal result3 = instance.getTotal();
		assertNotNull(result3);

		final BigDecimal result4 = instance.getPaid();
		assertNotNull(result4);

		final int result5 = instance.getPaidmonth();
		assertNotNull(result5);

		final boolean result6 = instance.isShow();
		assertEquals(result6, false);
	}

	@Test
	public void testGetDefaultSCNB() {
		final HomeloanBean instance = new HomeloanBean();
		instance.setStartdate(new Date());
		instance.setTotal(new BigDecimal("2400000"));
		instance.setPaid(new BigDecimal("16000"));
		instance.getDefaultSCNB();

		final CartesianChartModel model = instance.getHomeloanModel();
		assertEquals(model.getSeries().isEmpty(), false);

		final boolean show = instance.isShow();
		assertEquals(show, true);
	}

	@Test
	public void testGetDefaultOomsin() {
		final HomeloanBean instance = new HomeloanBean();
		instance.setStartdate(new Date());
		instance.setTotal(new BigDecimal("2400000"));
		instance.setPaid(new BigDecimal("16000"));
		instance.getDefaultOomsin();

		final CartesianChartModel model = instance.getHomeloanModel();
		assertEquals(model.getSeries().isEmpty(), false);

		final boolean show = instance.isShow();
		assertEquals(show, true);
	}

	@Test
	public void testRecalculate() {
		final HomeloanBean instance = new HomeloanBean();
		instance.recalculate();

		final CartesianChartModel model = instance.getHomeloanModel();
		assertEquals(model.getSeries().isEmpty(), true);

		final boolean show = instance.isShow();
		assertEquals(show, false);
	}

	@Test
	public void testCalculateError() {
		FacesContext context = ContextMocker.mockFacesContext();
		
		final HomeloanBean instance = new HomeloanBean();
		instance.setStartdate(new Date());
		instance.setTotal(new BigDecimal("2400000"));
		instance.setPaid(new BigDecimal("6000"));

		final List<InterestRate> oomsinList = new LinkedList<>();
		oomsinList.add(new InterestRate("1", new BigDecimal("5")));

		instance.calcurate(oomsinList);

		context.release();
	}

}