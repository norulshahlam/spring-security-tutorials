package shah.productservice.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import shah.productservice.model.Product;
import shah.productservice.dto.Coupon;
import shah.productservice.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceURL;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
	
		if(Objects.isNull(coupon)){
			product.setPrice(product.getPrice());
			return new ResponseEntity<Object>(repo.save(product),HttpStatus.OK);
		}

		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return new ResponseEntity<Object>(repo.save(product),HttpStatus.OK);
	}
}
