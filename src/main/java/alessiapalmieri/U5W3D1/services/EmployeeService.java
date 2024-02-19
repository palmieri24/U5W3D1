package alessiapalmieri.U5W3D1.services;

import alessiapalmieri.U5W3D1.DTO.NewEmployeeDTO;
import alessiapalmieri.U5W3D1.entities.Employee;
import alessiapalmieri.U5W3D1.exceptions.BadRequestException;
import alessiapalmieri.U5W3D1.exceptions.NotFoundException;
import alessiapalmieri.U5W3D1.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getEmployees(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return employeeRepository.findAll(pageable);
    }

    public Employee findById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Employee save(NewEmployeeDTO employee){
        employeeRepository.findByEmail(employee.email()).ifPresent(employee1 -> {
            throw new BadRequestException("Email " + employee.email() + " is already in use!");
        });
        employeeRepository.findByUsername(employee.username()).ifPresent(employee1 -> {
            throw new BadRequestException("Username " + employee.username() + " is already in use!");
        });

        Employee newEmployee = new Employee();
        newEmployee.setUsername(employee.username());
        newEmployee.setLastname(employee.lastname());
        newEmployee.setName(employee.name());
        newEmployee.setEmail(employee.email());
        newEmployee.setAvatarURL("https://ui-avatars.com/api/?name=" + employee.name() + "+" + employee.lastname());
        return employeeRepository.save(newEmployee);
    }

    public Employee findByIdAndUpdate(UUID id, Employee body){
        Employee found = this.findById(id);
        found.setUsername(body.getUsername());
        found.setName(body.getName());
        found.setLastname(body.getLastname());
        found.setEmail(body.getEmail());
        found.setAvatarURL(body.getAvatarURL());
        return employeeRepository.save(found);
    }
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Employee found = this.findById(id);
        employeeRepository.delete(found);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " non trovata"));
    }

//    public Employee uploadAvatar(long id, MultipartFile file) throws IOException{
//        Employee found = this.findById(id);
//        String avatarURL = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
//        found.setAvatarURL(avatarURL);
//        return employeeRepository.save(found);
//    }
}
