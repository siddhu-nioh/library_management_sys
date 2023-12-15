package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class books {
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private String bookGenre;
	private String Publications;
    private double lateFee;
    private LocalDate dueDate;
    private boolean reminderSent;
	
	
	public int getBookId() {
		return bookId;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	
	public String getBookGenre() {
		return bookGenre;
	}
	public String getpublications() {
		return Publications;
	}
	
	
	/* 
	 ==============================
	 	Setters (To Set the Data) 
	 ==============================
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}
	public void setpublications(String Publications ) {
		this.Publications= Publications;
	
}
	private boolean available;

    // Constructor
    public books() {
        // ... (existing code)
        this.available = true; // Initialize the book as available when created
    }

    // Getter and Setter for 'available'
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public long calculateDaysOverdue() {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(dueDate, currentDate);
    }

    public double calculateLateFee()throws Exception {
        if (dueDate != null && LocalDate.now().isAfter(dueDate) && !reminderSent) {
            long daysLate = LocalDate.now().until(dueDate).getDays();
            return daysLate * 2.5; // Adjust the late fee calculation as needed
        }
        return 0.0;
    }
    public boolean sendReminder()throws Exception {
        if (LocalDate.now().isAfter(dueDate) && !reminderSent) {
            System.out.println("Reminder: Please return the book '" + bookName + "'.");
            reminderSent = true;
            return true; // Reminder sent successfully
        }else {
        	System.out.println("the book is not given or there is time");
        }
        return false; // No reminder sent
    }
    
public boolean setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate; // Update the due date in the books object
    return true;

	
    }

public double getLateFee() {
	return lateFee;
}

public void setLateFee(double lateFee) {
	this.lateFee = lateFee;
}
}
	