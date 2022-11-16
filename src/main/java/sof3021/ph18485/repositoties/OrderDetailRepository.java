package sof3021.ph18485.repositoties;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sof3021.ph18485.entities.OrderDetail;
import sof3021.ph18485.entities.ReportCategory;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	@Query("select od from OrderDetail od where od.order.id=:orderId")
	List<OrderDetail> findByOrderId(@Param("orderId") Integer orderId);

	@Query("select new ReportCategory(od.product.category, sum(od.price*od.quantity), sum(od.quantity)) from OrderDetail od group by od.product.category")
	List<ReportCategory> reportByCategory();
	
	@Query("select new ReportCategory(od.product.category, sum(od.price*od.quantity), sum(od.quantity)) from OrderDetail od group by od.product.category")
	List<ReportCategory> reportByProduct();

}