package shah.couponservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import shah.couponservice.model.Coupon;


public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
