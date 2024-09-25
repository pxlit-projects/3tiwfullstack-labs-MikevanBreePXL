package be.pxl.services.service;

import be.pxl.services.domain.EmployeeEntity;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();

        return employees.stream()
                .map(employee -> mapToEmployeeResponse(employee))
                .toList();
    }

    @Override
    public EmployeeResponse getEmployee(long id) {
        EmployeeEntity entity = employeeRepository.getReferenceById(id);
        return mapToEmployeeResponse(entity);
    }

    private EmployeeResponse mapToEmployeeResponse(EmployeeEntity employee) {
        return EmployeeResponse.builder()
                .organizationId(employee.getOrganizationId())
                .departmentId(employee.getDepartmentId())
                .name(employee.getName())
                .age(employee.getAge())
                .position(employee.getPosition())
                .build();
    }

    @Override
    public void addEmployee(EmployeeRequest request) {
        EmployeeEntity employee = EmployeeEntity.builder()
                .organizationId(request.getOrganizationId())
                .departmentId(request.getDepartmentId())
                .name(request.getName())
                .age(request.getAge())
                .position(request.getPosition())
                .build();
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(long id, EmployeeRequest request) {
        EmployeeEntity employee = employeeRepository.findById(id).orElseThrow();

        employee.setOrganizationId(request.getOrganizationId());
        employee.setDepartmentId(request.getDepartmentId());
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setPosition(request.getPosition());

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
        employeeRepository.flush();
    }
}
