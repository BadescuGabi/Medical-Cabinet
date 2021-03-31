package appointment.actions;

import appointment.Appointment;

import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

import static medical_office.MedicalOffice.vaccinatedPatients;

public class Vaccine extends Appointment {
    private Double covidAntibody;

    public Vaccine(Double covidAntibody) throws ParseException {
        super();
        this.covidAntibody = covidAntibody;
    }

    public Vaccine() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nCovid antibody");
        this.covidAntibody = in.nextDouble();
    }

    public Double getCovidAntibody() {
        return covidAntibody;
    }

    public Vaccine setCovidAntibody(Double covidAntibody) {
        this.covidAntibody = covidAntibody;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vaccine)) return false;
        if (!super.equals(o)) return false;
        Vaccine vaccine = (Vaccine) o;
        return covidAntibody.equals(vaccine.covidAntibody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), covidAntibody);
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "covidAntibody=" + covidAntibody +
                "} " + super.toString();
    }

    public String checkCovidAntibody() {
        if (getCovidAntibody() < 2) {
            return "Person unvaccinated";
        }
        if (getCovidAntibody() < 2 && getPatient().getAge() > 55) {
            return " and must do vaccin immediately";
        }

        if (getCovidAntibody() > 7) {
            return "Person was vaccinated or has recent Covid";
        }
        return "";
    }

    public int calculatePrice() {
        if (getCovidAntibody() > 6) {
            price = 0;
        } else {
            price = 200;
            vaccinatedPatients.add(patient);
            setCovidAntibody(10.0);
        }
        return price;
    }

}
