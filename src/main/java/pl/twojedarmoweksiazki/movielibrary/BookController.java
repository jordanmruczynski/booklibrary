package pl.twojedarmoweksiazki.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Book> books) {
        return bookRepository.save(books);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);
        if (book != null) {
            book.setName(updatedBook.getName());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublishedYear(updatedBook.getPublishedYear());
            book.setRating(updatedBook.getRating());

            bookRepository.update(book);

            return 1;
        }
        return -1;
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);
        if (book != null) {
            if (updatedBook.getName() != null) book.setName(updatedBook.getName());
            if (updatedBook.getAuthor() != null) book.setAuthor(updatedBook.getAuthor());
            if (updatedBook.getPublishedYear() > 0) book.setPublishedYear(updatedBook.getPublishedYear());
            if (updatedBook.getRating() > 0) book.setRating(updatedBook.getRating());

            bookRepository.update(book);

            return 1;
        }
        return -1;
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return bookRepository.delete(id);
    }
}
