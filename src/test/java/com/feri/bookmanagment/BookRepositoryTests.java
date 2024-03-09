package com.feri.bookmanagment;

import com.feri.bookmanagment.model.Book;
import com.feri.bookmanagment.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataMongoTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        // Setup
        String title = "Test Title";
        bookRepository.save(new Book("1", title, "Author", "123456", "Genre"));

        // Execute
        List<Book> foundBooks = bookRepository.findByTitleContainingIgnoreCase("test");

        // Assert
        assertThat(foundBooks).hasSize(1);
        assertThat(foundBooks.get(0).getTitle()).isEqualTo(title);
    }
}
