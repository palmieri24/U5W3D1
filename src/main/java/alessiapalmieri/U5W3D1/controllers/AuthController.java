package alessiapalmieri.U5W3D1.controllers;

import alessiapalmieri.U5W3D1.DTO.EmployeeLoginDTO;
import alessiapalmieri.U5W3D1.DTO.LoginResponseDTO;
import alessiapalmieri.U5W3D1.DTO.NewEmployeeDTO;
import alessiapalmieri.U5W3D1.entities.Employee;
import alessiapalmieri.U5W3D1.services.AuthService;
import alessiapalmieri.U5W3D1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody EmployeeLoginDTO payload){
        return new LoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody NewEmployeeDTO newEmployee) {
        return this.employeeService.save(newEmployee);
    }
}
