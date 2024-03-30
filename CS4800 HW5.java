interface FoodItem {
    double getCost();
}

//Class Burger, Fries and Drink implemented as Food items
class Burger implements FoodItem {
    private double basePrice;
    public Burger(double basePrice) {
        this.basePrice = basePrice;
    }
    @Override
    public double getCost() {
        return basePrice;
    }
}

class Fries implements FoodItem {
    private double basePrice;
    public Fries(double basePrice) {
        this.basePrice = basePrice;
    }
    @Override
    public double getCost() {
        return basePrice;
    }
}

class Drink implements FoodItem {
    private double basePrice;
    public Drink(double basePrice) {
        this.basePrice = basePrice;
    }
    @Override
    public double getCost() {
        return basePrice;
    }
}

//Abstract class for Toppings
abstract class Topping implements FoodItem {
    protected FoodItem foodItem;
    public Topping(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}

//Class Cheese, Tomato, and Pickles implemented as Toppings
class Cheese extends Topping {
    private double cost;
    public Cheese(FoodItem foodItem, double cost) {
        super(foodItem);
        this.cost = cost;
    }
    @Override
    public double getCost() {
        return foodItem.getCost() + cost;
    }
}

class Tomato extends Topping {
    private double cost;
    public Tomato(FoodItem foodItem, double cost) {
        super(foodItem);
        this.cost = cost;
    }
    @Override
    public double getCost() {
        return foodItem.getCost() + cost;
    }
}

class Pickles extends Topping {
    private double cost;
    public Pickles(FoodItem foodItem, double cost) {
        super(foodItem);
        this.cost = cost;
    }
    @Override
    public double getCost() {
        return foodItem.getCost() + cost;
    }
}

//Class representing customer order
class Order {
    private List<FoodItem> foodItems = new ArrayList<>();
    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
    }
    public double getTotalCost() {
        double totalCost = 0.0;
        for (FoodItem foodItem : foodItems) {
            totalCost += foodItem.getCost();
        }
        return totalCost;
    }
}

//Class implementing loyalty discount status
class LoyaltyStatus {
    private double discount;
    public LoyaltyStatus(double discount) {
        this.discount = discount;
    }
    public double applyDiscount(double totalCost) {
        return totalCost * (1 - discount);
    }
}

//Class implementing Restaurant Order System
public class RestaurantOrderSystem {
    public static void main(String[] args) {
        //Create Food items with base prices
        FoodItem burger = new Burger(5.0);
        FoodItem fries = new Fries(2.0);
        FoodItem drink = new Drink(1.0);

        //List of Adding toppings to Food items
        FoodItem burgerWithCheese = new Cheese(burger, 1.0);
        FoodItem burgerWithTomato = new Tomato(burger, 1.0);
        FoodItem burgerWithPickles = new Pickles(burger, 1.0);
        FoodItem burgerWithCheeseAndTomato = new Tomato(burgerWithCheese, 1.5);
        FoodItem burgerWithPicklesAndTomato = new Pickles(burgerWithTomatos, 1.5);
        FoodItem burgerWithCheeseAndPickles = new Cheese(burgerWithPickles, 1.5);
        FoodItem burgerWithCheeseAndPicklesAndTomatos = new(burgerWithAll, 2.0);


        //Create a sample order
        Order order = new Order();
        order.addFoodItem(burgerWithAll);
        order.addFoodItem(fries);
        order.addFoodItem(drink);

        //Calculate total cost of order
        double totalCost = order.getTotalCost();
        System.out.println("Total cost before discount: $" + totalCost);

        //Apply loyalty discount
        LoyaltyStatus loyaltyStatus= new LoyaltyStatus(0.1); // Assuming 10% discount for loyalty status
		double discountedTotalCost = loyaltyStatus.applyDiscount(totalCost);
		System.out.println("Total cost after discount: $" + discountedTotalCost);
	}
}