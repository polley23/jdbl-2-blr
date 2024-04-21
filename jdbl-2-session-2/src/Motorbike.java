public class Motorbike extends Vehicle{

    Motorbike(String colour, String make) {
        super(colour, make);
    }

    @Override
    void drive() {
        System.out.println("Motorbike is running");
    }
}
