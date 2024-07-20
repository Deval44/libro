package system.library.libro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "copies")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Copy {

    @Id
    @Column(name = "copy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "book_id")
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn(name = "rack_id")
    private Rack rack;

    @OneToOne(mappedBy = "copy")
    private Slip slip;

    public Copy(Book book){
        this.book = book;
    }

    public void removeFromRack() {
        this.rack.getCopies().remove(this);
        this.rack = null;
    }
}
