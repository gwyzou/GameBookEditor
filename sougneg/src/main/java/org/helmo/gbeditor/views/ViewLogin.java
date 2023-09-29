package org.helmo.gbeditor.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.helmo.gbeditor.presenters.LoginPresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewBasic;
import org.helmo.gbeditor.presenters.interfaces.IViewToPresenter;

import java.util.Locale;

public class ViewLogin extends BorderPane implements IViewBasic, IViewToPresenter {
    private transient final LoginPresenter presenter;
    private transient final ViewStack viewStack;

    private transient final TextField name=new TextField();
    private transient final TextField surname= new TextField();


    public ViewLogin(LoginPresenter presenter, ViewStack viewStack) {
        this.viewStack = viewStack;
        this.presenter=presenter;
        presenter.setView(this);
        setupLayout();
        initPage();
    }
    private void setupLayout(){
        name.setPrefWidth(10);
        name.setMaxSize(200, 20);
        surname.setPrefWidth(10);
        surname.setMaxSize(200, 20);
    }


    private void initPage() {
        initValues();
        setCenterPanel();
    }

    private void initValues() {
        name.setText("");
        surname.setText("");

    }

    private void setCenterPanel() {
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(15);
        center.setPadding(new Insets(-10, 10, 10, 10));

        Button button1 = new Button("Connect");
        button1.setOnAction(action -> checkValues());

        center.getChildren().addAll(
                new Label("Name"),
                this.name,
                new Label("Surname"),
                this.surname,
                button1);

        this.setCenter(center);
    }

    public BorderPane getRoot() {
        return this;
    }



    private void checkValues() {
        if (!this.name.getText().isBlank() &&!this.surname.getText().isBlank()) {
            String newName = this.name.getText().trim().toLowerCase(Locale.ENGLISH);
            String newSurname = this.surname.getText().trim().toLowerCase(Locale.ENGLISH);

            this.presenter.checkLogin(newName, newSurname);
        }
    }

    @Override
    public void showError(String e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e);
        alert.showAndWait();
    }

    @Override
    public void onEditAuthor(String authorName) {
    }

    @Override
    public void setVisible() {
        initValues();
        viewStack.goTo(this);
    }


}
