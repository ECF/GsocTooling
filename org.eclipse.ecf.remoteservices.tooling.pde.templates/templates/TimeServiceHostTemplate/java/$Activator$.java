/*******************************************************************************
 * Copyright (c) 2013 X2CODE, Inc. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sakith Indula - initial API and implementation
 ******************************************************************************/

package $packageName$;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminEvent;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminListener;

import com.mycorp.examples.timeservice.ITimeService;

public class $Activator$ implements BundleActivator {
	
	
	public void start(BundleContext context) throws Exception {

		if (Boolean.getBoolean("verboseRemoteServiceAdmin")) {
			RemoteServiceAdminListener rsListener = new RemoteServiceAdminListener() {

				
				public void remoteAdminEvent(RemoteServiceAdminEvent event) {
					if (event.getType() == RemoteServiceAdminEvent.EXPORT_REGISTRATION) {
						System.out.println("Service Exported by RemoteServiceAdmin.  EndpointDescription Properties="
										+ event.getExportReference()
												.getExportedEndpoint()
												.getProperties());
					}
				}
			};
			context.registerService(RemoteServiceAdminListener.class.getName(),
					rsListener, null);
		}
		Dictionary<String, Object> props = getRemoteServiceProperties();

		ServiceRegistration<ITimeService> timeServiceRegistration = context
				.registerService(ITimeService.class, new TimeServiceImpl(),
						props);
	}
	
%if containerType == "ecf.genaric.server";
	private Dictionary<String, Object> getRemoteServiceProperties() {
		Dictionary<String,Object> props = new Hashtable<String,Object>();
		props.put("service.exported.interfaces", "*");
		props.put("service.exported.configs", "$containerType$");
		props.put("ecf.generic.server.id","$containerId$");
		props.put("ecf.generic.server.name", "$HostName$")
		Properties properties = System.getProperties();
		String config = properties.getProperty("service.exported.configs");
		if (config != null) {
			props.put("service.exported.configs", config);
			String configProps = config + ".";
			for(Object k: properties.keySet()) {
				if (k instanceof String) {
					String key = (String) k;
					if (key.startsWith(configProps) || key.equals("ecf.exported.async.interfaces")) props.put(key, properties.getProperty(key));
				}
			}
		}
		return props;
	}
 %endif
 %if containerType == "ecf.r_osgi.peer"
	 
	 private Dictionary<String, Object> getRemoteServiceProperties(){
		 Dictionary<String,Object> props = new Hashtable<String,Object>();
		 props.put("service.exported.interfaces", "*");
		 props.put("service.exported.configs","$containerType$");
		 props.put("", "")
	 }
	 
%endif	 
	 


	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
