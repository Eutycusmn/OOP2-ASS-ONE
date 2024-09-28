import java.time.LocalDate;

/**
 * Main class to demonstrate and test the Car Rental System.
 */
public class Main {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        // Add cars directly to the agency
        agency.AddCar(new Car("KDA123A", "Nissan", "Sunny"));
        agency.AddCar(new Car("KBB456B", "Mazda", "Demio"));
        agency.AddCar(new Car("KCC789C", "Subaru", "Forester"));

        // Add customers directly to the agency
        agency.AddCustomer(new Customer("C001", "Peter Otieno", "123 Nairobi St", "0712-345678"));
        agency.AddCustomer(new Customer("C002", "Mary Achieng", "456 Mombasa Rd", "0722-567890"));
        agency.AddCustomer(new Customer("C003", "James Njoroge", "789 Kisumu Ave", "0733-789012"));

        // List all customers
        System.out.println("=== Customer List ===");
        agency.ListCustomers();
        System.out.println();

        // List available cars
        System.out.println("=== Available Cars ===");
        agency.ListAvailableCars();
        System.out.println();

        // Rent a car to the first customer
        System.out.println(">>> Renting a car to Peter Otieno <<<");
        agency.RentCar(
            agency.GetCustomers().get(0), // Peter Otieno
            agency.GetCars().get(0), // Nissan Sunny
            LocalDate.now()
        );
        System.out.println();

        // List available cars after renting one
        System.out.println("=== Available Cars After Renting One ===");
        agency.ListAvailableCars();
        System.out.println();

        // Rent another car to the second customer
        System.out.println(">>> Renting a car to Mary Achieng <<<");
        agency.RentCar(
            agency.GetCustomers().get(1), // Mary Achieng
            agency.GetCars().get(1), // Mazda Demio
            LocalDate.now()
        );
        System.out.println();

        // List available cars after renting another one
        System.out.println("=== Available Cars After Renting Another One ===");
        agency.ListAvailableCars();
        System.out.println();

        // Return the first car rented by the first customer
        System.out.println(">>> Returning the car rented by Peter Otieno <<<");
        agency.ReturnCar(
            agency.GetCars().get(0), // Nissan Sunny
            LocalDate.now().plusDays(3)
        );
        System.out.println();

        // List available cars after returning one
        System.out.println("=== Available Cars After Returning One ===");
        agency.ListAvailableCars();
        System.out.println();

        // List all rentals
        System.out.println("=== All Rentals ===");
        agency.ListRentals();
        System.out.println();

        // Attempt to rent an unavailable car
        System.out.println(">>> Attempting to rent an unavailable car <<<");
        agency.RentCar(
            agency.GetCustomers().get(2), // James Njoroge
            agency.GetCars().get(1), // Mazda Demio (already rented)
            LocalDate.now()
        );
        System.out.println();

        // Rent the returned car to the third customer
        System.out.println(">>> Renting the returned car to James Njoroge <<<");
        agency.RentCar(
            agency.GetCustomers().get(2), // James Njoroge
            agency.GetCars().get(0), // Nissan Sunny (returned)
            LocalDate.now()
        );
        System.out.println();

        // List available cars after renting the returned car
        System.out.println("=== Available Cars After Renting the Returned Car ===");
        agency.ListAvailableCars();
        System.out.println();

        // List all rentals again
        System.out.println("=== All Rentals After Additional Transactions ===");
        agency.ListRentals();
    }
}
