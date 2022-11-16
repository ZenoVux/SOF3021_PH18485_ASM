package sof3021.ph18485.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sof3021.ph18485.entities.Account;
import sof3021.ph18485.entities.Order;
import sof3021.ph18485.repositoties.AccountRepository;
import sof3021.ph18485.repositoties.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public List<Order> findAll() {
		return orderRepo.findAll();
	}

	public Order save(Order order) {
		return orderRepo.save(order);
	}
	
	public Order create(String address, Account account) {
		Order order = new Order();
		order.setAddress(address);
		order.setAccount(account);
		order.setCreatedDate(new Date(System.currentTimeMillis()));
		return orderRepo.save(order);
	}
	
	public Order findById(Integer id) {
		Optional<Order> optional = orderRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Page<Order> findByAccountId( Integer accountId, Pageable pageable) {
		return orderRepo.findByAccountId(accountId, pageable);
	}

	public Page<Order> findAll(Pageable pageable) {
		return orderRepo.findAll(pageable);
	}

	public void deleteById(Integer id) {
		try {
			orderRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
