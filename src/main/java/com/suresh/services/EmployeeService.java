package com.suresh.services;

import java.sql.SQLException;
import java.util.List;

import com.suresh.Beans.Employee;
import com.suresh.Exceptions.EmployeeNotFoundException;
import com.suresh.Exceptions.InvalidSalaryException;


public interface EmployeeService {

	boolean addEmployee(Employee employee1) throws InvalidSalaryException, SQLException;

	boolean deleteEmployee(int empID) throws EmployeeNotFoundException, SQLException;

	boolean updateEmployeeInfo(Employee empl) throws EmployeeNotFoundException, InvalidSalaryException, SQLException;

	List<Employee> getEmployees() throws SQLException;
	
	public Employee getEmployeeById(int empID) throws EmployeeNotFoundException, SQLException;

	List<Employee> getHighestSalaryEmployee(double salary) throws SQLException;
	
	public List<Employee> sortEmployees(String str) throws SQLException;

	double getEmployeeHRA(int id);

	double getEmployeeGrossSalary(int id) throws EmployeeNotFoundException;
	

	

}
