package com.marcus.chiu.springmvc.c_service.service_implementation;

import com.marcus.chiu.springmvc.c_service.service.EmployeeService;
import com.marcus.chiu.springmvc.d_repository.dao.EmployeeDao;
import com.marcus.chiu.springmvc.e_entity.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by marcus.chiu on 10/17/16.
 * @Service - indicates this class as a service stereotype
 * @Transactional - starts a transaction on each method start and
 * commits it on each method exit (or rollback if method failed)
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    //EmployeeDaoImpl bean is used
    @Autowired
    private EmployeeDao dao;

    @Override
    public Employee findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        dao.saveEmployee(employee);
    }

    /**
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the e_entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     * @param employee
     */
    @Override
    public void updateEmployee(Employee employee) {
        System.out.println("EmployeeServiceImpl.updateEmployee");
        Employee entity = dao.findById(employee.getId());
        //Name embeddedName = e_entity.getNameTwo();

        if(entity != null) {
            entity.setName(employee.getName());
            entity.setJoiningDate(employee.getJoiningDate());
            entity.setSalary(employee.getSalary());
            entity.setSsn(employee.getSsn());
            entity.setText(employee.getText());
            entity.setBirthDate(employee.getBirthDate());
        }
    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeBySsn(ssn);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }

    @Override
    public Employee findEmployeeBySsn(String ssn) {
        return dao.findEmployeeBySsn(ssn);
    }

    @Override
    public boolean isEmployeeSsnUnique(Integer id, String ssn) {
        Employee employee = findEmployeeBySsn(ssn);
        return (employee == null || ((id != null) && (employee.getId() == id)));
    }
}
