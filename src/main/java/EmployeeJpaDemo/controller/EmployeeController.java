package EmployeeJpaDemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EmployeeJpaDemo.EmployeeRepo;
import EmployeeJpaDemo.entity.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepo employeerepo;

	@PostMapping("/save")
	 public ResponseEntity<Employee> saveCustomer(@RequestBody Employee employee){
		return new ResponseEntity<>(employeerepo.save(employee),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getEmployes(){
		return new ResponseEntity<>(employeerepo.findAll(),HttpStatus.OK);
		
		}
	
	@GetMapping(path="/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id){
		Optional<Employee> employee= employeerepo.findById(id);
		if(employee.isPresent()) {
			return new ResponseEntity<>(employee.get(),HttpStatus.OK);
		}
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return null;
		}   	

	@DeleteMapping(path="/deleteEmployeeById/{id}")
	// add a delete method
	public ResponseEntity<Void> deleteCustomer(@PathVariable long id){
		if(employeerepo.existsById(id)) {
			employeerepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee cust){
		Optional<Employee> customer= employeerepo.findById(id);
		if(customer.isPresent()) {
			customer.get().setEmployeeName(cust.getEmployeeName());
			customer.get().setEmployeeEmail(cust.getEmployeeEmail());
			customer.get().setEmployeeAddress(cust.getEmployeeAddress());
			
			return new ResponseEntity<>(employeerepo.save(customer.get()),HttpStatus.OK);
		}
				
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
private void sysout() {
	// TODO Auto-generated method stub

}
}
