import java.math.BigDecimal;

class Scratch {
    //1
    public static void main(String[] args) {
        MyFirstClass reference = new MyFirstClass();

        //2
        reference.printVariables();

        //3 - 8
        System.out.println(
                "Task : " + reference.Ex3(1, 3, 5, 7) + '\n' +
                        "Task 4 : " + reference.Ex4(5, 6) + '\n' +
                        "Task 5 : " + reference.Ex5(1) + '\n' +
                        "Task 6 : " + reference.Ex6(1) + '\n' +
                        "Task 7 : " + reference.Ex7("Dungeon master") + '\n' +
                        "Task 8 : " + reference.Ex8(2020));
    }
}

class MyFirstClass {
    int simpleInt = 1;
    byte simpleByte = Byte.MAX_VALUE;
    long simpleLong = (long) (Long.MAX_VALUE / 10000000);
    BigDecimal simpleDecimal = BigDecimal.valueOf(simpleLong);
    float simpleFloat = 0.1f;
    double simpleDouble = 0.01;
    boolean simpleBool = true != true;
    private char simpleChar = 'a';
    private String simpleString = "hello";
    private final int finalInt = simpleInt + 5;

    public void printVariables() {
        System.out.println(
                "Int : " + simpleInt + '\n' +
                        "Byte : " + simpleByte + '\n' +
                        "long : " + simpleLong + '\n' +
                        "BigDecimal :" + simpleDecimal + '\n' +
                        "Float : " + simpleFloat + '\n' +
                        "Double : " + simpleDouble + '\n' +
                        "Boolean : " + simpleBool + '\n' +
                        "Char : " + simpleChar + '\n' +
                        "String : " + simpleString + '\n' +
                        "Final Int : " + finalInt + '\n');
    }

    public int Ex3(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    public boolean Ex4(int a, int b) {
        return 10 <= (a + b) && (a + b) <= 20;
    }

    public String Ex5(int val) {
        return val < 0 ? "Negative" : "Positive";
    }

    public boolean Ex6(int val) {
        return val > 0;
    }

    public String Ex7(String name) {
        return "Hello, " + name;
    }

    public String Ex8(int year) {
        if (year % 4 == 0) {
            return "Leap year";
        } else {
            return "Not leap year";
        }
    }
}