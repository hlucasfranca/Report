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
package com.thjug.mock;

import javax.faces.application.FacesMessage;

/**
 *
 * @author @nuboat
 */
public final class MockFacesMessage extends FacesMessage {

	public MockFacesMessage(final Severity severity, final String summary, final String detail) {
		super(severity, summary, detail);
	}

	@Override
	public boolean equals(final Object f) {
		return (f instanceof FacesMessage) ? true : false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		return hash;
	}

}
