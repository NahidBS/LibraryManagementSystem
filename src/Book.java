import java.io.Serializable;
import java.time.LocalDate;

public class Book  implements Serializable, Comparable<Book> {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;

    private final int bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private LocalDate borrowDate;
    private int memberId;

    //constructor
    public Book(String title, String author, String isbn){
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.bookId = nextId++;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    //Getter & Setter (Encapsulation)
    public int getBookId(){
        return bookId;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor(){
        return author;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
    public String getIsbn(){
        return isbn;
        
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void setBorrowDate(LocalDate borrowDate)
            {
        this.borrowDate = borrowDate;
    }
    public LocalDate getBorrowDate(){
        return borrowDate;
    }

    public int getMemberId(){
        return memberId;
    }

    //BL method -- business logic
    //for borrowing book method
    public void borrowBook(int memberId){
        if(!isAvailable){
            throw new IllegalStateException("Book is already borrowed");
        }
        this.isAvailable = false; // after borrowing book no book is available
        this.borrowDate = LocalDate.now();
        this.memberId = memberId;
    }
    //return book method
    public void returnBook(){
        this.isAvailable = true;
        this.borrowDate = null;
        this.memberId = 0;
    }

    //comparable implementation for sorting
    @Override
    public int compareTo(Book other){
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString(){
        return String.format("Book{id=%d, title='%s', author='%s', isbn='%s', available='%s'}",bookId, title, author, isbn, isAvailable);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass(){
            return false;
        }
        Book book = (Book) obj;
        return bookId == book.bookId;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(bookId);
    }
}
