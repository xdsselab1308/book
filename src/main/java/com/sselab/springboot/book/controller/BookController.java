package com.sselab.springboot.book.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sselab.springboot.book.form.BookForm;
import com.sselab.springboot.book.form.BookUpdateForm;
import com.sselab.springboot.book.model.BookModel;
import com.sselab.springboot.book.service.BookService;
import com.sselab.springboot.book.vm.BookGetVM;
import com.sselab.springboot.book.vm.BookVM;


@RestController
public class BookController {

	private static final Log log = LogFactory.getLog(BookController.class);

    @Autowired
    private BookService bookService;

    private BookVM bookvm;

    @RequestMapping("/getBookInfo")
    @ResponseBody
    public List<BookVM> getUserInfo(@RequestParam Long authorId) {
    	List<BookModel> bookmodel = bookService.getBookInfo(authorId);
    	List<BookVM> list = bookvm.FromModel(bookmodel);
        return list;
    }
    
    @RequestMapping("/insertBook")
    @ResponseBody
    public long add(@Valid @RequestBody BookForm form) {
        long bookId = bookService.add(form);
        return bookId;
    }    
    
    @RequestMapping("/editBook")
    @ResponseBody
    public String edit1(@Valid @RequestBody BookUpdateForm form) {
    	String result = "fail";
        boolean bookId = bookService.updateById1(form);
        System.out.println(bookId);
        if(bookId){
        	result = "success";
        }
        return result;
    }        
    
    @RequestMapping("/deleteBook")
    @ResponseBody
    public long delete(@RequestParam Long bookId) {
        int flag = bookService.deleteById(bookId);
        return flag;
    }
    
    @RequestMapping("/getAllBook")
    @ResponseBody
    public List<BookGetVM> list1(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limits) {
    	List<BookGetVM> list = bookService.getBookInof(page, limits);
    	return list;
    }
    
    @RequestMapping("/getBookById")
    @ResponseBody
    public BookModel get(@RequestParam long bookId) {
    	BookModel model = bookService.getById(bookId);
    	return model;
    }
    
    @RequestMapping("/getBookByName")
    @ResponseBody
    public List<BookGetVM> getBook1(@RequestParam String bookname) {
    	List<BookGetVM> model = bookService.getByName(bookname);
    	return model;
    }    
    
    @RequestMapping("/getBookByAuthorName")
    @ResponseBody
    public List<BookGetVM> getBookByAuthorName(@RequestParam String authorname) {
    	List<BookGetVM> list = bookService.getBookByAuthorName(authorname);
    	return list;
    }        
    
}


