package com.example.demo.bootstrap;

import com.example.demo.Author;
import com.example.demo.Book;
import com.example.demo.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        initData();
    }

    private void initData(){

        Author eric = new Author("Eric", "Evans");
        Publisher p1 = new Publisher("Jacek Więckowski" , "Monte Cassino 4" );
        Book ddd = new Book("Domain driven design", "1234", p1);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(p1);
        bookRepository.save(ddd);



        Author rod = new Author("Rod", "Johnson");
        Publisher p2 = new Publisher("Monika Michalak" , "Monte Cassino 4/25" );
        Book noEJB = new Book("J2EE Developement without EJB", "23444", p2);

        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(p2);
        bookRepository.save(noEJB);


    }



}
