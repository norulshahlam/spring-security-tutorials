package shah.couponservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import shah.couponservice.model.Role;


public interface RoleRepo extends JpaRepository<Role, Long> {

}
