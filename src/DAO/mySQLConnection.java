/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class mySQLConnection {
    String Host = "";
    String Username = "";
    String Password = "";
    String Database = "";
    
    Connection connect = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    public static Connection getConnection()
    {
        Connection connection;
        String url = "jdbc:mysql://" + "localhost" + ":3306/" + "school";
        try {
            connection = DriverManager.getConnection(url, "root", "");
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public mySQLConnection(String Host, String Username, String Password, String Database) {
        this.Host = Host;
        this.Username = Username;
        this.Password = Password;
        this.Database = Database;
    }
    
    protected void driveTest () throws Exception {
        try {
            
                Class.forName("com.mysql.cj.jdbc.Driver");
                
        }
        catch (ClassNotFoundException e) {
            throw new Exception("My SQl not found driveTest");
        }
    }
    protected Connection getConnect() throws Exception {
        //nếu connection null thì khởi tạo mới
        if (this.connect == null) {
            driveTest();
            String url = "jdbc:mysql://" + this.Host + ":3306/" + this.Database;
            try{
                this.connect = DriverManager.getConnection(url, this.Username, this.Password);
                
                System.out.println("DAO.mySQLConnection.getConnect() driver OK"); 
            }        
            catch (SQLException e) {
                throw new Exception("không thể kết nối tới Database" + url +e.getMessage());
            }
        }
        
        return this.connect;
    }
        
    //    tạo statement để thực thi Queury
    protected Statement getStatement() throws Exception {
        if (this.statement == null) {
            this.statement = getConnect().createStatement();
        }
        else {
            this.statement.isClosed();
        }
        return this.statement;
    }
    
    //hàm thực thi các câu lệnh SQl 
    public ResultSet excuteQuery(String Query) throws Exception{
        try {
            this.resultSet = getStatement().executeQuery(Query);
            
        } catch (Exception e) {
            throw new Exception("Lỗi" + e.getMessage());
        }
        return this.resultSet;
    }
    
    //    thực thi các Insert, Update, Delete
        public int executeUpdate(String Query) throws Exception {
    //khai báo biến int để lưu trữ kết quả trong quá trình thực thi
        int res = Integer.MIN_VALUE;
        try {
            res = getStatement().executeUpdate(Query);
        } catch (Exception e) {
            throw new Exception("Error " + e.getMessage());
        }
        return res;
    }
    
    public void Close() throws Exception {
        if (this.resultSet != null && this.resultSet.isClosed()) {
            this.resultSet.close();
            this.resultSet = null;
        }
        if (this.statement != null && this.statement.isClosed()) {
            this.statement.close();
            this.statement = null;
        }
        if (this.connect != null && this.connect.isClosed()) {
            this.connect.close();
            this.connect = null;
        }
    }
    
    public static void main(String[] args) {
        mySQLConnection connection=new mySQLConnection("localhost","root","","school");
        try{
            connection.getConnect();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
}

