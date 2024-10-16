package be.pxl.services.controller;

import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.domain.dto.DepartmentWithoutEmployeesResponse;
import be.pxl.services.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final IDepartmentService departmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse getDepartment(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping(path = "/organization/{organizationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentWithoutEmployeesResponse> findByOrganization(@PathVariable Long organizationId) {
        return departmentService.getDepartmentByOrganizationId(organizationId);
    }

    @GetMapping(path = "/organization/{organizationId}/with-employees")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> findByOrganizationWithEmployees(@PathVariable Long organizationId) {
        return departmentService.getDepartmentWithEmployeesByOrganizationId(organizationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDepartment(@RequestBody final DepartmentRequest departmentRequest) {
        departmentService.addDepartment(departmentRequest);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDepartment(@PathVariable final Long id, @RequestBody final DepartmentRequest departmentRequest) {
        departmentService.updateDepartment(id, departmentRequest);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable final Long id) {
        departmentService.deleteDepartment(id);
    }
}
