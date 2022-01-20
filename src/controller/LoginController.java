package controller;

import dao.UserDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label notif;
    private UserDao uDao= new UserDao();
    private User newuser= new User();
    public void initialize(){
    }
    public User getUser(){
        return newuser;
    }
    @FXML
    private User Login() throws IOException {
        User u=new User();
        if (username.getText().equals("")||password.getText().equals("")){
            showAlert("Username atau password salah silahkan cek kembali");
        }else{
            newuser.setUsername(username.getText());
            newuser.setPassword(password.getText());
            u=uDao.login(newuser);
            if (u!=null){
                newuser.setId(u.getId());
                Stage new_stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view2.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                MainController controller= fxmlLoader.getController();
                controller.setController(this);
                new_stage.setTitle("Selamat Datang");
                new_stage.initModality(Modality.WINDOW_MODAL);
                username.getScene().getWindow().hide();
                new_stage.initOwner(username.getScene().getWindow());
                new_stage.setScene(scene);
                new_stage.show();
            }else{
                showAlert("Username atau password salah silahkan cek kembali");
            }

        }
        return u;
    }
    public void showAlert(String kalimat){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.show();
        alert.setContentText(kalimat);
    }

}
