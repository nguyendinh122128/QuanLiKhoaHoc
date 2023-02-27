/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.courseDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author DELL
 */
public class courseDAO {
    myConnectUnit connectUnit = new myConnectUnit("localhost", "root", "", "school");
    public courseDAO(){
        
    }
    
    public ArrayList<courseDTO> List(String condition, String orderBy) throws Exception{
        ResultSet result = this.connectUnit.Select("course", condition, orderBy);
        ArrayList<courseDTO> courses = new ArrayList<>();
        while (result.next() ) {            
            courseDTO course = new courseDTO();
//            course.setCoureTitle(result.getString("CourseID"));
            course.setCourseID(result.getInt("CourseID "));
            course.setCoureTitle(result.getString("Title"));
            course.setCredits(result.getInt("Credits"));
            course.setDepartment(result.getInt("DepartmentID"));
            courses.add(course);
        }
        return courses;
    }
    public ArrayList<courseDTO> List(String condition) throws Exception {
        return List(condition, null);
    }
    
    public ArrayList<courseDTO> List() throws Exception {
        return List(null);
    }
    
    public ArrayList<courseDTO> getAll() throws Exception{
        ResultSet result = this.connectUnit.Select("course");
        System.out.println("DAO.courseDAO.getAll() oke");
        ArrayList<courseDTO> courses = new ArrayList<>();
        while (result.next() ) {            
            courseDTO course = new courseDTO();
            course.setCourseID(result.getInt("CourseID"));
            course.setCoureTitle(result.getString("Title"));
            course.setCredits(result.getInt("Credits"));
            course.setDepartment(result.getInt("DepartmentID"));
            courses.add(course);
//        System.out.println(" "+ course.getCoureTitle());
        }
        return courses;
    }
    
    //Hàm thêm 
    public Boolean add(courseDTO cr) throws Exception{
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("CourseID", cr.getCourseID());
        insertValues.put("Title", cr.getCoureTitle());
        insertValues.put("Credits",cr.getCredits());
        insertValues.put("DepartmentID", cr.getDepartment());
        
        Boolean check = connectUnit.Insert("course", insertValues);
        return check;
    }
            
    /** 
     * @param cr chuyền vào dữ liệu course để xóa
     * @return true nếu thành công
     */
    
    public Boolean delete(courseDTO cr) throws Exception{
        // access courseID
        String condition = " CourseID = '" + cr.getCourseID() +"'";
        
        Boolean check = connectUnit.Delete("course", condition);
        
        return check;
    }
    
    //function Edit
    public Boolean edit(courseDTO cr) throws Exception{
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("Title", cr.getCoureTitle());
        insertValues.put("Credits",cr.getCredits());
        insertValues.put("DepartmentID", cr.getDepartment());
        
        System.out.println(cr.toString());
        
        String condition = " CourseID = '" + cr.getCourseID() +"'";
        
        Boolean check = connectUnit.Update("course", insertValues, condition);
        return check;
    }
    
    
    
    
    
//    public static void main(String[] args) throws Exception {
//        courseDAO cDao = new courseDAO();
//        cDao.getAll();
//    }
}
