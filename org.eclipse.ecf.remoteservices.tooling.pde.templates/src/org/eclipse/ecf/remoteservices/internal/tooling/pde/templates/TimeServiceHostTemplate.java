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
	 * common variables for both generic and r_osgi providers
	 */
	private TemplateOption Hostname;

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
		addGeneralOptions();
		addGenericServerOptions();
		addROSGiOptions();
	}

	private void addROSGiOptions() {
		addOption("Port", MyNLS.TSHT_25, MyNLS.TSHT_26, 2);
		addOption("internals", MyNLS.TSHT_28, MyNLS.TSHT_29, 2);
	}

	private void addGenericServerOptions() {
		addOption("info", "Info", "See https://wiki.eclipse.org/EIG:Configuration_Properties#ECF_generic_provider", 1).setEnabled(false);
		addBlankField(1);
		addOption("genericPort", MyNLS.TSHT_10, MyNLS.TSHT_11, 1);
		addOption("genericPort", MyNLS.TSHT_10, MyNLS.TSHT_11, 1);
		addOption("genericPath", MyNLS.TSHT_13, MyNLS.TSHT_14, 1);
		addOption("genericKeepAlive", MyNLS.TSHT_16, MyNLS.TSHT_17, 1);
		addOption("genericId", MyNLS.TSHT_19, MyNLS.TSHT_20, 1);
		addOption("genericBindAdress", MyNLS.TSHT_22, MyNLS.TSHT_23, 1);
	}

	private void addGeneralOptions() {
		Hostname = addOption(OPT_HOST_NAME, MyNLS.TSHT_1, null, 0);
		addOption("containerId", "Container Id", "ecftcp://localhost:3288/server", 0);
		addComboChoiceOption(OPT_SERVER_TYPE, "Server Type", new String[][] {
				{ OPT_SERVER_TYPE_GENERIC, "ECF Generic Server" }, { "serverTypeR_OSGi", "R_OSGi Server" } },
				OPT_SERVER_TYPE_GENERIC, 0);
	}

	@Override
	public void addPages(Wizard wizard) {
		WizardPage page0 = createPage(0);
		page0.setTitle(MyNLS.TSHT_6);
		page0.setDescription(MyNLS.TSHT_7);
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
		setManifestHeader("Bundle-Vendor", MyNLS.TSHT_33);
		setManifestHeader("Bundle-ActivationPolicy", "lazy");
	}

}
