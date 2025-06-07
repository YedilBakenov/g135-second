package kz.test.g135secondproject.mapper;


import kz.test.g135secondproject.dto.BookDto;
import kz.test.g135secondproject.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "fullName", source = "name")
    @Mapping(target = "saveDateBook", source = "registerBook")
    @Mapping(target = "updateDateBook", source = "updateBook")
    BookDto toDto(Book book);

    @Mapping(target = "name", source = "fullName")
    @Mapping(target = "registerBook", source = "saveDateBook")
    @Mapping(target = "updateBook", source = "updateDateBook")
    Book toEntity(BookDto bookDto);

    List<BookDto>toDtoList(List<Book> books);

    List<Book>toEntityList(List<BookDto> dtoList);

    default Page<BookDto> toDtoPage(Page<Book> bookPage) {
        List<BookDto> dtoList = toDtoList(bookPage.getContent());
        return new PageImpl<>(dtoList, bookPage.getPageable(), bookPage.getTotalElements());
    }
}

