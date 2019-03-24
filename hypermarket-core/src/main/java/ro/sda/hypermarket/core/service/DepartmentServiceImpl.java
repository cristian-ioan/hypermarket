package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Department;
import ro.sda.hypermarket.core.repository.DepartmentRepository;
import ro.sda.hypermarket.core.sda.DepartmentDao;

import java.util.List;

@Service("departmentService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department getById(Long id, boolean useHibernate) {
        if (useHibernate){
            return departmentDao.getById(id);
        }
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> getAll(boolean useHibernate) {
        if (useHibernate){
            return departmentDao.getAll();
        }
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Department createDepartment(Department department, boolean useHibernate) {
        if (useHibernate){
            return departmentDao.createDepartment(department);
        }
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department updateDepartment(Department department, boolean useHibernate) {
        if(useHibernate){
            return departmentDao.updateDepartment(department);
        }
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(Department department, boolean useHibernate) {
        if (useHibernate){
            departmentDao.deleteDepartment(department);
        }
        departmentRepository.delete(department);
    }
}
