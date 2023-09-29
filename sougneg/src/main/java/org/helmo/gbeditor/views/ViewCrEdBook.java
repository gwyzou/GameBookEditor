package org.helmo.gbeditor.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.helmo.gbeditor.presenters.CreateBookPresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewBasic;
import org.helmo.gbeditor.presenters.interfaces.IViewToCreateBookPresenter;


public class ViewCrEdBook extends BorderPane implements IViewBasic, IViewToCreateBookPresenter {
    private transient final CreateBookPresenter presenter;
    private transient final ViewStack viewStack;
    private transient final HBox footerBookData = new HBox();
    private transient final TextField name = new TextField();
    private transient final TextField language = new TextField();
    private transient final TextField bookNumber = new TextField();
    private transient final TextArea resume = new TextArea();
    private transient final GridPane centerBookData= new GridPane();




    public ViewCrEdBook(CreateBookPresenter createBookPresenter, ViewStack viewStack) {
        this.viewStack = viewStack;
        presenter=createBookPresenter;
        presenter.setView(this);
        setupProperty();
        setFooterBook();
    }
    private void setupProperty(){
        setupFooter();
        setupName();
        setupLang();
        setupBookNbr();
        setupResume();
        setupCenterBookData();
    }
    private void setupFooter(){
        footerBookData.setAlignment(Pos.BASELINE_RIGHT);
        footerBookData.setPadding(new Insets(10, 10, 10, 10));
    }
    private void setupName(){
        name.setPromptText("Book Title (150char max)");
    }
    private void setupLang(){
        language.setPromptText("#");
        language.setTooltip(new Tooltip("Enter number between 0 - 9"));
        language.setMaxWidth(50);
    }
    private void setupBookNbr(){
        bookNumber.setPromptText("##");
        bookNumber.setTooltip(new Tooltip("Enter number between 0 - 99"));
        bookNumber.setMaxWidth(50);
    }
    private void setupResume() {
        resume.setPromptText("Book Resume (500char max)");
    }
    private void setupCenterBookData() {
        centerBookData.setAlignment(Pos.TOP_CENTER);
        centerBookData.setHgap(10);
        centerBookData.setVgap(10);
        centerBookData.setPadding(new Insets(10, 10, 10, 10));
        centerBookData.addRow(1, new Label("Title"), new Label("Language"),new Label("Book Number"));
        centerBookData.addRow(2, name, language,bookNumber);
        centerBookData.add(new Label("Resume"), 0, 3);
        centerBookData.add(resume, 0, 4, 3, 4);
    }
    public void setViewPage(ViewPage viewPage){
        ScrollPane scrollPane = new ScrollPane(new VBox(centerBookData, viewPage));
        this.setCenter(scrollPane);
    }

    private void setHeader(String authorName) {
        VBox vHeader = new VBox();
        HBox hHeader = new HBox();

        setHeaderLayout(vHeader,hHeader);

        //Disconnect button
        Button disconnect = new Button("Disconnect");
        disconnect.setOnAction(action -> presenter.disconnect());

        hHeader.getChildren().addAll(new Label(authorName), disconnect);

        vHeader.getChildren().addAll(hHeader, new Separator());

        this.setTop(vHeader);
    }
    private void setHeaderLayout(VBox vBox,HBox hBox){
        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        hBox.setSpacing(15);
        hBox.setPadding(new Insets(10, 10, 10, 10));
    }

    @Override
    public void setCenterBookDataPanel(ItemBook itemBook){
        if(itemBook !=null){
            name.setText(itemBook.getTitle());
            language.setText(itemBook.getLanguage());
            bookNumber.setText(itemBook.getBookNumber());
            resume.setText(itemBook.getResume());
        }
    }
    private void initValues(){
        name.setText("");
        language.setText("");
        bookNumber.setText("");
        resume.setText("");
    }


    private void setFooterBook() {
        footerBookData.getChildren().clear();
        Button next = new Button("confirm");
        next.setOnAction(action ->{
            presenter.addBook(name.getText(), language.getText(),bookNumber.getText(),resume.getText());
        });
        Button previous = new Button("Go Back");
        previous.setOnAction(action->presenter.goBack());
        footerBookData.getChildren().addAll(previous,next);
        this.setBottom(footerBookData);
    }

    @Override
    public BorderPane getRoot() {
        return this;
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
        setHeader(authorName);
    }

    @Override
    public void setVisible() {
        viewStack.goTo(this);
        initValues();
    }

}