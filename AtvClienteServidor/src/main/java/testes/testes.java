package testes;

import java.sql.Connection;
import java.sql.SQLException;

import DBConnection.ConnectionFactory;

public class testes {
	
	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Conexão efetuada com sucesso");
		connection.close();
	}
}
