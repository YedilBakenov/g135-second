package kz.test.g135secondproject.controller;

import kz.test.g135secondproject.model.Book;
import kz.test.g135secondproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(value = "/")
    public String getIndex(Model model){

        List<Book> books = bookRepository.findAll();

        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping(value = "/details-book/{id}")
    public String getDetailsPage(@PathVariable long id, Model model){
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);

        return "details-page";
    }

    @PostMapping(value = "/update-book")
    public String updateBook(Book book){
        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping(value = "/add-book")
    public String addBook(Book book){
        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-book")
    public String deleteBook(@RequestParam long id){
        bookRepository.deleteById(id);
        return "redirect:/";
    }
}
