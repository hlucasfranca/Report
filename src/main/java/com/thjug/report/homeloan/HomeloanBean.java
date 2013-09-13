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

	private static final BigDecimal SCBTPLAN12 = new BigDecimal("4");
	private static final BigDecimal SCBTPLAN13 = new BigDecimal("6.82");

	private static final BigDecimal OOMSIMPLAN11 = new BigDecimal("1.25");
	private static final BigDecimal OOMSIMPLAN12 = new BigDecimal("5");
	private static final BigDecimal OOMSIMPLAN13 = new BigDecimal("6.5");

	public HomeloanBean() {
		show = false;
		startdate = new Date();
		homeloanModel = new CartesianChartModel();
	}

	public void recalculate() {
		show = false;
		homeloanModel = new CartesianChartModel();
	}

	public void genSCBTPlan1() {
		final List<InterestRate> scnbList = new LinkedList<>();
		scnbList.add(new InterestRate("1 - 6", BigDecimal.ZERO));
		scnbList.add(new InterestRate("7 - 12", SCBTPLAN12));
		scnbList.add(new InterestRate("13 - 36", SCBTPLAN13));

		scnbList.add(new InterestRate("37 - 42", BigDecimal.ZERO));
		scnbList.add(new InterestRate("43 - 48", SCBTPLAN12));
		scnbList.add(new InterestRate("49 - 72", SCBTPLAN13));

		scnbList.add(new InterestRate("73 - 78", BigDecimal.ZERO));
		scnbList.add(new InterestRate("79 - 84", SCBTPLAN12));
		scnbList.add(new InterestRate("85 - 108", SCBTPLAN13));

		scnbList.add(new InterestRate("109 - 114", BigDecimal.ZERO));
		scnbList.add(new InterestRate("115 - 120", SCBTPLAN12));
		scnbList.add(new InterestRate("121 - 144", SCBTPLAN13));

		scnbList.add(new InterestRate("145 - 160", BigDecimal.ZERO));
		scnbList.add(new InterestRate("161 - 166", SCBTPLAN12));
		scnbList.add(new InterestRate("167 - 180", SCBTPLAN13));

		scnbList.add(new InterestRate("181 - 186", BigDecimal.ZERO));
		scnbList.add(new InterestRate("187 - 192", SCBTPLAN12));
		scnbList.add(new InterestRate("193 - 216", SCBTPLAN13));

		scnbList.add(new InterestRate("217 - 222", BigDecimal.ZERO));
		scnbList.add(new InterestRate("223 - 228", SCBTPLAN12));
		scnbList.add(new InterestRate("229 - 252", SCBTPLAN13));

		scnbList.add(new InterestRate("253 - 258", BigDecimal.ZERO));
		scnbList.add(new InterestRate("259 - 264", SCBTPLAN12));
		scnbList.add(new InterestRate("265 - 288", SCBTPLAN13));

		scnbList.add(new InterestRate("289 - 294", BigDecimal.ZERO));
		scnbList.add(new InterestRate("295 - 300", SCBTPLAN12));
		scnbList.add(new InterestRate("301 - 324", SCBTPLAN13));

		scnbList.add(new InterestRate("325 - 330", BigDecimal.ZERO));
		scnbList.add(new InterestRate("331 - 336", SCBTPLAN12));
		scnbList.add(new InterestRate("337", SCBTPLAN13));

		homeloanModel.addSeries(calculate("SCBT", scnbList));
	}

	public void genOomsinPlan1() {
		final List<InterestRate>  oomsinList = new LinkedList<>();
		oomsinList.add(new InterestRate("1 - 12", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("12 - 24", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("25 - 36", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("37 - 48", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("49 - 60", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("61 - 72", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("73 - 84", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("85 - 96", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("97 - 108", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("109 - 120", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("121 - 132", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("133 - 144", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("145 - 156", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("157 - 168", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("169 - 180", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("181 - 192", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("193 - 204", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("205 - 216", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("217 - 228", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("229 - 240", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("241 - 252", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("253 - 264", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("265 - 276", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("277 - 288", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("289 - 294", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("295 - 306", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("301 - 324", OOMSIMPLAN13));

		oomsinList.add(new InterestRate("325 - 336", OOMSIMPLAN11));
		oomsinList.add(new InterestRate("337 - 348", OOMSIMPLAN12));
		oomsinList.add(new InterestRate("349", OOMSIMPLAN13));

		homeloanModel.addSeries(calculate("Oomsin", oomsinList));
	}

	public String genReport() {
		recalculate();
		genSCBTPlan1();
		genOomsinPlan1();
		return null;
	}

	public LineChartSeries calculate(final String bank, final List<InterestRate> rateList) {
		try {
			final List<Homeloan> result =  FACADE.calc(startdate, total, paid, rateList);
			final double totally = paid.multiply(new BigDecimal(result.size()-1))
					.add(result.get(result.size()-1).getPaid()).doubleValue();
			final LineChartSeries amountLine = new LineChartSeries(bank +" : Total " + totally + " baht");
			for (final Homeloan h : result) {
				amountLine.set(h.getCount(), h.getTotalafterpaid().doubleValue());
			}

			paidmonth = (result.size() > paidmonth) ? result.size() : paidmonth;

			show = true;
			return amountLine;
		} catch(final IllegalArgumentException e) {
			LOG.info(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, bank,  "Cannot Calculate"));
		}
		
		return new LineChartSeries(bank +" : Cannot Calculate");
	}

	public CartesianChartModel getHomeloanModel() {
		return homeloanModel;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(final Date startdate) {
		this.startdate = startdate;
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
