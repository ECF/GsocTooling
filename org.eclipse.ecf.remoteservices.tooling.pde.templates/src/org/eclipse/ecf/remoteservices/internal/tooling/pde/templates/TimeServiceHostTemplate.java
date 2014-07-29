/*******************************************************************************
 * Copyright (c) 2013 Sakith Indula. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.ui.templates.ITemplateSection;

public class TimeServiceHostTemplate extends TemplateSection {
	
	public TimeServiceHostTemplate() {
		setPageCount(1);
		setPageOPtions();

	}
	
	private void setPageOPtions(){
		addOption("HostName", "Host Name", "", 0);
		//addOption("containerType", "cotainer type", "ecf.generic.server", 0); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		addOption("containerId", "containerId", //$NON-NLS-1$ //$NON-NLS-2$
				"ecftcp://localhost:3288/server", 0); //$NON-NLS-1$
		
		/*
		addOption("containerType", "container Type", 
				new String[][] { {"privider1", "ecf.provider1"}, 
						{"genaric", "ecf.generic.server"}, {"privider2", "ecf.provider2"}}, 
				"genaric", 0);
		*/
		addOption("containerType", "Select the container type", 
				new String[][]{{"ecf.genaric.server", "ecf genaric server"},{"ecf.r_osgi.peer","rosgi server"},{"provider1","prividor2"}} 
				,"ecf.genaric.server", 0);
		
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

}
