package be.pxl.services.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentWithoutEmployeesResponse {
    private Long organizationId;
    private String name;
    private String position;
}
