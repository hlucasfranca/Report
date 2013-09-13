/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.mock;

import javax.faces.context.FacesContext;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * http://illegalargumentexception.blogspot.co.uk/2011/12/jsf-mocking-facescontext-for-unit-tests.html
 * 
 * @author  MCDOWELL
 * @since	TUESDAY, DECEMBER 27, 2011
 */
public abstract class ContextMocker extends FacesContext {

	private ContextMocker() {}

	private static final Release RELEASE = new Release();

	private static class Release implements Answer<Void> {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			setCurrentInstance(null);
			return null;
		}
	}

	public static FacesContext mockFacesContext() {
		FacesContext context = Mockito.mock(FacesContext.class);
		setCurrentInstance(context);
		Mockito.doAnswer(RELEASE)
				.when(context)
				.release();
		return context;
	}

}