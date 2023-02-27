/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DELL
 */
public class courseDTO {

    private int courseID, credits, department;
    private String coureTitle ;

    public courseDTO() {
    }

    public courseDTO(String coureTitle, int department, int courseID, int credits) {
        this.coureTitle = coureTitle;
        this.department = department;
        this.courseID = courseID;
        this.credits = credits;
    }

    public String getCoureTitle() {
        return coureTitle;
    }

    public int getDepartment() {
        return department;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getCredits() {
        return credits;
    }

    public void setCoureTitle(String coureTitle) {
        this.coureTitle = coureTitle;
    }


    public void setDepartment(int department) {
        this.department = department;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

}
