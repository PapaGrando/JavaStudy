import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Scratch {
    public static void main(String[] args) {
        String[] words = new String[]{

                "hi",
                "begin",
                "again",
                "hi",
                "sky",
                "hi",
                "black",
                "sky",
                "house",
                "bridge",
                "home",
                "bye",
                "bye",
                "hello",
                "home",
                "home",
                "home",
                "home"
        };

        CollectionsPractise cp = new CollectionsPractise();

        //1
        cp.ex1(words);

        //2
        TelephoneList tl = new TelephoneList();

        tl.add("barsik", "64544354");
        tl.add("barsik", "64544354");
        tl.add("barsik", "64544334");
        tl.add("barsik", "64544554");
        tl.add("Putin", "64533334");
        tl.add("koryaga", "645654");

        System.out.println(tl.get("Putin") + "\n");

        tl.print();
    }
}

class CollectionsPractise {

    void ex1(String[] words) {
        HashMap<String, Integer> uniqWords = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            boolean wordNotUniq = false;

            //извлекаем уникальные слова
            for (Map.Entry<String, Integer> word : uniqWords.entrySet()) {
                if (word.getKey().equals(words[i])) {
                    wordNotUniq = true;
                    break;
                }
            }
            if (!wordNotUniq) uniqWords.put(words[i], 1);

        }
        //подсчет слов
        for (Map.Entry<String, Integer> word : uniqWords.entrySet()) {
            int wordCount = 0;
            for (int i = 0; i < words.length; i++) {
                if (word.getKey().equals(words[i]))
                    wordCount++;
            }
            word.setValue(wordCount);
        }
        StringBuffer sb = new StringBuffer();

        for (String word : uniqWords.keySet()) {
            sb.append(word + " ");
        }

        System.out.println("Uniq words : " + sb.toString() + '\n');

        sb.delete(0, sb.length());

        for (Map.Entry<String, Integer> word : uniqWords.entrySet()) {
            sb.append(word.getKey() + "  -  " + word.getValue() + '\n');
        }

        System.out.println(sb.toString());
    }

}

class TelephoneList {
    Map<String, ArrayList<String>> telephoneNumbers = new HashMap<>();

    void add(String name, String number) {
        for (Map.Entry<String, ArrayList<String>> member : telephoneNumbers.entrySet()) {

            if (name.equals(member.getKey())) {

                for (String tel : member.getValue()) {

                    if (tel.equals(number)) {
                        System.out.println("Number is already exist");
                        return;
                    }
                }

                member.getValue().add(number);
                return;
            }
        }
        telephoneNumbers.put(name, new ArrayList<String>());
        telephoneNumbers.get(name).add(number);
    }

    public ArrayList get(String name) {
        return telephoneNumbers.get(name);
    }

    public void print() {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, ArrayList<String>> member : telephoneNumbers.entrySet()) {
            sb.append(member.getKey() + " - ");

            for (String tel : member.getValue()) {
                sb.append(tel + ", ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}