package be.pxl.services.service;

import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public List<OrganizationResponse> getAllOrganizations() {
        return organizationRepository.findAll().stream().map(organization -> mapToOrganizationResponse(organization)).toList();
    }

    private OrganizationResponse mapToOrganizationResponse(Organization entity) {
        return OrganizationResponse.builder()
                .name(entity.getName())
                .address(entity.getAddress())
                .employeeList(entity.getEmployeeList())
                .departmentList(entity.getDepartmentList())
                .build();
    }

    @Override
    public void addOrganization(OrganizationRequest request) {
        Organization organization = Organization.builder()
                .name(request.getName())
                .address(request.getAddress())
                .employeeList(request.getEmployeeList())
                .departmentList(request.getDepartmentList())
                .build();

        organizationRepository.save(organization);
    }
}
