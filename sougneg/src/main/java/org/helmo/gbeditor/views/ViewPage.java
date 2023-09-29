package org.helmo.gbeditor.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.helmo.gbeditor.presenters.PagePresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewBasic;
import org.helmo.gbeditor.presenters.interfaces.IViewToPagePresenter;

import java.util.Collection;
import java.util.Optional;

public class ViewPage extends BorderPane implements IViewBasic, IViewToPagePresenter {
    private transient final ObservableList<ItemPage> oList = FXCollections.observableArrayList();
    private transient final ListView<ItemPage> listView = new ListView<>();
    private transient final Button addPage = new Button("add new page");
    private transient final Button deletePage = new Button("Delete page");
    private transient final TextField deletePageField=new TextField();
    private transient final Dialog<String> deletePageDialog = new Dialog<>();
    private transient final TextArea rightTextArea = new TextArea();
    private transient final VBox rightScroll = new VBox();
    private transient final Button addLink = new Button("Add choice");
    private transient final BorderPane rightSide = new BorderPane();
    private transient final TextArea dialogArea = new TextArea();
    private transient final TextField dialogField =new TextField();
    private transient final Dialog<String[]> newPageDialog = new Dialog<>();
    private transient final TextArea dialogAreaChoice = new TextArea();
    private transient final TextField dialogFieldChoice =new TextField();
    private transient final Dialog<String[]> newChoiceDialog = new Dialog<>();
    private transient final Label deletePageConfirmationLabel=new Label();
    private transient final Dialog<String> deletePageConfirmation = new Dialog<>();
    private transient final PagePresenter presenter;

    public static final String NBR_OF_PAGE="Number of the page";
    public static final int MIN_CHOOSE_NBR=2;

    public ViewPage(PagePresenter presenter){
        setupLayout();
        this.setLeft(listView);
        this.setCenter(rightSide);
        this.setBottom(new HBox(addPage,deletePage));
        this.presenter = presenter;
        presenter.setView(this);
    }
    private void setupLayout(){
        setupListView();
        addPage.setOnAction(e->addNewPage());
        deletePage.setOnAction(e->selectDeletePage());
        setupDeleteDialog();
        setupConfirmationDeletePage();
        setupRightSide();

    }
    private void setupListView(){
        listView.setItems(oList);
        listView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    setFocusPage(newValue);
                });
    }
    private  void setupDeleteDialog(){
        deletePageDialog.setTitle("Delete Page");
        DialogPane dialogPane = deletePageDialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.CANCEL,ButtonType.OK);
        dialogPane.setContent(new VBox(new Label(NBR_OF_PAGE) ,deletePageField));
        deletePageDialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return deletePageField.getText();
            }
            return null;
        });
    }
    private void setupConfirmationDeletePage(){
        deletePageConfirmation.setTitle("Confirm Delete Page");
        deletePageConfirmation.setHeaderText("Please put which page you want to delete\n Be aware, each choice linked to this page will be deleted");
        DialogPane dialogPane = deletePageConfirmation.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.CANCEL,ButtonType.OK);
        deletePageConfirmation.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return deletePageField.getText();
            }
            return null;
        });
    }
    private void setupAddLink(){
        addLink.setOnAction(e->addNewChoice() );
        addLink.setDisable(true);
    }
    private void setupRightSide(){
        rightTextArea.setDisable(true);
        rightSide.setCenter(new ScrollPane(rightScroll));
        rightSide.setBottom(addLink);
        dialogArea.setPromptText("Text Of the Page");
        setupDialogAddPage();
        setupAddLink();
        setupDialogChoice();
    }
    private void setupDialogAddPage(){
        dialogField.setPromptText(NBR_OF_PAGE);
        dialogField.setTooltip(new Tooltip("if not filled or is not a number the page will be place in last place of the book"));
        newPageDialog.setTitle("Add new Page");
        newPageDialog.setHeaderText("Please put wanted text and page number in the following");
        DialogPane dialogPane = newPageDialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.CANCEL,ButtonType.OK);
        dialogPane.setContent(new VBox(8,new Label("Text Of the Page") ,dialogArea,new Label(NBR_OF_PAGE) ,dialogField));
        newPageDialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new String[]{dialogArea.getText(), dialogField.getText()};
            }
            return null;
        });
    }
    private void setupDialogChoice(){
        dialogArea.setPromptText("Text Of the Page");
        dialogField.setPromptText(NBR_OF_PAGE);
        dialogField.setTooltip(new Tooltip("if not filled or is not a number the page will be place in last place of the book"));
        newChoiceDialog.setTitle("Add new Choice");
        newChoiceDialog.setHeaderText("Please put wanted text and page number in the following");
        DialogPane dialogPane = newChoiceDialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.CANCEL,ButtonType.OK);
        dialogPane.setContent(new VBox(8,new Label("Text Of the Choice") ,dialogAreaChoice,new Label(NBR_OF_PAGE) ,dialogFieldChoice));
        newChoiceDialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new String[]{dialogAreaChoice.getText(), dialogFieldChoice.getText()};
            }
            return null;
        });
    }
    @Override
    public void setList(Collection<ItemPage> pageList,int autoFocus){
        restartValues();
        oList.clear();
        if(pageList!=null){
            oList.addAll(pageList);
        }
        listView.refresh();
        if(canFocus(autoFocus)){
            setFocusPage(oList.get(autoFocus));
        }
    }
    private boolean canFocus(int i){
        return i>=0 && i< oList.size();
    }
    private void setFocusPage(ItemPage newValue) {
        restartValues();
        if(newValue!=null){
            rightTextArea.setText(newValue.getText());
            addLink.setDisable(true);
            if(oList.size()>=MIN_CHOOSE_NBR){
                addLink.setDisable(false);
                newValue.getChoices().forEach(this::createMiniChoice);
            }
        }
    }
    private void restartValues(){
        rightScroll.getChildren().clear();
        rightTextArea.setText("");
        rightScroll.getChildren().add(rightTextArea);
    }
    private void createMiniChoice(ItemChoice choice){
        Optional<ItemPage> result =oList.stream().filter(page-> page.getPageNumber() == choice.getPageChoice().getPageNumber()).findFirst();
        if(result.isPresent()){
            ItemPage pageSearched = result.get();
            Button delete=new Button("Remove link");
            delete.setOnAction(e->deleteChoice(choice));
             rightScroll.getChildren().add(new VBox(
                     new HBox(new Label("Choice text"),new Label(choice.getTextChoice())),
                     new HBox(new Label("Linked To"),new Label(Integer.toString(pageSearched.getPageNumber()))),
                     delete
                     ));
        }
    }
    private void addNewPage(){
        restartDialog();
        addEditNewPage();
    }
    private void restartDialog(){
        dialogArea.setText("");
        dialogField.setText("");
    }
    private void addEditNewPage(){
        Optional<String[]> inputStrings = newPageDialog.showAndWait();
        if(inputStrings.isPresent()){
            if(presenter.newInputIsCorrectValue(inputStrings.get()[0])){
                presenter.addCorrectedNewPage(inputStrings.get()[0],inputStrings.get()[1]);
                listView.refresh();
            }else{
                addEditNewPage();
            }
        }
    }



    private void selectDeletePage() {
        restartDeletePeDialog();
        Optional<String> inputStrings = deletePageDialog.showAndWait();
        if(inputStrings.isPresent()){
            String input = inputStrings.get().trim();
            if(presenter.inRangeDeletePage(input)){
                confirmDeletePage(Integer.parseInt(input));
            }
        }
    }
    private void confirmDeletePage(int pageNbr){
        deletePageConfirmation.setHeaderText("Are you sure, there is "+presenter.calculLinkToPage(pageNbr)+" linked to this page");
        Optional<String> inputStrings = deletePageConfirmation.showAndWait();
        if(inputStrings.isPresent()){
            presenter.deletePage(pageNbr);
            if(oList.size()<MIN_CHOOSE_NBR){
                addLink.setDisable(true);
            }
        }
    }
    private void restartDeletePeDialog(){
        deletePageField.setText("");
    }

    private void addNewChoice() {
        restartDialogChoice();
        addEditChoice();
    }
    private void restartDialogChoice(){
        dialogFieldChoice.setText("");
        dialogAreaChoice.setText("");
    }
    private void addEditChoice(){
        Optional<String[]> inputStrings=newChoiceDialog.showAndWait();
        if(inputStrings.isPresent()){
            if(presenter.newInputChoice(inputStrings.get()[0],inputStrings.get()[1], listView.getFocusModel().getFocusedItem().getPageNumber())){
                presenter.addCorrectedNewChoice(inputStrings.get()[0],Integer.parseInt(inputStrings.get()[1].trim()),listView.getFocusModel().getFocusedItem());
            }else{
                addEditChoice();
            }
        }
    }
    private void deleteChoice(ItemChoice choice) {
        ItemPage itemPage=listView.getFocusModel().getFocusedItem();
        presenter.deleteChoice(itemPage,choice);
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
    public BorderPane getRoot() {
        return this;
    }

    @Override
    public void onEditAuthor(String authorName) {
    }

    @Override
    public void setVisible() {
    }
    //void setCenterPageDataPanel(List<ItemPage> pageList);
}
