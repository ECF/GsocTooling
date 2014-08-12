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
import org.eclipse.pde.ui.templates.TemplateOption;

public class TimeServiceHostTemplate extends TemplateSection {
	
	/**
	 * holds the values that enterd by the user to add them to template.
	 * this varibles decide that which service provder will be used to register
	 * the service
	 */
	private BooleanOption containerTypeGenaric;
	private BooleanOption containerTypeR_osgi;
	
	/**
	 * variables that holds information about the ecf genaric service provider
	 */
	private TemplateOption genaricPort;
	private TemplateOption genaricPath;
	private TemplateOption genaricId;
	private TemplateOption genaricBlindAdress;
	private TemplateOption genaricKeepAlive;
	
	/**
	 * variables that holds information about the r_osgi service provider
	 */
	private TemplateOption r_osgiPort;
	private TemplateOption r_osgiInternals;
	
	/**
	 * common variables for both generic and r_osgi providers
	 */
	private TemplateOption Hostname;
	private TemplateOption containerId;
	
	public TimeServiceHostTemplate() {
		setPageCount(2);
		setPageOPtions();
		
	}

	/**
	 * add options to use to interact with the template using text fields and 
	 * sevaral text fields
	 */
	private void setPageOPtions(){
		Hostname = addOption(Messages.TimeServiceHostTemplate_0, Messages.TimeServiceHostTemplate_1, Messages.TimeServiceHostTemplate_2, 0);
		containerId = addOption("containerId", "containerId", //$NON-NLS-1$ //$NON-NLS-2$
				"ecftcp://localhost:3288/server", 0); //$NON-NLS-1$
		
		containerTypeR_osgi = (BooleanOption)addOption(Messages.TimeServiceHostTemplate_3, Messages.TimeServiceHostTemplate_4, false, 0);
		containerTypeGenaric = (BooleanOption)addOption(Messages.TimeServiceHostTemplate_5, Messages.TimeServiceHostTemplate_8,false, 0);
		
		genaricPort = addOption(Messages.TimeServiceHostTemplate_9, Messages.TimeServiceHostTemplate_10, Messages.TimeServiceHostTemplate_11, 0);
		genaricPath = addOption(Messages.TimeServiceHostTemplate_12, Messages.TimeServiceHostTemplate_13, Messages.TimeServiceHostTemplate_14, 0);
		genaricKeepAlive = addOption(Messages.TimeServiceHostTemplate_15, Messages.TimeServiceHostTemplate_16, Messages.TimeServiceHostTemplate_17, 0);
		genaricId = addOption(Messages.TimeServiceHostTemplate_18, Messages.TimeServiceHostTemplate_19, Messages.TimeServiceHostTemplate_20, 0);
		genaricBlindAdress = addOption(Messages.TimeServiceHostTemplate_21, Messages.TimeServiceHostTemplate_22, Messages.TimeServiceHostTemplate_23, 0);
		
		r_osgiPort = addOption(Messages.TimeServiceHostTemplate_24, Messages.TimeServiceHostTemplate_25, Messages.TimeServiceHostTemplate_26, 0);
		r_osgiInternals = addOption(Messages.TimeServiceHostTemplate_27, Messages.TimeServiceHostTemplate_28, Messages.TimeServiceHostTemplate_29, 0);
		
	}	
	
	/**
	 * decide which options should be enabled and disabled upon diffrant 
	 * service providor
	 */
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
	
	@Override
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0);
		page.setTitle(Messages.TimeServiceHostTemplate_6);
		page.setDescription(Messages.TimeServiceHostTemplate_7);
		wizard.addPage(page);
		markPagesAdded();
	}

	/**
	 * @return section id that contains the template file
	 */
	@Override
	public String getSectionId() {

		return "TimeServiceHostTemplate"; //$NON-NLS-1$
	}
	
	@Override
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
	
	/**
	 * update and add service headers of the project manifest file
	 */
	@Override
	protected void updateModel(IProgressMonitor monitor){
		setManifestHeader(Messages.TimeServiceHostTemplate_30, Messages.TimeServiceHostTemplate_31);
		setManifestHeader(Messages.TimeServiceHostTemplate_32, Messages.TimeServiceHostTemplate_33);
		setManifestHeader(Messages.TimeServiceHostTemplate_34, Messages.TimeServiceHostTemplate_35);
	}

}
