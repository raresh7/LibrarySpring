package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookService;
import dao.BookServiceImpl.BookServiceImpl;
import entities.Book;
import entities.User;

/**
 * Servlet implementation class AddBook
 */
@WebServlet(urlPatterns={"/addbook"}, name="AddBookServlet")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user;
		user = (User)session.getAttribute("loggedUser");
		if(user.getIsAdmin()){
			response.sendRedirect("newbook.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    BookService bookService = new BookServiceImpl();
        
		Book book = new Book(	request.getParameter("title"),
								request.getParameter("author"),
								request.getParameter("isbn"),
								request.getParameter("state"));
		bookService.createBook(book); 
		
		response.sendRedirect("newbook.jsp");
	}

}
