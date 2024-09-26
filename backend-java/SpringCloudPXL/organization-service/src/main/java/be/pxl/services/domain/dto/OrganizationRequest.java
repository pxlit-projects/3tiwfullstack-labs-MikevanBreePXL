package be.pxl.services.domain.dto;

import be.pxl.services.domain.DepartmentEmbeddable;
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
public class OrganizationRequest {
    private String name;
    private String address;
    private List<EmployeeEmbeddable> employeeList;
    private List<DepartmentEmbeddable> departmentList;
}
