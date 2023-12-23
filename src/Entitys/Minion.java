package Entitys;

public class Minion implements Comparable<Minion> {
    private String name;
    private String bodyColor;
    private int age;

    public Minion(String name, String bodyColor, int age) {
        this.name = name;
        this.bodyColor = bodyColor;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Minion{" +
                "name='" + name + '\'' +
                ", bodyColor='" + bodyColor + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Minion other) {

        int nameComparison = this.name.compareTo(other.name);

        if (nameComparison != 0) {
            return nameComparison;
        }

        int bodyColorComparison = other.bodyColor.compareTo(this.bodyColor);
        //this.bodyColor.compareTo(other.bodyColor);
        if (bodyColorComparison != 0) {
            return bodyColorComparison;
        }

        return Integer.compare(this.age, other.age);
    }
}
