import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;
    private static final String BOOKS_FILE = "books.dat";
    private static final String MEMBERS_FILE ="members.dat";
    private static final String TRANSACTIONS_FILE = "transactions.dat";

    public Library(){
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    //Book operations:
    public void addBook(Book book){
        books.add(book);
        saveBooks();
    }
    public Book findBookById(String id){
        return books.stream().filter(book ->book.getId().equals(id)).findFirst().orElse(null);
    }
    public List<Book> getAllBooks(){
        return new ArrayList<>(books);
    }

    //Member operations
    public void addMember(Member member){
        member.add(member);
        saveMembers();
    }
    public Member findMemberById(String id){
        return members.stream().filter(member -> member.getId().equals(id)).findFirst().orElse(null);
    }

    //Transaction operation:
    public boolean checkoutBook(String bookId, String memberId){
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        if(book == null || member ==null || !book.isAvailable()){
            return false;
        }
        book.setAvailable(false);
        member.borrowBook(book);

        String transactionId = "TXN_" +System.currentTimeMillis();
        Transaction transaction = new Transaction(transactionId, book, member);
        transactions.add(transaction);
        saveBooks();
        saveMembers();
        saveTransactions();

        return true;


    }

}
