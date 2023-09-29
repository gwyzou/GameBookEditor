package org.helmo.gbeditor.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.helmo.gbeditor.presenters.MainPagePresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewBasic;
import org.helmo.gbeditor.presenters.interfaces.IViewToPresenter;

public class ViewMainPage extends BorderPane implements IViewBasic, IViewToPresenter {
    private transient final MainPagePresenter presenter;
    private transient final ViewStack viewStack;
    private transient final GridPane gridPane=new GridPane();
    private transient final ObservableList<ItemBook> oList = FXCollections.observableArrayList();
    private transient final ListView<ItemBook> listView = new ListView<>();

    private transient final ObservableList<ItemBook> oListPublished = FXCollections.observableArrayList();
    private transient final ListView<ItemBook> listViewPublished = new ListView<>();


    public ViewMainPage(MainPagePresenter presenter, ViewStack viewStack) {
        this.viewStack = viewStack;
        this.presenter=presenter;
        presenter.setView(this);
        setupLayout();
        initPage();
    }

    private void setupLayout(){
        this.gridPane.setPadding(new Insets(10));
        listView.setItems(oList);
        listView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    ifNotNullCenterPanel(newValue,false);
                });
        listViewPublished.setItems(oListPublished);
        listViewPublished.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    ifNotNullCenterPanel(newValue,true);
                });
    }
    private void initPage() {
        setFooter();
        initCenter();
    }
    private void initCenter(){
        HBox hBox=new HBox();
        VBox vBox=new VBox(new Label("Published Book List"),listViewPublished ,new Label("Editable Book List"),listView);
        hBox.getChildren().addAll(vBox,gridPane);
        this.setCenter(hBox);
    }

    private void setGridPaneCenterLayout(ItemBook itemBook,Boolean isPublished){
        gridPane.getChildren().clear();

        Label title=new Label(itemBook.getTitle());
        setCenterGridPaneLabelLayout(title);

        Label author=new Label(itemBook.getAuthor());
        setCenterGridPaneLabelLayout(author);

        Label resume=new Label(itemBook.getResume());
        setCenterGridPaneLabelLayout(resume);

        gridPane.addRow(0,new Label("ISBN :"),new Label(itemBook.getIsbn()));
        gridPane.addRow(1,new Label("Author :"),author);
        gridPane.addRow(2,new Label("Title :"),title);
        gridPane.addRow(3,new Label("Resume :"),resume);
        if(!isPublished) {
            Button edit = new Button("edit");
            edit.setOnAction(e -> presenter.editBook(itemBook));
            Button publish = new Button("publish");
            publish.setOnAction(e -> presenter.publishBook(itemBook));
            gridPane.addRow(4, edit, publish);
        }
    }
    private void ifNotNullCenterPanel(ItemBook itemBook,Boolean isPublished){
        if(itemBook !=null){
            setGridPaneCenterLayout(itemBook,isPublished);
        }
    }
    private void setCenterGridPaneLabelLayout(Label label){
        label.setWrapText(true);
        label.setMaxWidth(200);
    }

    private void setHeader(String authorName) {
        //Label Name
        this.setTop(null);

        Label author = new Label(authorName);

        //Disconnect button
        Button disconnect = new Button("Disconnect");
        disconnect.setOnAction(action -> presenter.disconnect());

        HBox hHeader = new HBox(author, disconnect);
        hHeader.setAlignment(Pos.BASELINE_RIGHT);
        hHeader.setSpacing(15);
        hHeader.setPadding(new Insets(10, 10, 10, 10));

        VBox header = new VBox(hHeader, new Separator());
        this.setTop(header);
    }

    private void setCenterPanel() {
        oList.clear();
        presenter.getBookList().forEach(oList::add);
        oListPublished.clear();
        presenter.getPublishedBook().forEach(oListPublished::add);
    }


    private void setFooter() {

        //AddBook button
        Button addBook = new Button("Add Book");
        addBook.setOnAction(action -> presenter.editBook(null));
        HBox footer = new HBox(addBook);
        setFooterLayout(footer);
        this.setBottom(footer);
    }
    private void setFooterLayout(HBox footer){
        footer.setAlignment(Pos.BASELINE_RIGHT);
        footer.setSpacing(15);
        footer.setPadding(new Insets(0, 10, 10, 10));
    }

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
        gridPane.getChildren().clear();
        setCenterPanel();
        viewStack.goTo(this);
    }

}
