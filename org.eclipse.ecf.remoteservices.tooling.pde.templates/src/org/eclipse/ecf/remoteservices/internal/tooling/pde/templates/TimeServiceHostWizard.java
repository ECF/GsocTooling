/*******************************************************************************
 * Copyright (c) 2013 Sakith Indula. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.IPluginContentWizard;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;
import org.eclipse.pde.ui.templates.PluginReference;
import org.eclipse.pde.ui.templates.TemplateOption;

public class TimeServiceHostWizard extends NewPluginTemplateWizard implements
		IPluginContentWizard {
	
	@Override
	public void init(IFieldData data) {
		super.init(data);
		setWindowTitle(MyNLS.TSHW_0);
	}

	@Override
	public ITemplateSection[] createTemplateSections() {
		
		return new ITemplateSection[] {new TimeServiceHostTemplate()};

	}

	/**
	 * @return the string array that contain the all imported bundles
	 */
	@Override
	public String[] getImportPackages() {
		
		return new String[] {
				"com.mycorp.examples.timeservice;version=\"1.0.0\"", //$NON-NLS-1$
				"org.osgi.framework;version=\"1.3.0\"" }; //$NON-NLS-1$
	}

	@Override
	public IPluginReference[] getDependencies(String schemaVersion) {
		IPluginReference[] dep = new IPluginReference[1];
		dep[0] = new PluginReference(
				"org.eclipse.osgi.services.remoteserviceadmin", "1.5.0", 0); //$NON-NLS-1$ //$NON-NLS-2$
		return dep;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage pPage) {
		TimeServiceHostTemplate section = (TimeServiceHostTemplate) getTemplateSections()[0];
		WizardPage page = section.getPage(0);
		if(page == pPage){
			IWizardPage nextPage = section.getPage(2);
			String serverType = section.getValue(TimeServiceHostTemplate.OPT_SERVER_TYPE).toString();
			if(serverType.equals("serverTypeGeneric")){
				nextPage = section.getPage(1);
			}
			System.out.println(serverType);
			return nextPage;
		}
		return null;
	}
}
