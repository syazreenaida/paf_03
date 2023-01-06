package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    public static final String SQL_FIND_BY_CUSTOMERNAME =
        "select *from customers where customer_name = '?'";

    public static final String SQL_INSERT_NEW_ORDER =
        "insert into order(order_id, delivery_id customer_name, ship_address, email_address, order_status, order_date) values('?', '?', '?', '?', '?', '?','?','?')";
    
}
