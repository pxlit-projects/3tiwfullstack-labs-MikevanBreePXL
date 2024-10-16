package be.pxl.services.client;

import be.pxl.services.domain.DepartmentEmbeddable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "department-service", path = "/api/department")
public interface DepartmentClient {
    @GetMapping
    List<DepartmentEmbeddable> getAllDepartments();

    @GetMapping(path = "/{id}")
    DepartmentEmbeddable getDepartment(@PathVariable Long id);

    @GetMapping(path = "/organization/{organizationId}/with-employees")
    List<DepartmentEmbeddable> findByOrganizationWithEmployees(@PathVariable Long organizationId);

    @PutMapping(path = "/{id}")
    void updateDepartment(@PathVariable final Long id, @RequestBody final DepartmentEmbeddable departmentRequest);
}
