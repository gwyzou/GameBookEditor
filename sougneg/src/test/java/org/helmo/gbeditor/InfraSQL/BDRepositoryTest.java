package org.helmo.gbeditor.InfraSQL;

import org.helmo.gbeditor.infrastructure.database.sql.SqlStorage;
import org.helmo.gbeditor.infrastructure.database.sql.SqlStorageFactory;
import org.helmo.gbeditor.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BDRepositoryTest {


    private static final SqlStorageFactory factory = new SqlStorageFactory(
            "org.apache.derby.jdbc.EmbeddedDriver",
            "jdbc:derby:memory:C:/Temp;create=true",
            "",
            ""
    );
    @BeforeEach
    public void setup() throws Exception {
        try(SqlStorage session = factory.newStorageSession()) {
            session.setup();
        }
    }
    @AfterEach
    public void teardown() throws Exception {
        try(SqlStorage session = factory.newStorageSession()) {
            session.tearDown();
        }
    }
    @Test
    void getIDAuhtor() throws Exception {
        //Data sample
        Author author1=new Author("1","1");
        Author author1_2=new Author("1","1");
        Author author2=new Author("2","2");

        try(SqlStorage session = factory.newStorageSession()) {
            assertEquals(1,session.getMat(author1));
            assertEquals(1,session.getMat(author1_2));
            assertEquals(2,session.getMat(author2));
        }
    }
    @Test
    void addPrimaryData() throws Exception {
        //Data sample
        Author author=new Author("1","1");
        Book book = new Book("title","resume",new Isbn("0-000000-00-0"),author);
        Book book2 = new Book("title","resume",new Isbn("0-000000-00-0"),author);
        Book book3 = new Book("title","resume",new Isbn("0-000000-00-0"),author);


        try(SqlStorage session = factory.newStorageSession()) {
            int userId=session.getMat(author);
            assertEquals(1,session.addOrUpdateBook(-1,book,userId));
            assertEquals(2,session.addOrUpdateBook(-1,book2,userId));
            assertEquals(-1,session.addOrUpdateBook(1,book3,userId));
        }
    }
    @Test
    void addPage() throws Exception{
        Author author=new Author("1","1");
        Book book = new Book("title","resume",new Isbn("0-000000-00-0"),author);
        book.addNewPage("tt",1);
        book.addNewPage("tt2",2);

        try(SqlStorage session = factory.newStorageSession()){
            int userId=session.getMat(author);
            //if true idBook == 1
            assertEquals(1,session.addOrUpdateBook(-1,book,userId));
            assertEquals(-1,session.addOrUpdateBook(1,book,userId));
        }
    }
    @Test
    void addChoices() throws Exception{
        Author author=new Author("1","1");
        Book book = new Book("title","resume",new Isbn("0-000000-00-0"),author);
        Page nbr1 = new Page("tt",1,null);
        Page nbr2 = new Page("tt",2,null);
        Page nbr3 = new Page("tt",3,null);
        List<Page> pageList=new ArrayList<>();

        pageList.add(nbr1);
        pageList.add(nbr2);
        pageList.add(nbr3);

        nbr1.addLink(new Choice(nbr2,"tt"));
        nbr1.addLink((new Choice(nbr3,"tt")));
        nbr2.addLink(new Choice(nbr3,"gg"));

        book.setPageList(pageList);

        try(SqlStorage session = factory.newStorageSession()){
            int userId=session.getMat(author);
            //if true idBook == 1
            assertEquals(1,session.addOrUpdateBook(-1,book,userId));
            assertEquals(-1,session.addOrUpdateBook(1,book,userId));
        }
    }
    @Test
    void loadAndPublishBook() throws Exception {
        Author author=new Author("1","1");
        Book book = new Book("title1","resume1",new Isbn("0-000000-00-0"),author);
        Book book2 = new Book("title2","resume2",new Isbn("0-000000-00-0"),author);

        try(SqlStorage session = factory.newStorageSession()){
            int userId=session.getMat(author);
            //if true idBook == 1
            assertEquals(1,session.addOrUpdateBook(-1,book,userId));
            assertEquals(2,session.addOrUpdateBook(-1,book2,userId));

            List<Book> loaded = new ArrayList<>(session.loadBookListFromMat(userId,0).keySet());
            assertEquals(2,loaded.size());
            assertEquals(0,session.loadBookListFromMat(userId,1).size());

            session.publishBook(1);

            assertEquals(1,session.loadBookListFromMat(userId,0).size());
            assertEquals(1,session.loadBookListFromMat(userId,1).size());


        }
    }
    @Test
    void loadFullBook() throws Exception {
        Author author=new Author("1","1");
        Book book = new Book("title","resume",new Isbn("0-000000-00-0"),author);
        Page nbr1 = new Page("tt",1,null);
        Page nbr2 = new Page("tt",2,null);
        Page nbr3 = new Page("tt",3,null);
        List<Page> pageList=new ArrayList<>();

        pageList.add(nbr1);
        pageList.add(nbr2);
        pageList.add(nbr3);

        nbr1.addLink(new Choice(nbr2,"tt"));
        nbr1.addLink((new Choice(nbr3,"tt")));
        nbr2.addLink(new Choice(nbr3,"gg"));

        book.setPageList(pageList);
        try(SqlStorage session = factory.newStorageSession()){
            int userId=session.getMat(author);
            assertEquals(1,session.addOrUpdateBook(-1,book,userId));
            Book get = session.loadFullBook(1);
            Iterator<Choice> iteratorBook = book.getPageList().get(0).getChoices().iterator();
            int pageBook =0;
            while (iteratorBook.hasNext()){
                iteratorBook.next();
                pageBook++;
            }
            Iterator<Choice> iteratorGet = get.getPageList().get(0).getChoices().iterator();
            int pageGet =0;
            while (iteratorGet.hasNext()){
                iteratorGet.next();
                pageGet++;
            }
            assertEquals(pageBook,pageGet);


        }
    }



}
