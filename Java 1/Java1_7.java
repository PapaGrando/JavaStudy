import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;

import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.S;

class Scratch {
    public static void main(String[] args) throws Exception {

        // 1 - 6
        Cat[] cats = new Cat[4];

        cats[0] = new Cat("barsik", 10, false);
        cats[1] = new Cat("leha", 15, false);
        cats[2] = new Cat("yelow", 25, false);
        cats[3] = new Cat("black", 25, true);

        Plate plate = new Plate(20);

        for (int i = 0; cats.length > i; i++) {
            cats[i].eat(plate);
            plate.info();
            cats[i].info();
            System.out.println();
        }

        System.out.println("_________");

        plate.addFood(30);
        plate.info();

        //eating again copyPast
        for (int i = 0; cats.length > i; i++) {
            cats[i].eat(plate);
            plate.info();
            cats[i].info();
            System.out.println();
        }
        //7 - 8
    }
}

class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite, boolean satiety) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = satiety;
    }

    public void eat(Plate plate) {

        if (plate.getFood() >= appetite && !satiety) {
            plate.takeFood(appetite);
            this.satiety = true;

            System.out.println("Cat : Food WAS eaten");
            return;
        }

        System.out.println("Cat : Food NOT eaten");
    }

    public void info() {
        System.out.println("satiety : " + this.satiety);
    }
}

class Plate {
    private int food;

    public int getFood() {
        return food;
    }

    public Plate(int food) {
        this.food = food;
    }

    public void info() {
        System.out.println("plate : " + food);
    }

    public int takeFood(int n) {

        if ((food - n) >= 0) {
            food -= n;
            return n;
        } else {
            System.out.println("plate : not enough food.");
            int f = food;
            food = 0;
            return f;
        }
    }

    public void addFood(int val) {
        food = val > 0 ? food + val : food;
    }
}

class Parser {
    private Timer timer = new Timer();
    static String desktopPath = System.getProperty("user.home") + "/Desktop";

    private void createFiles(int val) throws IOException {

        for (int i = 0; val > i; i++) {
            File myObj = new File(desktopPath + "/filenamestr" + i + ".txt");

            BufferedInputStream bin = new BufferedInputStream(new FileInputStream
                    (desktopPath + "/filenamestr" + i + ".txt"));
            StringBuilder sb = new StringBuilder("");
            int x;
            while ((x = bin.read()) != -1) {
                sb.append((char) x);
            }
            bin.close();
        }
    }
    public static void ex6() throws Exception {

        BufferedInputStream bin = new BufferedInputStream(new FileInputStream
                (desktopPath + "/STRJAVA.txt"));
        StringBuilder sb = new StringBuilder("");
        int x;
        while ((x = bin.read()) != -1){
            sb.append((char) x);
        }
        bin.close();
    }
}
