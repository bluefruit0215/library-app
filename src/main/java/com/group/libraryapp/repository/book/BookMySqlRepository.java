package com.group.libraryapp.repository.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Primary
@Repository
public class BookMySqlRepository  {
    //@Override
    public void saveBook() {
        System.out.println("MySqlRepository");

    }
}
