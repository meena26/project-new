package com.niit.springmvc;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Book;
import com.spring.model.Category;
import com.spring.services.BookServices;

@Controller
public class BookController {
	
	@Autowired
private BookServices bookServices;

public BookServices getBookService() {
	return bookServices;
}

public void setBookService(BookServices bookServices) {
	this.bookServices = bookServices;
}
@RequestMapping("/getAllBooks")
public ModelAndView getAllBooks(){
	List<Book> books = bookServices.getAllBooks();
	return new ModelAndView("bookList","books",books);
	
}
/*@RequestMapping("/getBooksList")
public @ResponseBody List<Book> getBooksList In JSON(){
	return bookServices.getAllBooks();
}*/

@RequestMapping("/getBookByIsbn/{isbn}")
public ModelAndView getBookByIsbn(@PathVariable(value="isbn") int isbn){
	Book b=bookServices.getBookByIsbn(isbn);
	System.out.println("iam here");
	return new ModelAndView("bookPage","bookObj",b);
}
@RequestMapping("/delete/{isbn}")
public String deleteBookByIsbn(@PathVariable(value="isbn")int isbn){
    bookServices.deleteBook(isbn);
  //  http://localhost:8080/welcomehelloworld
   return "redirect://getAllBooks";
}
//Two method for insert
//1. Method is to forward to bookForm.jsp


//2. Method is to insert the record by invoking dao method
//<a href=""></a> //get method
@RequestMapping(value="/admin/book/addBook",method=RequestMethod.GET)
public String getBookForm(Model model){
	Book book=new Book();
	Category category=new Category();
	category.setCid(1);//New Arrivals
	//set the category as 1 for the Book book
	book.setCategory(category);
	model.addAttribute("bookFormObj",book);
	return"bookForm";
}
@RequestMapping(value="/admin/book/addBook",method=RequestMethod.POST)
public String addBook( @Valid @ModelAttribute(value="bookFormObj")  Book book,BindingResult result){
	if(result.hasErrors())
		return "bookForm";
	bookServices.addBook(book);
	MultipartFile bookimage=book.getBookImage();
	if(bookimage!=null&& !bookimage.isEmpty()){
	Path path=Paths.get("F:\\workspace\\welcomehelloworld\\src\\main\\webapp\\WEB-INF\\resources\\images\\" + book.getIsbn() + ".png");
	try {
		bookimage.transferTo(new File(path.toString()));
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	return "redirect:/getAllBooks";
}	
@RequestMapping("/admin/book/editBook/{isbn}")
public ModelAndView getEditForm(@PathVariable(value="isbn") int isbn){
	//First read the record which has to be updated
		//select * from bookapp where isbn=?
		//Populate the form with already existing value

	Book book=bookServices.getBookByIsbn(isbn);
	return new ModelAndView("editForm","editBookObj",book);
	
}
@RequestMapping(value="/admin/book/editBook",method=RequestMethod.POST)
public String editBook(@ModelAttribute(value="editBookObj") Book book)
{
	
	bookServices.editBook(book);
	return"redirect:/getAllBooks";
	}

@RequestMapping("/getBooksList")
public @ResponseBody List<Book>getBooksListInJSON(){
	return bookServices.getAllBooks();
}

/*@RequestMapping("/booksListAngular")
public String getBooks(){
	return"booksListAnguler";
}*/

}

