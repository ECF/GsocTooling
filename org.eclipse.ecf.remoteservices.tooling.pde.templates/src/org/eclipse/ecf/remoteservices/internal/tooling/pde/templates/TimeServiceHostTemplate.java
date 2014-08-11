/*******************************************************************************
 * Copyright (c) 2013 Sakith Indula. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.ui.templates.BooleanOption;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.TemplateOption;

public class TimeServiceHostTemplate extends TemplateSection {
	
	private BooleanOption containerTypeGenaric;
	private BooleanOption containerTypeR_osgi;
	
	
	private TemplateOption genaricPort;
	private TemplateOption genaricPath;
	private TemplateOption genaricId;
	private TemplateOption genaricBlindAdress;
	private TemplateOption genaricKeepAlive;
	
	private TemplateOption r_osgiPort;
	private TemplateOption r_osgiInternals;
	private TemplateOption r_osgiProxyGeneration;
	private TemplateOption r_osgiMessages;
	
	private TemplateOption Hostname;
	
	public TimeServiceHostTemplate() {
		setPageCount(2);
		setPageOPtions();
		
	}

	private void setPageOPtions(){
		Hostname = addOption("HostName", "Host Name", "", 0);
		//addOption("containerType", "cotainer type", "ecf.generic.server", 0); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		addOption("containerId", "containerId", //$NON-NLS-1$ //$NON-NLS-2$
				"ecftcp://localhost:3288/server", 0); //$NON-NLS-1$
		
		containerTypeR_osgi = (BooleanOption)addOption("containerTypeR_osgi", "r_osgi", false, 0);
		containerTypeGenaric = (BooleanOption)addOption("containerTypeGenaric", "genaric",false, 0);
		
		genaricPort = addOption("genaricPort", "org.eclipse.ecf.provider.generic.port", "", 0);
		genaricPath = addOption("genaricPath", "ecf.generic.server.path", "", 0);
		genaricKeepAlive = addOption("genaricKeepAlive", "ecf.generic.server.keepAlive", "", 0);
		genaricId = addOption("genaricId", "ecf.generic.server.id", "", 0);
		genaricBlindAdress = addOption("genaricBlindAdress", "ecf.generic.server.bindAddress", "", 0);
		
		r_osgiPort = addOption("Port", "ch.ethz.iks.r_osgi.port", "", 0);
		r_osgiInternals = addOption("internals", "ch.ethz.iks.r_osgi.debug.proxyGeneration", "", 0);
		
				
	}	
	
	private void alterOptionGenaric(){
		containerTypeGenaric.setEnabled(!containerTypeR_osgi.isSelected());
		genaricPort.setEnabled(containerTypeGenaric.isSelected());
		genaricPath.setEnabled(containerTypeGenaric.isSelected());
		genaricKeepAlive.setEnabled(containerTypeGenaric.isSelected());
		genaricId.setEnabled(containerTypeGenaric.isSelected());
		genaricBlindAdress.setEnabled(containerTypeGenaric.isSelected());
		
		containerTypeR_osgi.setEnabled(!containerTypeGenaric.isSelected());
		r_osgiPort.setEnabled(containerTypeR_osgi.isSelected());
		r_osgiInternals.setEnabled(containerTypeR_osgi.isSelected());
		
	}
	
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0);
		page.setTitle(Messages.TimeServiceHostTemplate_6);
		page.setDescription(Messages.TimeServiceHostTemplate_7);
		wizard.addPage(page);
		markPagesAdded();
	}

	public String getSectionId() {

		return "TimeServiceHostTemplate"; //$NON-NLS-1$
	}
	
	public void validateOptions(TemplateOption changed) {
		if (changed == Hostname) {
			if (changed.isEmpty()) {
				flagMissingRequiredOption(changed);
			} else {
				resetPageState();
			}
		} else if (changed == containerTypeGenaric) {
			alterOptionGenaric();
		} else if (changed == containerTypeR_osgi) {
			alterOptionGenaric();
		}
	}
	
	protected void updateModel(IProgressMonitor monitor){
		setManifestHeader("Bundle-RequiredExecutionEnvironment", "J2SE-1.5");
		setManifestHeader("Bundle-Vendor", "Eclipse.org - ECF");
		setManifestHeader("Bundle-ActivationPolicy", "lazy");
	}

}
