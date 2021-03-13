class Scratch {
    public static void main(String[] args) {

        Cat cat = new Cat(100, 3, 0);
        Dog dog = new Dog(300, 3, 100);
        Dog dog2 = new Dog(700, 5, 170);


        Animal animal = (Animal) cat;
        System.out.println(animal.jump(100));

        System.out.println(dog.run(150));
        System.out.println(dog2.jump(2));
        System.out.println(cat.swim(2));
    }
}

abstract class Animal {
    protected int runPower;
    protected int jumpPower;
    protected int swimPower;

    public abstract boolean run(int distance);

    public abstract boolean jump(int distance);

    public abstract boolean swim(int distance);

    public Animal(int runPower, int jumpPower, int swimPower) {
        this.runPower = runPower;
        this.jumpPower = jumpPower;
        this.swimPower = swimPower;
    }
}

class Cat extends Animal {

    public Cat(int runPower, int jumpPower, int swimPower) {
        super(runPower, jumpPower, swimPower);
    }

    @Override
    public boolean run(int distance) {
        return distance < this.runPower;
    }

    @Override
    public boolean jump(int distance) {
        return distance < this.jumpPower;
    }

    /** Unsupported */
    @Override
    public boolean swim(int distance) {
        throw new UnsupportedOperationException("Cat can't swim");
    }


}

class Dog extends Animal {

    public Dog(int runPower, int jumpPower, int swimPower) {
        super(runPower, jumpPower, swimPower);
    }

    @Override
    public boolean run(int distance) {
        return distance < this.runPower;
    }

    @Override
    public boolean jump(int distance) {
        return distance < this.jumpPower;
    }

    @Override
    public boolean swim(int distance) {
        return distance < this.swimPower;
    }
}