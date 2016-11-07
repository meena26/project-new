package com.spring.services;
import java.util.List;
import com.spring.model.Book;
   public interface BookServices {
            List<Book> getAllBooks();
                   Book getBookByIsbn(int isbn);

                  public void deleteBook(int isbn);
	              //  bookDao.deleteBook(isbn);
void addBook(Book book);
void editBook(Book book);
   }
   



