package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.ConnectionFactory;
import models.Category;

public class CategoryDao {
	private Connection connection;

    public CategoryDao() {

        this.connection = new ConnectionFactory().getConnection();
    }

    public List<Category> getCategories(){
        try {
            List<Category> categories = new ArrayList<Category>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from tbcategorias");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategory(rs.getString("categoria"));
                categories.add(category);
            }
            rs.close();
            stmt.close();
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
