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
public final class Homeloan {

	private int year;
	private int month;
	private int count;
	private BigDecimal paid;
	private BigDecimal interest;
	private BigDecimal totalbeforepaid;
	private BigDecimal totalafterpaid;

	public int getYear() {
		return year;
	}

	public void setYear(final int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(final int month) {
		this.month = month;
	}

	public int getCount() {
		return count;
	}

	public void setCount(final int count) {
		this.count = count;
	}

	public BigDecimal getPaid() {
		return paid;
	}

	public void setPaid(final BigDecimal paid) {
		this.paid = paid;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(final BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getTotalbeforepaid() {
		return totalbeforepaid;
	}

	public void setTotalbeforepaid(final BigDecimal totalbeforepaid) {
		this.totalbeforepaid = totalbeforepaid;
	}

	public BigDecimal getTotalafterpaid() {
		return totalafterpaid;
	}

	public void setTotalafterpaid(final BigDecimal totalafterpaid) {
		this.totalafterpaid = totalafterpaid;
	}

}
