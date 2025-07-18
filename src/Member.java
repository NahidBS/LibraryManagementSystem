import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract class Member implements Serializable {
    public static final long serialVersisonUID =1L;
    public static int nextId = 1;

    protected final int memberId;
    protected String name;
    protected String email;
    protected List<Integer> borrowedBooks;

    //constructor
    public Member(String name, String email){
        this.memberId = nextId++;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    //Getters
    public int getMemberId(){
        return memberId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public List<Integer> getBorrowedBooks(){
        return new ArrayList<>(borrowedBooks);
    }

    //abstract method - must be implemented in subclasses
    public abstract int getMaxBooksAllowed();

    //common functionality
    public boolean canBorrowMoreBooks(){
        return borrowedBooks.size() < getMaxBooksAllowed();
    }
    public void borrowBook(int bookId){
        if(!canBorrowMoreBooks()){
            throw new IllegalStateException("Member has reached maximum book limit");
        }
        borrowedBooks.add(bookId);
    }

    public void returnBook(int bookId){
        borrowedBooks.remove(Integer.valueOf(BookId))
    }


}
