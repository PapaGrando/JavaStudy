import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class Scratch {
    static final String DIR = "C:\\Users\\-\\AppData\\Roaming\\Google\\AndroidStudio4.1\\scratches\\Java 3 (pro)\\byteFiles\\";

    public static void main(String[] args) {
        //1
        new Thread(new Ex1('A', 'B')).start();
        new Thread(new Ex1('B', 'C')).start();
        new Thread(new Ex1('C', 'A')).start();

        //2
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(DIR + "textFileThreads.txt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(new Ex2("thread 1", pw));
        Thread t2 = new Thread(new Ex2("thread 2", pw));
        Thread t3 = new Thread(new Ex2("thread 3", pw));

        try {

            t1.start();
            t1.join();

            t2.start();
            t2.join();

            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //3

         Ex3 ex3 = new Ex3();

        new Thread(()-> ex3.printing(5)).start();
        new Thread(()-> ex3.scanning(2)).start();
        new Thread(()-> ex3.printing(3)).start();
    }
}

class Ex1 implements Runnable {
    private char currentLetter;
    private char nextLetter;
    static char c = 'A';
    static Object checker = new Object();


    public Ex1(char currentLetter, char nextLetter) {
        this.currentLetter = currentLetter;
        this.nextLetter = nextLetter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (checker) {
                try {
                    while (c != currentLetter)
                        checker.wait();
                    System.out.print(currentLetter);
                    c = nextLetter;
                    checker.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Ex2 implements Runnable {
    PrintWriter pw;
    private String message;

    static Object monitor = new Object();

    Ex2(String message, PrintWriter pw) {
        this.message = message;
        this.pw = pw;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                pw.write(message + '\n');
                pw.flush();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Ex3 {

    private Object scanMonitor = new Object();
    private Object printMonitor = new Object();

    void scanning(int lists) {
        synchronized (scanMonitor) {
            for (int i = 0; i < lists; i++) {
                System.out.println("scanning list " + (i + 1));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("scanned list " + (i + 1));
            }

            System.out.println("scanning ready");
        }
    }

    void printing(int lists) {
        synchronized (printMonitor) {
            for (int i = 0; i < lists; i++) {
                System.out.println("printing list " + (i + 1));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("printed list " + (i + 1));
            }
        }

        System.out.println("printing ready");
    }
}