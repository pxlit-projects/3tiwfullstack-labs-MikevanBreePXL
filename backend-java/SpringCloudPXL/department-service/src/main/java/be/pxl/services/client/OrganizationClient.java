package be.pxl.services.client;

import be.pxl.services.domain.dto.OrganizationWithDepartmentsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "organization-service")
public interface OrganizationClient {
    @GetMapping(path = "/{id}/with-departments")
    OrganizationWithDepartmentsRequest findByIdWithDepartments(@PathVariable Long id);
}
