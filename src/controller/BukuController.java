package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Buku;

public class BukuController {
    @FXML
    private TableView<Buku> tableBuku;
    @FXML
    private TableColumn<String,Buku>column1;
    @FXML
    private TableColumn<String,Buku>column2;

    private MainController mainController;
    public void setController(MainController controller) {
        this.mainController = controller;
        tableBuku.setItems(controller.getbList());
    }
    public Buku getBook(){
        return tableBuku.getSelectionModel().getSelectedItem();
    }
    public void initialize(){
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("judul"));
    }
    @FXML
    private void pilihBuku(){
        if (tableBuku.getSelectionModel().getSelectedItem()==null){
            mainController.showAlert("silahkan pilih buku terlebih dahulu");
        }else{
            mainController.selected=tableBuku.getSelectionModel().getSelectedItem();
            mainController.setBukuController(this);
            tableBuku.getScene().getWindow().hide();
        }

    }

}
