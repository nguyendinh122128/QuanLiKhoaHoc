/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.courseDAO;
import DAO.personDAO;
import DAO.studentGradeDAO;
import DTO.courseDTO;
import DTO.personDTO;
import DTO.studentGradeDTO;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class studentGradeBUS {
    // Tạo 1 arr đựng value
    private ArrayList<studentGradeDTO> arr_studentGrades;
    
    private studentGradeDAO studentGradeDAO = new studentGradeDAO();
    
    public studentGradeBUS(){
        arr_studentGrades = new ArrayList<>();
        studentGradeDAO = new studentGradeDAO();
        try {
            arr_studentGrades = studentGradeDAO.getAll();
        } catch (Exception e) {
          System.out.println("Lỗi: " + e.getMessage());

        }
    }
    
    // Get all value
    public  ArrayList<studentGradeDTO> getStudentGradesAll() throws Exception{
        ArrayList<studentGradeDTO> listStudenGrades = studentGradeDAO.getAll();
        return listStudenGrades;
    }
    
    //Add value
    
    public Boolean add(studentGradeDTO std) throws Exception{
        if(studentGradeDAO.addKhongCanID(std) ){
            arr_studentGrades.add(std);
        }
        return false;
    }
    
    //Delete  value
    public Boolean delete(studentGradeDTO std) throws Exception{
        if(studentGradeDAO.delete(std) ){
            for(studentGradeDTO studentGrade : arr_studentGrades){
                if(studentGrade.getEnrollmentID() == std.getEnrollmentID()){
                    arr_studentGrades.remove(std);
                    break;
                }
            }
        }
        return false;
    }
    
    //Edit
    public Boolean edit(studentGradeDTO std) throws Exception {
        if ( studentGradeDAO.edit(std) ) {
            for ( studentGradeDTO studentGrade : arr_studentGrades ) {
                if (studentGrade.getEnrollmentID() == std.getEnrollmentID() ){
                    break;
                }
            }
        }
        
        return false;
    }
    
    //Tìm kiếm nhiều
    public ArrayList<studentGradeDTO> getStudentGradeFind(String studentID_find){ //Tìm kiếm courses nhiều
        
        ArrayList<studentGradeDTO> list_studentGrade_find =  new ArrayList<>();
        
        
        for(studentGradeDTO studentGrade : arr_studentGrades){
            
            String studentID_string = Integer.toString(studentGrade.getStudenID()); // Chuyền id của studentGrade thành string
            
            if(studentID_string.contains(studentID_find)){     // Tìm kiếm chuỗi con có trong chuỗi cha không trả về true
                
                list_studentGrade_find.add(studentGrade);
                
            }
        }
                return list_studentGrade_find;
    }
    
    // Lấy title của course cho việc thêm mới ở addNewStudentGrade
    
    public String getTitleCourse(int courseID){
        courseDAO crDAO = new courseDAO();
        try {
            ArrayList<courseDTO> arr_Courses = crDAO.getAll();
            for(courseDTO course : arr_Courses){
                if(courseID == course.getCourseID())
                    return (course.getCoureTitle() + "");
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    // Lấy nameStudent cho việc thêm mới ở addNewStudentGrade
    public  String getNameStudent(int studentID){
        personDAO psDAO = new personDAO();
        try {
            ArrayList<personDTO> arr_person = psDAO.getAll();
            for(personDTO person : arr_person){
                if(studentID == person.getPersonID())
                    return person.getFirstName() + " " + person.getLastName();
            }
        } catch (Exception e) {
        }
        
        
        return null;
    }
}
