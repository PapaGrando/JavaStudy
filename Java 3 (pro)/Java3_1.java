import java.util.ArrayList;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class Scratch {
    public static void main(String[] args) {
        Double[] dArray = {4.5, 4d, 32.5};
        Integer[] iArray = {4, 5, 6};
        String[] sArray = {"r", "lrl", "kek"};

        ArrayList testArrayList = convertToArrayList(dArray);
        System.out.println(testArrayList.get(0).getClass().toString());

        String[] testArrayString = switchValues(sArray, 1, 2);
        Integer[] testIntegerArray = switchValues(iArray, 1, 2);

        System.out.println(testArrayString[1]);
        System.out.println(testIntegerArray[1]);

        Box<Apple> box = new Box(new Apple());
        Box<Orange> box2 = new Box(new Orange(), new Orange(), new Orange());
        Box<Orange> box3 = new Box(new Orange());

        box2.compare(box);
        box.getWeight();
        box2.transfer(box3);
    }

    static <T> T[] switchValues(T[] array, int index1, int index2) {
        if (index1 > 0 && index1 < array.length &&
                index2 > 0 && index2 < array.length) {
            T buffer = array[index1];

            array[index1] = array[index2];
            array[index2] = buffer;

        } else {
            //throw new InvalidPropertiesFormatException("Indexes out of bounds");
        }
        return array;
    }

    static <T> ArrayList convertToArrayList(T[] array) {
        return new ArrayList(Arrays.asList(array));
    }
}

abstract class Fruit {
    private int weight;

    public Fruit(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}

class Orange extends Fruit {

    public Orange() {
        super(1);
    }
}

class Apple extends Fruit {

    public Apple() {
        super(2);
    }
}

class Box<T extends Fruit> {
    ArrayList<T> box = new ArrayList<>();

    public Box(T... fruit) {
        this.box.addAll(Arrays.asList(fruit));
    }

    public void add(T... fruit) {
        box.addAll(Arrays.asList(fruit));
    }

    public void add(ArrayList<T> fruits) {
        this.box.addAll(fruits);
    }

    public float getWeight() {
        float weight = 0;
        if (box.size() > 0)
            for (T fruit : this.box)
                weight += fruit.getWeight();
        return weight;
    }

    public boolean compare(Box<? extends Fruit> box) {
        return box.getWeight() == getWeight();
    }

    public void transfer(Box<T> targetBox) {
        targetBox.add(this.box);
        this.box.clear();
    }
}