package jsp.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.springboot.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	//fetch Book by author
	List<Book> findByAuthor(String author);
	
	//fetch Book with price > value
	List<Book> findByPriceGreaterThan(double price);
	
	///fetch Book with price between a range
	List<Book> findByPriceBetween(double startPrice, double endPrice);
	
	//fetch a Book based on published year
	@Query("select b from Book b where b.publishedYear = ?1")
	List<Book> getBookByPublishedYear(int publishedYear);
	
	//fetch a Book based on Availability
	@Query("select b from Book b where b.availability = ?1")
	List<Book> getBookByAvailability(boolean availability);
	
	//fetch a Book by genre
	@Query("select b from Book b where b.genre =:genre")
	List<Book> getBookByGenre(String genre);
}
