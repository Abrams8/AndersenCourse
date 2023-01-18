package TaskOnePatterns.Creational.Singleton;

public final class Singleton {
    private static Singleton instance;

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    private Singleton(){
    }
}
