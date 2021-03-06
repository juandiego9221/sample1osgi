package pe.com.jdmm21.felix.bookshelf.service.tui.sample.activator;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import pe.com.jdmm21.felix.bookshelf.service.tui.sample.ExampleServiceProxy;
import pe.com.jdmm21.felix.bookshelf.service.tui.sample.ExampleServiceProxyImpl;

public class ExampleActivator implements BundleActivator {
	ServiceRegistration reg = null;

	public void start(BundleContext context) throws Exception {
		Hashtable props = new Hashtable();
		props.put("osgi.command.scope", ExampleServiceProxy.SCOPE);
		props.put("osgi.command.function", ExampleServiceProxy.FUNCTIONS);
		this.reg = context.registerService(ExampleServiceProxy.class, new ExampleServiceProxyImpl(context), props);
		context.registerService(ExampleServiceProxy.class.getName(), new ExampleServiceProxyImpl(context), props);
//		testService(context);
	}

	public void stop(BundleContext context) throws Exception {
		if (this.reg != null) {
			context.ungetService(reg.getReference());
		}
	}

	private void testService(BundleContext bundleContext) {
		ServiceReference serviceReference = bundleContext.getServiceReference(ExampleServiceProxy.class.getName());
		if (serviceReference == null) {
			throw new RuntimeException("Service not registered: " + ExampleServiceProxy.class.getName());
		}
		ExampleServiceProxy exampleServiceProxy = (ExampleServiceProxy) bundleContext.getService(serviceReference);
		System.out.println("\nstarting greeting");
		try {
			exampleServiceProxy.greeting("juandiego");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
