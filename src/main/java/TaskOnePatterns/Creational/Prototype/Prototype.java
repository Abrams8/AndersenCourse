package TaskOnePatterns.Creational.Prototype;

public class Prototype {
    public static void main(String[] args) {
        MyObject prototype = new MyObject();
        MyObject clone = prototype.copy();
    }
}

interface Copyable{
    Copyable copy();
}

class MyObject implements Copyable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyObject copy() {
        MyObject myObject = new MyObject();
        myObject.setName(name);
        return myObject;
    }
}
