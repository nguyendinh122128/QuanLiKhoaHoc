/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DELL
 */
public class personDTO {
    int personID;
    String lastName, firstName, hireDate, enrollmentDate;

    public personDTO() {
    }

    public personDTO(int personID, String lastName, String firstName, String hireDate, String enrollmentDate) {
        this.personID = personID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.hireDate = hireDate;
        this.enrollmentDate = enrollmentDate;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    
    public int getPersonID() {
        return personID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public String toString() {
        return "personDTO{" + "personID=" + personID + ", lastName=" + lastName + ", firstName=" + firstName + ", hireDate=" + hireDate + ", enrollmentDate=" + enrollmentDate + '}';
    }

    
    
    
}
