public class TakeOff <T extends Flyable>{
    void fly(T flyable){
        flyable.fly();
    }
}
