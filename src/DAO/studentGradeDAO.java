/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.studentGradeDTO;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author DELL
 */
public class studentGradeDAO {
    myConnectUnit connectUnit = new myConnectUnit("localhost", "root", "", "school");
    public  studentGradeDAO(){
        
    }
    
    public  ArrayList<studentGradeDTO> List(String condition, String orderBy) throws Exception{
        ResultSet result = this.connectUnit.Select("studentgrade", condition, orderBy);
        ArrayList<studentGradeDTO> studentGrades = new ArrayList<>();
        while(result.next()){
            studentGradeDTO studentGrade = new studentGradeDTO();
            studentGrade.setEnrollmentID(result.getInt("EnrollmentID"));
            studentGrade.setCourseID(result.getInt("CourseID"));
            studentGrade.setStudenID(result.getInt("StudentID"));
            studentGrade.setGrade(result.getFloat("Grade"));
            studentGrades.add(studentGrade);
        }
        return studentGrades;        
    }
    
    public ArrayList<studentGradeDTO> List(String condition) throws Exception{
        return List(condition,null);
    }
    
    public ArrayList<studentGradeDTO> List() throws Exception{
        return  List(null);
    }
    
   public ArrayList<studentGradeDTO> getAll() throws Exception{
        ResultSet result = this.connectUnit.Select("studentgrade");
        ArrayList<studentGradeDTO> studentGrades = new ArrayList<>();
        while(result.next()){
            studentGradeDTO studentGrade = new studentGradeDTO();
            studentGrade.setEnrollmentID(result.getInt("EnrollmentID"));
            studentGrade.setCourseID(result.getInt("CourseID"));
            studentGrade.setStudenID(result.getInt("StudentID"));
            studentGrade.setGrade(result.getFloat("Grade"));
            studentGrades.add(studentGrade);

        }
//            System.out.println(studentGrades.get(0).toString());  //Chạy thử toString của studenGrades
        return studentGrades; 
   }
   
   // function Add
   public Boolean add(studentGradeDTO std) throws Exception{
       HashMap<String, Object> insertValues = new HashMap<>();
       insertValues.put("EnrollmentID", std.getEnrollmentID());
       insertValues.put("CourseID", std.getCourseID());
       insertValues.put("StudentID", std.getStudenID());
       insertValues.put("Grade", std.getGrade());
       
       Boolean check = connectUnit.Insert("studentgrade", insertValues);
       return  check;
   }
   
   public Boolean addKhongCanID(studentGradeDTO std) throws Exception{
       HashMap<String, Object> insertValues = new HashMap<>();
       insertValues.put("CourseID", std.getCourseID());
       insertValues.put("StudentID", std.getStudenID());
       insertValues.put("Grade", std.getGrade());
       
       Boolean check = connectUnit.Insert("studentgrade", insertValues);
       return  check;
   }
   //Function delete
   public Boolean delete(studentGradeDTO std) throws Exception{
        // access courseID
        String condition = " EnrollmentID = '" + std.getEnrollmentID()+"'";
        
        Boolean check = connectUnit.Delete("studentgrade", condition);
        
        return check;
    }
   
   //Function edit
   public Boolean edit(studentGradeDTO std) throws Exception{
       HashMap<String, Object> insertValues = new HashMap<>();
       insertValues.put("CourseID", std.getCourseID());
       insertValues.put("StudenID", std.getStudenID());
       insertValues.put("Grade", std.getGrade());
       
       System.out.println(std.toString());
        
       String condition = " EnrollmentID = '" + std.getEnrollmentID() + "'";
       
       Boolean check = connectUnit.Update("studentgrade", insertValues, condition);
       
       return check;

   }
}
