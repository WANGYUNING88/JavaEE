package bookshopapp.action.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bookshopapp.bean.Book;
import bookshopapp.dao.BookDao;

/**
 * Servlet implementation class BookSelectById
 */
@WebServlet("/BookSelectById")
public class BookSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSelectById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BookDao bookDao = new BookDao();
		Book book = bookDao.selcetBookById(bookId);
		Gson gson = new Gson();
		String bookStr = gson.toJson(book);
		System.out.println(bookStr);
		response.getWriter().append(bookStr);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
