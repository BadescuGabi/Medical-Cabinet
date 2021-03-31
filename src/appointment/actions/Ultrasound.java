package appointment.actions;

import appointment.Appointment;
import person.type.Doctor;
import person.type.Patient;

import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

public class Ultrasound extends Appointment {
    private boolean referral;

    public Ultrasound(String date, Patient patient, Doctor doctor, int price, int duration, boolean referral) throws ParseException {
        super(date, patient, doctor, price, duration);
        this.referral = referral;
    }

    public Ultrasound() throws ParseException {
        super();
        System.out.println("\nHas referal? Y/N");
        Scanner in=new Scanner(System.in);
        String s= in.nextLine();
        this.referral= !s.equalsIgnoreCase("y");

    }

    public boolean isReferral() {
        return referral;
    }

    public Ultrasound setReferral(boolean referral) {
        this.referral = referral;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ultrasound that = (Ultrasound) o;
        return referral == that.referral;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), referral);
    }

    @Override
    public String toString() {
        return "Ultrasound{" +
                "referral=" + referral +
                "} " + super.toString();
    }

    @Override
    public int calculatePrice() {
        if (patient.getAge() < 18) {
            price -= price / 3;
        }
        if (isReferral()) {
            price = 0;
        }
        return price;
    }
}
