package com.feri.bookmanagment.repository;

import com.feri.bookmanagment.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setup() {
        mongoTemplate.dropCollection(Book.class);
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.dropCollection(Book.class);
    }

    // Test za iskanje knjig po naslovu (ignoriranje velikosti črk)
    @Test
    void findByTitleContainingIgnoreCase_ShouldReturnBooks() {
        Book book1 = new Book(null, "Harry Potter", "J.K. Rowling", "ISBN1234", "Fantasy");
        bookRepository.save(book1);

        List<Book> foundBooks = bookRepository.findByTitleContainingIgnoreCase("harry");

        assertThat(foundBooks).hasSize(1);
        assertThat(foundBooks.get(0).getTitle()).isEqualTo(book1.getTitle());
    }

    // Test za iskanje knjig po avtorju (ignoriranje velikosti črk)
    @Test
    void findByAuthorContainingIgnoreCase_ShouldReturnBooks() {
        Book book1 = new Book(null, "The Hobbit", "J.R.R. Tolkien", "ISBN5678", "Fantasy");
        bookRepository.save(book1);

        List<Book> foundBooks = bookRepository.findByAuthorContainingIgnoreCase("tolkien");

        assertThat(foundBooks).hasSize(1);
        assertThat(foundBooks.get(0).getAuthor()).isEqualTo(book1.getAuthor());
    }

    // Test za iskanje knjig po žanru (ignoriranje velikosti črk)
    @Test
    void findByGenreContainingIgnoreCase_ShouldReturnBooks() {
        Book book1 = new Book(null, "1984", "George Orwell", "ISBN91011", "Dystopian");
        bookRepository.save(book1);

        List<Book> foundBooks = bookRepository.findByGenreContainingIgnoreCase("dystopian");

        assertThat(foundBooks).hasSize(1);
        assertThat(foundBooks.get(0).getGenre()).isEqualTo(book1.getGenre());
    }

    // Test za dodajanje nove knjige
    @Test
    void save_ShouldCorrectlyAddNewBook() {
        Book newBook = new Book(null, "New Book", "New Author", "ISBNNew", "New Genre");
        Book savedBook = bookRepository.save(newBook);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
    }

    // Test za brisanje knjige
    @Test
    void deleteById_ShouldRemoveBookCorrectly() {
        Book book = new Book(null, "To Be Deleted", "Author", "ISBNToDelete", "Genre");
        book = bookRepository.save(book);

        bookRepository.deleteById(book.getId());
        boolean exists = bookRepository.existsById(book.getId());

        assertThat(exists).isFalse();
    }
}
