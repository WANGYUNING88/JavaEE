package net.onest.okhttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetBookList
 */
@WebServlet("/GetBookList")
public class GetBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		Book book = new Book();
		book.setBookName("Java编程思想");
		book.setBookPrice("80.0");
		book.setBookDetails("深入理解Java虚拟机");
		List<String> images = new ArrayList<>();
		images.add("1.jpg");
		images.add("2.jpg");
		images.add("3.jpg");
		book.setBookImages(images);
		Book book1 = new Book();
		book1.setBookName("Android编程思想");
		book1.setBookPrice("80.0");
		book1.setBookDetails("Android高级程序员之路");
		List<String> images1 = new ArrayList<>();
		images1.add("4.jpg");
		images1.add("5.jpg");
		images1.add("6.jpg");
		book1.setBookImages(images1);
		List<Book> books = new ArrayList<>();
		books.add(book);
		books.add(book1);
		
		Gson gson = new Gson();
		String bookListStr = gson.toJson(books);
		
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
