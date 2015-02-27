/*******************************************************************************
 * Copyright (c) 2013 Sakith Indula. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates;

import org.eclipse.core.runtime.IProgressMonitor;

public class TimeServiceDSAsyncConsumerTemplate extends TemplateSection {

	/**
	 * @return the location of the template file
	 */
	@Override
	public String getSectionId() {

		return "TimeServiceDSAsyncConsumerTemplate"; //$NON-NLS-1$
	}

	/**
	 * update and add service-component service header to the project manifest file
	 */
	@Override
	protected void updateModel(IProgressMonitor monitor) {
		setManifestHeader(
				"Service-Component", "OSGI-INF/timeservicecomponentasync.xml"); //$NON-NLS-1$ //$NON-NLS-2$]
	}

}
