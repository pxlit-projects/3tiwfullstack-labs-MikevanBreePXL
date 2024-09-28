package be.pxl.services.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEmbeddable {
    @Column(name = "organization_id")
    private Long organizationId;
    @Column(name = "department_id")
    private Long departmentId;
    private String name;
    private int age;
    private String position;
}
