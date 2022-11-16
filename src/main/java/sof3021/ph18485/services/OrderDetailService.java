package sof3021.ph18485.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sof3021.ph18485.entities.OrderDetail;
import sof3021.ph18485.entities.ReportCategory;
import sof3021.ph18485.repositoties.OrderDetailRepository;

@Service
public class OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	public List<OrderDetail> findByOrderId( Integer orderId) {
		return orderDetailRepo.findByOrderId(orderId);
	}
	
	public List<OrderDetail> saveAll(List<OrderDetail> orderDetails) {
		return orderDetailRepo.saveAll(orderDetails);
	}
	
	public List<ReportCategory> reportByCategory() {
		return orderDetailRepo.reportByCategory();
	}

	public Page<OrderDetail> findAll(Pageable pageable) {
		return orderDetailRepo.findAll(pageable);
	}

	public <S extends OrderDetail> S save(S entity) {
		return orderDetailRepo.save(entity);
	}

	public OrderDetail findById(Integer id) {
		Optional<OrderDetail> optional = orderDetailRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public void deleteById(Integer id) {
		orderDetailRepo.deleteById(id);
	}
	
	
}
