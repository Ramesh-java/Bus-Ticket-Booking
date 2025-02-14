import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class BookingSystem implements ServiceProvider {
    @Override
    public void bookTicket(List<Passenger> newPassengers, List<Bus> buses, Iterator<Bus> iterator1, Scanner sc,Connection connection) {
        System.out.println("""
                            Enter your location to travel
                            1.Chennai ----> Bangalore
                            2.Chennai ----> Goa
                            3.Chennai ----> Kerala
                            4.Chennai ----> Hyderabad
                            """);
        String locInput = sc.next();
        System.out.println("Enter name : ");
        String passengerName = sc.next();
        System.out.println("Enter id : ");
        String Email = sc.next();
        System.out.println("Enter age : ");
        String age = sc.next();
        Passenger passenger = new Passenger(passengerName,Email,age,locInput);
        boolean found = false;
        iterator1 = buses.iterator();
        while (iterator1.hasNext()) {
            Bus bus = iterator1.next();
            if (passenger.locInput.equals(bus.busCode)) {
                found = true;
                if (bus.checkCapacity()) {
                    newPassengers.add(passenger);
                    System.out.println("Booking Successful");
                    passenger.display();
                    System.out.println();
                    bus.decreaseCapacity();
                    DatabaseOperation.updateBus(locInput,connection);

                } else {
                    System.out.println("Sorry, Seats are full");
                    System.out.println();
                }
            }
            if (!found) {
                System.out.println("Enter a valid Bus route");
                System.out.println();
            }
        }
    }
    @Override
    public void viewTicket(List<Passenger> passengers,List<Passenger> newPassenger, Iterator<Passenger> iterator, Scanner sc) {
        System.out.println("Enter id : ");
        String id = sc.next();
        boolean found = false;
        iterator = passengers.iterator();
        while (iterator.hasNext()){
            Passenger p = iterator.next();
            if (id.equals(p.getId())){
                p.display();
                System.out.println(("Route "+ Bus.routes.get(p.locInput)));
                found = true;
                break;
            }
        }
        if (!found){
            //Iterator<Passenger>iterator2=null;
            iterator= newPassenger.iterator();
            while (iterator.hasNext()){
                Passenger p = iterator.next();
                if (id.equals(p.getId())){
                    p.display();
                    System.out.println(("Route "+ Bus.routes.get(p.locInput)));
                    found = true;
                    break;
                }
            }
        }
        System.out.println();
        if (!found){
            System.out.println("Enter a valid Id");
            System.out.println();
        }
    }
    @Override
    public void cancelTicket(List<Bus> buses, List<Passenger> passengers, List<Passenger>newPassenger, Iterator<Passenger> iterator, Iterator<Bus> iterator1, Scanner sc, Connection connection) {
        System.out.println("Enter id : ");
        String id = sc.next();
        boolean found = false;
        String busCode = "";
        iterator = passengers.iterator();
        while (iterator.hasNext()) {
            Passenger p = iterator.next();
            if (id.equals(p.getId())){
                busCode = p.locInput;
                passengers.remove(p);
                found = true;
                System.out.println("ticket cancelled ");
                System.out.println();
                DatabaseOperation.delete(connection,busCode);
                break;
            }
        }if (!found){
            iterator = newPassenger.iterator();
            while (iterator.hasNext()) {
                Passenger p = iterator.next();
                if (id.equals(p.getId())){
                    busCode = p.locInput;
                    newPassenger.remove(p);
                    found = true;
                    System.out.println();
                    DatabaseOperation.delete(connection,busCode);
                    System.out.println("ticket cancelled ");
                    break;
                }
            }
        }
        if (!found){
            System.out.println("Enter a valid ID");
            System.out.println();
        }
        if (found){
            iterator1 = buses.iterator();
            while (iterator1.hasNext()){
                Bus bus = iterator1.next();
                if (busCode.equals(bus.busCode)){
                    bus.busCapacity++;
                }
            }
        }
        System.out.println();
    }
    @Override
    public void viewAll(List<Passenger> passengers,List<Passenger>newPassenger, Iterator<Passenger> iterator) {
        iterator = passengers.iterator();
        while (iterator.hasNext()){
            Passenger passenger = iterator.next();
            passenger.display();
            System.out.println("Route : "+ Bus.routes.get(passenger.locInput));
            System.out.println();
        }
        iterator = newPassenger.iterator();
        while (iterator.hasNext()){
            Passenger passenger = iterator.next();
            passenger.display();
            System.out.println("Route : "+ Bus.routes.get(passenger.locInput));
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    @Override
    public void availability(List<Bus> buses, Iterator<Bus> iterator1) {
        iterator1 = buses.iterator();
        while (iterator1.hasNext()){
            Bus bus = iterator1.next();
            System.out.println("Bus route : "+ bus.busRoute);
            System.out.println("Bus capacity = "+ bus.busCapacity);
            System.out.println("Bus code = "+ bus.busCode);
            System.out.println();
        }
    }
}