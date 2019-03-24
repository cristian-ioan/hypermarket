package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Employee;
import ro.sda.hypermarket.core.repository.EmployeeRepository;
import ro.sda.hypermarket.core.sda.EmployeeDao;

import java.util.List;

@Service("employeeService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getById(Long id, boolean useHibernate) {
        if (useHibernate){
            return employeeDao.getById(id);
        }
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAll(boolean useHibernate) {
        if (useHibernate){
            return employeeDao.getAll();
        }
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee employee, boolean useHibernate) {
        if (useHibernate){
            return employeeDao.createEmployee(employee);
        }
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee, boolean useHibernate) {
        if(useHibernate){
            return employeeDao.updateEmployee(employee);
        }
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Employee employee, boolean useHibernate) {
        if (useHibernate){
            employeeDao.deleteEmployee(employee);
        }
        employeeRepository.delete(employee);
    }
}
