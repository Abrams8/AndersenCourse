package TaskOnePatterns.Structural.Bridge;

public class PhpDeveloper implements Developer{
    @Override
    public void writeCode() {
        System.out.println("PHP dev writes code...");
    }
}
