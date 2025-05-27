package kz.test.g135secondproject.controller;

import kz.test.g135secondproject.db.DBConnector;
import kz.test.g135secondproject.model.Book;
import kz.test.g135secondproject.model.City;
import kz.test.g135secondproject.model3.Magazine;
import kz.test.g135secondproject.repository.BookRepository;
import kz.test.g135secondproject.repository.CItyRepository;
import kz.test.g135secondproject.repository3.MagazineRepository;
import kz.test.g135secondproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BooksController {

    private final DBConnector dbConnector;
    private final CItyRepository cityRepository;
    private final MagazineRepository magazineRepository;

    @Autowired
    @Qualifier("primary")
    private BookService bookService;


    @GetMapping(value = "/search-by-word")
    public String getBooks(@RequestParam String word,
                           Model model){
        model.addAttribute("books",
               bookService.searchByWord(word));

        return "index";
    }


    @GetMapping(value = "/")
    public String getIndex(Model model) {

        List<Book> books = bookService.findAllBooks();

        model.addAttribute("books", books);

        dbConnector.geInfo();

        return "index";
    }

    @GetMapping(value = "/details-book/{id}")
    public String getDetailsPage(@PathVariable long id, Model model) {
        Book book = bookService.findBookById(id);
        List<City> cities = cityRepository.findAll();
        cities.removeAll(book.getCities());

        model.addAttribute("book", book);
        model.addAttribute("cities", cities);

        return "details-page";
    }

    @GetMapping(value = "/get-by-name/{name}")
    public String getBookByName(@PathVariable String name, Model model) {
        Book book = bookService.findByName(name);

        model.addAttribute("book", book);

        return "details-page-book";
    }

    @GetMapping(value = "/by-author/{author}")
    public String getBookByAuthor(@PathVariable String author, Model model){
        model.addAttribute("book", bookService.findByAuthor(author));

        return "details-page-book";
    }

    @PostMapping(value = "/update-book")
    public String updateBook(Book book) {
        bookService.updateBook(book);
        return "redirect:/";
    }

    @PostMapping(value = "/add-book")
    public String addBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-book")
    public String deleteBook(@RequestParam long id) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }

    @PostMapping(value = "/add-city")
    public String addCityToBook(@RequestParam long book_id,
                                @RequestParam long city_id) {

        Book book = bookService.findBookById(book_id);
        City city = cityRepository.findById(city_id).get();

        book.getCities().add(city);

        bookService.saveBook(book);

        return "redirect:/details-book/" + book_id;
    }

    @PostMapping(value = "/delete-city")
    public String deleteCity(@RequestParam long book_id,
                             @RequestParam long city_id) {

        Book book = bookService.findBookById(book_id);
        City city = cityRepository.findById(city_id).get();

        book.getCities().remove(city);
        bookService.saveBook(book);

        return "redirect:/details-book/" + book_id;

    }

    @PostMapping(value = "/add-magazine")
    public String addBook(Magazine magazine) {
        magazineRepository.save(magazine);
        return "redirect:/";
    }
}
