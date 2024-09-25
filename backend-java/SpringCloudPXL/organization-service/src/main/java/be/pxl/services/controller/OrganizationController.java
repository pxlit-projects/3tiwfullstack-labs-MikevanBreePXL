package be.pxl.services.controller;

import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewOrganization(@RequestBody OrganizationRequest organizationRequest) {
        organizationService.addNewOrganization(organizationRequest);
    }
}
