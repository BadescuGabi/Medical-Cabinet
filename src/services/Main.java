package services;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException, IOException, SQLException {
        String ok = "";
        while (ok != "0") {
            System.out.println("Alegeti optiune:");
            System.out.println("1.Meniu normal");
            System.out.println("2:Meniu c.r.u.d");
            System.out.println("0:iesire program");
            Scanner in = new Scanner(System.in);
            ok = in.nextLine();
            if (ok.equals("1")) {
                Menu menu = Menu.getInstance();
                Menu.menu();
            }
            if (ok.equals("2")) {
                ServiceDB menu = ServiceDB.getInstance();
                ServiceDB.service();
            }
            if (ok.equals("0"))
                break;
            ok = in.nextLine();
        }
    }
}