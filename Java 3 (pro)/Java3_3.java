import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Scratch {
    public static void main(String[] args) {

        Reader.ex1();

        try {
            Reader.ex2();
            Reader.ex3(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Reader {
    static final String DIR = "C:\\Users\\-\\AppData\\Roaming\\Google\\AndroidStudio4.1\\scratches\\Java 3 (pro)\\byteFiles\\";
    static final int PAGE_SIZE = 1800;

    static public void ex1() {

        try {
            Files.lines(Paths.get(DIR + "50.txt")).forEachOrdered(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void ex2() throws IOException {
        ArrayList<InputStream> files = new ArrayList();

        File file = new File(DIR + "50.txt");
        File file1 = new File(DIR + "51.txt");
        File file2 = new File(DIR + "52.txt");
        File file3 = new File(DIR + "53.txt");
        File file4 = new File(DIR + "54.txt");

        files.add(new FileInputStream(file));
        files.add(new FileInputStream(file1));
        files.add(new FileInputStream(file2));
        files.add(new FileInputStream(file3));
        files.add(new FileInputStream(file4));

        BufferedInputStream in = new BufferedInputStream(
                new SequenceInputStream(Collections.enumeration(files)));

        StringBuffer sb = new StringBuffer();
        int x;
        while ((x = in.read()) != -1) {
            sb.append((char)x);
        }
        System.out.println(sb.toString());
        in.close();
    }

    static public void ex3(long page) throws FileNotFoundException, IOException {

        RandomAccessFile raf = new RandomAccessFile(DIR + "100MB.txt", "rw");

        Scanner sc = new Scanner(System.in);
        System.out.println("type page");
        page = sc.nextInt() - 1;

        raf.seek(page * PAGE_SIZE);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < PAGE_SIZE; i++) {
            sb.append((char) raf.read());
        }
        System.out.println(sb.toString());
        raf.close();
    }
}