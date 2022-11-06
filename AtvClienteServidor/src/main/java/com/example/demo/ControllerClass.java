package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.BookDao;
import dao.CategoryDao;
import models.Book;
import models.Category;

@Controller
public class ControllerClass {
	CategoryDao catDao = new CategoryDao();
	List<Category> categories = catDao.getCategories();
	
	// Método que chama da página inicial
		@GetMapping("/")
		public String index(ModelMap model) {
			
			return "index";
		}
		
	// Método chamado pelo botão "Buscar por Categoria" da página inicial
	@GetMapping("/booksByCategories")
	public String booksByCategories(ModelMap model) {
		
		model.addAttribute("category",categories);
		
		return "listaLivrosPorCategoria";
	}
	
	// Método chamado pelo botão "Consultar Livros" da página "listaLivrosPorCategoria"
		@GetMapping("/listBooksByCategory")
		public String LivrosPorCategoria(@RequestParam("categories") String category, ModelMap model) {
			
			model.addAttribute("category",categories);
			List<Book> listBooks = new ArrayList<Book>();
			BookDao bookDao = new BookDao();
			listBooks = bookDao.getBooks(category);
			model.addAttribute("book",listBooks);
			
			if(listBooks.isEmpty()) {
				
				model.addAttribute("table", false);
				model.addAttribute("error", true);
			}else {
				
				model.addAttribute("table", true);
				model.addAttribute("error", false);
			}
			
			
			return "listaLivrosPorCategoria";
		}
		
		// Método chamado pelo botão "CRUD Livros" da página inicial
	    @GetMapping("/CRUDBooks")
		    public String CRUDBooks(ModelMap model) {
		        model.addAttribute("category",categories);
		        return "CRUDBooks";
		    }
	 // Método chamado por algum dos botões "Inserir Livro","Editar Livro" ou "Excluir Livro" da página "gerLivros"
		@GetMapping("/modBook")
		public String modBook(@RequestParam( value ="codBook", required = true) String codBook, 
								@RequestParam(value ="title", required = true) String title, 
								@RequestParam(value ="author", required = true) String author,
								@RequestParam(value ="value", required = true) String value,
								@RequestParam(value = "action", required = true) String action,
								@RequestParam(value ="categories", required = true) String category,
								ModelMap model) {
			
			BookDao bookDao = new BookDao();
			int id = 0;
			float val = 0;
			
			if(!value.isEmpty()) {
				val = Float.parseFloat(value);
			}
			
			if(!codBook.isEmpty()) {
				id = Integer.parseInt(codBook);
			}
			
			Book book = new Book();
			book.setId(id);
			book.setAuthor(author);
			book.setCategory(category);
			book.setTitle(title);
			book.setValue(val);
			
			switch (action) {
			case "Insert Book": {
				
				try {
					bookDao.insertBook(book);
					model.addAttribute("insertSuc",true);
					model.addAttribute("category",categories);					
				} catch (Exception e) {
					model.addAttribute("insertError",true);
					model.addAttribute("category",categories);	
					return "CRUDBooks";
				}
				
			}
			break;
			case "Update Book":{
				try {
					bookDao.updateBooksById(book);
					model.addAttribute("updateSuc",true);
					model.addAttribute("category",categories);
				} catch (Exception e) {
					model.addAttribute("updateError",true);
					model.addAttribute("category",categories);	
					return "CRUDBooks";
				}
				
			}
			break;
			case "Delete Book":{
				try {
					bookDao.deleteBooksById(book);
					model.addAttribute("deleteSuc",true);
					model.addAttribute("category",categories);
				} catch (Exception e) {
					model.addAttribute("deleteError",true);
					model.addAttribute("category",categories);	
					return "CRUDBooks";
				}
				
			}
			break;
			default:
				
			}
			
			return "CRUDBooks";
		}
		
		// Método chamado pelo botão "Mostrar Livros" da página inicial 
	    @GetMapping("/booksById")
	    public String booksById(ModelMap model) {
	        return "booksById";
	    }
	    
	 // Método chamado pelo botão "Consultar Livro" da página "mostrarLivros"
	    @GetMapping("/searchBook")
	    public String searchBook(@RequestParam("codBook") String codBook, ModelMap model) {
	        List<Book> listBook = new ArrayList<Book>();
	        BookDao bookDao = new BookDao();
	        int id = Integer.parseInt(codBook);
	        listBook = bookDao.getBooks(id);
	        if(listBook.size() == 0) {
	            model.addAttribute("table", false);
	            model.addAttribute("error", true);
	        }else {
	            model.addAttribute("table", true);
	            model.addAttribute("error", false);
	        }
	        model.addAttribute("book",listBook);
	        return "booksById";
	    }
}
