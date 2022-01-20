package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Buku;
import utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDao implements daoInterface<Buku>{
    @Override
    public int addData(Buku data) {
        return 0;
    }

    @Override
    public int delData(Buku data) {
        return 0;
    }

    @Override
    public int updateData(Buku data) {
        int result=0;
        try {
            Connection con;
            con=JDBCConnection.getConnection();
            con.setAutoCommit(false);
            String query="UPDATE buku SET status=?  WHERE id=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,data.getStatus());
            ps.setInt(2,data.getId());
            result=ps.executeUpdate();
            if (result !=0){
                con.commit();
            }else{
                con.rollback();
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public ObservableList<Buku> showData() {
        ObservableList<Buku> bList = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM buku where status=1";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String judul = res.getString("judul");
                int status = res.getInt("status");
                bList.add(new Buku(id,judul,status));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bList;
    }
}
