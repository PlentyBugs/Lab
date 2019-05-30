package Lab.ServerClient;

import Lab.Things.Car;

import java.sql.*;
import java.util.ArrayList;

public final class PostgreSQL {

        /*
        ArrayList<Car> cars = getCars("wminecraft616@gmail.com");

        for(Car car : cars){
            System.out.println(car.getName());
            System.out.println(car.getProperty());
            System.out.println(car.getLocalDateTime());
            System.out.println(car.getCostForRepair());
            System.out.println(car.getOwner());
        }
        */

    //System.out.println(checkUser("wminecraft616@gmail.com", "14920356691136"));
    //System.out.println(checkUserOnExisting("wminecraft616@gmail.com"));
    //deleteCar("wminecraft616@gmail.com", "ModelX", "Red", "2019-05-13", 100000, false, 10, 100, false, 10, 100, false, 10, 100, false, 10, 100, false, 10, 100, false, 10, 100, false, 10, 100);
    //insertUser("zuko_20@mail.ru", "5134697");
    //Date date = new Date(new java.util.Date().getTime());
    //insertCar("zuko_20@mail.com", "Mazda7", "Cool", date, 59000, true, 1, 52, true, 1, 52, true, 1, 52, true, 1, 52, true, 1, 52, true, 1, 52, true, 1, 52);

    public static void insertUser(String EMAIL, String PASSWORD){

        Statement stmt = null;
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );
            int id = 0;
            while(rs.next()){
                id = rs.getInt("id");
            }
            id ++;

            String sql = "INSERT INTO USERS (ID,EMAIL,PASSWORD) " +
                    "VALUES (" + id + ", '" + EMAIL  + "', '" +  PASSWORD + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
            System.out.println("Successfully insert car to USERS Table");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting car to USERS Table");
        }
    }

    public static void insertCar(String EMAIL, String car, String property, String date, int repairCost, int x, int y, int id,
                                 boolean RIGHTBOTTOMWHEELISSKILLNEED, int RIGHTBOTTOMWHEELDEGREEOFBREAKAGE, int RIGHTBOTTOMWHEELQUALITY,
                                 boolean LEFTBOTTOMWHEELISSKILLNEED,  int LEFTBOTTOMWHEELDEGREEOFBREAKAGE,  int LEFTBOTTOMWHEELQUALITY,
                                 boolean RIGHTTOPWHEELISSKILLNEED,    int RIGHTTOPWHEELDEGREEOFBREAKAGE,    int RIGHTTOPWHEELQUALITY,
                                 boolean LEFTTOPWHEELISSKILLNEED,     int LEFTTOPWHEELDEGREEOFBREAKAGE,     int LEFTTOPWHEELQUALITY,
                                 boolean BRAKEISSKILLNEED,            int BRAKEDEGREEOFBREAKAGE,            int BRAKEQUALITY,
                                 boolean CABINISSKILLNEED,            int CABINDEGREEOFBREAKAGE,            int CABINQUALITY,
                                 boolean ENGINEISSKILLNEED,           int ENGINEDEGREEOFBREAKAGE,           int ENGINEQUALITY
    ){
        Statement stmt;
        Connection c;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            stmt = c.createStatement();

            if(checkCarOnExisting(id)){
                String sql = "UPDATE CARS SET " +
                        "EMAIL = '" + EMAIL + "'," +
                        "NAME = '" + car + "'," +
                        "PROPERTY = '" + property + "'," +
                        "RIGHTBOTTOMWHEELISSKILLNEED = " + RIGHTBOTTOMWHEELISSKILLNEED + "," +
                        "RIGHTBOTTOMWHEELDEGREEOFBREAKAGE = " + RIGHTBOTTOMWHEELDEGREEOFBREAKAGE + "," +
                        "RIGHTBOTTOMWHEELQUALITY = " + RIGHTBOTTOMWHEELQUALITY + "," +
                        "LEFTBOTTOMWHEELISSKILLNEED = " + LEFTBOTTOMWHEELISSKILLNEED + "," +
                        "LEFTBOTTOMWHEELDEGREEOFBREAKAGE = " + LEFTBOTTOMWHEELDEGREEOFBREAKAGE + "," +
                        "LEFTBOTTOMWHEELQUALITY = " + LEFTBOTTOMWHEELQUALITY + "," +
                        "RIGHTTOPWHEELISSKILLNEED = " + RIGHTTOPWHEELISSKILLNEED + "," +
                        "RIGHTTOPWHEELDEGREEOFBREAKAGE = " + RIGHTTOPWHEELDEGREEOFBREAKAGE + "," +
                        "RIGHTTOPWHEELQUALITY = " + RIGHTTOPWHEELQUALITY + "," +
                        "LEFTTOPWHEELISSKILLNEED = " + LEFTTOPWHEELISSKILLNEED + "," +
                        "LEFTTOPWHEELDEGREEOFBREAKAGE = " + LEFTTOPWHEELDEGREEOFBREAKAGE + "," +
                        "LEFTTOPWHEELQUALITY = " + LEFTTOPWHEELQUALITY + "," +
                        "BRAKEISSKILLNEED = " + BRAKEISSKILLNEED + "," +
                        "BRAKEDEGREEOFBREAKAGE = " + BRAKEDEGREEOFBREAKAGE + "," +
                        "BRAKEQUALITY = " + BRAKEQUALITY + "," +
                        "CABINISSKILLNEED = " + CABINISSKILLNEED + "," +
                        "CABINDEGREEOFBREAKAGE = " + CABINDEGREEOFBREAKAGE + "," +
                        "CABINQUALITY = " + CABINQUALITY + "," +
                        "ENGINEISSKILLNEED = " + ENGINEISSKILLNEED + "," +
                        "ENGINEDEGREEOFBREAKAGE = " + ENGINEDEGREEOFBREAKAGE + "," +
                        "ENGINEQUALITY = " + ENGINEQUALITY + "," +
                        "REPAIRCOST = " + repairCost + "," +
                        "x = " + x + "," +
                        "y = " + y +
                        "WHERE ID = " + id + ";";
                stmt.executeUpdate(sql);
            } else {
                String sql = "INSERT INTO CARS (EMAIL,NAME,PROPERTY," +
                        "RIGHTBOTTOMWHEELISSKILLNEED,RIGHTBOTTOMWHEELDEGREEOFBREAKAGE,RIGHTBOTTOMWHEELQUALITY," +
                        "LEFTBOTTOMWHEELISSKILLNEED,LEFTBOTTOMWHEELDEGREEOFBREAKAGE,LEFTBOTTOMWHEELQUALITY," +
                        "RIGHTTOPWHEELISSKILLNEED,RIGHTTOPWHEELDEGREEOFBREAKAGE,RIGHTTOPWHEELQUALITY," +
                        "LEFTTOPWHEELISSKILLNEED,LEFTTOPWHEELDEGREEOFBREAKAGE,LEFTTOPWHEELQUALITY," +
                        "BRAKEISSKILLNEED,BRAKEDEGREEOFBREAKAGE,BRAKEQUALITY," +
                        "CABINISSKILLNEED,CABINDEGREEOFBREAKAGE,CABINQUALITY" +
                        ",ENGINEISSKILLNEED,ENGINEDEGREEOFBREAKAGE,ENGINEQUALITY," +
                        "REPAIRCOST,orderdate, x, y) " +
                        "VALUES ('" + EMAIL  + "', '" +  car  + "', '" +  property  +
                        "', " +  RIGHTBOTTOMWHEELISSKILLNEED  + ", " +  RIGHTBOTTOMWHEELDEGREEOFBREAKAGE  + ", " +  RIGHTBOTTOMWHEELQUALITY  + ", " +
                        LEFTBOTTOMWHEELISSKILLNEED  + ", " +  LEFTBOTTOMWHEELDEGREEOFBREAKAGE  + ", " +  LEFTBOTTOMWHEELQUALITY  + ", " +
                        RIGHTTOPWHEELISSKILLNEED  + ", " +  RIGHTTOPWHEELDEGREEOFBREAKAGE  + ", " +  RIGHTTOPWHEELQUALITY  + ", " +
                        LEFTTOPWHEELISSKILLNEED  + ", " +  LEFTTOPWHEELDEGREEOFBREAKAGE  + ", " +  LEFTTOPWHEELQUALITY  + ", " +
                        BRAKEISSKILLNEED  + ", " +  BRAKEDEGREEOFBREAKAGE  + ", " +  BRAKEQUALITY  + ", " +
                        CABINISSKILLNEED  + ", " +  CABINDEGREEOFBREAKAGE  + ", " +  CABINQUALITY  + ", " +
                        ENGINEISSKILLNEED  + ", " +  ENGINEDEGREEOFBREAKAGE  + ", " +  ENGINEQUALITY  + ", " +
                        repairCost  + ", '" +  date + "', "  + x + ", " + y + ");";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            c.close();
            System.out.println("Successfully insert car to CARS Table");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting car to CARS Table");
        }
    }

    public static boolean checkUserOnExisting(String userEmail){
        boolean userCheck = false;

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS WHERE EMAIL = '" + userEmail + "';" );
            while ( rs.next() ) {
                if(userEmail.equals(rs.getString("email")))
                    userCheck = true;
            }
            rs.close();

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при чтении пользователя");
        }
        return userCheck;
    }

    public static boolean checkUser(String userEmail, String password){
        boolean userCheck = false;

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS WHERE EMAIL = '" + userEmail + "';" );
            while ( rs.next() ) {
                if(rs.getString("password").equals(password)){
                    userCheck = true;
                }
            }
            rs.close();

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при чтении пользователя");
        }
        return userCheck;
    }

    public static ArrayList<Car> getCars(String userEmail){
        ArrayList<Car> cars = new ArrayList<>();

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM CARS WHERE EMAIL = '" + userEmail + "';" );
            while ( rs.next() ) {
                Car car = new Car();
                car.setWheelBottomRight(rs.getBoolean("RIGHTBOTTOMWHEELISSKILLNEED"), rs.getInt("RIGHTBOTTOMWHEELDEGREEOFBREAKAGE"), rs.getInt("RIGHTBOTTOMWHEELQUALITY"));
                car.setWheelBottomLeft (rs.getBoolean("LEFTBOTTOMWHEELISSKILLNEED"),  rs.getInt("LEFTBOTTOMWHEELDEGREEOFBREAKAGE"),  rs.getInt("LEFTBOTTOMWHEELQUALITY"));
                car.setWheelTopRight   (rs.getBoolean("RIGHTTOPWHEELISSKILLNEED"),    rs.getInt("RIGHTTOPWHEELDEGREEOFBREAKAGE"),    rs.getInt("RIGHTTOPWHEELQUALITY"));
                car.setWheelTopLeft    (rs.getBoolean("LEFTTOPWHEELISSKILLNEED"),     rs.getInt("LEFTTOPWHEELDEGREEOFBREAKAGE"),     rs.getInt("LEFTTOPWHEELQUALITY"));
                car.setBrake           (rs.getBoolean("BRAKEISSKILLNEED"),            rs.getInt("BRAKEDEGREEOFBREAKAGE"),            rs.getInt("BRAKEQUALITY"));
                car.setCabin           (rs.getBoolean("CABINISSKILLNEED"),            rs.getInt("CABINDEGREEOFBREAKAGE"),            rs.getInt("CABINQUALITY"));
                car.setEngine          (rs.getBoolean("ENGINEISSKILLNEED"),           rs.getInt("ENGINEDEGREEOFBREAKAGE"),           rs.getInt("ENGINEQUALITY"));
                car.setName(rs.getString("name"));
                car.setCostForRepair(rs.getInt("repaircost"));
                car.setProperty(rs.getString("property"));
                car.setLocalDateTime(rs.getString("orderdate"));
                car.setOwner(rs.getString("email"));
                car.setX(rs.getInt(28));
                car.setY(rs.getInt(29));
                car.setId(rs.getInt(27));
                Server.getPositions().add(car.getX() + "-" + car.getY());
                cars.add(car);
            }
            for(String s : Server.getPositions()){
                System.out.println("!!!!!!!!!!!1");
                System.out.println(s);
                System.out.println("!!!!!!!!!!!1");
            }
            rs.close();

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при чтении машины");
        }
        return cars;
    }

    public static boolean checkCarOnExisting(int id){
        boolean exist = false;
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM CARS WHERE ID = " + id + ";" );
            while ( rs.next() ) {
                exist = true;
                break;
            }
            rs.close();

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при чтении машины");
        }
        return exist;
    }

    public static void deleteCar(int id, String email){
        Statement stmt;
        Connection c;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            stmt = c.createStatement();

            boolean correctUser = false;


            ResultSet rs = stmt.executeQuery( "SELECT * FROM CARS WHERE ID = " + id + " AND EMAIL = '" + email + "';" );
            while ( rs.next() ) {
                correctUser = true;
                break;
            }
            rs.close();

            if(correctUser){
                String sql = "DELETE FROM CARS WHERE ID = " + id +  ";";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            c.close();
            System.out.println("Successfully delete car from CARS Table");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting car from CARS Table");
        }
    }

    public static void deleteCar(int id){
        Statement stmt;
        Connection c;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            stmt = c.createStatement();

            String sql = "DELETE FROM CARS WHERE ID = " + id +  ";";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
            System.out.println("Successfully delete car from CARS Table");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting car from CARS Table");
        }
    }

    public static ArrayList<Car> getCarsNonUser(String userEmail){
        ArrayList<Car> cars = new ArrayList<>();

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM CARS WHERE EMAIL != '" + userEmail + "';" );
            while ( rs.next() ) {
                Car car = new Car();
                car.setWheelBottomRight(rs.getBoolean("RIGHTBOTTOMWHEELISSKILLNEED"), rs.getInt("RIGHTBOTTOMWHEELDEGREEOFBREAKAGE"), rs.getInt("RIGHTBOTTOMWHEELQUALITY"));
                car.setWheelBottomLeft (rs.getBoolean("LEFTBOTTOMWHEELISSKILLNEED"),  rs.getInt("LEFTBOTTOMWHEELDEGREEOFBREAKAGE"),  rs.getInt("LEFTBOTTOMWHEELQUALITY"));
                car.setWheelTopRight   (rs.getBoolean("RIGHTTOPWHEELISSKILLNEED"),    rs.getInt("RIGHTTOPWHEELDEGREEOFBREAKAGE"),    rs.getInt("RIGHTTOPWHEELQUALITY"));
                car.setWheelTopLeft    (rs.getBoolean("LEFTTOPWHEELISSKILLNEED"),     rs.getInt("LEFTTOPWHEELDEGREEOFBREAKAGE"),     rs.getInt("LEFTTOPWHEELQUALITY"));
                car.setBrake           (rs.getBoolean("BRAKEISSKILLNEED"),            rs.getInt("BRAKEDEGREEOFBREAKAGE"),            rs.getInt("BRAKEQUALITY"));
                car.setCabin           (rs.getBoolean("CABINISSKILLNEED"),            rs.getInt("CABINDEGREEOFBREAKAGE"),            rs.getInt("CABINQUALITY"));
                car.setEngine          (rs.getBoolean("ENGINEISSKILLNEED"),           rs.getInt("ENGINEDEGREEOFBREAKAGE"),           rs.getInt("ENGINEQUALITY"));
                car.setName(rs.getString("name"));
                car.setCostForRepair(rs.getInt("repaircost"));
                car.setProperty(rs.getString("property"));
                car.setLocalDateTime(rs.getString("orderdate"));
                car.setOwner(rs.getString("email"));
                car.setX(rs.getInt(28));
                car.setY(rs.getInt(29));
                car.setId(rs.getInt(27));
                Server.getPositions().add(car.getX() + "-" + car.getY());
                cars.add(car);
            }
            rs.close();

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при чтении машины");
        }
        return cars;
    }

    public static ArrayList<Car> pop(String userEmail){

        ArrayList<Car> cars = new ArrayList<>();

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "WITH CTE AS (" +
                    "   select *  " +
                    "   from CARS " +
                    "   where EMAIL != '" + userEmail + "' " +
                    ")" +
                    "DELETE FROM CTE;";

            ResultSet rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                Car car = new Car();
                car.setWheelBottomRight(rs.getBoolean("RIGHTBOTTOMWHEELISSKILLNEED"), rs.getInt("RIGHTBOTTOMWHEELDEGREEOFBREAKAGE"), rs.getInt("RIGHTBOTTOMWHEELQUALITY"));
                car.setWheelBottomLeft (rs.getBoolean("LEFTBOTTOMWHEELISSKILLNEED"),  rs.getInt("LEFTBOTTOMWHEELDEGREEOFBREAKAGE"),  rs.getInt("LEFTBOTTOMWHEELQUALITY"));
                car.setWheelTopRight   (rs.getBoolean("RIGHTTOPWHEELISSKILLNEED"),    rs.getInt("RIGHTTOPWHEELDEGREEOFBREAKAGE"),    rs.getInt("RIGHTTOPWHEELQUALITY"));
                car.setWheelTopLeft    (rs.getBoolean("LEFTTOPWHEELISSKILLNEED"),     rs.getInt("LEFTTOPWHEELDEGREEOFBREAKAGE"),     rs.getInt("LEFTTOPWHEELQUALITY"));
                car.setBrake           (rs.getBoolean("BRAKEISSKILLNEED"),            rs.getInt("BRAKEDEGREEOFBREAKAGE"),            rs.getInt("BRAKEQUALITY"));
                car.setCabin           (rs.getBoolean("CABINISSKILLNEED"),            rs.getInt("CABINDEGREEOFBREAKAGE"),            rs.getInt("CABINQUALITY"));
                car.setEngine          (rs.getBoolean("ENGINEISSKILLNEED"),           rs.getInt("ENGINEDEGREEOFBREAKAGE"),           rs.getInt("ENGINEQUALITY"));
                car.setName(rs.getString("name"));
                car.setCostForRepair(rs.getInt("repaircost"));
                car.setProperty(rs.getString("property"));
                car.setLocalDateTime(rs.getString("orderdate"));
                car.setOwner(rs.getString("email"));
                cars.add(car);
            }
            rs.close();

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при чтении машины");
        }
        return cars;
    }

    public static void deleteCar(String EMAIL, String car, String property, String date, int repairCost,
                          boolean RIGHTBOTTOMWHEELISSKILLNEED, int RIGHTBOTTOMWHEELDEGREEOFBREAKAGE, int RIGHTBOTTOMWHEELQUALITY,
                          boolean LEFTBOTTOMWHEELISSKILLNEED,  int LEFTBOTTOMWHEELDEGREEOFBREAKAGE,  int LEFTBOTTOMWHEELQUALITY,
                          boolean RIGHTTOPWHEELISSKILLNEED,    int RIGHTTOPWHEELDEGREEOFBREAKAGE,    int RIGHTTOPWHEELQUALITY,
                          boolean LEFTTOPWHEELISSKILLNEED,     int LEFTTOPWHEELDEGREEOFBREAKAGE,     int LEFTTOPWHEELQUALITY,
                          boolean BRAKEISSKILLNEED,            int BRAKEDEGREEOFBREAKAGE,            int BRAKEQUALITY,
                          boolean CABINISSKILLNEED,            int CABINDEGREEOFBREAKAGE,            int CABINQUALITY,
                          boolean ENGINEISSKILLNEED,           int ENGINEDEGREEOFBREAKAGE,           int ENGINEQUALITY
    ){
        Statement stmt = null;
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/auto_repair",
                            "zukoiuh", "14920356691");
            stmt = c.createStatement();

            String sql = "DELETE FROM CARS WHERE " +
                    "EMAIL = '" + EMAIL + "' AND " +
                    "NAME = '" + car + "' AND " +
                    "PROPERTY = '" +  property + "' AND " +
                    "RIGHTBOTTOMWHEELISSKILLNEED = " + RIGHTBOTTOMWHEELISSKILLNEED  + " AND " +
                    "RIGHTBOTTOMWHEELDEGREEOFBREAKAGE = " + RIGHTBOTTOMWHEELDEGREEOFBREAKAGE + " AND " +
                    "RIGHTBOTTOMWHEELQUALITY = " + RIGHTBOTTOMWHEELQUALITY + " AND " +
                    "LEFTBOTTOMWHEELISSKILLNEED = " + LEFTBOTTOMWHEELISSKILLNEED + " AND " +
                    "LEFTBOTTOMWHEELDEGREEOFBREAKAGE = " + LEFTBOTTOMWHEELDEGREEOFBREAKAGE + " AND " +
                    "LEFTBOTTOMWHEELQUALITY = " + LEFTBOTTOMWHEELQUALITY + " AND " +
                    "RIGHTTOPWHEELISSKILLNEED = " + RIGHTTOPWHEELISSKILLNEED + " AND " +
                    "RIGHTTOPWHEELDEGREEOFBREAKAGE = " + RIGHTTOPWHEELDEGREEOFBREAKAGE + " AND " +
                    "RIGHTTOPWHEELQUALITY = " + RIGHTTOPWHEELQUALITY + " AND " +
                    "LEFTTOPWHEELISSKILLNEED = " + LEFTTOPWHEELISSKILLNEED + " AND " +
                    "LEFTTOPWHEELDEGREEOFBREAKAGE = " + LEFTTOPWHEELDEGREEOFBREAKAGE + " AND " +
                    "LEFTTOPWHEELQUALITY = " + LEFTTOPWHEELQUALITY + " AND " +
                    "BRAKEISSKILLNEED = " + BRAKEISSKILLNEED + " AND " +
                    "BRAKEDEGREEOFBREAKAGE = " + BRAKEDEGREEOFBREAKAGE + " AND " +
                    "BRAKEQUALITY = " + BRAKEQUALITY + " AND " +
                    "CABINISSKILLNEED = " + CABINISSKILLNEED + " AND " +
                    "CABINDEGREEOFBREAKAGE = " + CABINDEGREEOFBREAKAGE + " AND " +
                    "CABINQUALITY = " + CABINQUALITY + " AND " +
                    "ENGINEISSKILLNEED = " + ENGINEISSKILLNEED + " AND " +
                    "ENGINEDEGREEOFBREAKAGE = " + ENGINEDEGREEOFBREAKAGE + " AND " +
                    "ENGINEQUALITY = " + ENGINEQUALITY + " AND " +
                    "REPAIRCOST = " + repairCost + " AND " +
                    "ORDERDATE = '" + date + "';";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
            System.out.println("Successfully deleting car to CARS Table");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting car to CARS Table");
        }
    }
}
