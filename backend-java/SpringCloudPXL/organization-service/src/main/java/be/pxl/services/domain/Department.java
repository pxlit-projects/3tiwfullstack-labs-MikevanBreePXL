package be.pxl.services.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Department {
    private Long organizationId;
    private String name;
    private String position;

    @ElementCollection
    private List<Employee> employeeList;
}
