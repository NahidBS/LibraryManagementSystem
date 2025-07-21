import java.util.ArrayList;
import java.util.List;
import java.io.*;

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
        loadData();
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
        members.add(member);
        saveMembers();
    }
    public Member findMemberById(String id){
        return members.stream().filter(member -> member.getId().equals(id)).findFirst().orElse(null);
    }
    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
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

    public boolean returnBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book == null || member == null || book.isAvailable()) {
            return false;
        }

        book.setAvailable(true);
        member.returnBook(book);

        transactions.stream()
                .filter(t -> t.getBook().getId().equals(bookId) &&
                        t.getMember().getId().equals(memberId) &&
                        t.getReturnDate() == null)
                .findFirst()
                .ifPresent(Transaction::completeTransaction);

        saveBooks();
        saveMembers();
        saveTransactions();

        return true;
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    // File handling methods
    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    private void saveMembers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
        } catch (IOException e) {
            System.err.println("Error saving members: " + e.getMessage());
        }
    }

    private void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTIONS_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.err.println("Error saving transactions: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing book data found. Starting with empty library.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMBERS_FILE))) {
            members = (List<Member>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing member data found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading members: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTIONS_FILE))) {
            transactions = (List<Transaction>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing transaction data found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }
    }
}
