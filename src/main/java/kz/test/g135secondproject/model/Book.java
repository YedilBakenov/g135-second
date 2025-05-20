package kz.test.g135secondproject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "cost")
    private int cost;

    @OneToOne
    @JoinColumn(name = "image_book_id")
    private ImageBook imageBook;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToMany
    private List<City> cities;
}
