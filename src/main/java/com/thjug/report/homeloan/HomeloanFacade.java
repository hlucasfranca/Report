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
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
public final class HomeloanFacade {

	private static final Logger LOG = LoggerFactory.getLogger(HomeloanFacade.class);

	private static final DecimalFormat monthFormat = new DecimalFormat("00");
	private static final BigDecimal PERCENT = new BigDecimal("100");

	public List<Homeloan> calc(final Date startdate, final BigDecimal total, final BigDecimal paid,
			final List<InterestRate> rateList)
			throws IllegalArgumentException {
		final List<Homeloan> result = new LinkedList<>();

		BigDecimal summary = total.abs();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(startdate);
		do {
			int month = calendar.get(Calendar.MONTH) + 1;
			int year  = calendar.get(Calendar.YEAR);

			final BigDecimal rate = getRate(result.size()+1, rateList);
			final BigDecimal interest = getInterest(year, month, rate, summary);

			if (interest.doubleValue() >= paid.doubleValue()) {
				throw new IllegalArgumentException(
						 "You paid less than your interest. \n"
						+"Please increse your amout.");
			}

			summary = summary.add(interest);

			final Homeloan loan = new Homeloan();
			loan.setTotalbeforepaid(summary);
			loan.setCount(result.size()+1);
			loan.setMonth(month);
			loan.setYear(year);
			loan.setInterest(interest);
			if (paid.doubleValue() >= summary.doubleValue()) {
				loan.setPaid(summary);
				summary = summary.subtract(summary);
			} else {
				loan.setPaid(paid);
				summary = summary.subtract(paid);
				result.add(loan);
			}
			loan.setTotalafterpaid(summary);

			calendar.add(Calendar.MONTH, 1);

			LOG.info("#{} {}{}	{} + {} - {} = {}",
						loan.getCount(), loan.getYear(), monthFormat.format(loan.getMonth()),
						loan.getTotalbeforepaid(), loan.getInterest(), loan.getPaid(), loan.getTotalafterpaid());
		} while(summary.doubleValue() > 0);

		final BigDecimal totally = paid.multiply(new BigDecimal(result.size()));
		LOG.info("Totally paid {} monty amount {} baht", result.size(), totally);

		return result;
	}

	public BigDecimal getInterest(final int year, final int month, final BigDecimal rate, final BigDecimal amount) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);

		final BigDecimal numberofdayinmonth = new BigDecimal(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		final BigDecimal numberofdayinyear =  new BigDecimal(calendar.getActualMaximum(Calendar.DAY_OF_YEAR));

		return amount.multiply(rate).multiply(numberofdayinmonth)
				.divide(PERCENT).divide(numberofdayinyear, 2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getRate(final int count, final List<InterestRate> rateList)
		throws IllegalArgumentException {
		for (final InterestRate interestRate : rateList) {
			if (inLength(count, interestRate.getMonth())) {
				return interestRate.getRate();
			}
		}

		throw new IllegalArgumentException("Interest not cover.");
	}

	public boolean inLength(final int count, final String month) {
		final String[] len = month.split("-");
		final int start = Integer.valueOf(len[0].trim());
		final int end = (len.length == 2) ? Integer.valueOf(len[1].trim()) : Integer.MAX_VALUE;

		return (start <= count && count <= end) ? true : false;
	}

}
