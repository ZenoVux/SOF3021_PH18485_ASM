package sof3021.ph18485.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sof3021.ph18485.entities.Product;
import sof3021.ph18485.repositoties.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	public void updateQuantity(Product product, int quantity) {
		product.setQuantity(quantity);
		productRepo.save(product);
	}
	
	public Product findById(Integer id) {
		Optional<Product> optional = productRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Page<Product> findByKeyword(String keyword, Pageable pageable) {
		return productRepo.findByKeyword(keyword, pageable);
	}

	public Page<Product> findByKeywordAndQuantity(String keyword, Pageable pageable) {
		return productRepo.findByKeywordAndQuantity(keyword, pageable);
	}
	
	public Page<Product> findByCategoryId(Integer cid, Pageable pageable) {
		return productRepo.findByCategoryId(cid, pageable);
	}
	
	public Page<Product> findByCategoryIdAndQuantity(Integer cid, Pageable pageable) {
		return productRepo.findByCategoryIdAndQuantity(cid, pageable);
	}
	
	public List<Product> findByCategoryIdExceptProductId(Integer cid, Integer pid, Pageable pageable) {
		return productRepo.findByCategoryIdExceptProductId(cid, pid, pageable);
	}
	
	public List<Product> findByDiscountIsYes(Pageable pageable) {
		return productRepo.findByDiscountIsYes(pageable);
	}

	public void deleteById(Integer id) {
		try {
			productRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Product> findByCategoryId(Integer cid) {
		return productRepo.findByCategoryId(cid);
	}

	public Product save(Product product) {
		return productRepo.save(product);
	}

}
