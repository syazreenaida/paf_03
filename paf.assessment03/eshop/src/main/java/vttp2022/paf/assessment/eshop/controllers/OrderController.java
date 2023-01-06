package vttp2022.paf.assessment.eshop.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@RestController
@RequestMapping(path = "/index", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	//TODO: Task 3
	@Autowired
	private CustomerRepository custRepo;

	@Autowired 
	private OrderRepository ordRepo;

	//Task 3(a)
	@PostMapping(path="order")
	public ResponseEntity<String> findCustomerByName(@RequestBody Map<String, Object> payload){

		String name = (String) payload.get("name");
		System.out.println(name);
		
		//query the databse
		Optional<Customer> cust = custRepo.findCustomerByName(name);

		if(cust.isEmpty()){
			JsonObject error = Json.createObjectBuilder()
				.add("error", "Customer %%%s%%% not found".formatted(name))
				.build();
			
			return ResponseEntity
				.status (HttpStatus.NOT_FOUND)
				.body(error.toString());
		}else{

			return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(cust.toString());
		}
		
		
	}

	

	//Task 3(b)
	@PostMapping(path="order")
	public String postOder(@RequestBody MultiValueMap<String, String> form, Model model){

		//retireve data 
		String name = form.getFirst("name");

		//generate order id
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		System.out.printf("Issuing %s order id for %s\n", orderId, name);

		return "index";

	}

	//task 6
	@GetMapping(path="/order/{name}/status", consumes= MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<String> getOrder(@RequestParam String name){

		List<Order> order = ordRepo.getOrderName(name);

		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		for(Order o: order) {
            arrayBuilder.add(o.toJSON());
        }
        JsonArray result = arrayBuilder.build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(result.toString());


	}


	
}
