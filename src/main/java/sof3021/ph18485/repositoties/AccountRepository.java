package sof3021.ph18485.repositoties;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sof3021.ph18485.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("select count(acc) > 0 from Account acc where acc.username=:username")
	boolean existsByUsername(@Param("username") String username);
	
	@Query("select count(acc) > 0 from Account acc where acc.email=:email")
	boolean existsByEmail(@Param("email") String email);
	
	@Query("select count(acc) > 0 from Account acc where acc.username!=:username and acc.email=:email")
	boolean existsByUsernameAndEmail(@Param("username") String username, @Param("email") String email);
	
	@Query("select acc from Account acc where acc.email=:email")
	Optional<Account> findByEmail(@Param("email") String email);
	
	@Query("select acc from Account acc where acc.resetPasswordToken=:resetPasswordToken")
	Optional<Account> findByResetPasswordToken(@Param("resetPasswordToken") String resetPasswordToken);
	
	@Query("select acc from Account acc where acc.username=:username")
	Optional<Account> findByUsername(@Param("username") String username);
	
	@Query("select acc from Account acc where acc.username=:username and acc.password=:password")
	Optional<Account> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	@Query("select acc from Account acc where acc.fullname like :keyword")
	Page<Account> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
}
