package org.aspedemo.db.customer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.aspedemo.exception.AspeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping(path="/{id}")
	public Optional<Customer> getCustomer(@PathVariable Integer id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return customer;
		}
		throw new AspeException(404, "Unknown customer ID: " + id, "Customer not found");
	}
	
	@PostMapping(path="/create")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Customer createCustomer(@RequestBody Customer customer) {
		try {
			Integer id = customer.getId();
			if (id != null) {
				throw new AspeException(422, "id not allowed", "please use PUT /customer/{id}");
			}
			customer = customerRepository.save(customer);
		}
		catch (IllegalArgumentException e) {
			throw new AspeException(422, "Cannot save an empty customer");
		}
		catch (Exception e) {
			throw new AspeException(422, "Validation exception", e.getMessage());
		}
		
		if (null == customer.getId()) {
			throw new AspeException(500, "Save failed");
		}
		
		return customer;
	}
	
	@PutMapping(path="/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public @ResponseBody Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		try {
			customer.setId(id);
			customer = customerRepository.save(customer);
			return customer;
		}
		catch(IllegalArgumentException e) {
			throw new AspeException(422, "Validation Failed");
		}
	}
	
	@PostMapping(path="/search")
	public List<Customer> search(@RequestBody Map<String, Object> filter) {
		CustomerByFilterSpec spec = new CustomerByFilterSpec(filter);
		return customerRepository.findAll(spec);
	}
	
	@PostMapping(path="/count")
	public long count(@RequestBody Map<String, Object> filter) {
		CustomerByFilterSpec spec = new CustomerByFilterSpec(filter);
		return customerRepository.count(spec);
	}
}
