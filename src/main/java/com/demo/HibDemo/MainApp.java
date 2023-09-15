package com.demo.HibDemo;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Retrieve All");
            System.out.println("6. Delete All");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createEmployee(employeeDAO, scanner);
                    break;
                case 2:
                    readEmployee(employeeDAO, scanner);
                    break;
                case 3:
                    updateEmployee(employeeDAO, scanner);
                    break;
                case 4:
                    deleteEmployee(employeeDAO, scanner);
                    break;
                case 5:
                    retrieveAllEmployees(employeeDAO);
                    break;
                case 6:
                    deleteAllEmployees(employeeDAO);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void createEmployee(EmployeeDAO employeeDAO, Scanner scanner) {
        Employee newEmployee = new Employee();
        
        System.out.print("Enter first name: ");
        newEmployee.setFirstName(scanner.next());
        
        System.out.print("Enter last name: ");
        newEmployee.setLastName(scanner.next());
        
        System.out.print("Enter age: ");
        newEmployee.setAge(scanner.nextInt());
        
        employeeDAO.create(newEmployee);
        System.out.println("Employee created.");
    }

    private static void readEmployee(EmployeeDAO employeeDAO, Scanner scanner) {
        System.out.print("Enter employee ID: ");
        Long id = scanner.nextLong();
        Employee retrievedEmployee = employeeDAO.getById(id);
        if (retrievedEmployee != null) {
            displayEmployeeDetails(retrievedEmployee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void updateEmployee(EmployeeDAO employeeDAO, Scanner scanner) {
        System.out.print("Enter employee ID: ");
        Long id = scanner.nextLong();
        Employee retrievedEmployee = employeeDAO.getById(id);
        if (retrievedEmployee != null) {
            System.out.println("Choose attribute to update:");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Age");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter new first name: ");
                    retrievedEmployee.setFirstName(scanner.next());
                    break;
                case 2:
                    System.out.print("Enter new last name: ");
                    retrievedEmployee.setLastName(scanner.next());
                    break;
                case 3:
                    System.out.print("Enter new age: ");
                    retrievedEmployee.setAge(scanner.nextInt());
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            employeeDAO.update(retrievedEmployee);
            System.out.println("Employee updated.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee(EmployeeDAO employeeDAO, Scanner scanner) {
        System.out.print("Enter employee ID: ");
        Long id = scanner.nextLong();
        employeeDAO.delete(id);
        System.out.println("Employee deleted.");
    }
    
    private static void retrieveAllEmployees(EmployeeDAO employeeDAO) {
        List<Employee> allEmployees = employeeDAO.getAll();
        if (!allEmployees.isEmpty()) {
            System.out.println("All Employees:");
            for (Employee employee : allEmployees) {
                displayEmployeeDetails(employee);
                System.out.println();
            }
        } else {
            System.out.println("No employees found.");
        }
    }
    
    private static void deleteAllEmployees(EmployeeDAO employeeDAO) {
        employeeDAO.deleteAll();
        System.out.println("All employees deleted.");
    }
    
    private static void displayEmployeeDetails(Employee employee) {
        System.out.println("Employee Details:");
        System.out.println("ID: " + employee.getId());
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
        System.out.println("Age: " + employee.getAge());
    }
}
