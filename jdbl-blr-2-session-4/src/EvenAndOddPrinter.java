import java.util.concurrent.atomic.AtomicInteger;

public class EvenAndOddPrinter {
    int max;
    volatile int i;

    EvenAndOddPrinter(int max){
        this.i = 0;
        this.max = max;
    }

    void printEven() throws InterruptedException {
        while(i<= this.max){
            synchronized (this) {
                if (i % 2 == 0) {
                    System.out.println(i++);
                    this.notify();
                }else{
                    this.wait();
                }
            }
        }
    }


    void printOdd() throws InterruptedException {
        while(i<= this.max){
            synchronized (this) {
                if (i % 2 == 1) {
                    System.out.println(i++);
                    this.notify();
                }else {
                    this.wait();
                }
            }
        }
    }

}
