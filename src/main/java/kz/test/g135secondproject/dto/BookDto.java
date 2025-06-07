package kz.test.g135secondproject.dto;

import kz.test.g135secondproject.model.ImageBook;
import kz.test.g135secondproject.model.Library;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private long id;
    private String fullName;
    private String author;
    private String description;
    private int cost;
    private ImageBook imageBook;
    private Library library;
    private LocalDate saveDateBook;
    private LocalDate updateDateBook;

}
