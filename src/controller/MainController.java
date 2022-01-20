package controller;

import dao.BookDao;
import dao.HistoryDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Buku;
import model.Riwayat;
import model.User;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class MainController {
    private LoginController loginController;
    private BukuController bukuController;
    @FXML
    private DatePicker pinjam;
    @FXML
    private DatePicker kembali;
    @FXML
    private Label buku;
    @FXML
    private Label nama;
    private User user;
    public User getUser() {
        return user;
    }

    private BookDao bDao=new BookDao();
    private HistoryDao hDao=new HistoryDao();
    private ObservableList<Buku> bList=bDao.showData();



    private ObservableList<Riwayat> hList= FXCollections.observableArrayList();
    public void sethList(ObservableList<Riwayat> hList) {
        this.hList = hList;
    }
    public ObservableList<Riwayat> gethList() {
        return hList;
    }

    public Buku selected;

    public ObservableList<Buku> getbList() {
        return bList;
    }
    public void initialize() {
    }

    public void setBukuController(BukuController controller){
        this.bukuController=controller;
        buku.setText(controller.getBook().getJudul());
    }
    public void setController(LoginController controller) {
        this.loginController = controller;
        user=controller.getUser();
        nama.setText(" "+controller.getUser().getUsername());
    }
    @FXML
    private void tampilBuku() throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view3.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        new_stage.setTitle("Daftar Buku");
        BukuController controller= fxmlLoader.getController();
        controller.setController(this);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.initOwner(buku.getScene().getWindow());
        new_stage.setScene(scene);
        new_stage.showAndWait();
    }
    @FXML
    private void showHistory() throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view4.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        new_stage.setTitle("Riwayat Peminjaman");
        RiwayatController controller= fxmlLoader.getController();
        controller.setMainController(this);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.initOwner(buku.getScene().getWindow());
        new_stage.setScene(scene);
        new_stage.showAndWait();
    }
    @FXML
    private void lendBook() throws IOException {
        if (kembali()==null||pinjam()==null){
            showAlert("silahkan pilih tanggal terlebih dahulu");
        }else{
            Riwayat riwayat=new Riwayat();
            riwayat.setPinjam(String.valueOf(pinjam()));
            riwayat.setKembali(String.valueOf(kembali()));
            riwayat.setBuku_id(bukuController.getBook().getId());
            riwayat.setUser_id(loginController.getUser().getId());
            RiwayatController controller= new RiwayatController();
            controller.setMainController(this);
            if (hList.get(hList.size()-1).getPengembalian()==null){
                showAlert("Maaf ada buku yang belum dikembalikan buku");
            }else {
                hDao.addData(riwayat);
                bDao.updateData(new Buku(bukuController.getBook().getId(),bukuController.getBook().getJudul(),0));
                bList.clear();
                bList.addAll(bDao.showData());
                hList.clear();
                hList.addAll(hDao.showData());
                showAlert("Buku berhasil dipinjam mohon dikembalikan sebelum \r\n"+kembali());
            }
        }

    }
    @FXML
    private void returnBook(){
        RiwayatController controller= new RiwayatController();
        controller.setMainController(this);
        if (hList.get(hList.size()-1).getPengembalian()!=null){
            showAlert("Maaf anda belum meminjam buku ");
        }else{
            Riwayat r=new Riwayat();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = sdf.format(new Date());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            long telat=(ChronoUnit.DAYS.between(LocalDate.parse(hList.get(hList.size()-1).getKembali(),formatter) , LocalDate.parse(dateNow,formatter)));
            int denda=0;
            if (telat>0){
                denda= (int) (telat*1000);
            }
            r.setId(hList.get(hList.size()-1).getId());
            r.setPengembalian(dateNow);
            r.setDenda(denda);
            bDao.updateData(new Buku(hList.get(hList.size()-1).getBuku_id(),1));
            bList.clear();
            bList.addAll(bDao.showData());
            hDao.updateData(r);
            hList.clear();
            hList.addAll(hDao.showData());
        }

    }
    @FXML
    private LocalDate pinjam(){
        LocalDate l= pinjam.getValue();
        return l;
    }
    @FXML
    private LocalDate kembali(){
        LocalDate l= kembali.getValue();
        return l;
    }
    public void showAlert(String kalimat){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.show();
        alert.setContentText(kalimat);
    }
    @FXML
    private void about(){
        showAlert("tes");
    }
    @FXML
    private void close(){
        showAlert("tes");
    }
}
