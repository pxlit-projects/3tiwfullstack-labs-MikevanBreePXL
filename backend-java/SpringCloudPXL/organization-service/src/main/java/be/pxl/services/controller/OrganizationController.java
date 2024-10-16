package be.pxl.services.controller;

import be.pxl.services.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import be.pxl.services.service.IOrganizationService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final IOrganizationService organizationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationResponse> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }

    @GetMapping(path = "/{id}/with-departments")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationWithDepartmentsResponse findByIdWithDepartments(@PathVariable Long id) {
        return organizationService.getOrganizationWithDepartmentsById(id);
    }

    @GetMapping(path = "/{id}/with-departments-and-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationWithDepartmentsAndEmployeesResponse findByIdWithDepartmentsAndEmployees(@PathVariable Long id) {
        return organizationService.getOrganizationWithDepartmentsAndEmployeesById(id);
    }

    @GetMapping(path = "/{id}/with-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationWithEmployeesResponse findByIdWithEmployeesResponse(@PathVariable Long id) {
        return organizationService.getOrganizationWithEmployeesById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewOrganization(@RequestBody OrganizationRequest organizationRequest) {
        organizationService.addNewOrganization(organizationRequest);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrganization(@PathVariable Long id, @RequestBody OrganizationRequest organizationRequest) {
        organizationService.updateOrganization(id, organizationRequest);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
    }
}
