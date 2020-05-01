package pe.com.jdmm21.felix.bookshelf.service.tui.sample;

import java.util.Set;

import pe.com.jdmm21.felix.bookshelf.inventory.api.Book;

public interface ExampleServiceProxy {
	String SCOPE = "book";
	String[] FUNCTIONS = new String[] { "add", "search" };

	String greeting(String name);

	Set<Book> search(String username, String password, String attribute, String filter);

	Set<Book> search(String username, String password, String attribute, int lower, int upper);
}
