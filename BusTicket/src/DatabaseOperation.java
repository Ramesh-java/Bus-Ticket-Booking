import java.sql.*;
import java.util.List;

class DatabaseOperation {
    public static void delete(Connection connection,  String busCode){

        String query="update myDB.bus set capacity=capacity + ? where id = ?";
        try (PreparedStatement pr=connection.prepareStatement(query)){
            pr.setInt(1,1);
            pr.setInt(2,Integer.parseInt(busCode));
            int rowsUpdated=pr.executeUpdate();
            if (rowsUpdated>0){
                System.out.println();
            }else System.out.println("not working");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void insert(List<Passenger> passengers, Connection connection){
        String query="insert into myDB.passenger(name,id,age,route)values(?,?,?,?)";
        try (PreparedStatement pr= connection.prepareStatement(query)){
            for (Passenger p:passengers){
                pr.setString(1,p.getPassengerName());
                pr.setString(2,p.getId());
                pr.setString(3,p.getAge());
                pr.setString(4,Bus.routes.get(p.locInput));
                int rowsInserted= pr.executeUpdate();
                if (rowsInserted>0){
                    System.out.println("inserted successfully ");
                }else {
                    System.out.println("not inserted");
                }
            }

            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void getRecords(List<Passenger>passengers,Connection connection){
        String query="select * from myDB.passenger";
        try (Statement statement=connection.createStatement()){
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                String name=resultSet.getString("name");
                String id=resultSet.getString("id");
                String age=resultSet.getString("age");
                String route=resultSet.getString("route");
                String busCode=Bus.getKeyByValue(route);
                passengers.add(new Passenger(name,id,age,busCode));
                //System.out.println(Bus.getKeyByValue(route)+busCode);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void updateBus(String id,Connection connection){
        String query="update myDB.bus set capacity=capacity - ? where id = ?";
        try (PreparedStatement pr=connection.prepareStatement(query)){
            pr.setInt(1,1);
            pr.setInt(2,Integer.parseInt(id));
            int rowsUpdated=pr.executeUpdate();
            if (rowsUpdated>0){
                System.out.println("cap ++");
            }else System.out.println("not working");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static  void capUpdate(List<Bus>buses,Connection connection){
        String query="select * from myDB.bus";
        try(Statement statement=connection.createStatement()) {
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                int capacity=resultSet.getInt("capacity");
                for (Bus b:buses){
                    if (b.busCode.equals(String.valueOf(id))){
                        b.busCapacity=capacity;
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
