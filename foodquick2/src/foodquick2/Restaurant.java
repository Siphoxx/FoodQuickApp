package foodquick2;

public class Restaurant {
  private String name;// Name of the restaurant
  private String location; // location of the restaurant
  private String contactNumber; // contact number of the restaurant

  public Restaurant(String name, String location, String contactNumber) {
    this.name = name; // set the restaurant name
    this.location = location; // set the restaurant location
    this.contactNumber = contactNumber; // set the restaurant contact number
  }

  // Getter method to retrieve the name of the restaurant or customer
  public String getName() {
    return name;
  }

  // Getter method to retrieve the location of the restaurant or customer
  public String getLocation() {
    return location;
  }

  // Getter method to retrieve the contact number of the restaurant or customer
  public String getContactNumber() {
    return contactNumber;
  }
}
