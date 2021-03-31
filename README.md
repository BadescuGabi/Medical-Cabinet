<h1>Medical office platform</h1>

This is a project for Advanced Object-Oriented Programming course using Java. It simulates an Medical Office paltform containing information about appointments,patients,doctors and nurses.

I implemented the following classes:

* **MedicalOffice** (Singleton class) which contains:<br/>
-all persons that are associated with office
<br/>-all appointments <br/>
-blood stuck<br/>
They are stored in lists and hashmaps.

* **Person** (Abstract class) which inheritance the following classes:
    * **Patient**
    * **Doctor** 
    * **Nurse**
<br/>
* **PersonTypeCriteria** is a generic class and implements interface **Criteria** and it's used for returning a specific type of list (with doctors,persons or nurses) 

* **Appointment** (Abstract class) and has abstract method for calculating price and inheritance the following classes:
    * **BloodTransfusion**
    * **Vaccine**
    * **Ultrasound**
    
* **Service** which contains the functionalities of project