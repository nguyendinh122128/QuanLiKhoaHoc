/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class myConnectUnit {
    private mySQLConnection connect;
    
    // Hàm khởi tạo kết nối mặc định
    public myConnectUnit(){
        connect = new mySQLConnection("localhost", "root", "", "school");
    }
    public  myConnectUnit(String host, String userName, String password, String database){
        connect = new mySQLConnection(host, userName, password, database);
    }
    public ResultSet Select(String TableName, String Condition, String OrderBy) throws Exception {
        // khai báo biến StringBuilder để tạo chuỗi Select
        StringBuilder query = new StringBuilder("SELECT * FROM " + TableName);
        // Đưa câu lệnh điều kiện vaò câu query
        this.AddCondition(query, Condition);
        // Đưa câu lệnh Order vào query
        this.AddOrderBy(query, OrderBy);
        query.append(";");
        // thực thi câu lệnh và trả kết quả
        return this.connect.excuteQuery(query.toString());
    }
    
    // Hàm over load Select giảm OrderBy parameter
    /**
     * Select * From Table Where Condition
     * @throws Exception 
     */
    public ResultSet Select(String TableName, String Condition) throws Exception {
        return this.Select(TableName, Condition, null);
    }
    // Hàm over load Select giảm Condition  parameter
    
    public ResultSet Select(String TableName) throws Exception {
        return this.Select(TableName, null, null);
    }
    
    
    //Hàm thêm điều kiện vào query
    private void AddCondition(StringBuilder query, String Condition) {
        if (Condition != null) {
            query.append(" WHERE "+ Condition);
        }
    }
    
    // Hàm thêm OrderBy vào query
    private void AddOrderBy(StringBuilder query, String OrderBy) {
        if ( OrderBy != null) {
            query.append(" ORDER BY " + OrderBy);
        }
    }
    
    // Hàm hỗ trợ Insert xuống SQL
    /**
     * Insert Into TableName values...
     * @throws Exception 
     */
    public boolean Insert(String TableName, HashMap<String, Object> ColumnValues) throws Exception {
        StringBuilder query = new StringBuilder("Insert Into " + TableName);
        StringBuilder valueInsert = new StringBuilder();
        query.append("(");
        // Duyệt và đưa thông tin tên cột và giá tri values vào
        for (String key : ColumnValues.keySet()) {
            query.append(key + ",");
            valueInsert.append("'" + ColumnValues.get(key).toString() + "',");
        }
        // cắt bỏ dấu , dư thừa
        query = query.delete(query.length() - 1, query.length());
        valueInsert = valueInsert.delete(valueInsert.length() - 1, valueInsert.length());
        // đưa giá trị của cột vào câu query
        query.append(") Values(" + valueInsert.toString() + ")");
        // chèn ký tự ; vào cuối dòng lệnh để cách câu
        query.append(";");
        System.out.println(query);
        return this.connect.executeUpdate(query.toString()) > 0;
    }
    
    
    public boolean InsertKhongCanID(String TableName, HashMap<String, Object> ColumnValues) throws Exception {
        StringBuilder query = new StringBuilder("Insert Into " + TableName);
        StringBuilder valueInsert = new StringBuilder();
        query.append("(");
        // Duyệt và đưa thông tin tên cột và giá tri values vào
        for (String key : ColumnValues.keySet()) {
            query.append(key + ",");
            valueInsert.append("'" + ColumnValues.get(key).toString() + "',");
        }
        // cắt bỏ dấu , dư thừa
        query = query.delete(query.length() - 1, query.length());
        valueInsert = valueInsert.delete(valueInsert.length() - 1, valueInsert.length());
        // đưa giá trị của cột vào câu query
        query.append(") Values(" + valueInsert.toString() + ")");
        // chèn ký tự ; vào cuối dòng lệnh để cách câu
        query.append(";");
        System.out.println(query);
        return this.connect.executeUpdate(query.toString()) > 0;
    }
    

    public boolean Update(String TableName, HashMap<String, Object> ColumnValues, String Condition) throws Exception {
        // khai báo biến để tạo chuỗi CSDL
        StringBuilder query = new StringBuilder("Update " + TableName + " Set ");
        // Duyệt và đưa thông tin tên cột, giá trị
        for (String key : ColumnValues.keySet()) {
            query.append(key + " = '" + ColumnValues.get(key).toString() + "',");
        }
        // cắt bỏ dấu , dư thừa
        query = query.delete(query.length() - 1, query.length());
        // đưa câu lệnh điều kiện vào trong query
        this.AddCondition(query, Condition);
        query.append(";");
        System.out.println(query);
        //thực thi và trả ra kết quả
        return this.connect.executeUpdate(query.toString()) >0;
    }
    

    public boolean Delete(String TableName, String Condition) throws Exception {
        StringBuilder query = new StringBuilder("Delete From " + TableName);
        this.AddCondition(query, Condition);
        query.append(";");
        System.out.println(query);
        return this.connect.executeUpdate(query.toString()) > 0;
    }
    
    // hàm đếm số cột trong CSDL
    public static int getColumnCount(ResultSet result) throws SQLException {
         return result.getMetaData().getColumnCount();
    }
    
    // hàm lấy tên cột trong result select từ CSDL
    public static String[] getColumnName(ResultSet result) throws SQLException {
        // lấy resultsetMetaDate từ Result
        ResultSetMetaData rsMetaData = (ResultSetMetaData) result.getMetaData();
        // lấy số lượng cột trong Result
        int ColumnCount = rsMetaData.getColumnCount();
        String[] list = new String[ColumnCount];
        for (int i=0 ; i < ColumnCount ; i++) {
            list[i] = rsMetaData.getColumnName(i);
        }
        return list;
    }
    
    public void Close() throws Exception {
        this.connect.Close();
    }
    public static void main(String[] args) {
        myConnectUnit connection = new myConnectUnit("localhost", "root", "", "school");
        try {
            if(connection.Select("course")!=null)
            {
                System.out.println("Lấy được");
            }
        } catch (Exception ex) {
            Logger.getLogger(myConnectUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  Statement creatStatement(){
        throw new UnsupportedOperationException("Not support yet!");
    }
    
}
