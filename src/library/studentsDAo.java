package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class studentsDAo {
	public studentsDAo(Connection conn) {
		super();
		this.conn = conn;
	}
	

	private Connection conn;
	
	
	public boolean addStudent(students st) {
		boolean f = false;
		try {
			String query = "insert into students(regno,password) values(?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, st.getRegno());
			ps.setString(2, st.getPassword());
			
			int i = ps.executeUpdate();
			if(i==1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public boolean regStudent(students st) {
		boolean f = false;
		try {
			String query = "insert into students(regno,password) values(?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, st.getRegno());
			ps.setString(2, st.getPassword());
			
			int i = ps.executeUpdate();
			if(i==1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	public boolean editStudent(students st) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement("update students set password=? where regno=?");
			ps.setString(1, st.getPassword());
			ps.setString(2, st.getRegno());
			int i = ps.executeUpdate();
			if(i==1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public boolean deleteStudent(String regno) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement("delete from students where regno=?");
			ps.setString(1,regno);
			int i = ps.executeUpdate();
			if(i>=1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public students viewStudent(String regno) {
		students st = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select *from students where regno=?");
			ps.setString(1, regno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				st = new students();
				st.setRegno(rs.getString(2));
				st.setPassword(rs.getString(3));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return st;
	}
	
	@SuppressWarnings("rawtypes")
	public List<students> getAllStudents(){
		List<students> list = new ArrayList();
		students st = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("select *from students");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				st = new students();
				st.setRegno(rs.getString(2));
				st.setPassword(rs.getString(3));
				list.add(st);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public static void addStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student reg no");
		String regno = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		//Setting the data to beans
		students st = new students();
		st.setRegno(regno);
		st.setPassword(password);
		
		//getting the method from dao.
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		boolean f = dao.addStudent(st);
		if(f) {
			System.out.println("Student added successfully");
		}else {
			System.out.println("Something went wrong");
		}
	}
	public static void regStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student reg no");
		String regno = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		//Setting the data to beans
		students st = new students();
		st.setRegno(regno);
		st.setPassword(password);
		
		//getting the method from dao.
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		boolean f = dao.addStudent(st);
		if(f) {
			System.out.println("Student registered successfully");
		}else {
			System.out.println("Something went wrong");
		}
	}
	
	public static void deleteStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student regno to delete");
		String regno = sc.next();
		students st = new students();
		st.setRegno(regno);
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		boolean f = dao.deleteStudent(regno);
		
		if(f) {
			System.out.println("Student details deleted succesfully");
		}else {
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
		if(f) {
			System.out.println("Student details updated");
		}else {
			System.out.println("something went wrong");
		}
	}
	
	public static void searchStudent() {
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		Scanner sc = new Scanner(System.in);
		String regno = sc.next();
		students st = dao.viewStudent(regno);
		
		if(st==null) {
			System.out.println("No book with the given ID");
		}
		else {
		System.out.println("Here are the student details");
		System.out.println("Regno = "+st.getRegno());
		System.out.println("Password = "+st.getPassword());
		}
	}
	
	public static void viewAllStudents() {
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		List<students> list = dao.getAllStudents();
		for(students st:list) {
			System.out.println("Student Details ---------->");
			System.out.println("---------------------------");
			System.out.println("regno = "+st.getRegno());
			System.out.println("password = "+st.getPassword());
			System.out.println("-----------------------------");
		}
	}
	public static void  AllStudents() {
		studentsDAo dao = new studentsDAo(DBconnection.getConnection());
		List<students> list = dao.getAllStudents();
		for(students st:list) {
			System.out.println("Student Details ---------->");
			System.out.println("---------------------------");
			System.out.println("regno = "+st.getRegno());
			System.out.println("password = "+st.getPassword());
			System.out.println("-----------------------------");
		}
	}
	
}
