package com.suresh.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suresh.AppConfig;
import com.suresh.Beans.Employee;
import com.suresh.Exceptions.EmployeeNotFoundException;
import com.suresh.Exceptions.InvalidSalaryException;

@Repository
public class EmployeeDAOImpl implements EmployeeDao {
	@Autowired
	private AppConfig factory;

	
	public EmployeeDAOImpl() {
		super();
	}
	@PostConstruct
	public DataSource getInstance(){
		 DataSource ds = factory.getDataSource();
		return ds;
	}
	
	@Override
	public boolean addEmployee(List<Employee> list) throws InvalidSalaryException, SQLException {
		PreparedStatement pstmt = null;
		DataSource ds = getInstance();
		try (Connection con = ds.getConnection();) {
			for (Employee e : list) {
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(
						"insert into employee (name,salary, departmentNumber, age) values ( ?, ?, ?, ?)");
				pstmt.setString(1, e.getName());
				pstmt.setDouble(2, e.getSalary());
				pstmt.setInt(3, e.getDeptNumber());
				pstmt.setInt(4, e.getAge());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			con.commit();
		}
		return true;
	}

	public boolean deleteEmployee(int empID) throws EmployeeNotFoundException, SQLException {
		if (getEmployeeById(empID) != null) {
			DataSource ds = getInstance();
			try (Connection con = ds.getConnection();) {
				PreparedStatement pstmt = con.prepareStatement("delete from employee where id=?");
				pstmt.setInt(1, empID);
				pstmt.setInt(1, empID);
				pstmt.executeUpdate();
			}
		}
		return true;

	}

	public boolean updateEmployeeInfo(Employee empl)
			throws EmployeeNotFoundException, InvalidSalaryException, SQLException {
		PreparedStatement pstmt = null;
		empl.getName();
		DataSource ds = getInstance();
		try (Connection con = ds.getConnection();) {
			pstmt = con
					.prepareStatement("update employee set name=?, age=?, salary=?,departmentNumber=?  where id = ?");
			pstmt.setString(1, empl.getName());
			pstmt.setInt(2, empl.getAge());
			pstmt.setDouble(3, empl.getSalary());
			pstmt.setInt(4, empl.getDeptNumber());
			pstmt.setInt(5, empl.getId());
			pstmt.executeUpdate();
		}
		return true;
	}

	public Employee getEmployeeById(int empID) throws EmployeeNotFoundException, SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataSource ds = getInstance();
		try (Connection con1 = ds.getConnection();) {
			pstmt = con1.prepareStatement("select * from employee where id = ?");
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setDeptNumber(rs.getInt("departmentNumber"));
				e.setAge(rs.getInt("age"));
				return e;
			} else {
				throw new EmployeeNotFoundException("There is no Employee in the database with the given Employee Id");
			}
		}

	}

	public List<Employee> getEmployees() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> employeesList = new ArrayList<>();
		DataSource ds = getInstance();
		try (Connection con = ds.getConnection();) {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employee");
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setDeptNumber(rs.getInt("departmentNumber"));
				e.setAge(rs.getInt("age"));
				employeesList.add(e);
			}
		}
		return employeesList;
	}

	public List<Employee> sortEmployees(String str) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> employeesList = new ArrayList<>();
		DataSource ds = getInstance();
		try (Connection con = ds.getConnection();) {
			String sqlQuery = null;
			if (str.equalsIgnoreCase("id")) {
				sqlQuery = "select * from employee order by id";
			} else if (str.equalsIgnoreCase("salary")) {
				sqlQuery = "select * from employee order by salary";
			} else if (str.equalsIgnoreCase("name and salary")) {
				sqlQuery = "select * from employee order by name and salary";
			} else if (str.equalsIgnoreCase("departmentNumber")) {
				sqlQuery = "select * from employee order by departmentNumber";
			} else if (str.equalsIgnoreCase("age")) {
				sqlQuery = "select * from employee order by age";
			}
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			employeesList.clear();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setDeptNumber(rs.getInt("departmentNumber"));
				e.setAge(rs.getInt("age"));
				employeesList.add(e);
			}
		}
		return employeesList;

	}

	@Override
	public List<Employee> getHighestSalaryEmployee(double salary) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> employeesList = new ArrayList<>();
		DataSource ds = getInstance();
		try (Connection con = ds.getConnection();) {
			pstmt = con.prepareStatement("select * from employee where salary>?");
			pstmt.setDouble(1, salary);
			rs = pstmt.executeQuery();
			employeesList.clear();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setDeptNumber(rs.getInt("departmentNumber"));
				e.setAge(rs.getInt("age"));
				employeesList.add(e);
			}
		}
		return employeesList;
	}

	@Override
	public int getEmployeeSalary(int id) throws SQLException {
		ResultSet resultSet = null;
		int salary = 0;
		DataSource ds = getInstance();
		try (Connection con = ds.getConnection();
				PreparedStatement statement = con
						.prepareStatement("select salary from employee where empid = ? ");) {
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				salary = resultSet.getInt("salary");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (resultSet != null)
				resultSet.close();
		}
		return salary;
	}
	
	
	

}
