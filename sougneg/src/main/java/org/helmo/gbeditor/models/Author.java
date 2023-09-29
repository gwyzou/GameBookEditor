package org.helmo.gbeditor.models;

/**
 * represent Author
 */
public class Author {
    private transient String name;
    private transient String surname;


    /**
     * Author constructor
     * throws exceptions if @param empty or null
     * @param nameUser
     * @param surnameUser
     */
    public Author(String nameUser, String surnameUser) {
        checkString(nameUser);
        checkString(surnameUser);
        name=nameUser;
        surname=surnameUser;
    }
    private void checkString(String string){
        if(string==null || string.isBlank()){
            throw new IllegalArgumentException("Both Name and Surname are required");
        }
    }
    @Override
    public String toString(){
        return name+" "+surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    /**
     * return build in matricule
     * @return
     */
    public String getMatricule() {
        return "210310";
    }

    /**
     * check input values and assigne them to property of Author object
     * @param name
     * @param surname
     */
    public void setNewAuthor(String name,String surname){
        checkString(name);
        checkString(surname);
        this.name=name;
        this.surname=surname;
    }
}
