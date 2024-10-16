package be.pxl.services.controller;

import be.pxl.services.domain.dto.EmployeeCreateRequest;
import be.pxl.services.domain.dto.EmployeeDetailRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;

    /*
    * Method 1 for doing HTTP statuses: ResponseEntity(<Void>)
    *
    *   @GetMapping
    *   public ResponseEntity getEmployees() {
    *     return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    *   }
    *
    *   Method 2 for doing HTTP statuses: @ResponseStatus(HttpStatus.< >)
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping(path = "/department/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> findByDepartment(@PathVariable Long departmentId) {
        return employeeService.findByDepartmentId(departmentId);
    }

    @GetMapping(path = "/organization/{organizationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> findByOrganizationId(@PathVariable Long organizationId) {
        return employeeService.findByOrganizationId(organizationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEmployee(@RequestBody EmployeeCreateRequest request) {
        employeeService.addEmployee(request);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@PathVariable long id, @RequestBody EmployeeDetailRequest request) {
        employeeService.updateEmployee(id, request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }
}
