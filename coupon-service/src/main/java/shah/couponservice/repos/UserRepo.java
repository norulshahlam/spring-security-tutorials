package shah.couponservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import shah.couponservice.model.User;


public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
