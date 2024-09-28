package be.pxl.services.service;

import be.pxl.services.domain.dto.*;

import java.util.List;

public interface IOrganizationService {
    OrganizationResponse getOrganizationById(Long id);
    OrganizationWithDepartmentsResponse getOrganizationWithDepartmentsById(Long id);
    OrganizationWithDepartmentsAndEmployeesResponse getOrganizationWithDepartmentsAndEmployeesById(Long id);
    OrganizationWithEmployeesResponse getOrganizationWithEmployeesById(Long id);
    void addNewOrganization(OrganizationRequest organization);
    void updateOrganization(Long id, OrganizationRequest organization);
    void deleteOrganization(Long id);
}
