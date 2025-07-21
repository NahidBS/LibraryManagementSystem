import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private String transactionId;
    private Book book;
    private Member member;
    private LocalDate checkoutDate;
    private LocalDate returnDate;

    //constructor
    public Transaction(String transactionId, Book book, Member member){
        this.transactionId = transactionId;
        this.book = book;
        this.member = member;
        this.checkoutDate = LocalDate.now();
        this.returnDate = null;
    }

    //setter & getter
    public String getTransactionId(){
        return transactionId;
    }
    public Book getBook(){
        return book;
    }
    public Member getMember(){
        return member;
    }
    public LocalDate getCheckoutDate(){
        return checkoutDate;
    }
    public LocalDate getReturnDate(){
        return returnDate;
    }

    public void completeTransaction() {
        this.returnDate = LocalDate.now();
    }

    @Override
    public String toString(){
        return "Transaction Id: " + transactionId+
                "\nBook: " + book.getTitle() +
                "\nMember: " + member.getName() +
                "\nCheckout Date: " + checkoutDate +
                "\nReturn Date: " + (returnDate != null ? returnDate : "Not Regurned Yet");
    }
}
