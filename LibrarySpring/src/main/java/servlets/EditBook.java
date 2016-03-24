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
 * Servlet implementation class EditBook
 */
@WebServlet(urlPatterns={"/editbook"}, name="EditBookServlet")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBook() {
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
		if(user == null || user.getIsAdmin() == false)
			response.sendRedirect("index.jsp");
		else
		{
		    BookService bookService = new BookServiceImpl();
			Book book = bookService.getBook(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("book", book);
			request.getRequestDispatcher("editbooks.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Book book = new Book(request.getParameter("title"), request.getParameter("author"), request.getParameter("isbn"), request.getParameter("state"));
		book.setId(Integer.parseInt(request.getParameter("id")));       
		BookService bookService = new BookServiceImpl();
		bookService.updateBook(book);
		response.sendRedirect("booklist");
	
	}

}
