/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.report.homeloan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.primefaces.model.chart.CartesianChartModel;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author PeerapatAsoktummarun
 */
public class HomeloanBeanNGTest {

	@Test
	public void testConstructor() {
		final HomeloanBean instance = new HomeloanBean();

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
	}

}