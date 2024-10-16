package be.pxl.services.service;

import be.pxl.services.client.EmployeeClient;
import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.domain.dto.DepartmentWithoutEmployeesResponse;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream().map(department -> {
            DepartmentResponse departmentResponse = mapToDepartmentResponse(department);
            departmentResponse.setEmployeeList(
                    employeeClient.findByDepartment(department.getId())
            );
            return departmentResponse;
        }).toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department entity = departmentRepository.getReferenceById(id);

        entity.setEmployees(
                employeeClient.findByDepartment(entity.getId())
        );

        return mapToDepartmentResponse(entity);
    }

    public void addDepartment(DepartmentRequest request) {
        Department newDepartment = Department.builder()
                .organizationId(request.getOrganizationId())
                .name(request.getName())
                .position(request.getPosition())
                .build();
        departmentRepository.save(newDepartment);
    }

    @Override
    public void updateDepartment(Long id, DepartmentRequest request) {
        Department entity = departmentRepository.getReferenceById(id);

        entity.setName(request.getName());
        entity.setOrganizationId(request.getOrganizationId());
        entity.setPosition(request.getPosition());

        departmentRepository.save(entity);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.getReferenceById(id);
        departmentRepository.delete(department);
        departmentRepository.flush();
    }

    @Override
    public List<DepartmentWithoutEmployeesResponse> getDepartmentByOrganizationId(Long organizationId) {
        return departmentRepository.findAll().stream()
                .filter(department -> department.getOrganizationId().equals(organizationId))
                .map(department -> new DepartmentWithoutEmployeesResponse(department.getOrganizationId(), department.getName(), department.getPosition()))
                .toList();
    }

    @Override
    public List<DepartmentResponse> getDepartmentWithEmployeesByOrganizationId(Long organizationId) {
        return departmentRepository.findAll().stream()
                .filter(department -> department.getOrganizationId().equals(organizationId))
                .map(department -> {
                    DepartmentResponse response = mapToDepartmentResponse(department);
                    response.setEmployeeList(
                            employeeClient.findByDepartment(department.getId())
                    );
                    return response;
                })
                .toList();
    }

    // PRIVATE METHODS //
    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .build();
    }
}
