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
import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.testng.annotations.Test;

/**
 *
 * @author @nuboat
 */
public class InterestRateNGTest {

	final static ObjectMapper mapper = new ObjectMapper();

	final List<InterestRate> rateList = new LinkedList<>();
	final InterestRate rate = new InterestRate("1-12", new BigDecimal("4"));

	public InterestRateNGTest() {
		rateList.add(new InterestRate(" 1 - 12", new BigDecimal("4")));
		rateList.add(new InterestRate("13 - 24", new BigDecimal("4")));
	}
	
	@Test
	public void testBeanToJson() throws IOException {
		final String json = mapper.writeValueAsString(rate);
		assertEquals(json, "{\"month\":\"1-12\",\"rate\":4}");
	}

	@Test
	public void testJsonToBean() throws IOException {
		final String json = "{\"month\":\"1-12\",\"rate\":4}";
		final InterestRate instance = mapper.readValue(json, InterestRate.class);
		assertEquals(instance.getMonth(), rate.getMonth());
	}

	@Test
	public void testBeanToJson2() throws IOException {
		final String json = mapper.writeValueAsString(rateList);
		assertEquals(json, "[{\"month\":\" 1 - 12\",\"rate\":4},{\"month\":\"13 - 24\",\"rate\":4}]");
	}

	@Test
	public void testJsonToBean2() throws IOException {
		final String json = "[{\"month\":\" 1 - 12\",\"rate\":4},{\"month\":\"13 - 24\",\"rate\":4}]";
		final List<InterestRate> instance = mapper.readValue(json, new TypeReference<List<InterestRate>>(){});
		assertEquals(instance.get(0).getMonth(), " 1 - 12");
		assertEquals(instance.get(1).getMonth(), "13 - 24");
	}

}
