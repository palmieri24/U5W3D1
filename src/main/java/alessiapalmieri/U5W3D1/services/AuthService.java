package alessiapalmieri.U5W3D1.services;

import alessiapalmieri.U5W3D1.DTO.EmployeeLoginDTO;
import alessiapalmieri.U5W3D1.entities.Employee;
import alessiapalmieri.U5W3D1.exceptions.UnauthorizedException;
import alessiapalmieri.U5W3D1.repositories.EmployeeRepository;
import alessiapalmieri.U5W3D1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(EmployeeLoginDTO payload) {
        Employee employee = employeeService.findByEmail(payload.email());
        if (employee.getPassword().equals(payload.password())) {

            return jwtTools.createToken(employee);
        } else {
            throw new UnauthorizedException("Credenziali sbagliate!");
        }


    }
}
