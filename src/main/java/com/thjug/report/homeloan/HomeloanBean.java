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

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ViewScoped
@ManagedBean(name = "homeloan")
public final class HomeloanBean extends AbstractBean {

	private static final Logger LOG = LoggerFactory.getLogger(HomeloanBean.class);

	private Date startdate;
	private BigDecimal total;
	private BigDecimal paid;
	private int paidmonth = 0;
	private boolean show;
	private CartesianChartModel homeloanModel;
	private static final HomeloanFacade FACADE = new HomeloanFacade();

	public HomeloanBean() {
		show = false;
		startdate = new Date();
		homeloanModel = new CartesianChartModel();
	}

	public void recalculate() {
		show = false;
		homeloanModel = new CartesianChartModel();
	}

	public String genReport()
			throws IOException {
		recalculate();
		homeloanModel.addSeries(calculate("SCBT", RateFactory.getRate("SCBT")));
		homeloanModel.addSeries(calculate("Oomsin", RateFactory.getRate("Oomsin")));
		homeloanModel.addSeries(calculate("UOB", RateFactory.getRate("UOB")));
		homeloanModel.addSeries(calculate("Krungsri", RateFactory.getRate("Krungsri")));
		homeloanModel.addSeries(calculate("BBL", RateFactory.getRate("BBL")));
		return null;
	}

	public LineChartSeries calculate(final String bank, final List<InterestRate> rateList) {
		try {
			final List<Homeloan> result = FACADE.calc(startdate, total, paid, rateList);
			final double totally = paid.multiply(new BigDecimal(result.size() - 1))
					.add(result.get(result.size() - 1).getPaid()).doubleValue();
			final LineChartSeries amountLine = new LineChartSeries(bank + " : Total " + totally + " baht");
			for (final Homeloan h : result) {
				amountLine.set(h.getCount(), h.getTotalafterpaid().doubleValue());
			}

			paidmonth = (result.size() > paidmonth) ? result.size() : paidmonth;

			show = true;
			return amountLine;
		} catch (final IllegalArgumentException e) {
			LOG.info(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, bank, "Cannot Calculate"));
		}

		return new LineChartSeries(bank + " : Cannot Calculate");
	}

	public CartesianChartModel getHomeloanModel() {
		return homeloanModel;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(final BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getPaid() {
		return paid;
	}

	public void setPaid(final BigDecimal paid) {
		this.paid = paid;
	}

	public int getPaidmonth() {
		return paidmonth;
	}

	public boolean isShow() {
		return show;
	}

}
