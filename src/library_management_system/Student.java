package library_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Student {
    
    public String sid;
    public String name;
    public String batch;
    public String branch;
    public String email;
    public String mobile;
    private String password;
    
    private Connection con;
    private Statement statement;
    private ResultSet rst;
    
    public Student()
    {
        String mysql_password="kush";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management","root", mysql_password);
            statement = con.createStatement();
            String query="Select S.SID,S.Name,S.batch,S.Branch,S.Email,S.Mobile,L.Password from login_details L,Student S where L.Id=S.Sid";
            rst=statement.executeQuery(query);

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public Student(String sid,String name,String batch,String branch,String email,String mobile){
        this();
        this.sid=sid;
        this.name=name;
        this.batch=batch;
        this.branch=branch;
        this.email=email;
        this.mobile=mobile;
        this.password="";
    }
    
    public Student(String sid){
        this();
        try {
            String query="Select*from Student where sid='"+sid+"';";
            ResultSet rs = statement.executeQuery(query);
            
            while(rs.next()){
                this.sid=rs.getString("SID");
                this.name=rs.getString("Name");
                this.batch=rs.getString("Batch");
                this.branch=rs.getString("Branch");
                this.email=rs.getString("email");
                this.mobile=rs.getString("mobile");
                this.password="";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void save(){
        try {
            String query="Insert into student values('"+sid+"','"+name+"','"+batch+"','"+branch+"','"+email+"','"+mobile+"');";
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void setPassword(String s){
        password=s;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void addUser(){
        try {
            String query="Insert into login_details values('student','"+sid+"','"+password+"');";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
