package foodquick2;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Foodquick {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try {
      // Create a new customer
      // Prompt the user to enter their name, phone number, address, location, and email
      // Validate the user's input using regular expressions and other checks
      String customerName = getValidCustomerName(scanner, "Enter customer name: ");
      String customerContactNumber =
          getValidPhoneNumber(scanner, "Enter customer contact number: ");
      String customerAddress =
          getValidInput(scanner, "Enter customer address: ", "Address is required.");
      String customerLocation =
          getValidInput(scanner, "Enter customer location: ", "Location is required.");
      String customerEmail = getValidEmail(scanner, "Enter customer email: ");

      Customer customer = new Customer(customerName, customerContactNumber, customerAddress,
          customerLocation, customerEmail);

      // Create a new restaurant
      // Prompt the user to enter the restaurant's name, location, and phone number
      // Validate the user's input using regular expressions and other checks
      String restaurantName =
          getValidInput(scanner, "Enter restaurant name: ", "Name is required.");
      String restaurantLocation = getValidLocation(scanner, "Enter restaurant location: ");
      String restaurantContactNumber =
          getValidPhoneNumber(scanner, "Enter restaurant contact number: ");

      Restaurant restaurant =
          new Restaurant(restaurantName, restaurantLocation, restaurantContactNumber);

      // Create a new Restaurant object with the entered information
      int orderNumber = (int) (Math.random() * 10000); // generate a random order number
      Order order = new Order(orderNumber, customer, restaurant);

      // Add meals to the order
      // Prompt the user to enter the number of meals
      // Validate the user's input using regular expressions and other checks
      int numMeals =
          getValidIntInput(scanner, "Enter number of meals: ", "Number of meals is required.");
      // Iterate through each meal to gather its details
      for (int i = 0; i < numMeals; i++) {
        String mealName = getValidMealName(scanner, "Enter meal name: ", "Meal name is required.");
        double mealPrice =
            getValidDoubleInput(scanner, "Enter meal price: ", "Meal price is required.");
        scanner.nextLine();

        // Get special instructions for the order
        System.out.print("Enter special instructions for this meal (optional): ");
        String specialInstructions = scanner.nextLine();
        Meal meal = new Meal(mealName, mealPrice, specialInstructions);
        order.addMeal(meal);
      } // Read drivers.txt file and find the driver in the correct area with the
        // smallest load
      List<Driver> drivers = new ArrayList<>();
      try (BufferedReader reader = new BufferedReader(new FileReader("drivers.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
          String[] parts = line.split(",");
          String name = parts[0].trim();
          String location = parts[1].trim();
          String loadStr = parts[2].trim();
          int load;
          try {
            load = Integer.parseInt(loadStr);
          } catch (NumberFormatException e) {
            System.out.println("Invalid load: " + loadStr);
            continue;
          }
          Driver driver = new Driver(name, location, load);
          drivers.add(driver);
        }
      } catch (IOException e) {
        System.out.println("Error reading drivers.txt file");
      }

      Driver nearestDriver = null;
      for (Driver driver : drivers) {
        if (driver.getLocation().equals(customerLocation)) {
          if (nearestDriver == null || driver.getLoad() < nearestDriver.getLoad()) {
            nearestDriver = driver;
          }
        }
      }

      order.setDriver(nearestDriver);

      // Create an invoice for the customer's order
      // Retrieve the necessary information from the order object
      String invoice = "Order number " + order.getOrderNumber() + "\n";
      // Add customer details if a driver is assigned
      if (order.getDriver() != null) {
        invoice += "Customer: " + order.getCustomer().getName() + "\n";
        invoice += "Email: " + order.getCustomer().getEmail() + "\n";
        invoice += "Phone number: " + order.getCustomer().getContactNumber() + "\n";
        invoice += "Location: " + order.getCustomer().getLocation() + "\n\n";
        invoice += "You have ordered the following from " + order.getRestaurant().getName() + " in "
            + order.getRestaurant().getLocation() + ":\n\n";
        // Iterate through each meal and add its details
        for (Meal meal : order.getMeals()) {
          invoice += "- " + meal.getName() + " ($" + meal.getPrice() + ")\n";
          if (!meal.getSpecialInstructions().isEmpty()) {
            invoice += "  Special instructions: " + meal.getSpecialInstructions() + "\n\n";
          }
        }
        invoice += "Total: $" + order.calculateTotal() + "\n\n";
        invoice += "Your order will be delivered by " + order.getDriver().getName() + " in "
            + order.getDriver().getLocation() + "\n";
      } else {
        invoice += "No driver available for delivery.\n";
      }
      // Add a thank you message
      invoice += "Thank you for your order!";

      // Write the invoice to a file
      try (PrintWriter writer = new PrintWriter(new FileWriter("invoice.txt"))) {
        writer.println(invoice);
        System.out.println("Writing to file: " + new File("invoice.txt").getAbsolutePath());
      } catch (IOException e) {
        System.out.println("Error writing to invoice.txt file: " + e.getMessage());
      }
    } finally {
      scanner.close();
    }
  }


  // Method to get a valid customer name
  private static String getValidCustomerName(Scanner scanner, String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine();
      if (input.matches("[a-zA-Z]+")) { // only accept letters (a-z or A-Z)
        return input;
      } else {
        System.out.println("Invalid customer name. Please enter a name with only letters.");
      }
    }
  }

  /**
   * Method to get a valid customer name
   * 
   * @param scanner the scanner object to read user input
   * @param prompt the prompt to display to the user
   * @return a valid customer name
   */
  private static String getValidInput(Scanner scanner, String prompt, String errorMessage) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine();
      if (!input.trim().isEmpty()) {
        return input;
      } else {
        System.out.println(errorMessage);
      }
    }
  }

  /**
   * Method to get a valid phone number
   * 
   * @param scanner the scanner object to read user input
   * @param prompt the prompt to display to the user
   * @return a valid phone number
   */
  private static String getValidPhoneNumber(Scanner scanner, String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine();
      if (Pattern.matches("\\d{3}[-.]?\\d{3}[-.]?\\d{4}", input)) {
        return input;
      } else {
        System.out.println(
            "Invalid phone number. Please enter a valid phone number in the format XXX-XXX-XXXX.");
      }
    }
  }

  /**
   * Method to get a valid email address
   * 
   * @param scanner the scanner object to read user input
   * @param prompt the prompt to display to the user
   * @return a valid email address
   */
  private static String getValidEmail(Scanner scanner, String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine();
      if (Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", input)) {
        return input;
      } else {
        System.out.println("Invalid email. Please enter a valid email address.");
      }
    }
  }

  /**
   * Method to get a valid positive integer input
   * 
   * @param scanner the scanner object to read user input
   * @param prompt the prompt to display to the user
   * @param errorMessage the error message to display if the input is invalid
   * @return a valid positive integer
   */
  private static int getValidIntInput(Scanner scanner, String prompt, String errorMessage) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine();
      try {
        int number = Integer.parseInt(input);
        if (number > 0) {
          return number;
        } else {
          System.out.println("Please enter a positive integer.");
        }
      } catch (NumberFormatException e) {
        System.out.println(errorMessage);
      }
    }
  }

  /**
   * Method to get a valid positive double input
   * 
   * @param scanner the scanner object to read user input
   * @param prompt the prompt to display to the user
   * @param errorMessage the error message to display if the input is invalid
   * @return a valid positive double
   */
  private static double getValidDoubleInput(Scanner scanner, String prompt, String errorMessage) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.next();
      try {
        double number = Double.parseDouble(input);
        if (number > 0) {
          scanner.nextLine();
          return number;
        } else {
          System.out.println("Please enter a positive number.");
        }
      } catch (NumberFormatException e) {
        System.out.println(errorMessage);
      }
    }
  }

  /**
   * Method to get a valid location input
   * 
   * @param scanner the scanner object to read user input
   * @param prompt the prompt to display to the user
   * @return a valid location
   */
  private static String getValidLocation(Scanner scanner, String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine();
      if (input.matches(".*[a-zA-Z]+.*")) {
        return input;
      } else if (input.matches("\\d+")) {
        System.out.println("Invalid location. Numeric-only locations are not allowed.");
      } else {
        System.out.println("Invalid location. Please enter a valid location.");
      }
    }
  }

  /**
   * Method to get a valid meal name input
   * 
   * @param scanner the scanner object to read user input
   * @param prompt the prompt to display to the user
   * @param errorMessage the error message to display if the input is invalid
   * @return a valid meal name
   */
  private static String getValidMealName(Scanner scanner, String prompt, String errorMessage) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine();
      if (input.matches("[a-zA-Z]+")) {
        return input;
      } else {
        System.out.println("Invalid meal name. Please enter a name with only letters.");
      }
    }
  }
}
