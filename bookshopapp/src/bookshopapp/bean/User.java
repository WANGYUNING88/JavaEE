package bookshopapp.bean;

public class User {
	private int userId;
	private String userName ;
	private String password;
	private String userImg;
	public User() {
		
	}
	public User(String user_name ,String user_password) {

			userName = user_name;
			password = user_password;
		  
		}
	public User(String user_name ,String user_password ,String user_img) {

		userName = user_name;
		password = user_password;
		userImg = user_img;
	  
	}
	public User(int id,String user_name ,String user_password,String user_img) {
		userId = id;
		userName = user_name;
		password = user_password;
		userImg = user_img;
	}
	
	
	
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
