public class Passenger {
    private String passengerName ;
    private String id ;
    private String age ;
    String locInput ;
    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public Passenger(String passengerName, String id , String age, String locInput) {
        setPassengerName(passengerName);
        setId(id);
        setAge(age);
        this.locInput = locInput;
    }
    void display(){
        System.out.println("Name : "+passengerName);
        System.out.println("E-mail : "+id);
        System.out.println("Age : "+age);
    }
}
