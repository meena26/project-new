package com.spring.dao;

//import javax.websocket.Session;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Book;



@Repository
public class BookDaoImp implements BookDao {
@Autowired

private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}
	public List<Book> getAllBooks() {
		//it will open a new session always.
		Session session=sessionFactory.openSession();
		//Selecting all records from the table
		List<Book> books=session.createQuery("from Book").list();
		session.close();// close the session.
		
		return books;
		
	}
	public Book getBookByIsbn(int i) {
		//reading the record from the table
	  Session session=sessionFactory.openSession();
	//select * from book where isbn=i
	//if we call get method,Record doesnot exist it will return null
	//if we call load, if the record doesnt exist it will throw exception
	  Book book=(Book) session.get(Book.class, i);  
	session.close();
	return book;
	}
	public void deleteBook(int isbn){
		 Session session=sessionFactory.openSession();
		 Book book=(Book) session.get(Book.class, isbn);
		session.delete(book);
		 session.flush();
		session.close();
		}
 	public void addBook(Book book) {
    		Session session=sessionFactory.openSession();
    		session.save(book);
    		session.flush();
    		session.close();	
    }	 	
public void editBook(Book book)
{
	 Session session=sessionFactory.openSession();
	// Book book=(Book) session.get(Book.class, isbn);
	session.update(book);
	 session.flush();
	session.close();
}
}


