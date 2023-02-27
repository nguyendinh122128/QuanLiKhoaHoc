/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DELL
 */
public class studentGradeDTO {
    int enrollmentID, courseID, studenID;
    float grade;

    public studentGradeDTO() {
    }

    public studentGradeDTO(int enrollmentID, int coureseID, int studenID, float grade) {
        this.enrollmentID = enrollmentID;
        this.courseID = coureseID;
        this.studenID = studenID;
        this.grade = grade;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getStudenID() {
        return studenID;
    }

    public float getGrade() {
        return grade;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public void setCourseID(int coureseID) {
        this.courseID = coureseID;
    }

    public void setStudenID(int studenID) {
        this.studenID = studenID;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "studentGradeDTO{" + "enrollmentID=" + enrollmentID + ", coureseID=" + courseID + ", studenID=" + studenID + ", grade=" + grade + '}';
    }
    
    
}
