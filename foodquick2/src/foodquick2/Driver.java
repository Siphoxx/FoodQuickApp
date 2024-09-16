// Package declaration for the Driver class
package foodquick2;


// Driver class represents a driver with their details

public class Driver {
  // Private fields to store driver information
  private String name;
  private String location;
  private int load;


  // Constructor to initialize a Driver object

  public Driver(String name, String location, int load) {
    this.name = name;
    this.location = location;
    this.load = load;
  }


  // Getter method to retrieve the driver's name

  public String getName() {
    return name;
  }

  // Getter method to retrieve the driver's location

  public String getLocation() {
    return location;
  }


  // Getter method to retrieve the driver's load

  public int getLoad() {
    return load;
  }
}
