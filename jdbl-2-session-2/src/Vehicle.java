public abstract class Vehicle {
    String colour;
    String make;

    Vehicle(String colour, String make){
        this.colour = colour+"hello";
        this.make = make+"dsds";
    }
    abstract void drive();

    void run(){
        System.out.println("Vehicle is running");
    }
}
