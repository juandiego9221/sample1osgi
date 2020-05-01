package pe.com.jdmm21.felix.bookshelf.service.tui.sample;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ExampleServiceProxyImpl implements ExampleServiceProxy {

	BundleContext bundleContext;

	public ExampleServiceProxyImpl(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public String greeting(String name) {
		System.out.println("hello " + name);
		return name;
	}

}
