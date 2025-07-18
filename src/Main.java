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
            }
        }
    }
}
