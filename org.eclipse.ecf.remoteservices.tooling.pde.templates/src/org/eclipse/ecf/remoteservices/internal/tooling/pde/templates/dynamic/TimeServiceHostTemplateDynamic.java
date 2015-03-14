/*******************************************************************************
 * Copyright (c) 2013 Sakith Indula. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates.dynamic;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.ui.templates.BooleanOption;
import org.eclipse.pde.ui.templates.TemplateOption;
import org.eclipse.ecf.remoteservices.internal.tooling.pde.templates.TemplateSection; 
import org.eclipse.ecf.remoteservices.internal.tooling.pde.templates.MyNLS;

public class TimeServiceHostTemplateDynamic extends TemplateSection {
	
	/**
	 * holds the values that entered by the user to add them to template.
	 * this variables decide that which service provider will be used to register
	 * the service
	 */
	private BooleanOption containerTypeGeneric;
	private BooleanOption containerTypeR_osgi;
	
	/**
	 * variables that holds information about the ecf generic service provider
	 */
	private TemplateOption genericPort;
	private TemplateOption genericPath;
	private TemplateOption genericId;
	private TemplateOption genericBindAdress;
	private TemplateOption genericKeepAlive;
	
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
	
	public TimeServiceHostTemplateDynamic() {
		setPageCount(2);
		setPageOPtions();
		
	}

	/**
	 * add options to use to interact with the template using text fields and 
	 * Several text fields
	 */
	private void setPageOPtions(){
		Hostname = addOption(MyNLS.TSHT_0, MyNLS.TSHT_1, MyNLS.TSHT_2, 0);
		containerId = addOption("containerId", "containerId", //$NON-NLS-1$ //$NON-NLS-2$
				"ecftcp://localhost:3288/server", 0); //$NON-NLS-1$
		
		containerTypeR_osgi = (BooleanOption)addOption(MyNLS.TSHT_3, MyNLS.TSHT_4, false, 0);
		containerTypeGeneric = (BooleanOption)addOption(MyNLS.TSHT_5, MyNLS.TSHT_8,false, 0);
		
		genericPort = addOption(MyNLS.TSHT_9, MyNLS.TSHT_10, MyNLS.TSHT_11, 0);
		genericPath = addOption(MyNLS.TSHT_12, MyNLS.TSHT_13, MyNLS.TSHT_14, 0);
		genericKeepAlive = addOption(MyNLS.TSHT_15, MyNLS.TSHT_16, MyNLS.TSHT_17, 0);
		genericId = addOption(MyNLS.TSHT_18, MyNLS.TSHT_19, MyNLS.TSHT_20, 0);
		genericBindAdress = addOption(MyNLS.TSHT_21, MyNLS.TSHT_22, MyNLS.TSHT_23, 0);
		
		r_osgiPort = addOption(MyNLS.TSHT_24, MyNLS.TSHT_25, MyNLS.TSHT_26, 0);
		r_osgiInternals = addOption(MyNLS.TSHT_27, MyNLS.TSHT_28, MyNLS.TSHT_29, 0);
		
	}	
	
	/**
	 * decide which options should be enabled and disabled upon different 
	 * service provider
	 */
	private void alterOptionGeneric(){
		containerTypeGeneric.setEnabled(!containerTypeR_osgi.isSelected());
		genericPort.setEnabled(containerTypeGeneric.isSelected());
		genericPath.setEnabled(containerTypeGeneric.isSelected());
		genericKeepAlive.setEnabled(containerTypeGeneric.isSelected());
		genericId.setEnabled(containerTypeGeneric.isSelected());
		genericBindAdress.setEnabled(containerTypeGeneric.isSelected());
		
		containerTypeR_osgi.setEnabled(!containerTypeGeneric.isSelected());
		r_osgiPort.setEnabled(containerTypeR_osgi.isSelected());
		r_osgiInternals.setEnabled(containerTypeR_osgi.isSelected());
		
	}
	
	@Override
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0);
		page.setTitle(MyNLS.TSHT_6);
		page.setDescription(MyNLS.TSHT_7);
		wizard.addPage(page);
		markPagesAdded();
	}

	/**
	 * @return section id that contains the template file
	 */
	@Override
	public String getSectionId() {

		return "TimeServiceHostTemplateDynamic"; //$NON-NLS-1$
	}
	
	@Override
	public void validateOptions(TemplateOption changed) {
		if (changed == Hostname) {
			if (changed.isEmpty()) {
				flagMissingRequiredOption(changed);
			} else {
				resetPageState();
			}
		} else if (changed == containerTypeGeneric) {
			alterOptionGeneric();
		} else if (changed == containerTypeR_osgi) {
			alterOptionGeneric();
		}
	}
	
	/**
	 * update and add service headers of the project manifest file
	 */
	@Override
	protected void updateModel(IProgressMonitor monitor){
		setManifestHeader(MyNLS.TSHT_30, MyNLS.TSHT_31);
		setManifestHeader(MyNLS.TSHT_32, MyNLS.TSHT_33);
		setManifestHeader(MyNLS.TSHT_34, MyNLS.TSHT_35);
	}

}
