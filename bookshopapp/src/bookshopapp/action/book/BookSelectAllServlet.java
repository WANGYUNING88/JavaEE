package bookshopapp.action.book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bookshopapp.bean.Book;
import bookshopapp.dao.BookDao;

/**
 * Servlet implementation class BookSelectAllServlet
 */
@WebServlet("/BookSelectAllServlet")
public class BookSelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDao bookDao = new BookDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSelectAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		List<Book> bookList = new ArrayList<Book>();
	
		bookList = bookDao.selectAll();
		
		Gson gson = new Gson();
		String bookListStr = gson.toJson(bookList);	
		response.getWriter().append(bookListStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
