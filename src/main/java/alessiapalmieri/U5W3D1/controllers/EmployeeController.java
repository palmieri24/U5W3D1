package alessiapalmieri.U5W3D1.controllers;

import alessiapalmieri.U5W3D1.DTO.NewEmployeeDTO;
import alessiapalmieri.U5W3D1.entities.Employee;
import alessiapalmieri.U5W3D1.exceptions.BadRequestException;
import alessiapalmieri.U5W3D1.exceptions.NotFoundException;
import alessiapalmieri.U5W3D1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Page<Employee> getEmployee(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy) {
        return employeeService.getEmployees(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    public Employee findById(@PathVariable UUID id){
        return employeeService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody @Validated NewEmployeeDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return employeeService.save(body);
        }
    }

    @PutMapping("/{id}")
    public Employee findByIdAndUpdate(@PathVariable UUID id, @RequestBody  Employee body ){
        return employeeService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) throws NotFoundException {
        employeeService.findByIdAndDelete(id);
    }

//    @PatchMapping("/{id}/avatar")
//    public Employee uploadAvatar(@RequestParam("avatar")MultipartFile file, @PathVariable long id){
//        try {
//            return employeeService.uploadAvatar(id,file);
//        } catch (IOException e){
//            throw new RuntimeException(e);
//        }
//    }


}
