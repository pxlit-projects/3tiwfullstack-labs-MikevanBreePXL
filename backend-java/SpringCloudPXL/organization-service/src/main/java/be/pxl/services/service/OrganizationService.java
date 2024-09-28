package be.pxl.services.service;

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

    @Override
    public OrganizationResponse getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .map(organization -> mapToOrganizationResponse(organization)).orElseThrow();
    }

    @Override
    public OrganizationWithDepartmentsResponse getOrganizationWithDepartmentsById(Long id) {
        Organization queryOrganization = organizationRepository.findById(id).orElseThrow();
        return new OrganizationWithDepartmentsResponse(queryOrganization.getName(), queryOrganization.getAddress(), queryOrganization.getDepartmentList());
    }

    @Override
    public OrganizationWithDepartmentsAndEmployeesResponse getOrganizationWithDepartmentsAndEmployeesById(Long id) {
        Organization queryOrganization = organizationRepository.findById(id).orElseThrow();
        return new OrganizationWithDepartmentsAndEmployeesResponse(queryOrganization.getName(), queryOrganization.getAddress(), queryOrganization.getDepartmentList(), queryOrganization.getEmployeeList());
    }

    @Override
    public OrganizationWithEmployeesResponse getOrganizationWithEmployeesById(Long id) {
        Organization queryOrganization = organizationRepository.findById(id).orElseThrow();
        return new OrganizationWithEmployeesResponse(queryOrganization.getName(), queryOrganization.getAddress(), queryOrganization.getEmployeeList());
    }

    @Override
    public void addNewOrganization(OrganizationRequest request) {
        Organization organization = Organization.builder()
                .name(request.getName())
                .address(request.getAddress())
                .employeeList(request.getEmployeeList())
                .departmentList(request.getDepartmentList())
                .build();

        organizationRepository.save(organization);
    }

    @Override
    public void updateOrganization(Long id, OrganizationRequest organization) {
        Organization entity = organizationRepository.getReferenceById(id);
        entity.setName(organization.getName());
        entity.setAddress(organization.getAddress());
        entity.setEmployeeList(organization.getEmployeeList());
        entity.setDepartmentList(organization.getDepartmentList());
        
        organizationRepository.save(entity);
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
        organizationRepository.flush();
    }

    // PRIVATE METHODS //
    private OrganizationResponse mapToOrganizationResponse(Organization entity) {
        return OrganizationResponse.builder()
                .name(entity.getName())
                .address(entity.getAddress())
                .build();
    }
}
