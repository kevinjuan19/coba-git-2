package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Riwayat;
import model.User;
import utility.JDBCConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class HistoryDao implements daoInterface<Riwayat>{

    @Override
    public int addData(Riwayat data) {
        int result=0;
        try {
            Connection con;
            con= JDBCConnection.getConnection();
            con.setAutoCommit(false);
            String query="INSERT INTO daftarpinjaman (tanggalPinjam,tanggalKembali,buku_id,user_id)values(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, String.valueOf(data.getPinjam()));
            ps.setString(2,  String.valueOf(data.getKembali()));
            ps.setInt(3, data.getBuku_id());
            ps.setInt(4,data.getUser_id());
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
    public int delData(Riwayat data) {
        return 0;
    }

    @Override
    public int updateData(Riwayat data) {
        int result=0;
        try {
            Connection con;
            con=JDBCConnection.getConnection();
            con.setAutoCommit(false);
            String query="UPDATE daftarpinjaman SET tanggalDikembalikan=?,denda=? WHERE id=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,data.getPengembalian());
            ps.setInt(2,data.getDenda());
            ps.setInt(3,data.getId());

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
    public ObservableList<Riwayat> showData() {
        ObservableList<Riwayat> rList = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM daftarpinjaman";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String a = res.getString("tanggalPinjam");
                String b = res.getString("tanggalKembali");
                String c = res.getString("tanggalDikembalikan");
                int d = res.getInt("denda");
                int e = res.getInt("buku_id");
                int f = res.getInt("user_id");
                rList.add(new Riwayat(id,a,b,c,d,e,f));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rList;
    }

    public ObservableList<Riwayat> showHistory(User data) {
        ObservableList<Riwayat> rList = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM daftarpinjaman WHERE user_id=?";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String a = res.getString("tanggalPinjam");
                String b = res.getString("tanggalKembali");
                String c = res.getString("tanggalDikembalikan");
                int d = res.getInt("denda");
                int e = res.getInt("buku_id");
                int f = res.getInt("user_id");
                rList.add(new Riwayat(id,a,b,c,d,e,f));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rList;
    }
}
