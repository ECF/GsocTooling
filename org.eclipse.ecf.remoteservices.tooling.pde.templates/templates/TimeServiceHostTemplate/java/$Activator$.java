/**
 * This bundle activator registers an OSGi service with specific 
 * service attributes. These attributes are picked up by 
 * ECF's OSGi RSA implementation. 
 */
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

% Options in the template:
% packageName
% Activator
% containerType
% containerId
% HostName
% containerTypeGeneric
% containerTypeR_OSGI

public class $Activator$ implements BundleActivator {
	
	public void start(BundleContext context) throws Exception {
		registerRSAListener(context);
		Dictionary<String, Object> props = getRemoteServiceProperties();
		context.registerService(ITimeService.class, new TimeServiceImpl(), props);
	}
	
	/**
	 * Registers a RSA listener if requested.
	 */
	private void registerRSAListener(BundleContext context){
		// If the verboseRemoteServiceAdmin system property is set
		// then register debug listener
		if (Boolean.getBoolean("verboseRemoteServiceAdmin")) {
			
			// Register a RemoteServiceAdminListener so we can report to sdtout
			// when a remote service has actually been successfully exported by
			// the RSA implementation
			RemoteServiceAdminListener rsListener = new RemoteServiceAdminListener() {
				
				public void remoteAdminEvent(RemoteServiceAdminEvent event) {
					if (event.getType() == RemoteServiceAdminEvent.EXPORT_REGISTRATION) {
						System.out.println("Service Exported by RemoteServiceAdmin.  EndpointDescription Properties="
										+ event.getExportReference().getExportedEndpoint().getProperties());
					}
				}
			};
			// Register our listener as service via whiteboard pattern, and RemoteServiceAdmin will callback
			context.registerService(RemoteServiceAdminListener.class.getName(), rsListener, null);
		}

	}

	/**
	 * Returns a Dictionary with remote service properties.
	 */
%if containerTypeGeneric
	private Dictionary<String, Object> getRemoteServiceProperties() {
	// This is the only required service property to trigger remote services
		Dictionary<String,Object> props = new Hashtable<String,Object>();
		props.put("service.exported.interfaces", "*");
		if("$containerType$".length() > 0)
			props.put("service.exported.configs", "$containerType$");
		props.put("ecf.generic.server.id","$containerId$");
		props.put("ecf.generic.server.name", "$HostName$");
		props.put("org.eclipse.ecf.provider.generic.port", "$genericPort$");
		props.put("ecf.generic.server.path", "$genericPath$");
		props.put("ecf.generic.server.keepAlive", "$genericKeepAlive$");
		props.put("ecf.generic.server.id", "$genericId$");
		props.put("ecf.generic.server.bindAddress", "$genericBindAdress$");
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

% else	
	 private Dictionary<String, Object> getRemoteServiceProperties(){
		 Dictionary<String,Object> props = new Hashtable<String,Object>();
		 props.put("service.exported.interfaces", "*");
		 props.put("service.exported.configs","$containerType$");
		 props.put("ch.ethz.iks.r_osgi.port", "$Port$");
		 return props;
	 }
	 
% endif	 
	public void stop(BundleContext context) throws Exception {
	}
}
