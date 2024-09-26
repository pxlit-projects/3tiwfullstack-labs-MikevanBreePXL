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

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department entity = departmentRepository.getReferenceById(id);
        return mapToDepartmentResponse(entity);
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .build();
    }

    public void addDepartment(DepartmentRequest request) {
        Department newDepartment = Department.builder()
                .organizationId(request.getOrganizationId())
                .name(request.getName())
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
        departmentRepository.deleteById(id);
        departmentRepository.flush();
    }
}
