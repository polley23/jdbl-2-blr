public final class SingletonExample {

    // Eager
    //static SingletonExample singletonExample = new SingletonExample();


    //Lazy
    static volatile SingletonExample singletonExample;
    private SingletonExample(){
    }
    public static SingletonExample GetInstance(){
        if(singletonExample == null){
            synchronized (SingletonExample.class){
                if(singletonExample == null) {
                    singletonExample = new SingletonExample();
                }
            }
        }
        return singletonExample;
    }
}
