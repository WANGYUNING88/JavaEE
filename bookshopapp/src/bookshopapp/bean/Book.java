package bookshopapp.bean;

public class Book {
	private int bookId;
	private String bookName ;
	private String bookImg;
	private double bookPrice;
	private String bookDetail;
	
	public Book() {
		
	}
	public Book(String name,String img,double price ,String detail) {
			bookName = name;
			bookImg = img;
			bookPrice = price;
			bookDetail = detail;
		}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookImg() {
		return bookImg;
	}
	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookDetail() {
		return bookDetail;
	}
	public void setBookDetail(String bookDetail) {
		this.bookDetail = bookDetail;
	}
	
	
	
}
