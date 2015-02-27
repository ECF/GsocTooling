/*******************************************************************************
 * Copyright (c) 2013 Sakith Indula. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.ecf.remoteservices.internal.tooling.pde.templates;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = Messages.Activator_0;
	private static Activator plugin;

	public Activator() {

	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {

		super.start(bundleContext);
		plugin = this;
		
		Dictionary<String, String> props = new Hashtable<String, String>();
		props.put("endpoint.id", "xxxx");
		
		bundleContext.registerService(AbstractUIPlugin.class, this, props);
		
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		plugin = null;
		super.stop(bundleContext);
	}

	public static Activator getDefault() {
		return plugin;
	}

}
