package com.suresh.Beans;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Employee implements Comparable<Employee> {

	private int id;
	private String name;
	private int number;
	private double salary;
	private int age;
	private int deptNumber;
	
	@Autowired
	private Address address;
	
	@Autowired
	private List<PhoneNumber> phoneNumbers;
	
	@Autowired
	private EmploymentPeriod employmentPeriod;
	
//	@Autowired
//	private Gender gender;

	public Employee() {

	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Gender getGender() {
//		return gender;
//	}
//
//	public void setGender(Gender gender) {
//		this.gender = gender;
//	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void addPhoneNumbers(PhoneNumber phoneNumber) {
		this.phoneNumbers.add(phoneNumber);
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getHRA() {
		return this.getSalary() * 0.20;
	}

	public Double getGrossSalary() {
		return this.getHRA() + this.getSalary();
	}

	public EmploymentPeriod getEmploymentPeriod() {
		return employmentPeriod;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(int deptNumber) {
		this.deptNumber = deptNumber;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void setEmploymentPeriod(EmploymentPeriod employmentPeriod) {
		this.employmentPeriod = employmentPeriod;
	}

	@Override
	public String toString() {
		return "Employee:\n\tid: " + id + "\n\tname: " + name + "\n\tgender: null " +  "\n\tDepartmentNumber: "
				+ deptNumber + "\n\tAge: " + age + "\n\tAddress: " + address + "\n\tphoneNumbers: " + phoneNumbers
				+ "\n\tsalary: " + salary + "\n" + employmentPeriod + "\n\tGorss salary: " + this.getGrossSalary()
				+ "\n";
	}

	public int compareTo(Employee o) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		if (name.compareTo(o.name) > 0)
			return AFTER;
		if (name.compareTo(o.name) < 0)
			return BEFORE;
		// compare by salary
		if (salary > o.salary)
			return AFTER;
		if (salary < o.salary)
			return BEFORE;

		return EQUAL;
	}

	public static class EmployeeSalaryComparator implements Comparator<Employee> {

		public int compare(Employee e1, Employee e2) {
			final int BEFORE = -1;
			final int EQUAL = 0;
			final int AFTER = 1;

			// compare by salary
			if (e1.salary > e2.salary)
				return AFTER;
			if (e1.salary < e2.salary)
				return BEFORE;

			return EQUAL;
		}

		// Collections.sort(getEmployess(),new Comparator())

	}

	public static class EmployeeDepartmentComparator implements Comparator<Employee> {

		public int compare(Employee o1, Employee o2) {
			final int BEFORE = -1;
			final int EQUAL = 0;
			final int AFTER = 1;

			// compare by salary
			if (o1.deptNumber > o2.deptNumber)
				return AFTER;
			if (o1.deptNumber < o2.deptNumber)
				return BEFORE;
			return EQUAL;
		}

	}

}
