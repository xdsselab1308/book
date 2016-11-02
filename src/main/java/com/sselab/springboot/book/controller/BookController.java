package com.sselab.springboot.book.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sselab.springboot.book.form.BookForm;
import com.sselab.springboot.book.form.BookUpdateForm;
import com.sselab.springboot.book.model.BookModel;
import com.sselab.springboot.book.service.BookService;
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
    public long edit(@Valid @RequestBody BookUpdateForm form) {
        long bookId = bookService.updateById(form);
        return bookId;
    }
    
    @RequestMapping("/deleteBook")
    @ResponseBody
    public long delete(@RequestParam Long bookId) {
        int flag = bookService.deleteById(bookId);
        return flag;
    }
    
    @RequestMapping("/getAllBook")
    @ResponseBody
    public List<BookModel> list(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limits) {
    	List<BookModel> list = bookService.selectPaged(page, limits);
    	return list;
    }
    
    
    @RequestMapping("/getBookById")
    @ResponseBody
    public BookModel get(@RequestParam long bookId) {
    	BookModel model = bookService.getById(bookId);
    	return model;
    }
    
}


