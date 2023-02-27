/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.courseDTO;
import DTO.personDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author DELL
 */
public class personDAO {
    myConnectUnit connectUnit = new myConnectUnit("localhost", "root", "", "school");
    public personDAO(){
        
    }
    
    public ArrayList<personDTO> List(String condition, String orderBy) throws Exception{
        ResultSet result = this.connectUnit.Select("course", condition, orderBy);
        ArrayList<personDTO> persons = new ArrayList<>();
        while (result.next() ) {            
            personDTO person = new personDTO();
            person.setPersonID(result.getInt("PersonID"));
            person.setLastName(result.getString("Lastname"));
            person.setFirstName(result.getString("Firstname"));
            person.setHireDate(result.getString("HireDate"));
            person.setEnrollmentDate(result.getString("EnrollmentDate"));
            persons.add(person);
        }
        return persons;
    }
    public ArrayList<personDTO> List(String condition) throws Exception {
        return List(condition, null);
    }
    
    public ArrayList<personDTO> List() throws Exception {
        return List(null);
    }
    
    public ArrayList<personDTO> getAll() throws Exception{
        ResultSet result = this.connectUnit.Select("person");
        
//        System.out.println("DAO.courseDAO.getAll() oke");// Kieerm tra may chay

        ArrayList<personDTO> persons = new ArrayList<>();
        while (result.next() ) {            
            personDTO person = new personDTO();
            person.setPersonID(result.getInt("PersonID"));
            person.setLastName(result.getString("Lastname"));
            person.setFirstName(result.getString("Firstname"));
            person.setHireDate(result.getString("HireDate"));
            person.setEnrollmentDate(result.getString("EnrollmentDate"));
            persons.add(person);
            
        }
        return persons;
    }
    
    //Hàm thêm 
    public Boolean add(personDTO ps) throws Exception{
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("PersonID", ps.getPersonID());
        insertValues.put("Lastname", ps.getLastName());
        insertValues.put("Firstname",ps.getFirstName());
        insertValues.put("HireDate", ps.getHireDate());
        insertValues.put("EnrollmentDate", ps.getEnrollmentDate());
        
        Boolean check = connectUnit.Insert("person", insertValues);
        return check;
    }
            
    /** 
     * @param cr chuyền vào dữ liệu course để xóa
     * @return true nếu thành công
     */
    
    public Boolean delete(personDTO ps) throws Exception{
        // access courseID
        String condition = " PersonID = '" + ps.getPersonID() +"'";
        
        Boolean check = connectUnit.Delete("person", condition);
        
        return check;
    }
    
    //function Edit
    public Boolean edit(personDTO ps) throws Exception{
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("Lastname", ps.getLastName());
        insertValues.put("Firstname",ps.getFirstName());
        insertValues.put("HireDate", ps.getHireDate());
        insertValues.put("EnrollmentDate", ps.getEnrollmentDate());
        
        System.out.println(ps.toString());
        
        String condition = " PersonID = '" + ps.getPersonID()+"'";
        
        Boolean check = connectUnit.Update("person", insertValues, condition);
        return check;
    }
}
