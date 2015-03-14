/*******************************************************************************
 * Copyright (c) 2013 Sakith Indula. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates;

import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.IPluginContentWizard;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;

public class TimeServiceConsumerWizard extends NewPluginTemplateWizard
		implements IPluginContentWizard {

	@Override
	public void init(IFieldData data) {
		super.init(data);
		setWindowTitle(MyNLS.TSCW_0);
	}

	@Override
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] { new TimeServiceConsumerTemplate() };

	}

	/**
	 * @return the string array that contain the all imported bundles
	 */
	@Override
	public String[] getImportPackages() {
		return new String[] {
				"com.mycorp.examples.timeservice;version=\"1.0.0\"", //$NON-NLS-1$
				"org.osgi.framework", //$NON-NLS-1$
				"org.osgi.service.remoteserviceadmin;version=\"1.0.0\"", //$NON-NLS-1$
				"org.osgi.util.tracker" }; //$NON-NLS-1$

	}

	@Override
	public IPluginReference[] getDependencies(String schemaVersion) {
		return new IPluginReference[0];
	}

}
