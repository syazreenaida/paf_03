package vttp2022.paf.assessment.eshop.respositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Customer;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class CustomerRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	// You cannot change the method's signature
	public Optional<Customer> findCustomerByName(String name) {
		
		// TODO: Task 3 

		//perform query
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_BY_CUSTOMERNAME, name);
		
		final List<Customer> cust = new LinkedList<>();

		//attemp to next row
		while(rs.next())
			cust.add(Customer.create(rs));
		
		if(!rs.next()){
			return Optional.empty();
		}else{
			//String userName = rs.getString("name");
			return Optional.of(cust.get(0));
		}
		
		
	}


}
