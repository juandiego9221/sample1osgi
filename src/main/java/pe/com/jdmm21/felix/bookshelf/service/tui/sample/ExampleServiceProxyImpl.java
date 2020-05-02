package pe.com.jdmm21.felix.bookshelf.service.tui.sample;

import java.util.HashSet;
import java.util.Set;

import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pe.com.jdmm21.felix.bookshelf.inventory.api.Book;
import pe.com.jdmm21.felix.bookshelf.inventory.api.BookNotFoundException;
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
		System.out.println("hello " + name);
		System.out.println("hello " + name);
		return name;
	}

	@Override
	public String add(@Descriptor("username") String username, @Descriptor("password") String password,
			@Descriptor("isbn") String isbn, @Descriptor("title") String title, @Descriptor("author") String author,
			@Descriptor("category") String category, @Descriptor("rating") int rating)
			throws BookAlreadyExistsException, InvalidBookException, InvalidCredentialsException {
		BookshelfService service = lookupService();
		String sessionId = service.login(username, password.toCharArray());
		service.addBook(sessionId, isbn, title, author, category, rating);
		return isbn;
	}

	@Override
	public Set<Book> search(@Descriptor("username") String username, @Descriptor("password") String password,
			@Descriptor("attribute") String attribute, @Descriptor("filter") String filter)
			throws InvalidCredentialsException {
		BookshelfService service = lookupService();
		String sessionId = service.login(username, password.toCharArray());
		Set<String> results = null;
		if ("title".equals(attribute)) {
			results = service.searchBooksByTitle(sessionId, filter);
		} else if ("author".equals(attribute)) {
			results = service.searchBooksByAuthor(sessionId, filter);
		} else if ("category".equals(attribute)) {
			results = service.searchBooksByGroup(sessionId, filter);
		} else {
			throw new RuntimeException(
					"Invalid attribute, expecting one of { 'title', 'author', 'category' } got '" + attribute + "'");
		}
		return getBooks(sessionId, service, results);
	}

	@Override
	public Set<Book> search(@Descriptor("username") String username, @Descriptor("password") String password,
			@Descriptor("attribute") String attribute, @Descriptor("lower") int lower, @Descriptor("upper") int upper)
			throws InvalidCredentialsException {
		if (!"rating".equals(attribute)) {
			throw new RuntimeException("Invalid attribute, expecting 'rating' got '" + attribute + "'");
		}
		BookshelfService service = lookupService();
		String sessionId = service.login(username, password.toCharArray());
		Set<String> results = service.searchBooksByGrade(sessionId, lower, upper);
		return getBooks(sessionId, service, results);
	}

	@Override
	public Book searchBook(@Descriptor("username") String username, @Descriptor("password") String password,
			@Descriptor("isbn") String isbn) throws BookNotFoundException, InvalidCredentialsException {
		BookshelfService bookshelfService = lookupService();
		String sessionId = bookshelfService.login(username, password.toCharArray());
		Book result = bookshelfService.getBook(sessionId, isbn);
		return result;
	}

	@Override
	public String delete(@Descriptor("username") String username, @Descriptor("password") String password,
			@Descriptor("isbn") String isbn) throws BookNotFoundException, InvalidCredentialsException {
		BookshelfService bookshelfService = lookupService();
		String sessionId = bookshelfService.login(username, password.toCharArray());
		bookshelfService.removeBook(sessionId, isbn);
		return "deleted with isbn: " + isbn;
	}

	protected BookshelfService lookupService() {
		ServiceReference reference = this.bundleContext.getServiceReference(BookshelfService.class.getName());
		if (reference == null) {
			throw new RuntimeException("BookshelfService not registered, cannot invoke operation");
		}
		return (BookshelfService) this.bundleContext.getService(reference);
	}

	private Set<Book> getBooks(String sessionId, BookshelfService service, Set<String> results) {
		Set<Book> books = new HashSet<Book>();
		for (String isbn : results) {
			Book book;
			try {
				book = service.getBook(sessionId, isbn);
				books.add(book);
			} catch (Exception e) {
				System.err.println("ISBN " + isbn + " referenced but not found");
			}
		}
		return books;
	}

}
