public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Vehicle vehicle = new Car("black", "hyundai",2l);
        ride(new Car("black", "hyundai",2l));
        ride(new Motorbike("black", "hyundai"));
        fly(new Aeroplane());

        fly(()->{
            System.out.println("Flying");
        });

        IDrive drive = new IDrive(){
            public void drive(){

            }
            public void run(){

            }
        };

        drive.drive();


    }

    public static void ride(Vehicle vehicle){
        vehicle.drive();
    }

    public static void fly(IFly flyable){
     flyable.fly();
    }

}