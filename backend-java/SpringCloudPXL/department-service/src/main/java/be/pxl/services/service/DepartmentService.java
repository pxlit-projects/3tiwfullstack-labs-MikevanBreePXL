package be.pxl.services.service;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream().map(department -> mapToDepartmentResponse(department)).toList();
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .employeeList(department.getEmployeeList())
                .build();
    }

    public void addDepartment(DepartmentRequest request) {
        Department newDepartment = Department.builder()
                .organizationId(request.getOrganizationId())
                .name(request.getName())
                .employeeList(request.getEmployeeList())
                .build();
        departmentRepository.save(newDepartment);
    }
}
