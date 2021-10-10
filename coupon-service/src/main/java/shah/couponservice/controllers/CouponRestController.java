package shah.couponservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shah.couponservice.model.Coupon;
import shah.couponservice.repos.CouponRepo;

@RestController
@RequestMapping("/couponapi")
@CrossOrigin
public class CouponRestController {

	@Autowired
	CouponRepo repo;

	@PostMapping("/coupons")
	public ResponseEntity<?> create(@RequestBody Coupon coupon) {
		System.out.println("adding coupon...");
		Coupon save = null;
		try {
			save = repo.save(coupon);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Check SQL Contraints", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(save, HttpStatus.OK);

	}

	@GetMapping("/coupons/{code}")
	public Coupon getCoupon(@PathVariable("code") String code) {
		System.out.println("fetching coupon....");
		return repo.findByCode(code);

	}
}
