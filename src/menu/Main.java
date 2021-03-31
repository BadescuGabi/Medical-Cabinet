package menu;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        Service service = Service.getInstance();
        Service.menu();
    }
}
