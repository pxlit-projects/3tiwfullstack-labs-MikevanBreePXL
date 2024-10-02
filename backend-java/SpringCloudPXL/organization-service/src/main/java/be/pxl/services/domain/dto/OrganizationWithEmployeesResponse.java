package be.pxl.services.domain.dto;

import be.pxl.services.domain.EmployeeEmbeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationWithEmployeesResponse {
    private String name;
    private String address;
    private List<EmployeeEmbeddable> employees;
}
