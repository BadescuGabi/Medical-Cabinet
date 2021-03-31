package meniu;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Service service = Service.getInstance();
        Service.menu();
    }
}
