package menu;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        Menu menu = Menu.getInstance();
        Menu.menu();
    }
}
