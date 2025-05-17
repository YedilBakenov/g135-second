package kz.test.g135secondproject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "images_book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "color_page")
    private String colorPage;

    @Column(name = "size")
    private int size;

    @Column(name = "main_font")
    private int MainFont;

}
