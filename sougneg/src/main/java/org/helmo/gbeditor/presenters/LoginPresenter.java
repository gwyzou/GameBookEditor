package org.helmo.gbeditor.presenters;
import org.helmo.gbeditor.repositories.IRepository;
import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.views.interfaces.IBasicPresenter;
import org.helmo.gbeditor.presenters.interfaces.IViewToPresenter;

import java.util.Locale;

/**
 * presenter associated to LoginView
 */
public class LoginPresenter implements IBasicPresenter {
    private transient IViewToPresenter loginView;
    private transient final MainPresenter mainPresenter;
    private transient final IRepository repository;

    /**
     * constructor
     * @param mainPresenter
     */
    public LoginPresenter(IRepository repository, MainPresenter mainPresenter) {
        this.mainPresenter=mainPresenter;
        this.repository=repository;
        mainPresenter.addPresenter(EnumPresentersNames.LOGIN_PAGE_PRESENTER,this);
    }

    /**
     * Create author
     * and go to next page if success else tell view isError
     * @param nameUser
     * @param surnameUser
     */
    public void checkLogin(String nameUser, String surnameUser){

        try{
            Author author  = new Author(nameUser.toLowerCase(Locale.ENGLISH), surnameUser.toLowerCase(Locale.ENGLISH));
            try{
                repository.getMatricule(author);
                mainPresenter.setAuthor(author);
                mainPresenter.goTo(EnumPresentersNames.MAIN_PAGE_PRESENTER);
            }catch (RuntimeException e){
                loginView.showError("Unable to connect to the data Base");
            }
        }catch (IllegalArgumentException e){
            loginView.showError(e.getMessage());
        }
    }

    @Override
    public void onEditAuthor() {
    }

    @Override
    public void onDataReceived(Book book) {
    }

    @Override
    public void setView(IViewToPresenter basicView) {
        loginView=basicView;
    }

    @Override
    public void setViewVisible() {
        loginView.setVisible();
    }
}
