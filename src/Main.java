import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library libray = new Library();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\nLibrary Management System: ");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Checkout Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Book");
            System.out.println("6. View All Member");
            System.out.println("7. View All Transactions");
            System.out.println("8. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    addBook(Library, scanner);
                    break;

                case 2:
                    addMember(Library, scanner);
                    break;
                case 3:
                    checkoutBook(library, scanner);
                    break;
                case 4:
                    returnBook(library, scanner);
                    break;
                case 5:
                    viewAllBooks(library);
                    break;
                case 6:
                    viewAllMembers(library);
                    break;
                case 7:
                    viewAllTransactions(library);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    
    
    private static void addBook(Library library, Scanner scanner){
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        Book book = new Book(id, title, author);
        library.addBook(book);
        System.out.println("Book added successfully!");

    }

    private static void addMember(Library library, Scanner scanner) {
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();

        Member member = new Member(id, name);
        library.addMember(member);
        System.out.println("Member added successfully!");
    }

}
