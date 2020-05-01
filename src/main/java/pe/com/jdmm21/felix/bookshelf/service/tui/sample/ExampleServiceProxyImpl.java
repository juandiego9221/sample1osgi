package pe.com.jdmm21.felix.bookshelf.service.tui.sample;

import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pe.com.jdmm21.felix.bookshelf.inventory.api.Book;
import pe.com.jdmm21.felix.bookshelf.inventory.api.InvalidBookException;
import pe.com.jdmm21.felix.bookshelf.service.api.BookAlreadyExistsException;
import pe.com.jdmm21.felix.bookshelf.service.api.BookshelfService;
import pe.com.jdmm21.felix.bookshelf.service.api.InvalidCredentialsException;

public class ExampleServiceProxyImpl implements ExampleServiceProxy {

	BundleContext bundleContext;

	public ExampleServiceProxyImpl(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public String greeting(String name) {
		System.out.println("hello " + name);
		return name;
	}

	@Override
	public String add(String username, String password, String isbn, String titile, String author, String category,
			int rating) throws BookAlreadyExistsException, InvalidBookException, InvalidCredentialsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> search(String username, String password, String attribute, String filter)
			throws InvalidCredentialsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> search(String username, String password, String attribute, int lower, int upper)
			throws InvalidCredentialsException {
		// TODO Auto-generated method stub
		return null;
	}

	protected BookshelfService lookupService() {
		ServiceReference reference = this.bundleContext.getServiceReference(BookshelfService.class.getName());
		if (reference == null) {
			throw new RuntimeException("BookshelfService not registered, cannot invoke operation");
		}
		return (BookshelfService) this.bundleContext.getService(reference);
	}

}
