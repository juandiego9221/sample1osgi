package pe.com.jdmm21.felix.bookshelf.service.tui.sample;

import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pe.com.jdmm21.felix.bookshelf.inventory.api.Book;

public class ExampleServiceProxyImpl implements ExampleServiceProxy {

	BundleContext bundleContext;

	public ExampleServiceProxyImpl(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
//df
	public String greeting(String name) {
		System.out.println("hello " + name);
		return name;
	}

	@Override
	public Set<Book> search(String username, String password, String attribute, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> search(String username, String password, String attribute, int lower, int upper) {
		// TODO Auto-generated method stub
		return null;
	}

}
