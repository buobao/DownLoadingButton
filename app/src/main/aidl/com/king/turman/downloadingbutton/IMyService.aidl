package com.king.turman.downloadingbutton;

import com.king.turman.downloadingbutton.Book;

interface IMyService {

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<Book> getBook();
    void addBook(in Book book);
}