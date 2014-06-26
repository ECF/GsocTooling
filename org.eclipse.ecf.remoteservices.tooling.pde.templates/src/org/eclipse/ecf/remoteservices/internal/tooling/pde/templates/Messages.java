/*******************************************************************************
 * Copyright (c) 2014 Sakith Indula Jun 25, 2014. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates;

import org.eclipse.osgi.util.NLS;

/**
 * @author Saki
 * 
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.ecf.remoteservices.internal.tooling.pde.templates.messages"; //$NON-NLS-1$
	public static String TimeServiceConsumerWizard_0;
	public static String TimeServiceDsAsyncWizard_0;
	public static String TimeServiceDSConsumerWizard_0;
	public static String TimeServiceHostTemplate_6;
	public static String TimeServiceHostTemplate_7;
	public static String TimeServiceHostWizard_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
