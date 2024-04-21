public class Car extends Vehicle{
    Long numberOfWheels;
    Car(String colour, String make, Long numberOfWheels){
        super(colour, make);
        this.numberOfWheels = numberOfWheels;
    }

    Car(String colour, String make){
        super(colour,make);
        // this.colour = colour;
       // this.make = make;
    }
    @Override
    void drive() {
        System.out.println("Car is running");
    }
}
