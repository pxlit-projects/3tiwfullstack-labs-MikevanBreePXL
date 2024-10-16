package be.pxl.services.service;

import be.pxl.services.client.NotificationClient;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.NotificationRequest;
import be.pxl.services.domain.dto.EmployeeCreateRequest;
import be.pxl.services.domain.dto.EmployeeDetailRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(employee -> mapToEmployeeResponse(employee))
                .toList();
    }

    @Override
    public EmployeeResponse getEmployee(long id) {
        Employee entity = employeeRepository.getReferenceById(id);
        return mapToEmployeeResponse(entity);
    }

    @Override
    public void addEmployee(EmployeeCreateRequest request) {
        Employee employee = Employee.builder()
                .name(request.getName())
                .age(request.getAge())
                .position(request.getPosition())
                .build();
        employeeRepository.save(employee);

        NotificationRequest notificationRequest = NotificationRequest.builder()
                .message("Employee created")
                .sender("Verzender")
                .build();
        notificationClient.sendNotification(notificationRequest);
    }

    @Override
    public void updateEmployee(long id, EmployeeDetailRequest request) {
        Employee employee = employeeRepository.findById(id).orElseThrow();

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

    @Override
    public List<EmployeeResponse> findByDepartmentId(Long departmentId) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getDepartmentId().equals(departmentId))
                .map(employee -> mapToEmployeeResponse(employee))
                .toList();
    }

    @Override
    public List<EmployeeResponse> findByOrganizationId(Long organizationId) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getOrganizationId().equals(organizationId))
                .map(employee -> mapToEmployeeResponse(employee))
                .toList();
    }

    // PRIVATE METHODS //
    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .organizationId(employee.getOrganizationId())
                .departmentId(employee.getDepartmentId())
                .name(employee.getName())
                .age(employee.getAge())
                .position(employee.getPosition())
                .build();
    }
}
