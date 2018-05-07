package bookshopapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bookshopapp.bean.User;
import bookshopapp.common.DbConnection;

public class UserDao {
	
	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps =null;
	private ResultSet rs=null;
	private String sql;
	private User usered = null;
	//userµÇÂ¼
	public User Login(String userName ,String password) {
		
		
		sql = "select * from user where userName = ? and userPassword = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				usered = new User(
						rs.getInt("userId"),
						rs.getString("userName"),
						rs.getString("userPassword"),
						rs.getString("userImg"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return usered;
		
	}
	//user×¢²á
	public boolean Register(String user_name ,String user_password,String user_img) {
		
		sql = "insert into user(userName,userPassword,userImg) values(?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, user_password);
			ps.setString(3, user_img);
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;	
	}
}
