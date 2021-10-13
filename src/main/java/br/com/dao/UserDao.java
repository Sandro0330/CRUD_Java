package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.model.User;

/**
 * Esta classe DAO fornece operações de banco de dados CRUD para o
 * usuários da tabela no banco de dados. 
 * 
 * 
 * @author Sandro
 *
 */


public class UserDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/jsp_crud?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345678";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + " (nome, email, pais) VALUES (?, ?, ?);";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String SELECT_USER_BY_ID = "select id, name, email, pais from users where id = ?";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?, email = ?, pais = ? where id = ?;";
	
	public UserDao() {
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword); 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPais());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
			
		}
	}
	
	public User selectUser(int id) {
		User user = null;
		// a instrução try-with-resource fechará automaticamente a conexão. 
		try(Connection connection = getConnection();
				// Etapa 2: Criar uma instrução usando o objeto de conexão 
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Etapa 3: execute a consulta ou atualize a consulta 
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Etapa 4: Processar o objeto ResultSet. 
			while(resultSet.next()) {
				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String pais = resultSet.getString("pais");
				user = new User(id, nome, email, pais);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	public List<User> selectAllUsers() {
		// usando try-with-resources para evitar o fechamento de recursos (código padrão) 
		
		List<User> users = new ArrayList<User>();
		
		// Etapa 1: Estabelecendo uma conexão 
		try(Connection connection = getConnection();
			// Etapa 2: Criar uma instrução usando o objeto de conexão 
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
			
			// Etapa 3: execute a consulta ou atualize a consulta 
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Etapa 4: Processar o objeto ResultSet
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String pais = resultSet.getString("pais");
				users.add(new User(id, nome, email, pais));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}	
		return users;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		boolean deletar;
		try(Connection connection = getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);) {
			preparedStatement.setInt(1, id);
			deletar = preparedStatement.executeUpdate() > 0;
		}
		return deletar;
	}

	public boolean updateUser(User user) throws SQLException {
		boolean atualizacao;
		try(Connection connection = getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
			
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPais());
			preparedStatement.setInt(4, user.getId());
			
			atualizacao = preparedStatement.executeUpdate() > 0;
		} 
		
		return atualizacao;
	}
	
	private void printSQLException(SQLException ex) {
		for(Throwable throwable : ex) {
			if(throwable instanceof SQLException) {
				throwable.printStackTrace(System.err);
				System.err.println("Estado SQL: " +  ((SQLException) throwable).getSQLState());
				System.err.println("Error Code: " + ((SQLException) throwable).getErrorCode());
				System.err.println("Mensagem: " + throwable.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Causa do erro: " + t);
					t = t.getCause();
				}
				
			}
		}
	}
	
	
	
	
	
}









































