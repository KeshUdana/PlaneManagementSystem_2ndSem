import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
/**
 * This public class w20515441_PlaneManagement  represents a plane management system , which users to buy, cancel, and search for tickets,
 * as well as view seating availability and print ticket information.
 */
public class w20515441_PlaneManagement {
    /**
     * Inner class made to handle common input operations throughout the program
     */
        public static class  Common_input {
            private static final Scanner scanner = new Scanner(System.in);
        }
            private static final Ticket[] tickets = new Ticket[52]; //array created to ho ld ticket
            private static int tick_count = 0;
            public static String[][] seating = {
                    {" O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O",},
                    {" O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O "},
                    {" O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O "},
                    {" O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O ", " O "},
            };
    /**
     * Below method evaluates the seat row letter input and returns the corresponding index.
     */
            public static int seat_evaluation() {
                System.out.print("Enter row letter please: ");
                String seat_letter = Common_input.scanner.next();
                int seat_corr_num = 0;
                if (seat_letter.equals("A") ||seat_letter.equals("B")||seat_letter.equals("C")||seat_letter.equals("D")) {
                    switch (seat_letter.toUpperCase()) {
                        case "A":
                            break;
                        case "B":
                            seat_corr_num++;
                            break;
                        case "C":
                            seat_corr_num += 2;
                            break;
                        case "D":
                            seat_corr_num += 3;
                            break;
                        default:
                            System.out.println("Seat not valid");
                    }
                }else{
                    return seat_evaluation();
                }return seat_corr_num;
            }
    /**
     * Prints information about all purchased tickets.
     * Also displays the total price of tickets sold.
     */
            public static void print_tickets_info() {
                int tot_price = 0;
                System.out.println("Printing ticket information for all tickets:\n");
                for (int i = 0; i < tick_count; i++) {
                    Ticket ticket = tickets[i];
                    Person person = ticket.getPerson();
                    System.out.println("Ticket #" + (i + 1) + ":");
                    System.out.println("Seat No: " + ticket.getRow()+ (ticket.getSeat()+1));
                    System.out.println("Seat price: $" + ticket.getPrice());
                    System.out.println("Passenger: " + person.getName() + " " + person.getSurname());
                    System.out.println("Email: " + person.getEmail());
                    System.out.println("--------------------------------");
                    tot_price = ticket.getPrice() + tot_price;

                }if (tot_price!=0){
                System.out.println("$ " + tot_price);
                }else{
                    System.out.println("No tickets have been purchased");
                }

            }
    /**
     * Searches for a ticket based on seat row and number
     * If the seat is reserved, it prints the ticket information.
     * Lets user know that the seat has not been bought yet, if not found
     * .Otherwise, it indicates that the seat is available for purchase.
     */
    public static void search_ticket() {
        int seat_corr_num = seat_evaluation();
        if (seat_corr_num == -1) return;
        System.out.print("Enter seat number please: ");
        int seat_number = Common_input.scanner.nextInt();
        seat_number--; // Adjust for zero-based indexing

        if (seating[seat_corr_num][seat_number].equals(" X ")) {
            // Seat is taken, find and print the corresponding ticket information
            for (int i = 0; i < tick_count; i++) {
                Ticket ticket = tickets[i];
                if (ticket.getRow().equals(String.valueOf((char) (seat_corr_num + 'A')))) {
                    Person person = ticket.getPerson();
                    System.out.println("Ticket Information:");
                    System.out.println("Seat No: " + ticket.getRow()+ (ticket.getSeat()+1));
                    System.out.println("Seat price: $" + ticket.getPrice());
                    System.out.println("Passenger: " + person.getName() + " " + person.getSurname());
                    System.out.println("Email: " + person.getEmail());
                    return; // Exit the method after printing ticket information
                }
            }
        } else {
            System.out.println("This seat is available for purchase");
        }
        /**
         * Allows a user to purchase a seat by inputting their details and selecting an available seat.
         */
    }  public static void buy_seat() {
        System.out.println("""
                Choose your seat from the available options:
                Rows A -14 seats\s
                Rows B-12 seats
                Rows C-12 seats
                Rows D-14 seats
                """);
        int seat_corr_num = seat_evaluation();
        if (seat_corr_num == -1) return;
        System.out.print("Enter seat number please: ");
        try {
            int seat_number = Common_input.scanner.nextInt();
            Common_input.scanner.nextLine();
            seat_number--;

            // Check if the seat is available
            boolean isSeatValid = false;
            if ((seat_corr_num == 0 || seat_corr_num == 3) && seat_number <= 15) {
                isSeatValid = true;
            } else if ((seat_corr_num == 1 || seat_corr_num == 2) && seat_number <= 12) {
                isSeatValid = true;
            } else {
                System.out.println("Seat number is out of range");
            }

            if (isSeatValid) {
                if (seating[seat_corr_num][seat_number].equals(" O ")) {
                    // Check if the seat is already purchased
                    boolean seatAlreadyBought = false;
                    for (int i = 0; i < tick_count; i++) {
                        Ticket ticket = tickets[i];
                        // Check if the seat and row match with an existing ticket
                        if (ticket.getSeat() == (seat_number) && ticket.getRow().equals(String.valueOf((char) (seat_corr_num + 'A')))) {
                            seatAlreadyBought = true;
                            break;
                        } else if (ticket.getSeat() == (seat_number) && ticket.getRow().equals(String.valueOf((char) (seat_corr_num + 'B')))) {
                            seatAlreadyBought = true;
                            break;
                        } else if (ticket.getSeat() == (seat_number) && ticket.getRow().equals(String.valueOf((char) (seat_corr_num + 'C')))) {
                            seatAlreadyBought = true;
                            break;
                        } else if (ticket.getSeat() == (seat_number) && ticket.getRow().equals(String.valueOf((char) (seat_corr_num + 'D')))) {
                            seatAlreadyBought = true;
                            break;
                        }
                    }

                    if (!seatAlreadyBought) {
                        // Seat is available and has not already been bought
                        // Update seating and create ticket
                        seating[seat_corr_num][seat_number] = " X ";

                        // Input for person class
                        String name = "";
                        while (name.isEmpty() || !name.matches("[a-zA-Z]+")) {
                            System.out.print("Enter your name: ");
                            name = Common_input.scanner.nextLine().trim();
                            if (!name.matches("[a-zA-Z]+")) {
                                System.out.println("Invalid name. Name should contain only alphabetic characters. Please try again.");
                            }
                        }

                        String surname = "";
                        while (surname.isEmpty() || !surname.matches("[a-zA-Z]+")) {
                            System.out.print("Enter your surname: ");
                            surname = Common_input.scanner.nextLine().trim();
                            if (!surname.matches("[a-zA-Z]+")) {
                                System.out.println("Invalid surname. Surname should contain only alphabetic characters. Please try again.");
                            }
                        }

                        String email = "";
                        while (email.isEmpty()) {
                            System.out.print("Enter your email: ");
                            email = Common_input.scanner.nextLine().trim();
                            if (email.isEmpty()) {
                                System.out.println("Email cannot be empty. Please try again.");
                            }
                        }
                        // Create a Person object using the constructor
                        Person person = new Person(name, surname, email);

                        // Adjusted Ticket object creation using seat_number instead of seat_letter
                        int price;
                        if (seat_number <= 5) {
                            price = 200;
                            System.out.println("Your ticket costs $" + price);
                        } else if (seat_number <= 9) {
                            price = 150;
                            System.out.println("Your ticket costs $" + price);
                        } else {
                            price = 180;
                            System.out.println("Your ticket costs $" + price);
                        }

                        // Create a Ticket object using the constructor
                        Ticket ticket = new Ticket(seat_number, String.valueOf((char) (seat_corr_num + 'A')), price, person);

                        // Store the ticket and increment the ticket count
                        tickets[tick_count++] = ticket;

                        // Print ticket information
                        System.out.println("Seat No: " + ticket.getRow() + (ticket.getSeat() + 1));
                        System.out.println("Seat price:$ " + ticket.getPrice());
                        System.out.println("Passenger: " + person.getName() + " " + person.getSurname());

                        // Call the save() method to save the ticket information into a file
                        ticket.save();
                    } else {
                        System.out.println("Seat has already been bought");
                    }
                } else {
                    System.out.println("Seat has already been purchased");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Entered seat number is out of range.Please try again");
        }
    }
    public static void cancel_seat() {
        System.out.println("""
                Choose your seat from the available options:
                Rows A -14 seats\s
                Rows B-12 seats
                Rows C-12 seats
                Rows D-14 seats
                """);
        int seat_corr_num = seat_evaluation();
        if (seat_corr_num == -1) return;
        System.out.print("");
        System.out.print("Enter seat number please: ");
        int seat_number = Common_input.scanner.nextInt();
        seat_number = seat_number - 1;
        try {
            if (seating[seat_corr_num][seat_number].equals(" X ")) {
                seating[seat_corr_num][seat_number] = " O ";

                // Get the file corresponding to the cancelled seat
                File file = new File("D:\\MyDEGREE\\SOFTWARE DEV\\SfDev2\\JavaCW\\w20515441_PlaneManagement\\" + (char) (seat_corr_num + 'A') + (seat_number + 1) + ".txt");

                // Check if the file exists and delete it
                if (file.exists()) {
                    if (file.delete()) {
                        System.out.println("Seat has been cancelled,corresponding file has been deleted.");
                    }}
            } else {
                System.out.println("You have entered the wrong seat");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Entered seat number out of bounds");
        }
    }

    public static void find_first_available() {
                for (int i = 0; i < seating.length; i++) {
                    for (int j = 0; j < seating[i].length; j++) {
                        if (seating[i][j].equals(" O ")) {
                            String row;
                            switch (i) {
                                case 0:
                                    row = "A";
                                    break;
                                case 1:
                                    row = "B";
                                    break;
                                case 2:
                                    row = "C";
                                    break;
                                case 3:
                                    row = "D";
                                    break;
                                default:
                                    System.out.println("Error: Invalid row index found.");
                                    return;
                            }
                            System.out.println("Seat " + row + (j + 1) + " is available.");
                            return;
                        }
                    }
                }
                System.out.println("No available seats found.");
            }

            //method to show seating plan
            public static void show_seating_plan(String[][] seating) {
                for (String[] strings : seating) {
                    for (String string : strings) {
                        System.out.print(string + " ");
                    }
                    System.out.println();
                }
            }

            // Main menu
            public static void main(String[] args) {
                int loop_breaker = 0; //variable initialized to break loop when user chooses 0.
                while (loop_breaker == 0) {
                    System.out.println( " ");
                    System.out.println("            Welcome to the Plane Management application           ");
                    System.out.println("**********************************************************");
                    System.out.println("*                        MAIN MENU                       *");
                    System.out.println("**********************************************************");
                    System.out.println("Please press the corresponding number to proceed.");
                    System.out.println("1. Buy a seat");
                    System.out.println("2. Cancel a seat");
                    System.out.println("3. Find first available seat");
                    System.out.println("4. Show seating plan ");
                    System.out.println("5. Print ticket information and total sales");
                    System.out.println("6. Search ticket");
                    System.out.println("0. Quit ");
                    System.out.println("**********************************************************");
                    try {
                        System.out.print("Please select an option:  ");
                        int option = Common_input.scanner.nextInt();
                        switch (option) {
                            case 1:
                                buy_seat();
                                break;
                            case 2:
                                cancel_seat();
                                break;
                            case 3:
                                find_first_available();
                                break;
                            case 4:
                                show_seating_plan(seating);
                                break;
                            case 5:
                                print_tickets_info();
                                break;
                            case 6:
                                search_ticket();
                                break;
                            case 0:
                                System.out.println("Thank you for using the Plane Management System.");
                                loop_breaker++;
                                break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid option.Press any key to try again");
                        Common_input.scanner.next();
                    }
                }
            }
    }