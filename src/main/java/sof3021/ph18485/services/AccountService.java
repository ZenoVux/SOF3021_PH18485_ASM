package sof3021.ph18485.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sof3021.ph18485.entities.Account;
import sof3021.ph18485.repositoties.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;

	public Account save(Account account) {
		return accountRepo.save(account);
	}
	
	public void updatePassword(Account account, String password) {
		account.setPassword(password);
		accountRepo.save(account);
	}
	
	public void updateProfile(Account account, String fullname, String email, String photo) {
		account.setFullname(fullname);
		account.setEmail(fullname);
		account.setPhoto(photo);
		accountRepo.save(account);
	}

	public boolean existsByUsername(String username) {
		return accountRepo.existsByUsername(username);
	}
	
	public boolean existsByEmail(String email) {
		return accountRepo.existsByEmail(email);
	}
	
	public boolean existsByUsernameAndEmail(String username, String email) {
		return accountRepo.existsByUsernameAndEmail(username, email);
	}

	public Account findById(Integer id) {
		Optional<Account> optional = accountRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Account findByEmail(String email) {
		Optional<Account> optional = accountRepo.findByEmail(email);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Account findByResetPasswordToken(String resetPasswordToken) {
		Optional<Account> optional = accountRepo.findByResetPasswordToken(resetPasswordToken);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Account findByUsername(String username) {
		Optional<Account> optional = accountRepo.findByUsername(username);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Account findByUsernameAndPassword(String username, String password) {
		Optional<Account> optional = accountRepo.findByUsernameAndPassword(username, password);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Page<Account> findByKeyword( String keyword, Pageable pageable) {
		return accountRepo.findByKeyword(keyword, pageable);
	}

	public void deleteById(Integer id) {
		try {
			accountRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Account> findAll() {
		return accountRepo.findAll();
	}

}
