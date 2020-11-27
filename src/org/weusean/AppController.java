package org.weusean;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class AppController {
    AppModel model = new AppModel();

    public JFXTextField txtPassword;
    public JFXTextField txtUsername;
    public JFXButton btnLogin;

    @FXML
    public void login(ActionEvent actionEvent) {
        System.out.println("Button is pressed");
    }

    @FXML
    void initialize() {
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'sample.fxml'.";

        txtUsername.textProperty().bindBidirectional(model.password());
        txtPassword.textProperty().bindBidirectional(model.password());

    }
}

class AppModel {
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();

    public StringProperty username() {
        return username;
    }

    public final String getUsername() {
        return username().get();
    }

    public final void setUsername(String username) {
        this.username().set(username);
    }

    public StringProperty password() {
        return password;
    }

    public final String getPassword() {
        return password().get();
    }

    public final void setPassword(String password) {
        this.password().set(password);
    }
}