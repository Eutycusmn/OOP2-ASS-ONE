import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a car in the rental system.
 */
class Car {
    private String registration_number; // The registration number of the car
    private String make; // The make of the car (e.g., Toyota, Honda)
    private String model; // The model of the car (e.g., Corolla, Civic)
    private boolean is_available; // Availability status of the car

    /**
     * Constructs a Car object with the specified details.
     *
     * @param registration_number the registration number of the car
     * @param make the make of the car
     * @param model the model of the car
     */
    public Car(String registration_number, String make, String model) {
        this.registration_number = registration_number;
        this.make = make;
        this.model = model;
        this.is_available = true; // Cars are available by default when created
    }

    // Getter for registration number
    public String GetRegistrationNumber() {
        return registration_number;
    }

    // Getter for make
    public String GetMake() {
        return make;
    }

    // Getter for model
    public String GetModel() {
        return model;
    }

    // Getter for availability status
    public boolean IsAvailable() {
        return is_available;
    }

    // Setter for availability status
    public void SetAvailable(boolean available) {
        is_available = available;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registration_number='" + registration_number + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", is_available=" + is_available +
                '}';
    }
}

/**
 * Represents a customer in the rental system.
 */
class Customer {
    private String customer_id; // The ID of the customer
    private String name; // The name of the customer
    private String address; // The address of the customer
    private String phone_number; // The phone number of the customer

    /**
     * Constructs a Customer object with the specified details.
     *
     * @param customer_id the ID of the customer
     * @param name the name of the customer
     * @param address the address of the customer
     * @param phone_number the phone number of the customer
     */
    public Customer(String customer_id, String name, String address, String phone_number) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    // Getter for customer ID
    public String GetCustomerId() {
        return customer_id;
    }

    // Getter for name
    public String GetName() {
        return name;
    }

    // Getter for address
    public String GetAddress() {
        return address;
    }

    // Getter for phone number
    public String GetPhoneNumber() {
        return phone_number;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id='" + customer_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}

/**
 * Represents a rental transaction in the rental system.
 */
class Rental {
    private Car car; // The car being rented
    private Customer customer; // The customer renting the car
    private LocalDate rental_date; // The date the car was rented
    private LocalDate return_date; // The date the car was returned

    /**
     * Constructs a Rental object with the specified details.
     *
     * @param car the car being rented
     * @param customer the customer renting the car
     * @param rental_date the date the car was rented
     */
    public Rental(Car car, Customer customer, LocalDate rental_date) {
        this.car = car;
        this.customer = customer;
        this.rental_date = rental_date;
        this.return_date = null; // Return date is null until the car is returned
    }

    // Getter for car
    public Car GetCar() {
        return car;
    }

    // Getter for customer
    public Customer GetCustomer() {
        return customer;
    }

    // Getter for rental date
    public LocalDate GetRentalDate() {
        return rental_date;
    }

    // Getter for return date
    public LocalDate GetReturnDate() {
        return return_date;
    }

    /**
     * Marks the car as returned and sets the return date.
     *
     * @param return_date the date the car was returned
     */
    public void ReturnCar(LocalDate return_date) {
        this.return_date = return_date;
        car.SetAvailable(true); // Mark the car as available again
    }

    @Override
    public String toString() {
        return "Rental{" +
                "car=" + car +
                ", customer=" + customer +
                ", rental_date=" + rental_date +
                ", return_date=" + return_date +
                '}';
    }
}

/**
 * Manages the car rental system, including cars, customers, and rentals.
 */
class RentalAgency {
    private List<Car> cars; // List of cars in the rental agency
    private List<Customer> customers; // List of customers in the rental agency
    private List<Rental> rentals; // List of rental transactions

    /**
     * Constructs a RentalAgency object.
     */
    public RentalAgency() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    /**
     * Adds a car to the rental agency's inventory.
     *
     * @param car the car to be added
     */
    public void AddCar(Car car) {
        cars.add(car);
    }

    /**
     * Adds a customer to the rental agency's records.
     *
     * @param customer the customer to be added
     */
    public void AddCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Finds an available car for rental.
     *
     * @return an available car, or null if no cars are available
     */
    public Car FindAvailableCar() {
        for (Car car : cars) {
            if (car.IsAvailable()) {
                return car;
            }
        }
        return null;
    }

    /**
     * Rents a car to a customer.
     *
     * @param customer the customer renting the car
     * @param car the car to be rented
     * @param rental_date the date the car is rented
     */
    public void RentCar(Customer customer, Car car, LocalDate rental_date) {
        if (car.IsAvailable()) {
            car.SetAvailable(false); // Mark the car as not available
            Rental rental = new Rental(car, customer, rental_date);
            rentals.add(rental); // Add the rental transaction to the list
            System.out.println("Car rented to " + customer.GetName());
        } else {
            System.out.println("Car is not available.");
        }
    }

    /**
     * Returns a rented car.
     *
     * @param car the car being returned
     * @param return_date the date the car is returned
     */
    public void ReturnCar(Car car, LocalDate return_date) {
        for (Rental rental : rentals) {
            if (rental.GetCar().equals(car) && rental.GetReturnDate() == null) {
                rental.ReturnCar(return_date); // Mark the car as returned
                System.out.println("Car returned.");
                return;
            }
        }
        System.out.println("Rental not found.");
    }

    /**
     * Lists all available cars.
     */
    public void ListAvailableCars() {
        for (Car car : cars) {
            if (car.IsAvailable()) {
                System.out.println(car);
            }
        }
    }

    /**
     * Lists all customers.
     */
    public void ListCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * Lists all rental transactions.
     */
    public void ListRentals() {
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }

    // Getter for cars list
    public List<Car> GetCars() {
        return cars;
    }

    // Getter for customers list
    public List<Customer> GetCustomers() {
        return customers;
    }
}
