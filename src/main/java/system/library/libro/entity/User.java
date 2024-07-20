package system.library.libro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "Book_Author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnore
    private Set<Book> booksAuthored;

    @OneToMany(mappedBy = "user")
    private Set<Slip> slips;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
