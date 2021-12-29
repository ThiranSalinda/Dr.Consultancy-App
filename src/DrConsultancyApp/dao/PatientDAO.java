package DrConsultancyApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DrConsultancyApp.model.Patient;

public class PatientDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "P@ssw0rd";
    
    private static final String INSERT_PATIENT = "INSERT INTO patients(name,email,description) VALUES(?,?,?);";

    private static final String SELECT_PATIENT_BY_ID = "select id,name,email,description from patients where id =?;";
    private static final String SELECT_ALL_PATIENTS = "select * from patients";
    private static final String DELETE_PATIENT = "delete from patients where id = ?;";
    private static final String UPDATE_PATIENT = "update patients set name = ?,email= ?, description =? where id = ?;";
    
    public PatientDAO() {}
    //method used to get connection for CRUD OPERATIONS
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public void createTable() {
    	try {
    		Connection connection = getConnection();
        	System.out.println("conncetion okay");
        	Statement statement = connection.createStatement();
            System.out.println("statement okay");
            statement.executeQuery("create table patients(id number(5) NOT NULL,name varchar2(200),email varchar2(200),description varchar2(200))");
            statement.close();
            connection.close();
        	
    	}catch (SQLException e) {
        	e.getMessage();
        }
    }
    //INSERT PATIENT METHOD
    public void insertPatient(Patient patient) throws SQLException {
        //System.out.println(INSERT_PATIENT);
        try{
        	//createTable();
        	Connection connection = getConnection();
        	System.out.println("conncetion okay");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT);
            System.out.println("statement okay");
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getEmail());
            preparedStatement.setString(3, patient.getDescription());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            
        } catch (SQLException e) {
        	e.getMessage();
        }
    }
    
    //UPDATE PATIENT METHOD
    public boolean updatePatient(Patient patient) throws SQLException {
        boolean rowUpdated = false;
        try{
        	Connection connection = getConnection();
        	System.out.println("conncetion okay");
        	PreparedStatement statement = connection.prepareStatement(UPDATE_PATIENT);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getEmail());
            statement.setString(3, patient.getDescription());
            statement.setInt(4, patient.getId());

            rowUpdated = statement.executeUpdate()>0;
            statement.close();
            connection.close();
        }catch(SQLException e) {
        	e.getMessage();
        }
        return rowUpdated;
    }
    
    //SELECT PATIENT BY ID METHOD
    public Patient selectPatient(int id) {
        Patient patient = null;
        try{
        	Connection connection = getConnection();
        	System.out.println("conncetion okay");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
       
            ResultSet rs = preparedStatement.executeQuery();

            
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String description = rs.getString("description");
                System.out.println(name);
                patient = new Patient(id, name, email, description);
            }
            preparedStatement.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
        	e.getMessage();
        }
        return patient;
    }
    
    public List <Patient> selectAllPatients() {

        
        List <Patient> patients = new ArrayList<Patient>();//check here.....................................
       
        try{
        	Connection connection = getConnection();

           System.out.println("conncetion okay");
           PreparedStatement preparedStatement = connection.prepareStatement("select * from patients");
           System.out.println("statement okay");
            
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs.toString());

           
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String description = rs.getString("description");
                System.out.println(id+" "+name+" "+email+" "+description);
                System.out.println("inside loop");
                patients.add(new Patient(id, name, email, description));
            }
            System.out.println("RSLoop okay");
            preparedStatement.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
        	e.getMessage();
        }
        System.out.println("Return okay");
        return patients;
       
    }
    
    
    public boolean deletePatient(int id) throws SQLException {
        boolean rowDeleted = false;
        try{
        	Connection connection = getConnection();
        	System.out.println("conncetion okay");
        	PreparedStatement statement = connection.prepareStatement(DELETE_PATIENT);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            statement.close();
            connection.close();
        }catch (SQLException e) {
        	e.getMessage();
        }
        return rowDeleted;
    }

}
