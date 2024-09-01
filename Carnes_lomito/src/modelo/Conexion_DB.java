package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion_DB {

    private static String dbname = "usersjulio";
    private static String username = "root";
    private static String password = "Alvaro3213634400";

    static Connection con = null;

    public static Connection getConnection() {
        if (con != null) {
            return con;
        }
        // Obtener base de datos, usuario y contraseña del archivo de configuración
        return getConnection(dbname, username, password);
    }

    private static Connection getConnection(String db_name, String user_name, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/" + db_name + "?user=" + user_name + "&password=" + password);
            System.out.println("connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
