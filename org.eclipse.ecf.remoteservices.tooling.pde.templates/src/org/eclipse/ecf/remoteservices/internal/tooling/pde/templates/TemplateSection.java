/*******************************************************************************
 * Copyright (c) 2013 Sakith Indula. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.osgi.framework.Bundle;

public abstract class TemplateSection extends OptionTemplateSection {

	/**
	 *Package name of the template
	 */
	private String packageName;

	/**
     * Returns the location of the template
     * @return    URL of the template location.
     */
	@Override
	public URL getTemplateLocation() {
		Bundle b = Activator.getDefault().getBundle();
		String path = "/templates/" + getSectionId(); //$NON-NLS-1$
		URL url = b.getEntry(path);
		if (url != null) {

			try {
				return new URL(getInstallURL(), path);
			} catch (MalformedURLException m) {
				m.printStackTrace();
			}
		}
		return null;

	}
	//test

	protected String getFormattedPackageName(String id) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < id.length(); i++) {
			char ch = id.charAt(i);
			if (buffer.length() == 0) {
				if (Character.isJavaIdentifierStart(ch))
					buffer.append(Character.toLowerCase(ch));
			} else {
				if (Character.isJavaIdentifierPart(ch) || ch == '.')
					buffer.append(ch);
			}
		}
		return buffer.toString().toLowerCase(Locale.ENGLISH);
	}

	@Override
	protected void initializeFields(IFieldData data) {

		String packageName = getFormattedPackageName(data.getId());
		initializeOption(KEY_PACKAGE_NAME, packageName);
		this.packageName = getFormattedPackageName(data.getId());
	}

	@Override
	public void initializeFields(IPluginModelBase model) {
		String id = model.getPluginBase().getId();
		String packageName = getFormattedPackageName(id);
		initializeOption(KEY_PACKAGE_NAME, packageName);
		this.packageName = getFormattedPackageName(id);
	}

	@Override
	public int getNumberOfWorkUnits() {
		return super.getNumberOfWorkUnits() + 1;
	}

	@Override
	public String getStringOption(String name) {
		if (name.equals(KEY_PACKAGE_NAME)) {
			return packageName;
		}
		return super.getStringOption(name);
	}

	public String getUsedExtensionPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getNewFiles() {
		// TODO Auto-generated method stub
		return new String[0];
	}

	@Override
	protected URL getInstallURL() {
		// TODO Auto-generated method stub
		return Activator.getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
	}

	@Override
	protected ResourceBundle getPluginResourceBundle() {

		return Platform.getResourceBundle(Activator.getDefault().getBundle());
	}

	@Override
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub

	}

}
