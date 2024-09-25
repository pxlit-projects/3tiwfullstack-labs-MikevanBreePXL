package be.pxl.services.service;

import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {
    List<OrganizationResponse> getAllOrganizations();
    void addOrganization(OrganizationRequest request);
}
