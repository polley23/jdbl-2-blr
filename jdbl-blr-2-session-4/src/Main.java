public class Main {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("Hello world!");
//
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Thread.yield();
//                System.out.println("Hello World");
//
//            }
//        });
//        thread1.setPriority(Thread.MIN_PRIORITY);
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Good bye");
//
//            }
//        });
//        thread2.setPriority(Thread.MAX_PRIORITY);
//
//        thread1.start();
//        thread2.start();
//
//        try {
//            thread1.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            thread2.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//        System.out.println("Hello class");

//        Incrementor incrementor  = new Incrementor(10000);
//        Thread[] threads = new Thread[10000];
//
//        for(int i = 0 ;i<10000; i++){
//            threads[i]= new Thread(incrementor);
//            threads[i].start();
//        }
//
//        for(int i = 0 ;i<10000; i++){
//           threads[i].join();
//        }



//        Thread thread1 = new Thread(incrementor);
//
//        Thread thread2 = new Thread(incrementor);
//
//        thread1.start();
//        thread2.start();
//
//        thread1.join();
//        thread2.join();


///        System.out.println(incrementor.i);
        EvenAndOddPrinter evenAndOddPrinter = new EvenAndOddPrinter(200000);

        Thread oddThread = new Thread( ()->{
            try {
                evenAndOddPrinter.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread evenThread = new Thread( ()->{
            try {
                evenAndOddPrinter.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();


    }
}