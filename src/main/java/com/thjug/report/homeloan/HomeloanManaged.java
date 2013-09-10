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
@ManagedBean
@RequestScoped
public final class HomeloanManaged implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(HomeloanManaged.class);

	private Date startdate;
	private BigDecimal total;
	private BigDecimal paid;

	private List<Homeloan> resultList;
	private List<InterestRate> rateList;

	private static final HomeloanFacade facade = new HomeloanFacade();

	public String calcurate() {
		try {
			resultList = facade.calc(startdate, total, paid, rateList);
			return "report";
		} catch(final IllegalArgumentException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot Calcurate", e.getMessage()));
			return null;
		} catch(final Exception e) {
			LOG.error(e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Server Internal Error", e.getMessage()));
			return null;
		}

	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(final Date startdate) {
		this.startdate = startdate;
	}

	public List<Homeloan> getResultList() {
		return resultList;
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

	public List<InterestRate> getRateList() {
		return rateList;
	}

	public void setRateList(final List<InterestRate> rateList) {
		this.rateList = rateList;
	}

	public void setResultList(final List<Homeloan> resultList) {
		this.resultList = resultList;
	}

}
