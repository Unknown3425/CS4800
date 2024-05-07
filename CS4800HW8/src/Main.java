import java.util.*;

//Snack class listing properties of a snack
class Snack{
    String name;
    double price;
    int quantity;
    public Snack(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

//Interface representing different states of the vending machine
interface StateOfVendingMachine{
    void selectSnack(VendingMachine vendingMachine, Snack snack);
    void insertMoney(VendingMachine vendingMachine, double money);
    void dispenseSnack(VendingMachine vendingMachine);
}

//Idle state of the vending machine
class IdleState implements StateOfVendingMachine{
    public void selectSnack(VendingMachine vendingMachine, Snack snack){
        vendingMachine.setSelectedSnack(snack);
        vendingMachine.changeState(new WaitingForMoneyState());
    }
//
    public void insertMoney(VendingMachine vendingMachine, double money){
        System.out.println("Choose a snack.");
    }
    public void dispenseSnack(VendingMachine vendingMachine){
        System.out.println("Choose a snack.");
    }
}

//Waiting for money state of the vending machine
class WaitingForMoneyState implements StateOfVendingMachine{
    public void selectSnack(VendingMachine vendingMachine, Snack snack){
        System.out.println("Insert money.");
    }
//Check if enough money was inserted
    public void insertMoney(VendingMachine vendingMachine, double money){
        if (money >= vendingMachine.getSelectedSnack().price){
            vendingMachine.setMoneyInserted(money);
            vendingMachine.changeState(new DispensingSnackState());
        } else{
            System.out.println("Insufficient money inserted.");
        }
    }
    public void dispenseSnack(VendingMachine vendingMachine){
        System.out.println("Insert money.");
    }
}

//Dispensing snack state of the vending machine
class DispensingSnackState implements StateOfVendingMachine{
    public void selectSnack(VendingMachine vendingMachine, Snack snack){
        System.out.println("Dispensing snack.");
    }
    public void insertMoney(VendingMachine vendingMachine, double money){
        System.out.println("Dispensing snack.");
    }
//If statement to check snack quantity
    public void dispenseSnack(VendingMachine vendingMachine){
        if (vendingMachine.getSelectedSnack().quantity > 0){
            System.out.println("Dispensing " + vendingMachine.getSelectedSnack().name);
            vendingMachine.getSelectedSnack().quantity--;
            vendingMachine.changeState(new IdleState());
        } else{
            System.out.println("Sorry, this snack is out of stock.");
            vendingMachine.changeState(new IdleState());
        }
    }
}

//Snack dispenser chain of responsibility
class SnackDispenseHandler{
    List<Snack> snacks = new ArrayList<>();
    int currentIndex = 0;

//Add snacks with stated properties, Snickers quantity set to 0 to satisfy requirement
    public SnackDispenseHandler(){
        snacks.add(new Snack("Coke", 1.5, 5));
        snacks.add(new Snack("Pepsi", 1.75, 3));
        snacks.add(new Snack("Cheetos", 2, 4));
        snacks.add(new Snack("Doritos", 2.25, 6));
        snacks.add(new Snack("KitKat", 1.25, 2));
        snacks.add(new Snack("Snickers", 1.5, 0));
    }
//Access array to obtain the next snack
    public Snack getNextSnack(){
        if (currentIndex < snacks.size()){
            return snacks.get(currentIndex++);
        } else{
            return null;
        }
    }
}

//Vending machine class
class VendingMachine{
    private StateOfVendingMachine currentState;
    private Snack selectedSnack;
    private double moneyInserted;

    public VendingMachine(){
        currentState = new IdleState();
    }
    public void setSelectedSnack(Snack snack){
        selectedSnack = snack;
    }
    public Snack getSelectedSnack(){
        return selectedSnack;
    }
    public void setMoneyInserted(double money){
        moneyInserted = money;
    }
    public double getMoneyInserted(){
        return moneyInserted;
    }
    public void changeState(StateOfVendingMachine newState){
        currentState = newState;
    }
    public void selectSnack(Snack snack){
        currentState.selectSnack(this, snack);
    }
    public void insertMoney(double money){
        currentState.insertMoney(this, money);
    }
    public void dispenseSnack(){
        currentState.dispenseSnack(this);
    }
}

//Driver class to run the vending machine
public class Main{
    public static void main(String[] args){
        VendingMachine vendingMachine = new VendingMachine();
        SnackDispenseHandler dispenser = new SnackDispenseHandler();
//Run while 6 snacks haven't been chosen
        for (int i=0; i<6; i++){
            Snack snack = dispenser.getNextSnack();
            System.out.println("Selected snack: " + snack.name);
            vendingMachine.selectSnack(snack);
            vendingMachine.insertMoney(snack.price);
            vendingMachine.dispenseSnack();
            System.out.println();
        }
    }
}
