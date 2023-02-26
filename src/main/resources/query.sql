select * from customer;
select * from orders;



SELECT a.customerid, a.customer_name, a.country, b.amount, b.vat
FROM customer a
         INNER JOIN orders b ON a.customerid=b.customerid
where a.customerid=3;