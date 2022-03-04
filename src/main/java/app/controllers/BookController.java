package app.controllers;

import app.entities.AddressBook;
import app.entities.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import app.repositories.BookRepository;
import app.repositories.BuddyRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final BuddyRepository buddyRepository;

    @Autowired
    public BookController(BookRepository bookRepository, BuddyRepository buddyRepository) {
        this.bookRepository = bookRepository;
        this.buddyRepository = buddyRepository;
    }

    // Single page Javascript application
    @GetMapping("/app")
    String singlePageApp() {
        return "spa";
    }

    /*
    ###################################### REST API stuff ######################################
     */

    @GetMapping("/api/book/")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    List<AddressBook> getAddressBooks() {
        List<AddressBook> result = new ArrayList<>();
        bookRepository.findAll().forEach(result::add);
        return result;
    }

    @PostMapping("/api/book/")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    AddressBook createAddressBook() {
        return bookRepository.save(new AddressBook());
    }

    @GetMapping("/api/book/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    AddressBook getAddressBook(@PathVariable("id") long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping(value = "/api/book/{id}/buddy", consumes = "application/json")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    BuddyInfo createBuddy(@PathVariable("id") long id, @RequestBody BuddyInfo buddy) {
        AddressBook book = bookRepository.findById(id).get();
        buddy.setAddressBook(book);
        return buddyRepository.save(buddy);
    }

    @DeleteMapping("/api/book/{id}/buddy/{bid}")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    BuddyInfo deleteBuddy(@PathVariable("id") long id, @PathVariable("bid") long bid) {
        BuddyInfo buddy = buddyRepository.findById(bid).get();
        buddyRepository.delete(buddy);
        return buddy;
    }

    /*
    ###################################### Template stuff ######################################
     */
    @GetMapping("/book/new")
    String createBook() {
        return "new-book";
    }

    // View address book given the id
    @GetMapping("/book/{id}")
    String getAddressBook(@PathVariable("id") long id, Model model) {
        Optional<AddressBook> res = bookRepository.findById(id);
        res.ifPresent(addressBook -> model.addAttribute("bookId", addressBook.getId()));
        model.addAttribute("buddies", buddyRepository.findAll());
        return "show-book";
    }

    // Add a BuddyInfo to the address book
    @PostMapping(value = "/book/{id}/add", consumes = "application/json", produces = "application/json")
    @ResponseBody
    BuddyInfo addBuddy(@RequestBody BuddyInfo buddy, @PathVariable("id") long id) {
        System.out.println("In addBuddy handler");
        Optional<AddressBook> res = bookRepository.findById(id);
        if (res.isPresent()) {
            AddressBook ab = res.get();
            buddy.setAddressBook(ab);
            System.out.println(buddy);
            return buddyRepository.save(buddy);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
    }

    // Remove a BuddyInfo object
    @DeleteMapping(value="/buddy/{id}")
    @ResponseBody
    BuddyInfo removeBuddy(@PathVariable("id") long id) {
        Optional<BuddyInfo> res = buddyRepository.findById(id);
        if (res.isPresent()) {
            BuddyInfo bi = res.get();
            buddyRepository.delete(bi);
            return bi;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
    }

    @RequestMapping("/hello")
    String home() {
        return "hello";
    }
}
