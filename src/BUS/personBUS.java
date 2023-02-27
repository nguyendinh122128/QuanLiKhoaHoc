/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.courseDAO;
import DAO.personDAO;
import DTO.courseDTO;
import DTO.personDTO;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class personBUS {
    private ArrayList<personDTO> arr_person;
    
    private personDAO personDAO = new personDAO();
    
    public personBUS(){
        arr_person = new ArrayList<>();
        personDAO = new personDAO();
        try {
            arr_person = personDAO.getAll();
        } catch (Exception e) {
//            Logger.getLogger(courseBUS.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList<personDTO> getCoursesFind(String personID_find){ //Tìm kiếm courses nhiều
        
        ArrayList<personDTO> list_persons_find =  new ArrayList<>();
        
        for(personDTO person : arr_person){
            
            String personID_string = Integer.toString(person.getPersonID()); // Chuyền id của person thành string
            
            if(personID_string.contains(personID_find)){     // Tìm kiếm chuỗi con có trong chuỗi cha không trả về true
                
                list_persons_find.add(person);
                
            }
        }
                return list_persons_find;
    }
    
    // Get all course
    public ArrayList<personDTO> getCoureseAll() throws Exception{
        ArrayList<personDTO> listPersons = personDAO.getAll();
        return  listPersons;
    }
    
    //Add course
    public  Boolean add(personDTO ps) throws Exception{
        if(personDAO.add(ps) ){
            arr_person.add(ps);
        }
        return false;
    }
    
    //Delete course
    public Boolean delete(personDTO ps) throws Exception{
        if(personDAO.delete(ps) ){
            for(personDTO person : arr_person){
                if(person.getPersonID()== ps.getPersonID()){
                    arr_person.remove(ps);
                    break;
                }
            }
        }
        return false;
    }
}
