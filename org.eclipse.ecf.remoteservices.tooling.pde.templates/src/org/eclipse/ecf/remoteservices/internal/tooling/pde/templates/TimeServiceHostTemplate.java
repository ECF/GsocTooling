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
import org.eclipse.pde.ui.templates.ComboChoiceOption;
import org.eclipse.pde.ui.templates.TemplateOption;

public class TimeServiceHostTemplate extends TemplateSection {


	public static final String OPT_SERVER_TYPE = "serverType";
	public static final String OPT_SERVER_TYPE_GENERIC = "serverTypeGeneric";
	public static final String OPT_HOST_NAME = "hostName";

	/**
	 * holds the values that entered by the user to add them to template. this
	 * variable decides that which service provider will be used to register the
	 * service
	 */
	private ComboChoiceOption serverType;

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

	private WizardPage page1;

	private WizardPage page2;

	public TimeServiceHostTemplate() {
		setPageCount(3);
		setPageOptions();
	}

	/**
	 * add options to use to interact with the template using text fields and
	 * Several text fields
	 */
	private void setPageOptions() {
		Hostname = addOption(OPT_HOST_NAME, Messages.TimeServiceHostTemplate_1, null, 0);
		containerId = addOption("containerId", "Container Id", "ecftcp://localhost:3288/server", 0);
		serverType = addComboChoiceOption(OPT_SERVER_TYPE, "Server Type", new String[][] {
				{ OPT_SERVER_TYPE_GENERIC, "ECF Generic Server" }, { "serverTypeR_OSGi", "R_OSGi Server" } },
				OPT_SERVER_TYPE_GENERIC, 0);

		genericPort = addOption("genericPort", Messages.TimeServiceHostTemplate_10,
				Messages.TimeServiceHostTemplate_11, 1);
		genericPath = addOption("genericPath", Messages.TimeServiceHostTemplate_13,
				Messages.TimeServiceHostTemplate_14, 1);
		genericKeepAlive = addOption("genericKeepAlive", Messages.TimeServiceHostTemplate_16,
				Messages.TimeServiceHostTemplate_17, 1);
		genericId = addOption("genericId", Messages.TimeServiceHostTemplate_19, Messages.TimeServiceHostTemplate_20, 1);
		genericBindAdress = addOption("genericBindAdress", Messages.TimeServiceHostTemplate_22,
				Messages.TimeServiceHostTemplate_23, 1);

		r_osgiPort = addOption("Port", Messages.TimeServiceHostTemplate_25, Messages.TimeServiceHostTemplate_26, 2);
		r_osgiInternals = addOption("internals", Messages.TimeServiceHostTemplate_28,
				Messages.TimeServiceHostTemplate_29, 2);

	}

	@Override
	public void addPages(Wizard wizard) {
		WizardPage page0 = createPage(0);
		page0.setTitle(Messages.TimeServiceHostTemplate_6);
		page0.setDescription(Messages.TimeServiceHostTemplate_7);
		wizard.addPage(page0);
		page1 = createPage(1);
		page1.setTitle("Generic Server Options");
		page1.setDescription("Generic Server Options");
		wizard.addPage(page1);
		page2 = createPage(2);
		page2.setTitle("R_OSGi Options");
		page2.setDescription("R_OSGi Options");
		wizard.addPage(page2);
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
		}
	}

	/**
	 * update and add service headers of the project manifest file
	 */
	@Override
	protected void updateModel(IProgressMonitor monitor) {
		setManifestHeader("Bundle-RequiredExecutionEnvironment", "J2SE-1.5");
		setManifestHeader("Bundle-Vendor", Messages.TimeServiceHostTemplate_33);
		setManifestHeader("Bundle-ActivationPolicy", "lazy");
	}

}
