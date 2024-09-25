package be.pxl.services.service;

import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {
    List<OrganizationResponse> getAllOrganizations();
    OrganizationResponse getOrganizationById(Long id);
    void addNewOrganization(OrganizationRequest organization);
    void updateOrganization(Long id, OrganizationRequest organization);
    void deleteOrganization(Long id);
}
