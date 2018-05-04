package bookshopapp.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bookshopapp.bean.User;
import bookshopapp.dao.UserDao;
import bookshopapp.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();;
    /**
     * Default constructor. 
     */
    public UserRegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("接收到客户端的请求");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		InputStream is = request.getInputStream();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is, "utf-8"));
		
		StringBuffer stringBuffer = new StringBuffer();
		String str = null;
		while((str = reader.readLine()) != null){
			stringBuffer.append(str);
		}
		
		String userStr = stringBuffer.toString();
		if(userStr!=null) {
			System.out.println(userStr);
		}
		Gson gson = new Gson();
		User user = gson.fromJson(userStr, User.class);
		boolean result = false;
		UserDao userDao = new UserDao();
		result = userDao.Register(user.getUserName(),user.getPassword());
		System.out.println(result);
	
		if(result == true) {
			response.getWriter().append("注册成功");
		}else {
			response.getWriter().append("注册失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
