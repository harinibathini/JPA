package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Book{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int bookId;
        private String bookName;
        private String bookAuthor;
        private String bookPublisher;
        private int bookPrice;
        private Date PublishedDate;

        @ManyToOne
        private Library library;

        public Book() {
        }

        public Book(int bookId, String bookName, String bookAuthor, String bookPublisher, int bookPrice, Date publishedDate, Library library) {
                this.bookId = bookId;
                this.bookName = bookName;
                this.bookAuthor = bookAuthor;
                this.bookPublisher = bookPublisher;
                this.bookPrice = bookPrice;
                PublishedDate = publishedDate;
                this.library = library;
        }

        public Book(String bookName, String bookAuthor, String bookPublisher, int bookPrice, Date publishedDate, Library library) {
                this.bookName = bookName;
                this.bookAuthor = bookAuthor;
                this.bookPublisher = bookPublisher;
                this.bookPrice = bookPrice;
                PublishedDate = publishedDate;
                this.library = library;
        }

        @Override
        public String toString() {
                return "Book{" +
                        "bookId=" + bookId +
                        ", bookName='" + bookName + '\'' +
                        ", bookAuthor='" + bookAuthor + '\'' +
                        ", bookPublisher='" + bookPublisher + '\'' +
                        ", bookPrice=" + bookPrice +
                        ", PublishedDate=" + PublishedDate +
                   //     ", library=" + library +
                        '}';
        }
}
