package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Users {

    Connection connection;
    private Integer id;
    private String fullname;
    private String username;
    private String password;
    private String phone;

    public Users() {

    }

    //datos desde el formulario
    public Users(Integer ID, String UNAME, String PASW, String FNAME, String TEL) {
        this.id = ID;
        this.username = UNAME;
        this.password = PASW;
        this.fullname = FNAME;
        this.phone = TEL;
    }

    public Integer getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    // Insertar un nuevo CRUD de usuario
    public static void insertUser(Users user) {
        //crear el objeto de la clase  Conexion_DB
        Connection con = modelo.Conexion_DB.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO `users`(`full_name`, `username`, `password`, `phone`) VALUES (?,?,?,?)");

            ps.setString(1, user.getFullname());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());

            ps.setString(4, user.getPhone());

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Nuevo usuario añadido");
            } else {
                JOptionPane.showMessageDialog(null, "Ocurre algo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
