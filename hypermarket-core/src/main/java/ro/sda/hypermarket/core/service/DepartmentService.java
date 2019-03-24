package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department getById(Long id, boolean useHibernate);
    List<Department> getAll(boolean useHibernate);
    Department createDepartment(Department department, boolean useHibernate);
    Department updateDepartment(Department department, boolean useHibernate);
    void deleteDepartment(Department department, boolean useHibernate);
}
