package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.Employee;

public interface EmployeeMapper {
    int insert(Employee record);

    int insertSelective(Employee record);
    
    List<Employee> selPlanner();
    
    List<Employee> selEmp(Employee record);
    
    //µÇÂ¼
    Employee login(Employee emp);
    
    List<Employee> selEmpByNameOrCode(Employee emp);
    List<Employee> selJobByCode(String jobCode);
}