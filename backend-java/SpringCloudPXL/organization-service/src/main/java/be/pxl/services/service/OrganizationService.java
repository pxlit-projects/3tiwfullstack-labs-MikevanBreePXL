package be.pxl.services.service;

import be.pxl.services.client.DepartmentClient;
import be.pxl.services.client.EmployeeClient;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.*;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {
    private final OrganizationRepository organizationRepository;
    private final EmployeeClient employeeClient;
    private final DepartmentClient departmentClient;

    @Override
    public List<OrganizationResponse> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream()
                .map(this::mapToOrganizationResponse)
                .toList();
    }

    @Override
    public OrganizationResponse getOrganizationById(Long id) {
        Organization organization = organizationRepository.getReferenceById(id);
        return mapToOrganizationResponse(organization);
    }

    @Override
    public OrganizationWithDepartmentsResponse getOrganizationWithDepartmentsById(Long id) {
        Organization queryOrganization = organizationRepository.findById(id).orElseThrow();
        return OrganizationWithDepartmentsResponse.builder()
                .name(queryOrganization.getName())
                .address(queryOrganization.getAddress())
                .departments(departmentClient.findByOrganizationWithEmployees(id))
                .build();
    }

    @Override
    public OrganizationWithDepartmentsAndEmployeesResponse getOrganizationWithDepartmentsAndEmployeesById(Long id) {
        Organization queryOrganization = organizationRepository.findById(id).orElseThrow();
        return OrganizationWithDepartmentsAndEmployeesResponse.builder()
                .name(queryOrganization.getName())
                .address(queryOrganization.getAddress())
                .departments(departmentClient.findByOrganizationWithEmployees(id))
                .employees(employeeClient.findByOrganizationId(id))
                .build();
    }

    @Override
    public OrganizationWithEmployeesResponse getOrganizationWithEmployeesById(Long id) {
        Organization queryOrganization = organizationRepository.findById(id).orElseThrow();
        return OrganizationWithEmployeesResponse.builder()
                .name(queryOrganization.getName())
                .address(queryOrganization.getAddress())
                .employees(queryOrganization.getEmployeeList())
                .build();
    }

    @Override
    public void addNewOrganization(OrganizationRequest request) {
        Organization organization = Organization.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();

        organizationRepository.saveAndFlush(organization);
    }

    @Override
    public void updateOrganization(Long id, OrganizationRequest organization) {
        Organization entity = organizationRepository.getReferenceById(id);
        entity.setName(organization.getName());
        entity.setAddress(organization.getAddress());
        
        organizationRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
        organizationRepository.flush();
    }

    // PRIVATE METHODS //
    private OrganizationResponse mapToOrganizationResponse(Organization entity) {
        return OrganizationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .build();
    }
}
