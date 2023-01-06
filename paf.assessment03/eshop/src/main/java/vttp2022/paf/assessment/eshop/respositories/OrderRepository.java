package vttp2022.paf.assessment.eshop.respositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

import vttp2022.paf.assessment.eshop.models.Order;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class OrderRepository {
	// TODO: Task 3
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public void insertOrder(List<Order> o, String orderId, String order){

		List <Object[]> data = o.stream()
							.map(orders -> {
								Object[] l = new Object[7];
                                
                                l[0]=orders.getOrderId();
                                l[1]=orders.getDeliveryId();
								l[2]=orders.getName();
								l[3]=orders.getAddress();
								l[4]=orders.getEmail();
								l[5]=orders.getStatus();
								l[6]=orders.getOrderDate();
                                
                                return l;
							})
							.toList();
		jdbcTemplate.batchUpdate(SQL_INSERT_NEW_ORDER, data);
		
	}

    public List<Order> getOrderName(String name) {
        return null;
    }

    

	

}
