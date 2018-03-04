package com.suresh.Dao;

import java.sql.SQLException;
import java.util.List;

import com.suresh.Beans.Employee;
import com.suresh.Exceptions.EmployeeNotFoundException;
import com.suresh.Exceptions.InvalidSalaryException;


public interface EmployeeDao {

	boolean addEmployee(List<Employee> employee1) throws InvalidSalaryException, SQLException;

	boolean deleteEmployee(int empID) throws EmployeeNotFoundException, SQLException;

	boolean updateEmployeeInfo(Employee empl) throws EmployeeNotFoundException, InvalidSalaryException, SQLException;

	List<Employee> getEmployees() throws SQLException;
	
	public Employee getEmployeeById(int empID) throws EmployeeNotFoundException, SQLException;

	List<Employee> getHighestSalaryEmployee(double salary) throws SQLException;
	
	public List<Employee> sortEmployees(String str) throws SQLException;
	
	public int getEmployeeSalary(int id) throws SQLException;
	

}
