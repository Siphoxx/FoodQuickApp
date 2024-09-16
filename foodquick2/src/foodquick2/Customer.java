// Package declaration for the Customer class
package foodquick2;


// Customer class represents a customer with their details

public class Customer {
  // Private fields to store customer information
  private String name;
  private String contactNumber;
  private String address;
  private String location;
  private String email;


  // Constructor to initialize a Customer object

  public Customer(String name, String contactNumber, String address, String location,
      String email) {
    this.name = name;
    this.contactNumber = contactNumber;
    this.address = address;
    this.location = location;
    this.email = email;
  }


  // Getter method to retrieve the customer's name

  public String getName() {
    return name;
  }


  // Getter method to retrieve the customer's contact number

  public String getContactNumber() {
    return contactNumber;
  }

  // Getter method to retrieve the customer's address

  public String getAddress() {
    return address;
  }


  // Getter method to retrieve the customer's location

  public String getLocation() {
    return location;
  }


  // Getter method to retrieve the customer's email

  public String getEmail() {
    return email;
  }
}
