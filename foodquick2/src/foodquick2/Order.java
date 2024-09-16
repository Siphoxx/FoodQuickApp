package foodquick2;

import java.util.ArrayList;
import java.util.List;

public class Order {
  private int orderNumber; // unique order number
  private Customer customer;
  private Restaurant restaurant; // restaurant where the order was placed
  private List<Meal> meals; // list of meals in the order
  private Driver driver; // driver assigned to the order

  public Order(int orderNumber, Customer customer, Restaurant restaurant) {
    this.orderNumber = orderNumber; // set the order number
    this.customer = customer;
    this.restaurant = restaurant;
    this.meals = new ArrayList<>(); // initialize the list of meals
  }

  // Getter method to retrieve the unique identifier for the order
  public int getOrderNumber() {
    return orderNumber;
  }

  // Getter method to retrieve the customer associated with the order
  public Customer getCustomer() {
    return customer;
  }

  // Getter method to retrieve the restaurant associated with the order
  public Restaurant getRestaurant() {
    return restaurant;
  }

  // Getter method to retrieve the list of meals in the order
  public List<Meal> getMeals() {
    return meals;
  }

  // Method to add a meal to the order
  public void addMeal(Meal meal) {
    meals.add(meal);
  }

  // Method to calculate the total cost of the meals in the order
  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  public double calculateTotal() {
    double total = 0;
    for (Meal meal : meals) {
      total += meal.getPrice();
    }
    return total;
  }
}
