package be.pxl.services.domain.dto;

import be.pxl.services.domain.Department;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequest {
    private String name;
    private String address;

    @ElementCollection
    private List<Department> departmentList;
}
