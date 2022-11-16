package sof3021.ph18485.repositoties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sof3021.ph18485.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("select c from Category c where c.name like :keyword")
	Page<Category> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
