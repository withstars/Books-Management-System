package com.book.web;

import com.book.domain.Book;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(HttpServletRequest request,String searchWord){
        boolean exist=bookService.matchBook(searchWord);
        if (exist){
            ArrayList<Book> books = bookService.queryBook(searchWord);

            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books",books);
            return modelAndView;
        }
        else{
            return new ModelAndView("admin_books","error","没有匹配的图书");
        }
    }
    @RequestMapping("/allbooks.html")
    public ModelAndView allBook(){
        ArrayList<Book> books=bookService.getAllBooks();
        ModelAndView modelAndView=new ModelAndView("admin_books");
        modelAndView.addObject("books",books);
        return modelAndView;
    }
    @RequestMapping("/deletebook.html")
    public ModelAndView deleteBook(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        int res=bookService.deleteBook(bookId);
        ArrayList<Book> books=bookService.getAllBooks();
        if (res==1){
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("succ","删除成功！");
            modelAndView.addObject("books",books);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("error","删除失败！");
            modelAndView.addObject("books",books);
            return modelAndView;
        }
    }

    @RequestMapping("/book_add.html")
    public ModelAndView addBook(HttpServletRequest request){

            return new ModelAndView("admin_book_add");

    }

    @RequestMapping("/book_add_do.html")
    public ModelAndView addBookDo(HttpServletRequest request,BookAddCommand bookAddCommand){
        Book book=new Book();
        book.setBookId(0);
        book.setPrice(bookAddCommand.getPrice());
        book.setState(bookAddCommand.getState());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setPressmark(bookAddCommand.getPressmark());
        book.setLanguage(bookAddCommand.getLanguage());


        boolean succ=bookService.addBook(book);
        ArrayList<Book> books=bookService.getAllBooks();
        if (succ){
            ModelAndView modelAndView= new ModelAndView("admin_books");
            modelAndView.addObject("succ","图书添加成功");
            modelAndView.addObject("books",books);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView= new ModelAndView("admin_books");
            modelAndView.addObject("error","图书添加失败");
            modelAndView.addObject("books",books);
            return modelAndView;
        }
    }



}
