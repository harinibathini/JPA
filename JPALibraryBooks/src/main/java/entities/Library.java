package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libraryId;
    private String libraryName;
    private String libraryCity;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<Book> books;

    public Library() {
    }

    public Library(int libraryId, String libraryName, String libraryCity, List<Book> books) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.libraryCity = libraryCity;
        this.books = books;
    }

    public Library(String libraryName, String libraryCity) {
        this.libraryName = libraryName;
        this.libraryCity = libraryCity;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryId=" + libraryId +
                ", libraryName='" + libraryName + '\'' +
                ", libraryCity='" + libraryCity + '\'' +
         //       ", books=" + books +
                '}';
    }
}
