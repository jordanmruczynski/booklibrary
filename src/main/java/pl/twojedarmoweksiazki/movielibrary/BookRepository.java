package pl.twojedarmoweksiazki.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM books",
                (rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getInt("rating"))
        );
    }

    public Book getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id = ?",
                (rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getInt("rating")), id);
    }

    public int save(List<Book> books) {
        books.forEach(book -> jdbcTemplate.update("INSERT INTO books (name, author, publishedYear, rating) VALUES (?, ?)",
                book.getName(), book.getAuthor(), book.getPublishedYear(), book.getRating()));
        return 1;
    }

    public int update(Book book) {
        return jdbcTemplate.update("UPDATE books SET name = ?, author = ?, publishedYear = ?, rating = ? WHERE id = ?",
                book.getName(), book.getAuthor(), book.getPublishedYear(), book.getRating(), book.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

}
