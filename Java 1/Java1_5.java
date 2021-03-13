import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Scratch {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Petya", "Dvornik", "hi@gmail.com", "88005553555", 100000, 24));
        persons.add(new Person("Vasya", "Boss", "h4e@gmail.com", "88005553535", 106000, 44));
        persons.add(new Person("Obama", "It", "hi@mail.ru", "88002253555", 190000, 59));
        persons.add(new Person("Van", "Noob", "hfffi@gmail.com", null, 10000, 18));
        persons.add(new Person("Putin", "Vor", "bigboss@gov.ru", "88305553555", 1003000, 70));

        for (int i = 0; persons.size() > i; i++)
            if (persons.get(i).getAge() > 40) persons.get(i).showData();
    }
}

class Person{

    private String fullName;
    private String position;
    private String email;
    private String phone ;
    private long salary;
    private int age;

    public Person(String fullName, String position , String email,
                  String phone, long salary, int age){

        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void showData(){
        System.out.println(this.fullName);
        System.out.println(this.position);
        System.out.println(this.email);
        System.out.println(this.phone);
        System.out.println(this.salary);
        System.out.println(this.age);
        System.out.println();
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public long getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }
}