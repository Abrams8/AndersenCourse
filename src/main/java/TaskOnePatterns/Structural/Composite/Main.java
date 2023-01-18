package TaskOnePatterns.Structural.Composite;

public class Main {
    public static void main(String[] args) {
        Team team = new Team();
        Developer javaDeveloper = new JavaDeveloper();
        Developer secondJavaDeveloper = new JavaDeveloper();
        Developer cppDeveloper = new CppDeveloper();

        team.addDeveloper(javaDeveloper);
        team.addDeveloper(secondJavaDeveloper);
        team.addDeveloper(cppDeveloper);

        team.createProject();
    }

}
