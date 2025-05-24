package kz.test.g135secondproject.controller;

import kz.test.g135secondproject.db.DBConnector;
import kz.test.g135secondproject.model.Book;
import kz.test.g135secondproject.model.City;
import kz.test.g135secondproject.model3.Magazine;
import kz.test.g135secondproject.repository.BookRepository;
import kz.test.g135secondproject.repository.CItyRepository;
import kz.test.g135secondproject.repository3.MagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BooksController {

    private final BookRepository bookRepository;
    private final DBConnector dbConnector;
    private final CItyRepository cityRepository;
    private final MagazineRepository magazineRepository;


    @GetMapping(value = "/search")
    public String getBooksByWord(@RequestParam String word,
                                 Model model){
        List<Book> books = bookRepository.findByOptionWord(word);
        model.addAttribute("books", books);

        return "index";

    }


    @GetMapping(value = "/")
    public String getIndex(Model model) {

        List<Book> books = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        model.addAttribute("books", books);

        dbConnector.geInfo();

        return "index";
    }

    @GetMapping(value = "/details-book/{id}")
    public String getDetailsPage(@PathVariable long id, Model model) {
        Book book = bookRepository.findById(id).get();
        List<City> cities = cityRepository.findAll();
        cities.removeAll(book.getCities());

        model.addAttribute("book", book);
        model.addAttribute("cities", cities);

        return "details-page";
    }

    @GetMapping(value = "/get-by-name/{name}")
    public String getBookByName(@PathVariable String name, Model model) {
        Book book = bookRepository.findByName(name);

        model.addAttribute("book", book);

        return "details-page-book";
    }

    @PostMapping(value = "/update-book")
    public String updateBook(Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping(value = "/add-book")
    public String addBook(Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-book")
    public String deleteBook(@RequestParam long id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping(value = "/add-city")
    public String addCityToBook(@RequestParam long book_id,
                                @RequestParam long city_id) {

        Book book = bookRepository.findById(book_id).get();
        City city = cityRepository.findById(city_id).get();

        book.getCities().add(city);

        bookRepository.save(book);

        return "redirect:/details-book/" + book_id;
    }

    @PostMapping(value = "/delete-city")
    public String deleteCity(@RequestParam long book_id,
                             @RequestParam long city_id) {

        Book book = bookRepository.findById(book_id).get();
        City city = cityRepository.findById(city_id).get();

        book.getCities().remove(city);
        bookRepository.save(book);

        return "redirect:/details-book/" + book_id;

    }

    @PostMapping(value = "/add-magazine")
    public String addBook(Magazine magazine) {
        magazineRepository.save(magazine);
        return "redirect:/";
    }
}
