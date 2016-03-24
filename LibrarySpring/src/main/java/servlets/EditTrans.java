package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.BookService;
import dao.TransactionService;
import dao.UserService;
import dao.BookServiceImpl.BookServiceImpl;
import dao.TransactionServiceImpl.TransactionServiceImpl;
import dao.UserServiceImpl.UserServiceFactory;
import entities.Book;
import entities.Transaction;
import entities.User;


/**
 * Servlet implementation class EditTrans
 */
@WebServlet(urlPatterns={"/edittrans"}, name="EditTransServlet")
public class EditTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTrans() {
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
		    TransactionService transService = new TransactionServiceImpl();
		    UserService userService = UserServiceFactory.getLocalUserService();
		    BookService bookService = new BookServiceImpl();
			Transaction trans = transService.getTransaction(Integer.parseInt(request.getParameter("id")));
			
			System.out.println(trans);
			request.setAttribute("trans", trans);
			request.setAttribute("users", userService.getAll());
			List <Book> books = new ArrayList<Book>();
			books = bookService.getAvailableBooks();
			books.add(trans.getBook());
			request.setAttribute("books", books);
			request.getRequestDispatcher("edittrans.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		BookService bookService = new BookServiceImpl();
        UserService userService = UserServiceFactory.getLocalUserService();
		TransactionService transService = new TransactionServiceImpl();
        Transaction trans = new Transaction(userService.getUser(Integer.parseInt(request.getParameter("user"))),
		                                    bookService.getBook(Integer.parseInt(request.getParameter("book"))),
		                                    LocalDate.parse(request.getParameter("dateOfBorrow"), format),
		                                    LocalDate.parse(request.getParameter("expectedDateOfReturn"), format)); 
		        trans.setId(Integer.parseInt(request.getParameter("id")));
		transService.updateTransaction(trans);
		
		response.sendRedirect("lentbooks");
		
	}

}
