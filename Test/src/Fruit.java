public class Fruit {
    private String name;
    private String color;
    private int size;

    public Fruit(String name, String color, int size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setColor(String newColor) {
        this.color = newColor;
    }

    public void setSize(int newSize) {
        this.size = newSize;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getSize() {
        return this.size;
    }
}
