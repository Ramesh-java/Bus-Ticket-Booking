import java.util.HashMap;
import java.util.Map;
public class Bus {
    String busRoute ;
    int busCapacity;
    String busCode;
    static Map<String , String> routes = new HashMap<>();

    Bus(String busRoute,String busCode){
        this.busRoute = busRoute;
        busCapacity = 3;
        this.busCode = busCode;
    }
    public static void route(){
        routes.put("1","Chennai----->Goa");//Chennai----->Goa
        routes.put("2","Chennai---->Bangalore");
        routes.put("3","Chennai---->Kerala");
        routes.put("4","Chennai---->Mumbai");
    }
    static String getKeyByValue(String value) {
        for (Map.Entry<String, String> entry : Bus.routes.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
    public void increaseCapacity(){
        busCapacity++;
    }
    public void decreaseCapacity(){
        busCapacity--;
    }
    public boolean checkCapacity(){
        return busCapacity>0;
    }
}