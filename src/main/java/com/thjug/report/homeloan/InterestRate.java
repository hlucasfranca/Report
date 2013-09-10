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

/**
 *
 * @author @nuboat
 */
public final class InterestRate {

    private String month;
    private BigDecimal rate;

	public String getMonth() {
		return month;
	}

	/**
	 * Input in range format. Ex 1-36 means Rate for month 1 - month 6
	 * @param month
	 */
	public void setMonth(final String month) {
		this.month = month;
	}

	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * Interest Rate Percent. Ex 5.25%
	 * @param rate
	 */
	public void setRate(final BigDecimal rate) {
		this.rate = rate;
	}

}
