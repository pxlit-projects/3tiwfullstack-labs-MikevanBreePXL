package be.pxl.services.client;

import be.pxl.services.domain.EmployeeEmbeddable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-service", path = "/api/employee")
public interface EmployeeClient {
    @GetMapping(path = "/department/{departmentId}")
    List<EmployeeEmbeddable> findByDepartment(@PathVariable Long departmentId);
}
