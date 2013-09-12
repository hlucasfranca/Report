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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public abstract class AbstractBean implements Serializable  {

	private List<String> messsages;

	public AbstractBean() {
		messsages = new LinkedList<>();
	}

	public void clear() {
		messsages.clear();
	}

	public List<String> getMesssages() {
		return messsages;
	}

	public void addMessage(final String msg) {
		messsages.add(msg);
	}

}
