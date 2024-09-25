package be.pxl.services.service;

import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse getDepartmentById(Long id);
    void addDepartment(DepartmentRequest request);
    void updateDepartment(Long id, DepartmentRequest request);
    void deleteDepartment(Long id);
}
