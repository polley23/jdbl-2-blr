import java.util.concurrent.atomic.AtomicInteger;

public class Incrementor implements Runnable{
    int max;
    AtomicInteger i;
    Incrementor(int max){
        this.i = new AtomicInteger();
        this.max = max;
    }
    @Override
    public  void run() {
        //synchronized (this) {
            if (i.get()< max) {
                i.getAndAdd(1);
            }
      //  }
    }
}
