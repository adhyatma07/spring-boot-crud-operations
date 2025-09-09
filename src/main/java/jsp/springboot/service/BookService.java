package jsp.springboot.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jsp.springboot.dao.BookDao;
import jsp.springboot.dto.ServerResponse;
import jsp.springboot.entity.Book;
import jsp.springboot.exception.NoRecordFoundException;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	//Save book
	public ResponseEntity<ServerResponse<Book>> saveBook(Book book) {
		ServerResponse<Book> response = new ServerResponse<Book>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Book record saved");
		response.setData(bookDao.saveBook(book));
		
		return new ResponseEntity<ServerResponse<Book>>(response,HttpStatus.CREATED);
	}
	
	//find all books
	public ResponseEntity<ServerResponse<List<Book>>> getAllBooks() {
		ServerResponse<List<Book>> response = new ServerResponse<List<Book>>();
		List<Book> books = bookDao.getAllBooks();
		if(books.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Fetched all the books");
			response.setData(books); 
			
			return new ResponseEntity<ServerResponse<List<Book>>> (response, HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("No records found");
	}
	
	
	//fetch Book by Id
	public ResponseEntity<ServerResponse<Book>> getBookById(int id) {
		ServerResponse<Book> response = new ServerResponse<Book>();
		Optional<Book> optional = bookDao.getBookById(id);
		
		if(optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found a Book by id : "+id);
			response.setData(optional.get());
			
			return new ResponseEntity<ServerResponse<Book>>(response, HttpStatus.OK);
		}
		else {
				throw new NoRecordFoundException("There is no record with the id " + id);
		}
	}
	
	//Update a book
	public ResponseEntity<ServerResponse<Book>> updateBook(Book book) {
		ServerResponse<Book> response = new ServerResponse<Book>();
		Optional<Book> optional = bookDao.getBookById(book.getId());
		
		if(optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Updated a Record Successfully!");
			response.setData(bookDao.saveBook(book));
			
			return new ResponseEntity<ServerResponse<Book>>(response,HttpStatus.OK);
		}
		else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Record updation failure!");
			
			return new ResponseEntity<ServerResponse<Book>>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	//delete book by id
	public ResponseEntity<ServerResponse<Book>> deleteBookById(int id) {
	    ServerResponse<Book> response = new ServerResponse<>();
	    Optional<Book> optional = bookDao.getBookById(id);

	    if (optional.isPresent()) {
	        bookDao.deleteBookById(id);
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Deleted record with id " + id + " successfully");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        throw new NoRecordFoundException("There is no record with id " + id);
	    }
	}

	//fetch a book by author
	public ResponseEntity<ServerResponse<List<Book>>> fetchBooByAuthor(String author) {
		List<Book> books = bookDao.fetchBookByAuthor(author);
		ServerResponse<List<Book>> response = new ServerResponse<List<Book>>();
		
		if(books.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Record found with the author name "+author);
			response.setData(books);
			
			return new ResponseEntity<ServerResponse<List<Book>>>(response,HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("There are no books of "+author);
	}
	
	//fetch a book price greater than 
	public ResponseEntity<ServerResponse<List<Book>>> getBookByPriceGreaterThan(double price) {
		List<Book> books = bookDao.fetchBookByGreaterThan(price);
		ServerResponse<List<Book>> response = new ServerResponse<List<Book>>();
		
		if(books.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books found");
			response.setData(books);
			
			return new  ResponseEntity<ServerResponse<List<Book>>>(response,HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("Sorry No Books Found");
	}
	
	//fetch a Book by price between
	public ResponseEntity<ServerResponse<List<Book>>> getBookByPriceBetween(double start, double end) {
		ServerResponse<List<Book>> response = new ServerResponse<List<Book>>();
		
		List<Book> books = bookDao.fetchBookByPriceBetween(start, end);
		
		if(books.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books found in between the specified range");
			response.setData(books);
			
			return new ResponseEntity<ServerResponse<List<Book>>>(response,HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("No Books between "+start+ " and "+end+" 😐");
	}
	
	//fetch a book by published year
	public ResponseEntity<ServerResponse<List<Book>>> getBookByPublishedYear(int publishedYear) {
		ServerResponse<List<Book>> response  = new ServerResponse<List<Book>>();
		List<Book> books = bookDao.fetchBookByPublishedYear(publishedYear);
		
		if(books.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found a Records with the published year "+publishedYear);
			response.setData(books);
			
			return new ResponseEntity<ServerResponse<List<Book>>>(response,HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("No Records found to fetch");
	}
	
	//fetch a book by availability
	public ResponseEntity<ServerResponse<List<Book>>> getBookByAvailability(boolean availability) {
		ServerResponse<List<Book>> response = new ServerResponse<List<Book>>();
		List<Book> books = bookDao.fetchBookByAvailability(availability);
		
		if(books.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found a Records");
			response.setData(books);
			
			return new ResponseEntity<ServerResponse<List<Book>>>(response,HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("Sorry No Records found");
	}
	
	//fetch a book by genre
	public ResponseEntity<ServerResponse<List<Book>>> getBookByGenre(String genre) {
		ServerResponse<List<Book>> response = new ServerResponse<List<Book>>();
		List<Book> books = bookDao.fetchBookGenre(genre);
		
		if(books.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found a Records");
			response.setData(books);
			
			return new ResponseEntity<ServerResponse<List<Book>>>(response,HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("No records found");
	}
	
	//fetch a book by pagination
	public ResponseEntity<ServerResponse<Page<Book>>> getBookByPagination(int pageNumber, int pageSize) {
		ServerResponse<Page<Book>> response = new ServerResponse<Page<Book>>();
		Page<Book> pages  =bookDao.fetchBookByPagination(pageNumber, pageSize);
		
		if(!pages.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book records retrieved");
			response.setData(pages);
			
			return new ResponseEntity<ServerResponse<Page<Book>>>(response,HttpStatus.OK);
		} else 
			throw new NoRecordFoundException("No records found");
	}
	
	//fetch a book by sorting
	public ResponseEntity<ServerResponse<List<Book>>> getBookBySorting(String field) {
		ServerResponse<List<Book>> response = new ServerResponse<List<Book>>();
		List<Book> books = bookDao.fetchBookBySorting(field);
		
		if(books.size() > 0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Sorted Book Records retrieved");
			response.setData(books);
			
			return new ResponseEntity<ServerResponse<List<Book>>>(response,HttpStatus.OK);
		} else 
			throw new NoRecordFoundException("No records found");		
	}
	
	//fetch a book by both pagination and sorting
	public ResponseEntity<ServerResponse<Page<Book>>> getBookByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		ServerResponse<Page<Book>> response = new ServerResponse<Page<Book>>();
		
		Page<Book> pages = bookDao.fetchBookByPaginationAndSorting(pageNumber, pageSize, field);
		
		if(!pages.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Paginated and Sorted the Book records");
			response.setData(pages);
			
			return new ResponseEntity<ServerResponse<Page<Book>>>(response,HttpStatus.OK);
		}
		else 
			throw new NoRecordFoundException("No records found");
	}
}
