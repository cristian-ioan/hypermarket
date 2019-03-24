package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getById(Long id, boolean useHibernate);
    List<Employee> getAll(boolean useHibernate);
    Employee createEmployee(Employee employee, boolean useHibernate);
    Employee updateEmployee (Employee employee, boolean useHibernate);
    void deleteEmployee(Employee employee, boolean useHibernate);

}
