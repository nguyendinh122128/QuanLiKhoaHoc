package BUS;


import DAO.courseDAO;
import DTO.courseDTO;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class courseBUS {
    private ArrayList<courseDTO> arr_course;
    
    private courseDAO courseDAO = new courseDAO();
    
    public courseBUS(){
        arr_course = new ArrayList<>();
        courseDAO = new courseDAO();
        try {
            arr_course = courseDAO.getAll();
        } catch (Exception e) {
//            Logger.getLogger(courseBUS.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //Tìm kiếm 
    public ArrayList<courseDTO> getCoursesFind(String courseID_find){ //Tìm kiếm courses nhiều
        
        ArrayList<courseDTO> list_courses_find =  new ArrayList<>();
        
//        String courseID_find_string = Integer.toString(courseID_find);  // Chuyển id_tìm kiếm thành String
        
        for(courseDTO course : arr_course){
            
            String courseID_string = Integer.toString(course.getCourseID()); // Chuyền id của course thành string
            
            if(courseID_string.contains(courseID_find)){     // Tìm kiếm chuỗi con có trong chuỗi cha không trả về true
                
                list_courses_find.add(course);
                
            }
        }
                return list_courses_find;
    }
    
    // Get all course
    public ArrayList<courseDTO> getCoureseAll() throws Exception{
        ArrayList<courseDTO> listCourses = courseDAO.getAll();
        return  listCourses;
    }
    
    //Add course
    public  Boolean add(courseDTO cr) throws Exception{
        if(courseDAO.add(cr) ){
            arr_course.add(cr);
        }
        return false;
    }
    
    //Delete course
    public Boolean delete(courseDTO cr) throws Exception{
        if(courseDAO.delete(cr) ){
            for(courseDTO course : arr_course){
                if(course.getCourseID() == cr.getCourseID() ){
                    arr_course.remove(cr);
                    break;
                }
            }
        }
        return false;
    }
}
