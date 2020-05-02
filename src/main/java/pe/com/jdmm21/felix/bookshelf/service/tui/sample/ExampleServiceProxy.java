package pe.com.jdmm21.felix.bookshelf.service.tui.sample;

import java.util.Set;

import pe.com.jdmm21.felix.bookshelf.inventory.api.Book;
import pe.com.jdmm21.felix.bookshelf.inventory.api.BookNotFoundException;
import pe.com.jdmm21.felix.bookshelf.inventory.api.InvalidBookException;
import pe.com.jdmm21.felix.bookshelf.service.api.BookAlreadyExistsException;
import pe.com.jdmm21.felix.bookshelf.service.api.InvalidCredentialsException;

public interface ExampleServiceProxy {
	String SCOPE = "book";
	String[] FUNCTIONS = new String[] { "add", "search", "searchBook", "delete", "modifyByGroup", "modifyByGrade" };

	String greeting(String name);

	String add(String username, String password, String isbn, String title, String author, String category, int rating)
			throws BookAlreadyExistsException, InvalidBookException, InvalidCredentialsException;

	Set<Book> search(String username, String password, String attribute, String filter)
			throws InvalidCredentialsException;

	Set<Book> search(String username, String password, String attribute, int lower, int upper)
			throws InvalidCredentialsException;

	Book searchBook(String username, String password, String isbn)
			throws BookNotFoundException, InvalidCredentialsException;

	String delete(String username, String password, String isbn)
			throws BookNotFoundException, InvalidCredentialsException;

	Book modifyByGroup(String username, String password, String isbn, String group)
			throws BookNotFoundException, InvalidCredentialsException, InvalidBookException;

	Book modifyByGrade(String username, String password, String isbn, int grade)
			throws BookNotFoundException, InvalidBookException, InvalidCredentialsException;
}
