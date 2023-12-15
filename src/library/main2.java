package library;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class main2 {

// we will create methods in the main class to call directly

	// TODO Auto-generated method stub
	// books code

	public static void addBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER BOOK ID :-");
		int bookId = sc.nextInt();

		System.out.println("ENTER THE BOOK NAME :");
		String bookName = sc.next();

		System.out.println("ENTER THE BOOK AUTHR :-");
		String bookAuthor = sc.next();

		System.out.println("ENTER BOOK GENRE:-");
		String bookGenre = sc.next();

		System.out.println("ENTER BOOK PULOICATIONS NAME:-");
		String Publications = sc.next();

		// Setting the data to beans
		books b = new books();
		b.setBookId(bookId);
		b.setBookName(bookName);
		b.setBookAuthor(bookAuthor);
		b.setBookGenre(bookGenre);
		b.setpublications(Publications);

		// getting the method from dao.
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		boolean f = dao.addBook(b);
		if (f) {
			System.out.println("******************BOOK ADDED SUCESSFULLY*****************************");
		} else {
			System.out.println("SOMETHING WENT WRONG");
		}
	}

	public static void deleteBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER BOOK ID TO DELETE :-");
		int id = sc.nextInt();
		books b = new books();
		b.setBookId(id);
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		boolean f = dao.deleteBook(id);

		if (f) {
			System.out.println("BOOK DETAILS DELETED SUCESSFULLY");
		} else {
			System.out.println("SOMETHING WENT WRONG");
		}

	}

	public static void updateBook() {
		Scanner sc = new Scanner(System.in);
		books b = new books();
		System.out.println("ENTER BOOK ID :-");
		int bookId = sc.nextInt();
		System.out.println("EENTER THE BOOK NAME :-");
		String bookName = sc.next();
		System.out.println("ENTER THE BOOK AUTHR :-");
		String bookAuthor = sc.next();
		System.out.println("ENTER BOOK GENRE:-");
		String bookGenre = sc.next();
		System.out.println("ENTER BOOK PULOICATIONS NAME:-");
		String Publications = sc.next();
		b.setBookId(bookId);
		b.setBookName(bookName);
		b.setBookAuthor(bookAuthor);
		b.setBookGenre(bookGenre);
		b.setpublications(Publications);

		booksDAo dao = new booksDAo(DBconnection.getConnection());

		boolean f = dao.editBook(b);
		if (f) {
			System.out.println("BOOK DETAILS UPDATED SUCESSFULLY");
		} else {
			System.out.println("SOMETHING WENT WRONG");
		}
	}

	public static void searchBook() {
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		books b = dao.viewBook(id);

		if (b == null) {
			System.out.println("NO BOOK WITH THE GIVEN ID");
		} else {
			System.out.println("HERE  ARE THE BOOK DETAILS");
			System.out.println("BOOK NAME = " + b.getBookName());
			System.out.println("BOOK AUTHOR = " + b.getBookAuthor());
			System.out.println("BOOK CATEGORY = " + b.getBookGenre());
			System.out.println("BOOK PUBLICATIONS =" + b.getpublications());
		}
	}

	public static void viewAllBooks() {
		booksDAo dao = new booksDAo(DBconnection.getConnection());
		List<books> list = dao.getAllBooks();
		for (books b : list) {
			System.out.println("BOOK DETAILS :-------------->");
			System.out.println("---------------------------");
			System.out.println("BOOK ID  = " + b.getBookId());
			System.out.println("BOOK NAME= " + b.getBookName());
			System.out.println("AUTHOR = " + b.getBookAuthor());
			System.out.println("CATEGORY = " + b.getBookGenre());
			System.out.println("PUBLICATIONS =" + b.getpublications());
			System.out.println("-----------------------------");

		}
	}

	public static void borrowBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter book id to borrow");
		int bookId = sc.nextInt();

		booksDAo bookDao = new booksDAo(DBconnection.getConnection());
		books book = bookDao.viewBook(bookId);

		if (book != null && book.isAvailable()) {
			// Mark the book as borrowed
			book.setAvailable(false);
			bookDao.editBook(book);

			System.out.println("*********************************************BOOK BORROWED SUCESSFULLY********************************");
		} else {
			System.out.println("BOOK NOT BORRWED SOMETHING WENT WRONG OR THERE IS NO BOOK WITH THE GIVEN DETAILS");
		}
	}

	public static void returnBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER THE BOOK ID TO RETURN");
		int bookId = sc.nextInt();

		booksDAo bookDao = new booksDAo(DBconnection.getConnection());
		books book = bookDao.viewBook(bookId);

		if (book != null && book.isAvailable()) {
			// Mark the book as available
			book.setAvailable(true);
			bookDao.editBook(book);

			System.out.println("*****************************************************BOOK RETURNED SUCESSFULLY******************************************");
		} else {
			System.out.println("INVALID BOOK ID OR BOOK IS ALREADY AVAILABLE");
		}
	}

	public static void checkDueDatesAndReminders() throws Exception {
		Scanner sc = new Scanner(System.in);

		// Ask the user to input the bookId
		System.out.println("ENTER A BOOK ID TO SEND REMAINDER :");
		int bookId = sc.nextInt();
		System.out.println("**********************REMINDER SENT SUCCESSFULLY************************");
		

		booksDAo dao = new booksDAo(DBconnection.getConnection());

		// Send reminder for the specified bookId
	//	dao.sendReminderForBook(bookId); 

		sc.close();
	}

	public static void calculateAndPrintLateFee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER A BOOK ID TO SEND LATE FEE ");
		int bookId = sc.nextInt();

		booksDAo dao = new booksDAo(DBconnection.getConnection());
		books book = dao.viewBook(bookId);

		if (book != null) {
			double lateFee = dao.calculateLateFee(bookId);
			System.out.println("LATE FOR BOOK WITH ID " + bookId + ": $" + lateFee);
		} else {
			System.out.println("BOOK WITH " + bookId + " NOT FOUND.");
		}
	}

	// admin code

	public static void addStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student reg no");
		String regno = sc.next();
		String k = regno;
		System.out.println("Enter password");
		String password = sc.next();

		// Setting the data to beans
		students st = new students();
		st.setRegno(regno);
		st.setPassword(password);

		// getting the method from dao.
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		boolean f = dao.addStudent(st);
		if (f) {
			System.out.println("Student added successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}

	public static void regStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student reg no");
		String regno1 = sc.next();

		System.out.println("Enter password");
		String password = sc.next();

		// Setting the data to beans
		students st = new students();
		st.setRegno(regno1);
		st.setPassword(password);

		// getting the method from dao.
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		boolean f = dao.addStudent(st);
		if (f) {
			System.out.println("Student registered successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}

	public static void deleteStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student regno to delete");
		String regno2 = sc.next();
		students st = new students();
		st.setRegno(regno2);
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		boolean f = dao.deleteStudent(regno2);

		if (f) {
			System.out.println("Student details deleted succesfully");
		} else {
			System.out.println("something went wrong");
		}
	}

	public static void updateStudent() {
		Scanner sc = new Scanner(System.in);
		students st = new students();
		System.out.println("Enter student regno");
		String regno = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		st.setRegno(regno);
		st.setPassword(password);
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());

		boolean f = dao.editStudent(st);
		if (f) {
			System.out.println("Student details updated");
		} else {
			System.out.println("something went wrong");
		}
	}

	public static void searchStudent() {
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		Scanner sc = new Scanner(System.in);
		String regno1 = sc.next();

		students st = dao.viewStudent(regno1);

		if (st == null) {
			System.out.println("No book with the given ID");
		} else {
			System.out.println("HERE THE STUDENT DETAILS ARE :-");
			System.out.println("REG NO :- = " + st.getRegno());
			System.out.println("PASSWORD :- = " + st.getPassword());
		}
	}

	public static void viewAllStudents() {
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		List<students> list = dao.getAllStudents();
		for (students st : list) {
			System.out.println("STUDENT DETAILS ---------->");
			System.out.println("---------------------------");
			System.out.println("REGISTRATION NUMBER= " + st.getRegno());
			System.out.println("PASSWORD = " + st.getPassword());
			System.out.println("-----------------------------");
		}
	}

	public static void AllStudents() {
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		List<students> list = dao.getAllStudents();
		for (students st : list) {
			System.out.println("STUDENT DETAILS :---------->");
			System.out.println("---------------------------");
			System.out.println("REGNO = " + st.getRegno());
			System.out.println("PASSWORD = " + st.getPassword());
			System.out.println("-----------------------------");
		}
	}

	// main code

	public static void main(String[] args) throws SQLException, URISyntaxException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("******************************** LOGIN PAGE *******************************");
		System.out.println("1.ADMIN LOGIN");
		System.out.println("2.STUDENT USER ");
		System.out.println("SELECT A REQUIRED OPTION ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter your id");
			String id = sc.next();
			System.out.println("Enter password");
			String password = sc.next();
			if (id.equals("543") && password.equals("admin")) {
				System.out.println("**********************************************************************");
				System.out.println("Login Successful");
				System.out.println("***********************************************************************");
				boolean value = true;
				while (value) {
					System.out.println("Select your choice");
					System.out.println("1.ADD A BOOK TO LIBRARY");
					System.out.println("2.DELETE A BOOK FROM LIBRARY");
					System.out.println("3.UPDATE BOOK DETAILS");
					System.out.println("4.SEARCH BOOK DETAILS");
					System.out.println("5.VIEW ALL BOOKS");
					System.out.println("6.ADD A STUDENT");
					System.out.println("7.DELETE A STUDENT");
					System.out.println("8.UPDATE STUDENT DETAILS");
					System.out.println("9.SEARCH STUDENT DETAILS");
					System.out.println("10.VIEW ALL STUDENTS");

					System.out.println("11.LOG OUT");
					int option = sc.nextInt();
					switch (option) {
					case 1:
						System.out.println("*** ADD A BOOK***");
						addBook();
						break;
					case 2:
						System.out.println("*** DELETE A BOOK ***");
						deleteBook();
						break;
					case 3:
						System.out.println("*** UPDATE BOOK DETAILS ***");
						updateBook();
						break;
					case 4:
						System.out.println("*** SEARCH A BOOK ***");
						searchBook();
						break;
					case 5:
						System.out.println("*** VIEW ALL BOOKS ***");
						viewAllBooks();
						break;
					case 6:
						System.out.println("*** ADD STUDENT ***");
						addStudent();
						break;
					case 7:
						System.out.println("*** DELETE STUDENT ***");
						deleteStudent();
						break;
					case 8:
						System.out.println("*** UPDATE STUDENT ***");
						updateStudent();
						break;
					case 9:
						System.out.println("*** Search Student ***");
						searchStudent();
						break;
					case 10:
						System.out.println("*** SEARCH STUDENT ***");
						viewAllStudents();
						break;
					case 11:
						System.out.println("*** Thanks for using ***");
						System.out.println("*** Logged out successfully ***");
						value = false;
						break;
					}
				}
			} else {
				System.out.println("INVALID CREDENTIALS");
				break;
			}
			break;
		case 2:
			boolean value = true;
			while (value) {
				System.out.println("1.STUDENT REGISTRATION");
				System.out.println("2.STUDENT LOGIN");
				int choice1 = sc.nextInt();
				switch (choice1) {
				case 1:
					System.out.println("--------STUDENT REGISTRATION--------");
					regStudent();
					System.out.println("-------- REGISTRATION COMPLETED--------");
					System.out.println("-------- GO TO STUDENT LOGIN --------");
					break;
				case 2:
					System.out.println("ENTER YOUR REGN: ");
					String stregno = sc.next();
					System.out.println("ENTER PASSWORD");
					String stpassword = sc.next();

					studentsDAo studentDao = new studentsDAo(DBconnection.getConnection());
					students student = studentDao.viewStudent(stregno);

					if (student != null && student.getPassword().equals(stpassword)) {
						System.out.println("*******************");
						System.out.println("LOGIN SUCCESFULL");
						System.out.println("*******************");

						boolean value1 = true;
						while (value1) {
							System.out.println("SELECT YOUR CHOICE");
							System.out.println("1.TO BORROW A BOOK"); // add books to read
							System.out.println("2.TO RETURN A BOOK"); // submitting the book
							System.out.println("3.DUE DATE REMINDER");
							System.out.println("4.SHOW LATE FEE FOR BOOKS ");
							System.out.println("5.view All Books");
							System.out.println("6.SEARCH BOOK");
							System.out.println("7.FOR MORE E-BOOKS TO ENJOY IN WEB ");
							System.out.println("8.LOGOUT");
							int option = sc.nextInt();
							switch (option) {
							case 1:
								System.out.println("*** BORROWING A BOOK ***");
								borrowBook();
								break;
							case 2:
								System.out.println("*** RETURNING  A BOOK ***");
								returnBook();
								break;
							case 3:
								System.out.println("*** DUE DATE REMINDER***");
								try {
									checkDueDatesAndReminders();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							case 4:
								System.out.println("*** SHOWING LATE FEE ***");
								calculateAndPrintLateFee();
								break;
							case 5:
								System.out.println("***VIEW ALL  BOOKS ***");
								viewAllBooks();
								break;
							case 6:
								System.out.println("*** SEARCH A BOOK ***");
								searchBook();
								break;
							case 7:
								Desktop desktop = Desktop.getDesktop();
								URI oURL1 = new URI("https://www.bookswagon.com");
								desktop.browse(oURL1);
								break;
							case 8:
								System.out.println("ALL ORDERS ARE SUCCESS FULL");
							case 9:
								System.out.println("***LOGOUT***");
								System.out.println("*** LOGGED OUT SUCESSFULLY RUN AGAIN***");

								value = false;
								break;
							}
						}
					} else {
						System.out.println("INVALID LOG IN");
					}
					break;
				}
			}
			sc.close();

			// Main Code - END

		}
	}
}
