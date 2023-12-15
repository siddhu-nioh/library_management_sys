package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class booksDAo {
	public booksDAo(Connection conn) {
		super();
		this.conn = conn;
	}
	
	private Connection conn;
	
	public boolean addBook(books b) {
		boolean f = false;
		try {
			String query = "insert into books(bookId,bookName,bookAuthor,bookGenre,Publications) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, b.getBookId());
			ps.setString(2, b.getBookName());
			ps.setString(3, b.getBookAuthor());
			ps.setString(4, b.getBookGenre());
			ps.setString(5, b.getpublications());
			int i = ps.executeUpdate();
			if(i==1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	public boolean editBook(books b) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement("update books set bookName=?,bookAuthor=?,bookGenre=?,Publications=? where bookId=?");
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getBookAuthor());
			ps.setString(3, b.getBookGenre());
			
			ps.setString(4, b.getpublications());
			ps.setInt(5, b.getBookId());
			
			int i = ps.executeUpdate();
			if(i==1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public boolean deleteBook(int bookId) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement("delete from books where bookId=?");
			ps.setInt(1, bookId);
			int i = ps.executeUpdate();
			if(i>=1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public books viewBook(int bookId) {
		books b = new books();
		try {
			PreparedStatement ps = conn.prepareStatement("select *from books where bookId=?");
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setBookAuthor(rs.getString(3));
				b.setBookGenre(rs.getString(4));
				b.setpublications(rs.getString(5));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	public books viewbook(books b, int bookId) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("select *from books where bookId=?");
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				b= new books();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setBookAuthor(rs.getString(3));
				b.setBookGenre(rs.getString(4));
				b.setpublications(rs.getString(5));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public List<books> getAllBooks(){
		@SuppressWarnings("rawtypes")
		List<books> list = new ArrayList();
		books b = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("select *from books");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				b = new books();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setBookAuthor(rs.getString(3));
				b.setBookGenre(rs.getString(4));
				b.setpublications(rs.getString(5));
				list.add(b);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public static void addBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter book id");
		int bookId = sc.nextInt();
		
		System.out.println("Enter book name");
		String bookName = sc.next();
		
		System.out.println("Enter book Author");
		String bookAuthor = sc.next();
		
		System.out.println("Enter book Genre");
		String bookGenre = sc.next();
		
		System.out.println("Enter book publications ");
		String Publications = sc.next();
		
		//Setting the data to beans
		books b = new books();
		b.setBookId(bookId);
		b.setBookName(bookName);
		b.setBookAuthor(bookAuthor);
		b.setBookGenre(bookGenre);
		b.setpublications(Publications);
		
		//getting the method from dao.
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		boolean f = dao.addBook(b);
		if(f) {
			System.out.println("Book added successfully");
		}else {
			System.out.println("Something went wrong");
		}
	}
	
	public static void deleteBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter book id to delete");
		int id = sc.nextInt();
		books b = new books();
		b.setBookId(id);
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		boolean f = dao.deleteBook(id);
		
		if(f) {
			System.out.println("Book details deleted succesfully");
		}else {
			System.out.println("something went wrong");
		}
		
	}
	
	public static void updateBook() {
		Scanner sc = new Scanner(System.in);
		books b = new books();
		System.out.println("Enter book id");
		int bookId = sc.nextInt();
		System.out.println("Enter book name");
		String bookName = sc.next();
		System.out.println("Enter book Author");
		String bookAuthor = sc.next();
		System.out.println("Enter book category");
		String bookCategory = sc.next();
		System.out.println("Enter book publications ");
		String Publications = sc.next();
		b.setBookId(bookId);
		b.setBookName(bookName);
		b.setBookAuthor(bookAuthor);
		b.setBookGenre(bookCategory);
		b.setpublications(Publications);
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		
		boolean f = dao.editBook(b);
		if(f) {
			System.out.println("book details updated");
		}else {
			System.out.println("something went wrong");
		}
	}
	
	public static void searchBook() {
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		books b = dao.viewBook(id);
		
		if(b==null) {
			System.out.println("No book with the given ID");
		}
		else {
		System.out.println("Here are the book details");
		System.out.println("Book Name = "+b.getBookName());
		System.out.println("Book Author = "+b.getBookAuthor());
		System.out.println("Book Category = "+b.getBookGenre());
		System.out.println("book publications ="+b.getpublications());
		
		}
	}
	
	public static void viewAllBooks() {
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		List<books> list = dao.getAllBooks();
		for(books b:list) {
			System.out.println("Books Details------------->");
			System.out.println("---------------------------");
			System.out.println("Id = "+b.getBookId());
			System.out.println("Book Name = "+b.getBookName());
			System.out.println("Author = "+b.getBookAuthor());
			System.out.println("Category = "+b.getBookGenre());
			System.out.println("Publications ="+b.getpublications());
			System.out.println("-----------------------------");
		}
	}
	

	public boolean borrowBook(int bookId, String studentRegNo) {
        try {
            String query = "UPDATE books SET available = false, studentRegNo = ? WHERE bookId = ? AND available = true";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, studentRegNo);
                pst.setInt(2, bookId);

                int rowsAffected = pst.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnBook(int bookId) {
        try {
            String query = "UPDATE books SET available = true, studentRegNo = null WHERE bookId = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, bookId);

                int rowsAffected = pst.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLateFee(int bookId, double lateFee) {
        try {
            String query = "UPDATE books SET lateFee = ? WHERE bookId = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setDouble(1, lateFee);
                pst.setInt(2, bookId);

                int rowsAffected = pst.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean setDueDate(int bookId, LocalDate dueDate) {
        try {
            String query = "UPDATE books SET dueDate = ? WHERE bookId = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setObject(1, dueDate);
                pst.setInt(2, bookId);

                int rowsAffected = pst.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public double calculateLateFee(int bookId) {
        try {
            String query = "SELECT dueDate, reminderSent FROM books WHERE bookId = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, bookId);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        LocalDate dueDate = rs.getObject("dueDate", LocalDate.class);
                        boolean reminderSent = rs.getBoolean("reminderSent");

                        if (dueDate != null && !reminderSent && LocalDate.now().isAfter(dueDate)) {
                            long daysLate = LocalDate.now().until(dueDate).getDays();
                            return daysLate * 2.5; // Adjust the late fee calculation as needed
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public void sendReminderForBook(int bookId) throws Exception {
        books book = viewBook(bookId);
        if (book != null) {
            book.sendReminder();
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }

    @SuppressWarnings("unused")
	private books searchBook(int bookId) {
    	booksDAo dao = new booksDAo(DBconnection.getConnection());
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		books b = dao.viewBook(id);
		
		if(b==null) {
			System.out.println("No book with the given ID");
		}
		else {
		System.out.println("Here are the book details");
		System.out.println("Book Name = "+b.getBookName());
		System.out.println("Book Author = "+b.getBookAuthor());
		System.out.println("Book Category = "+b.getBookGenre());
		System.out.println("book publications ="+b.getpublications());
		
		}
		// TODO Auto-generated method stub
		return null;
	}


	public void checkDueDatesAndReminders() throws Exception {
        try {
            String query = "SELECT bookId, dueDate FROM books WHERE available = false AND dueDate IS NOT NULL AND reminderSent = false";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        int bookId = rs.getInt("bookId");
                        LocalDate dueDate = rs.getObject("dueDate", LocalDate.class);

                        books book = new books();
                        book.setBookId(bookId);
                        book.setDueDate(dueDate);
                        book.sendReminder();
                   
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }}}
    

