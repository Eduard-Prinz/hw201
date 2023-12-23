package mapDd.HW112.service;

import org.springframework.stereotype.Service;
import mapDd.HW112.exception.EmployeeAlreadyAddedException;
import mapDd.HW112.exception.EmployeeNotFoundException;
import mapDd.HW112.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }



    @Override
    public Employee addsEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFirstName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFirstName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFirstName())) {
            employees.remove(employee.getFirstName());
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFirstName())) {
            return employees.get(employee.getFirstName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}