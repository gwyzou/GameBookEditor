package org.helmo.gbeditor.views;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.helmo.gbeditor.presenters.interfaces.IViewBasic;

public class ViewStack extends StackPane {
    private transient IViewBasic currentIViewBasic;

    public void setView(IViewBasic IViewBasic){
        this.getChildren().add(IViewBasic.getRoot());
        IViewBasic.getRoot().setVisible(false);

    }
    public void setFirstView(IViewBasic IViewBasic){
        if(IViewBasic !=null){
            currentIViewBasic = IViewBasic;
            goTo(IViewBasic);
        }
    }
    public void goTo(IViewBasic iViewBasic){
        if(currentIViewBasic !=null && iViewBasic!=null){
            currentIViewBasic.getRoot().setVisible(false);
            currentIViewBasic = iViewBasic;
            currentIViewBasic.getRoot().setVisible(true);
            currentIViewBasic.getRoot().toFront();
        }
    }

    public Parent getRoot(){
        return this;
    }

}
