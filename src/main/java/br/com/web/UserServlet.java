package br.com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.UserDao;
import br.com.model.User;

/**
 * Este servlet atua como um controlador de página para o aplicativo, 
 * lidando com todos solicitações do usuário. 
 * 
 * @author Sandro
 *
 */

@SuppressWarnings("unused")
@WebServlet("/")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 3924616201083265405L;
	private UserDao userDao;
	
	public void inicio() {
		userDao = new UserDao();
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		try {
			switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					showNewForm(request, response);
					break;
				case "delete":
					showNewForm(request, response);
					
				case "/edit":
					showNewForm(request, response);
					break;
				case "/update":
					showNewForm(request, response);
					break;
				default:
					listUser(request, response);
					break;
				
			}
		} catch (SQLException exception) {
			throw new ServletException(exception);
		}
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<User> listUser = userDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("email");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");
		
		User livro = new User(id, nome, email, pais);
		userDao.updateUser(livro);
		response.sendRedirect("list");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		response.sendRedirect("list");
	}
}