package be.pxl.services.controller;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;

    /*
    * Method 1 for doing HTTP statuses: ResponseEntity(<Void>)
    */
    @GetMapping
    public ResponseEntity getEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    /*
     * Method 2 for doing HTTP statuses: @ResponseStatus(HttpStatus.)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEmployee(@RequestBody EmployeeRequest request) {
        employeeService.addEmployee(request);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@PathVariable long id, @RequestBody EmployeeRequest request) {
        employeeService.updateEmployee(id, request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }
}
