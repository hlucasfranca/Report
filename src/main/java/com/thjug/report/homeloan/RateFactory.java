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
import java.util.List;
import java.util.ResourceBundle;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author @nuboat
 */
public final class RateFactory {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final ResourceBundle RB = ResourceBundle.getBundle("interests");

	public static List<InterestRate> getRate(final String bankname)
		throws IOException {
		return MAPPER.readValue(RB.getString(bankname), new TypeReference<List<InterestRate>>(){});
	}

}
