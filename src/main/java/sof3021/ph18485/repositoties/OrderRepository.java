package sof3021.ph18485.repositoties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sof3021.ph18485.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("select o from Order o where o.account.id=:accountId")
	Page<Order> findByAccountId(@Param("accountId") Integer accountId, Pageable pageable);
}
