class Scratch {
    public static void main(String[] args) {
        Team team = new Team("GasProm", new Member[] {
                new Member("Putin", (int) Double.POSITIVE_INFINITY),
                new Member("Sechin", 1000),
                new Member("Greff", 250),
                new Member("Petya", 100)});

        team.getMembersData();

        Course course = new Course( new Event[]{
                new Event("Raspil", 10),
                new Event("Drink beer", 100),
                new Event("Meeting", 100000),
                new Event("Sleep", 700)});

        course.DoEvent(team);

        team.showResults();
    };

    }

class Team {
    private  String name;

    private Member[] members;

    public Team(String name, Member[] members) {
        this.name = name;
        this.members = members;
    }

    public Member[] getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public void getMembersData () {
        String str = "";
        StringBuffer sb = new StringBuffer(str);

        sb.append(String.format("TEAM %s \n", this.name));

        for (int i = 0; this.members.length > i; i++){
            sb.append(members[i].getName());
            sb.append(" : ");
            sb.append(members[i].getPower());
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    public void showResults(){
        Resultable[] resultable;

        resultable = this.members;

        for (int i = 0; resultable.length > i; i++){
            System.out.println(members[i].getName() + " - " + resultable[i].getResults());
        }
    }
}

class Member implements Resultable{
    private String name;
    private int power;
    private int results;

    public Member(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    @Override
    public int getResults() {
        return results;
    }
    @Override
    public void addPoints(int val) {
        this.results += val;
    }
}

class Course{
    private Event[] events;

    public Course(Event[] events) {
        this.events = events;
    }

    public void DoEvent(Team team){

        System.out.println("\nDOING EVENTS...\n");
        Member[] members = team.getMembers();

        Resultable resultableMember;
        for (int i = 0; this.events.length > i; i++){
            for (int j = 0; members.length > j; j++) {
                resultableMember = members[j];
                if (members[j].getPower() >= events[i].getPowerNeeded()){
                    resultableMember.addPoints(1);
                }
            }
        }
    }
}

class Event{
    private String name;
    private int powerNeeded;

    public Event(String name, int powerNeeded) {
        this.name = name;
        this.powerNeeded = powerNeeded;
    }

    public String getName() {
        return name;
    }

    public int getPowerNeeded() {
        return powerNeeded;
    }
}

interface Resultable{
    int getResults();
    void addPoints(int val);
}