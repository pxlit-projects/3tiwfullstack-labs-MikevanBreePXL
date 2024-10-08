package be.pxl.services.service;

import be.pxl.services.domain.dto.EmployeeCreateRequest;
import be.pxl.services.domain.dto.EmployeeDetailRequest;
import be.pxl.services.domain.dto.EmployeeResponse;

import java.util.List;

public interface IEmployeeService  {
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployee(long id);
    void addEmployee(EmployeeCreateRequest request);
    void updateEmployee(long id, EmployeeDetailRequest request);
    void deleteEmployee(long id);
    List<EmployeeResponse> findByDepartmentId(Long departmentId);
    List<EmployeeResponse> findByOrganizationId(Long organizationId);
}
