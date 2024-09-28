package be.pxl.services.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEmbeddable {
    @Column(name = "organization_id")
    private Long organizationId;
    private String name;
    private String position;

    @ElementCollection
    private List<EmployeeEmbeddable> employees;
}
