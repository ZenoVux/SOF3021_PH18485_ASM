package sof3021.ph18485.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sof3021.ph18485.entities.Category;
import sof3021.ph18485.repositoties.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	public Category create(String name) {
		Category category = new Category();
		category.setName(name);
		return categoryRepo.save(category);
	}
	
	public Category update(Category category, String name) {
		category.setName(name);
		return categoryRepo.save(category);
	}

	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	public Page<Category> findByKeyword(String keyword, Pageable pageable) {
		return categoryRepo.findByKeyword(keyword, pageable);
	}

	public Category findById(Integer id) {
		Optional<Category> optional = categoryRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void deleteById(Integer id) {
		try {
			categoryRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
