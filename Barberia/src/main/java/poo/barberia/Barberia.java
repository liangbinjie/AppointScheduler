package poo.barberia;

import poo.view.Dashboard;

public class Barberia {

    public static void main(String[] args) {
        AppointScheduler control = AppointScheduler.getInstance();
        Dashboard v = new Dashboard();
        v.setVisible(true);
    }
}
