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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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

	private List<Homeloan> resultList;
	private List<InterestRate> rateList;

	private static final HomeloanFacade facade = new HomeloanFacade();

	public HomeloanBean() {
		startdate = new Date();
		total = new BigDecimal("2600000");
		paid = new BigDecimal("14000");

		final InterestRate rate1 = new InterestRate();
		rate1.setMonth("1 - 6");
		rate1.setRate(new BigDecimal("0"));
		rateList.add(rate1);

		final InterestRate rate2 = new InterestRate();
		rate2.setMonth("7 - 12");
		rate2.setRate(new BigDecimal("4.0"));
		rateList.add(rate2);

		final InterestRate rate3 = new InterestRate();
		rate3.setMonth("13 - 36");
		rate3.setRate(new BigDecimal("6.82"));
		rateList.add(rate3);
	}

	public String calcurate() {
		try {
			resultList = facade.calc(startdate, total, paid, rateList);
			return null;
		} catch(final IllegalArgumentException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot Calcurate", e.getMessage()));
			return null;
		} catch(final Exception e) {
			LOG.error(e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Server Internal Error", e.getMessage()));
			return null;
		}

	}
	
}
