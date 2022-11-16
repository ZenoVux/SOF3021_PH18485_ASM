package sof3021.ph18485.repositoties;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sof3021.ph18485.entities.Product;
import sof3021.ph18485.entities.ReportCategory;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	

	@Query("select p from Product p where p.discount > 0 and p.quantity > 0 and p.available = 1")
	List<Product> findByDiscountIsYes(Pageable pageable);
	
	@Query("select p from Product p where p.category.id=:cid")
	List<Product> findByCategoryId(@Param("cid") Integer cid);
	
	@Query("select p from Product p where p.category.id=:cid and p.id!=:pid and p.quantity > 0 and p.available = 1")
	List<Product> findByCategoryIdExceptProductId(@Param("cid") Integer cid, @Param("pid") Integer pid, Pageable pageable);
	
	@Query("select p from Product p where p.category.id=:cid")
	Page<Product> findByCategoryId(@Param("cid") Integer cid, Pageable pageable);
	
	@Query("select p from Product p where p.category.id=:cid and p.quantity > 0 and p.available = 1")
	Page<Product> findByCategoryIdAndQuantity(@Param("cid") Integer cid, Pageable pageable);
	
	@Query("select p from Product p where p.name like :keyword")
	Page<Product> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("select p from Product p where p.name like :keyword and p.quantity > 0 and p.available = 1")
	Page<Product> findByKeywordAndQuantity(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("select p from Product p where p.name like :keyword and p.category.id=:cid")
	Page<Product> findByKeywordAndCategoryId(@Param("keyword") String keyword, @Param("cid") Integer cid, Pageable pageable);
	

}