import java.util.Scanner;

public class CoffeeMachine {

    // ---- Inner Coffee class ----
    static class Coffee {
        final int water;
        final int milk;
        final int beans;
        final int price;

        Coffee(int water, int milk, int beans, int price) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.price = price;
        }
    }

    // ---- CoffeeMachine fields ----
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private int cupsMade;
    private boolean needsCleaning;

    // ---- Constructor ----
    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        this.cupsMade = 0;
        this.needsCleaning = false;
    }

    // ---- Getter for cleaning status ----
    public boolean needsCleaning() {
        return needsCleaning;
    }

    // ---- Methods ----
    public void buy(Coffee coffee) {
        if (water < coffee.water) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (beans < coffee.beans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough cups!");
        } else {
            water -= coffee.water;
            milk -= coffee.milk;
            beans -= coffee.beans;
            cups -= 1;
            money += coffee.price;
            cupsMade++;
            System.out.println("I have enough resources, making you a coffee!");
            if (cupsMade == 10) {
                needsCleaning = true;
            }
        }
    }

    public void fill(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " ml of water");
        System.out.println(this.milk + " ml of milk");
        System.out.println(this.beans + " g of coffee beans");
        System.out.println(this.cups + " disposable cups");
        System.out.println("$" + this.money + " of money");
    }

    public void clean() {
        if (needsCleaning) {
            cupsMade = 0;
            needsCleaning = false;
            System.out.println("I have been cleaned!");
        }
    }

    // ---- Main method ----
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);

        while (true) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String action = scanner.next();

            if (action.equals("buy")) {
                if (machine.needsCleaning()) {
                    System.out.println("I need cleaning!");
                    continue;
                }
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String input = scanner.next();
                if (input.equals("back")) {
                    continue;
                }
                Coffee coffee;
                switch (input) {
                    case "1":
                        coffee = new Coffee(250, 0, 16, 4);
                        break;
                    case "2":
                        coffee = new Coffee(350, 75, 20, 7);
                        break;
                    case "3":
                        coffee = new Coffee(200, 100, 12, 6);
                        break;
                    default:
                        continue;
                }
                machine.buy(coffee);
            } else if (action.equals("fill")) {
                System.out.println("Write how many ml of water you want to add:");
                int addWater = scanner.nextInt();
                System.out.println("Write how many ml of milk you want to add:");
                int addMilk = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans you want to add:");
                int addBeans = scanner.nextInt();
                System.out.println("Write how many disposable cups you want to add:");
                int addCups = scanner.nextInt();
                machine.fill(addWater, addMilk, addBeans, addCups);
            } else if (action.equals("take")) {
                machine.take();
            } else if (action.equals("remaining")) {
                machine.printState();
            } else if (action.equals("clean")) {
                machine.clean();
            } else if (action.equals("exit")) {
                break;
            }
        }
    }
}
