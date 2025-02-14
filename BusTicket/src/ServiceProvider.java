import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
interface ServiceProvider {
    void bookTicket(List<Passenger> newPassenger, List<Bus> buses, Iterator<Bus> iterator1, Scanner sc,Connection connection);
    void viewTicket(List<Passenger> newPassenger,List<Passenger> passengers,Iterator<Passenger> iterator,Scanner sc);
    void cancelTicket(List<Bus> buses, List<Passenger> passengers, List<Passenger>newPassenger, Iterator<Passenger> iterator, Iterator<Bus> iterator1, Scanner sc, Connection connection);
    void viewAll(List<Passenger> passengers,List<Passenger>newPassenger,Iterator<Passenger> iterator);
    void availability(List<Bus> buses,Iterator<Bus> iterator1);
}