package pl.poznan.put.optilion;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.poznan.put.optilion.dbconnector.Services;
import pl.poznan.put.optilion.files.FileService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start");

        System.out.println("Create database...");
        Services.getSessionFactory();

        System.out.println("Create functions...");
        runSQLfile("resources/sql_views.sql");
        runSQLfile("resources/sql_ranking_functions.sql");

        System.out.println("Init database...");
        new InitDatabase().init();
        System.out.println("Done.");

        System.out.println("Setting ownership");
        new BindOwner().bind();
        System.out.println("Done.");
    }

    public static void runSQLfile(String file) {
        String command = FileService.readFile(file);
        Session session = Services.getSessionFactory().getObject().openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery(command).executeUpdate();
        tx.commit();
        session.close();
    }

}
