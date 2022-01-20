package controller;

import dao.HistoryDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Buku;
import model.Riwayat;
import model.User;

public class RiwayatController {
    @FXML
    private TableView<Riwayat> tableRiwayat=new TableView<>();
    @FXML
    private TableColumn<String,Riwayat> column1;
    @FXML
    private TableColumn<String,Riwayat>column2;
    @FXML
    private TableColumn<String,Riwayat> column3;
    @FXML
    private TableColumn<String,Riwayat>column4;
    @FXML
    private TableColumn<String,Riwayat>column5;
    @FXML
    private TableColumn<String,Riwayat>column6;
    private MainController mainController;
    private User u;
    private HistoryDao hDao=new HistoryDao();
    private ObservableList<Riwayat> rList= FXCollections.observableArrayList();
    private boolean cek=true;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        rList= hDao.showHistory(mainController.getUser());
        tableRiwayat.setItems(rList);
        mainController.sethList(rList);
    }

    public void initialize(){

        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("pinjam"));
        column3.setCellValueFactory(new PropertyValueFactory<>("kembali"));
        column4.setCellValueFactory(new PropertyValueFactory<>("pengembalian"));
        column5.setCellValueFactory(new PropertyValueFactory<>("denda"));
        column6.setCellValueFactory(new PropertyValueFactory<>("buku_id"));

    }
}
