package bookshopapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshopapp.bean.Book;

import bookshopapp.common.DbConnection;

public class BookDao {
 
	//查询全部
	public List<Book> selectAll(){
		Connection connection = DbConnection.getConnection();
		 PreparedStatement ps =null;
		ResultSet rs =null ;
		 String sql;
		
		List<Book> bookList = new ArrayList<Book>();
		try {
			if(connection.isClosed()) {
				connection = DbConnection.getConnection();
			}
			
			sql = "select * from book";
			ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			Book book = null;
			while(rs.next()){
				book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setBookImg(rs.getString("bookImg"));
				book.setBookPrice(rs.getDouble("bookPrice"));
				System.out.println(book);
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				connection.close();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
			
		}
		
		return bookList;
		
	}
	//根据id
	public Book selcetBookById(int bookId) {
		Connection connection = DbConnection.getConnection();
		 PreparedStatement ps =null;
		ResultSet rs = null ;
		 String sql;
		
		Book book = new Book();
		sql =  "select * from book where bookId = ?";
		try {
			if(connection.isClosed()) {
				connection = DbConnection.getConnection();
			}
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			while(rs.next()){
				book.setBookId(rs.getInt("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setBookImg(rs.getString("bookImg"));
				book.setBookPrice(rs.getDouble("bookPrice"));
				book.setBookDetail(rs.getString("bookDetail"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			
		}
		return book;
	}
		
}
