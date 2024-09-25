package be.pxl.services.service;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;

import java.util.List;

public interface IEmployeeService  {
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployee(long id);
    void addEmployee(EmployeeRequest request);
    void updateEmployee(long id, EmployeeRequest request);
    void deleteEmployee(long id);
}
