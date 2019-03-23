package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import ro.sda.hypermarket.core.entity.Department;

import java.util.List;

public interface DepartmentDao {

    Session getCurrentSession();
    Department getById(Long id);
    List<Department> getAll();
    Department createDepartment(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(Department department);
}
