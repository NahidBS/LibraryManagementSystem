import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
    private String id;
    private String name;
    private List<Book> borrowedBooks;

    //constructor
    public Member(String id, String name){
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    //setter & getter
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }

    public void borrowBook(Book book){
        borrowedBooks.add(book);
    }
    public void returnBook(Book book){
        borrowedBooks.remove(book);
    }
    @Override
    public String toString(){
        return String.format("Member{ID:%s, Name:%s, Borrowed Books:%s", id, name, borrowedBooks.size());
    }
}