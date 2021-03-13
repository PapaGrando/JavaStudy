import java.util.Random;
import java.util.Scanner;

class Scratch {
    public static void main(String[] args) {
        MyClass.ex1();
        MyClass.ex2();
    }

    static class MyClass {

        public static void ex1() {

            final int MAX_VALUE = 9;
            final int MIN_VALUE = 0;
            final int ATTEMPTS = 3;

            Random rand = new Random();
            Scanner sc = new Scanner(System.in);

            boolean gameIsActive = true;
            while (gameIsActive) {

                int secretVal = rand.nextInt(((MAX_VALUE + 1) + MIN_VALUE) + MIN_VALUE);
                //System.out.println(secretVal);

                System.out.println(String.format("Guess the number in %d attempts", ATTEMPTS));

                for (int i = 0; ATTEMPTS > i; i++) {
                    int x = sc.nextInt();

                    if (x == secretVal) {
                        System.out.println("success");
                        break;
                    } else if (x > secretVal) {
                        System.out.println("secret value is LOWER");
                        continue;
                    } else {
                        System.out.println("secret value is UPPER");
                        continue;
                    }
                }

                System.out.println("Try again? 1/0");
                int a = sc.nextInt();

                if (a == 0) gameIsActive = false;

            }
            System.out.println("Game Ended");
            sc.close();
        }

        static void ex2() {
            String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
                    "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi",
                    "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper",
                    "pineapple", "pumpkin", "potato"};

            Random rand = new Random();
            Scanner sc = new Scanner(System.in);

            String secretWord, userWord;
            secretWord = words[rand.nextInt(words.length)];

            System.out.println(secretWord);

            boolean gameIsActive = true;
            while (gameIsActive) {

                System.out.println("Guess the word");

                userWord = sc.nextLine();

                if (userWord.equals(secretWord)) {
                    System.out.println(String.format("success %s", secretWord));
                    gameIsActive = false;
                    break;
                }

                for (int i = 0; secretWord.length() > i; i++) {

                    if (i >= userWord.length()) break;

                    if (secretWord.charAt(i) == userWord.charAt(i)) {
                        System.out.print(secretWord.charAt(i));
                    } else {
                        System.out.print('#');
                    }
                }

                System.out.print("############ ");
                System.out.println("Correct letters. Try again");
            }
            System.out.println("Game ended");
            sc.close();
        }
    }
}

