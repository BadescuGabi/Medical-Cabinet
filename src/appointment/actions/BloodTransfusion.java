package appointment.actions;

import appointment.Appointment;
import person.enums_and_salary.BloodGroup;
import person.type.Doctor;
import person.type.Nurse;
import person.type.Patient;

import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

import static medical_office.MedicalOffice.*;

public class BloodTransfusion extends Appointment {
    private Nurse nurse;

    public BloodTransfusion(String date, Patient patient, Doctor doctor,  int duration, Nurse nurse) throws ParseException {
        super(date, patient, doctor,0, duration);
        this.nurse = nurse;
    }

    public BloodTransfusion() throws ParseException {
        System.out.println("\nChoose nurse by corresponding index:\n");
        for (int i = 0; i < nurses.size(); i++)
            System.out.println(nurses.get(i).getId() + ":\n" + (nurses.get(i)).toString());
        Scanner in = new Scanner(System.in);
        int op = in.nextInt();
        for (int i = 0; i < nurses.size(); i++)
            if (nurses.get(i).getId() == op) {
                nurse = nurses.get(i);
                break;
            }

        System.out.println("\ndonnation/receive?");
        String s ="";
        s=in.next();
        if (s.equalsIgnoreCase("donnation")){
            donnation();;
        }
        if(s.equalsIgnoreCase("receive")){
            receive();
        }
    }

    public Nurse getNurse() {
        return nurse;
    }

    public BloodTransfusion setNurse(Nurse nurse) {
        this.nurse = nurse;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BloodTransfusion)) return false;
        if (!super.equals(o)) return false;
        BloodTransfusion that = (BloodTransfusion) o;
        return Objects.equals(nurse, that.nurse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nurse);
    }

    @Override
    public String toString() {
        return "BloodTransfusion{" +
                "nurse=" + nurse +
                "} " + super.toString();
    }

    @Override
    public int calculatePrice() {
        return 0;
    }

    public void donnation() {

        if (patient.getBloodGroup().equals(BloodGroup.ZERO)) {//posibil sa fie nevoie de ==
            bloodStock.put(BloodGroup.ZERO, (bloodStock.get(BloodGroup.ZERO) + 0.5));
            bloodStock.put(BloodGroup.A, (bloodStock.get(BloodGroup.A) + 0.5));
            bloodStock.put(BloodGroup.AB, (bloodStock.get(BloodGroup.AB) + 0.5));
            bloodStock.put(BloodGroup.B, (bloodStock.get(BloodGroup.B) + 0.5));
        }
        if (patient.getBloodGroup().equals(BloodGroup.A)) {//posibil sa fie nevoie de ==
            bloodStock.put(BloodGroup.A, (bloodStock.get(BloodGroup.A) + 0.5));
            bloodStock.put(BloodGroup.AB, (bloodStock.get(BloodGroup.AB) + 0.5));
        }
        if (patient.getBloodGroup().equals(BloodGroup.B)) {//posibil sa fie nevoie de ==
            bloodStock.put(BloodGroup.AB, (bloodStock.get(BloodGroup.AB) + 0.5));
            bloodStock.put(BloodGroup.B, (bloodStock.get(BloodGroup.B) + 0.5));
        }
        if (patient.getBloodGroup().equals(BloodGroup.AB)) {//posibil sa fie nevoie de ==
            bloodStock.put(BloodGroup.AB, (bloodStock.get(BloodGroup.AB) + 0.5));
        }
        patient.setDonate(patient.getDonate() + 1);
    }

    public void receive() {
        if (patient.getBloodGroup().equals(BloodGroup.ZERO)) {//posibil sa fie nevoie de ==
            bloodStock.put(BloodGroup.ZERO, (bloodStock.get(BloodGroup.ZERO) - 0.5));
            bloodStock.put(BloodGroup.A, (bloodStock.get(BloodGroup.A) - 0.5));
            bloodStock.put(BloodGroup.AB, (bloodStock.get(BloodGroup.AB) - 0.5));
            bloodStock.put(BloodGroup.B, (bloodStock.get(BloodGroup.B) - 0.5));
        }
        if (patient.getBloodGroup().equals(BloodGroup.A)) {//posibil sa fie nevoie de ==
            bloodStock.put(BloodGroup.A, (bloodStock.get(BloodGroup.A) - 0.5));
            bloodStock.put(BloodGroup.AB, (bloodStock.get(BloodGroup.AB) - 0.5));
        }
        if (patient.getBloodGroup().equals(BloodGroup.B)) {//posibil sa fie nevoie de ==
            bloodStock.put(BloodGroup.AB, (bloodStock.get(BloodGroup.AB) - 0.5));
            bloodStock.put(BloodGroup.B, (bloodStock.get(BloodGroup.B) - 0.5));
        }
        if (patient.getBloodGroup().equals(BloodGroup.AB)) {//posibil sa fie nevoie de ==
            bloodStock.put(BloodGroup.AB, (bloodStock.get(BloodGroup.AB) - 0.5));
        }
    }

}

