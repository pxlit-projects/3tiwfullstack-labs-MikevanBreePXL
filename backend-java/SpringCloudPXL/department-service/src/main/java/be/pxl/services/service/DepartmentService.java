package be.pxl.services.service;

import be.pxl.services.client.OrganizationClient;
import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.domain.dto.DepartmentWithoutEmployeesResponse;
import be.pxl.services.domain.dto.OrganizationWithDepartmentsRequest;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;
    private final OrganizationClient organizationClient;

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream().map(department -> mapToDepartmentResponse(department)).toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department entity = departmentRepository.getReferenceById(id);
        return mapToDepartmentResponse(entity);
    }

    public void addDepartment(DepartmentRequest request) {
        Department newDepartment = Department.builder()
                .organizationId(request.getOrganizationId())
                .name(request.getName())
                .build();
        departmentRepository.save(newDepartment);

        OrganizationWithDepartmentsRequest response = organizationClient.findByIdWithDepartments(request.getOrganizationId());
        response.getDepartmentList().add(request);
    }

    @Override
    public void updateDepartment(Long id, DepartmentRequest request) {
        Department entity = departmentRepository.getReferenceById(id);

        boolean updateOrganizations = !entity.getOrganizationId().equals(request.getOrganizationId());
        if (updateOrganizations) {
            OrganizationWithDepartmentsRequest oldOrganization = organizationClient.findByIdWithDepartments(entity.getId());
            oldOrganization.getDepartmentList().remove(entity);
        }

        entity.setName(request.getName());
        entity.setOrganizationId(request.getOrganizationId());
        entity.setPosition(request.getPosition());

        departmentRepository.save(entity);

        if (updateOrganizations) {
            OrganizationWithDepartmentsRequest newOrganization = organizationClient.findByIdWithDepartments(request.getOrganizationId());
            newOrganization.getDepartmentList().add(request);
        }
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.getReferenceById(id);
        OrganizationWithDepartmentsRequest response = organizationClient.findByIdWithDepartments(department.getOrganizationId());

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
                .map(department -> mapToDepartmentResponse(department))
                .toList();
    }

    // PRIVATE METHODS //
    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .build();
    }
}
