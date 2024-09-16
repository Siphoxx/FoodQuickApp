package foodquick2;

public class Meal {
  // Meal class represents a meal with a name, price, and special instructions

  private String name; // name of the meal
  private double price; // price of the meal
  private String specialInstructions; // special instructions for the meal

  public Meal(String name, double price, String specialInstructions) {
    this.name = name;
    this.price = price;
    this.specialInstructions = specialInstructions; // set the special instructions
  }

  // Getter method to retrieve the name of the menu item or product
  public String getName() {
    return name;
  }

  // Getter method to retrieve the price of the menu item or product
  public double getPrice() {
    return price;
  }

  // Getter method to retrieve any special instructions or notes for the menu item or product
  public String getSpecialInstructions() {
    return specialInstructions;
  }
}
