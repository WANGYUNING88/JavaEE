package lovepets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lovepets.common.DbConnection;

import lovepets.bean.User;

public class UserDao {
	
	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps =null;
	private ResultSet rs=null;
	private String sql;
	private User usered = null;
	//userµÇÂ¼
	public User Login(String userName ,String password) {
		
		
		sql = "select * from user where user_name = ? and user_password = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				usered = new User(
						rs.getInt("user_id"),
						rs.getString("user_name"),
						rs.getString("user_password"),
						rs.getString("user_tel"),
						rs.getString("user_email"));
				
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
	public boolean Register(String user_name ,String user_password,String user_tel,String user_email) {
		
		sql = "insert into user(user_name,user_password,user_tel,user_email) values(?,?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, user_password);
			ps.setString(3, user_tel);
			ps.setString(4, user_email);
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
