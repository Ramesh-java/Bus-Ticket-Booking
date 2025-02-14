
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        DbConfig dbconfig=new DbConfig();
        Connection connection= dbconfig.getConnection();
        System.out.println("WELCOME TO dk_rx BUS STAND");
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("Chennai---->Goa","1")) ;
        buses.add(new Bus("Chennai---->Bangalore","2")) ;
        buses.add(new Bus("Chennai---->Kerala","3")) ;
        buses.add(new Bus("Chennai---->Mumbai","4")) ;
        Bus.route();
        DatabaseOperation.capUpdate(buses,connection);
        Scanner sc = new Scanner(System.in);
        ServiceProvider booking = new BookingSystem();
        List<Passenger> passengers = new ArrayList<>();
        DatabaseOperation.getRecords(passengers,connection);
        List<Passenger>newPassenger=new ArrayList<>();
        Iterator<Passenger> iterator = null;
        Iterator<Bus> iterator1 = null;
        boolean booktic = true;
        while (booktic) {
            System.out.println("""
                    What you want to do
                    1.Book Tickets
                    2.View Tickets
                    3.Cancel Tickets
                    4.View All
                    5.Availability
                    6.Exit
                    """);
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    booking.bookTicket(newPassenger,buses,iterator1,sc,connection);
                    break;
                }
                case 2: {
                    booking.viewTicket(passengers,newPassenger,iterator,sc);
                    break;
                }
                case 3:{
                    booking.cancelTicket(buses,passengers,newPassenger,iterator,iterator1,sc, connection);
                    break;
                }
                case 4: {
                    booking.viewAll(passengers,newPassenger,iterator);
                    break;
                }
                case 5:{
                    booking.availability(buses,iterator1);
                    break;
                }
                case 6:{
                    booktic = false;
                }
            }
        }
        DatabaseOperation.insert(newPassenger,connection);
        dbconfig.destroy(connection);
        sc.close();
    }
}
