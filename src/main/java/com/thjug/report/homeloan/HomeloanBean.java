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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@RequestScoped
@ManagedBean(name = "homeloan")
public final class HomeloanBean implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(HomeloanBean.class);

	private Date startdate;
	private BigDecimal total;
	private BigDecimal paid;
	private int paidmonth = 0;

	private CartesianChartModel homeloanModel = new CartesianChartModel();
	
	private static final HomeloanFacade facade = new HomeloanFacade();

	public HomeloanBean() {
		startdate = new Date();
		total = new BigDecimal("2400000");
		paid = new BigDecimal("16000");

		final List<InterestRate> scnbList = new LinkedList<>();
		scnbList.add(new InterestRate("1 - 6", new BigDecimal("0")));
		scnbList.add(new InterestRate("7 - 12", new BigDecimal("4")));
		scnbList.add(new InterestRate("13 - 36", new BigDecimal("6.82")));

		scnbList.add(new InterestRate("37 - 42", new BigDecimal("0")));
		scnbList.add(new InterestRate("43 - 48", new BigDecimal("4")));
		scnbList.add(new InterestRate("49 - 72", new BigDecimal("6.82")));

		scnbList.add(new InterestRate("73 - 78", new BigDecimal("0")));
		scnbList.add(new InterestRate("79 - 84", new BigDecimal("4")));
		scnbList.add(new InterestRate("85 - 108", new BigDecimal("6.82")));

		scnbList.add(new InterestRate("109 - 114", new BigDecimal("0")));
		scnbList.add(new InterestRate("115 - 120", new BigDecimal("4")));
		scnbList.add(new InterestRate("121 - 144", new BigDecimal("6.82")));

		scnbList.add(new InterestRate("145 - 160", new BigDecimal("0")));
		scnbList.add(new InterestRate("161 - 166", new BigDecimal("4")));
		scnbList.add(new InterestRate("167 - 180", new BigDecimal("6.82")));

		scnbList.add(new InterestRate("181 - 186", new BigDecimal("0")));
		scnbList.add(new InterestRate("187 - 192", new BigDecimal("4")));
		scnbList.add(new InterestRate("193", new BigDecimal("6.82")));

		final List<Homeloan> resultScnbList = calcurate(startdate, total, paid, scnbList);
		if (resultScnbList != null) {
			final double totally = paid.multiply(new BigDecimal(resultScnbList.size()-1))
					.add(resultScnbList.get(resultScnbList.size()-1).getPaid()).doubleValue();
			final LineChartSeries amountLine = new LineChartSeries("SCNB : Total " + totally + " baht");
			for (final Homeloan h : resultScnbList) {
				amountLine.set(h.getCount(), h.getTotalafterpaid().doubleValue());
			}

			homeloanModel.addSeries(amountLine);
			paidmonth = (resultScnbList.size() > paidmonth) ? resultScnbList.size() : paidmonth;
		}

		final List<InterestRate>  oomsinList = new LinkedList<>();
		oomsinList.add(new InterestRate("1 - 12", new BigDecimal("1.25")));
		oomsinList.add(new InterestRate("12 - 24", new BigDecimal("4")));
		oomsinList.add(new InterestRate("25 - 36", new BigDecimal("6.5")));

		oomsinList.add(new InterestRate("37 - 48", new BigDecimal("1.25")));
		oomsinList.add(new InterestRate("49 - 60", new BigDecimal("4")));
		oomsinList.add(new InterestRate("61 - 72", new BigDecimal("6.5")));

		oomsinList.add(new InterestRate("73 - 84", new BigDecimal("1.25")));
		oomsinList.add(new InterestRate("85 - 96", new BigDecimal("4")));
		oomsinList.add(new InterestRate("97 - 108", new BigDecimal("6.5")));

		oomsinList.add(new InterestRate("109 - 120", new BigDecimal("1.25")));
		oomsinList.add(new InterestRate("121 - 132", new BigDecimal("4")));
		oomsinList.add(new InterestRate("133 - 144", new BigDecimal("6.5")));

		oomsinList.add(new InterestRate("145 - 156", new BigDecimal("1.25")));
		oomsinList.add(new InterestRate("157 - 168", new BigDecimal("4")));
		oomsinList.add(new InterestRate("169 - 180", new BigDecimal("6.5")));

		oomsinList.add(new InterestRate("181 - 192", new BigDecimal("1.25")));
		oomsinList.add(new InterestRate("193 - 204", new BigDecimal("4")));
		oomsinList.add(new InterestRate("205 - 216", new BigDecimal("6.5")));
		final List<Homeloan> resultOomsinList = calcurate(startdate, total, paid, oomsinList);
		if (resultOomsinList != null) {
			final double totally = paid.multiply(new BigDecimal(resultOomsinList.size()-1))
					.add(resultOomsinList.get(resultOomsinList.size()-1).getPaid()).doubleValue();
			final LineChartSeries amountLine = new LineChartSeries("Oomsin : Total " + totally + " baht");
			for (final Homeloan h : resultOomsinList) {
				amountLine.set(h.getCount(), h.getTotalafterpaid().doubleValue());
			}

			homeloanModel.addSeries(amountLine);
			paidmonth = (resultOomsinList.size() > paidmonth) ? resultOomsinList.size() : paidmonth;
		}
	}

	public List<Homeloan> calcurate(final Date startdate, final BigDecimal total, final BigDecimal paid,
			final List<InterestRate> rateList) {
		try {
			return facade.calc(startdate, total, paid, rateList);
		} catch(final IllegalArgumentException e) {
			LOG.error(e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Cannot Calcurate", e.getMessage()));
			return null;
		} catch(final Exception e) {
			LOG.error(e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Server Internal Error", e.getMessage()));
			return null;
		}

	}

	public CartesianChartModel getHomeloanModel() {
		return homeloanModel;
	}

	public Date getStartdate() {
		return startdate;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getPaid() {
		return paid;
	}

	public int getPaidmonth() {
		return paidmonth;
	}

	
}
