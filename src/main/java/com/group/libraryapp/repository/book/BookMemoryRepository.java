package com.group.libraryapp.repository.book;

import org.springframework.stereotype.Repository;

@Repository
public class BookMemoryRepository{

    // private final List<Book> books = new ArrayList<Book>();

    public void saveBook() {
        // books.add(new Book());
        System.out.println("MemoryRepository");
    }
}