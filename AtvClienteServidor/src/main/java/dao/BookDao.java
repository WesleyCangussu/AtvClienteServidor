package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.ConnectionFactory;
import models.Book;

public class BookDao {
private Connection connection;
	
	public BookDao() {
		
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void insertBook(Book book) {
		String sql = "insert into tblivros" + "(codlivro,titulo, autor, categoria, valor)" + "values (?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, book.getId());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getCategory());
			stmt.setFloat(5, book.getValue());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			}
	}
	
		public List<Book> getBooks(String categoria){
			try {
				List<Book> books = new ArrayList<Book>();
				PreparedStatement stmt = this.connection.prepareStatement("select * from tblivros where categoria=?");
				stmt.setString(1, categoria);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("codlivro"));
					book.setTitle(rs.getString("titulo"));
					book.setAuthor(rs.getString("autor"));
					book.setCategory(rs.getString("categoria"));
					book.setValue(rs.getFloat("valor"));
					books.add(book);
				}
				rs.close();
				stmt.close();
				return books;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		public List<Book> getBooks(int id){
			try {
				List<Book> books = new ArrayList<Book>();
				PreparedStatement stmt = this.connection.prepareStatement("select * from tblivros where codlivro=?");
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("codlivro"));
					book.setTitle(rs.getString("titulo"));
					book.setAuthor(rs.getString("autor"));
					book.setCategory(rs.getString("categoria"));
					book.setValue(rs.getFloat("valor"));
					books.add(book);
				}
				rs.close();
				stmt.close();
				return books;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		
	public void updateBooksById(Book book) {
		String sql = "update tblivros set titulo=?, autor=?, categoria=?, valor=? where codlivro=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory());
			stmt.setFloat(4, book.getValue());
			stmt.setInt(5, book.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);		
			}
	}
	
	public void deleteBooksById(Book book) {
		String sql = "delete from tblivros where codlivro=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, book.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
