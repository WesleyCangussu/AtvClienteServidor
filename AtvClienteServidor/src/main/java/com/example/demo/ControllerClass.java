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
		public String LivrosPorCategoria(@RequestParam("categorias") String category, ModelMap model) {
			
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
	
		
}
