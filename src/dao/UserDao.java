package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Buku;
import model.User;
import utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements daoInterface<User>{
    @Override
    public int addData(User data) {
        return 0;
    }

    @Override
    public int delData(User data) {
        return 0;
    }

    @Override
    public int updateData(User data) {
        return 0;
    }

    @Override
    public List<User> showData() {
        ObservableList<User> uList = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM user";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String nama = res.getString("username");
                String password = res.getString("password");
                uList.add(new User(id,nama,password));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return uList;
    }
    public User login(User data){
        User u;
        try {
            Connection con;
            con=JDBCConnection.getConnection();
            String query="SELECT * FROM user WHERE username=? AND password=md5(?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,data.getUsername());
            ps.setString(2,data.getPassword());
            ResultSet res = ps.executeQuery();
            res.next();
            if (res.getString("username")==null||res.getString("username")==""){
                u=null;
            }else{
                u=new User(res.getInt("id"),res.getString("username"),res.getString("password"));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            u=null;
        }
        return u;
    }
}
