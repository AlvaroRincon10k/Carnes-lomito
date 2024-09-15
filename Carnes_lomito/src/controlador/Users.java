package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
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
    private String gender;
    private byte[] picture;

    public Users() {

    }

    //datos desde el formulario
    public Users(Integer ID, String UNAME, String PASW, String FNAME, String TEL, String GENDER, byte[] PICTURE) {
        this.id = ID;
        this.username = UNAME;
        this.password = PASW;
        this.fullname = FNAME;
        this.phone = TEL;
        this.gender = GENDER;
        this.picture = PICTURE;
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

    public String getGender() {
        return gender;
    }

    public byte[] getPicture() {
        return picture;
    }

    // Insertar un nuevo CRUD de usuario
    public static void insertUser(Users user) {
        //crear el objeto de la clase  Conexion_DB
        Connection con = modelo.Conexion_DB.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO `users`(`full_name`, `username`, `password`, `phone`, `gender`,`picture`) VALUES (?,?,?,?,?,?)");

            ps.setString(1, user.getFullname());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getGender());
            ps.setBytes(6, user.getPicture());

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Nuevo usuario añadido");
            } else {
                JOptionPane.showMessageDialog(null, "Ocurre algo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Obtener lista de usuarios
    public ArrayList<Users> UsersList() {

        ArrayList<Users> user_list = new ArrayList<>();
        connection = modelo.Conexion_DB.getConnection();

        ResultSet rs;
        PreparedStatement ps;

        String query = "SELECT `id`,`username`,`password`,`full_name`,`phone`, `gender`, `picture`FROM `users`";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            // El órden del select y del while debe ser como el del constructor
            Users user;
            while (rs.next()) {
                user = new Users(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBytes("picture")
                );
                user_list.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_list;
    }

    // Actualizar producto
    // changeImage significa que si desea actualizar la imagen del producto
    public static void updateUsers(Users user, boolean changeImage) {
        Connection con = modelo.Conexion_DB.getConnection();
        PreparedStatement ps;
        // Si es true es por que cambió la imagen
        if (changeImage) {

            try {
                ps = con.prepareStatement("UPDATE `users` SET `full_name`=?,`username`=?,`password`=? ,`phone`=? ,`gender`=? ,`picture`=? WHERE `id` = ?");

                ps.setString(1, user.getFullname());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getPhone());
                ps.setString(5, user.getGender());
                ps.setBytes(6, user.getPicture());
                //el ID lo debo enviar para actualizar
                ps.setInt(7, user.getId());

                if (ps.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurre algo");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //no actualiza la imagen
            try {
                ps = con.prepareStatement("UPDATE `users` SET `full_name`=?,`username`=?,`password`=? ,`phone`=? ,`gender`=?  WHERE `id` = ?");

                ps.setString(1, user.getFullname());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getPhone());
                ps.setString(5, user.getGender());
                ps.setInt(6, user.getId());

                if (ps.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurre algo");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Eliminar producto por id
    public static void deleteUsers(Integer id) {
        Connection con = modelo.Conexion_DB.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM `users` WHERE `id` = ?");
            ps.setInt(1, id);
            // Mostrar un mensaje de confirmación antes de eliminar el usuario
            int YesOrNo = JOptionPane.showConfirmDialog(null, "¿Realmente quieres eliminar a estos usuarios?", "Eliminar usuarios", JOptionPane.YES_NO_OPTION);
            if (YesOrNo == 0) {
                if (ps.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Usuarios eliminados");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurre algo");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
