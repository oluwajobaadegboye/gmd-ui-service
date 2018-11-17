package edu.cs.mum.gdmstore.uiservice.uiservice.controller;

import edu.cs.mum.gdmstore.uiservice.uiservice.model.Product;
import edu.cs.mum.gdmstore.uiservice.uiservice.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;
	@Value("${product-service-url}")
    private String productServiceUrl;

    @Value("${supplier-service-url}")
    private String supplierServiceUrl;

	@RequestMapping(value={"/products","/products/browse"}, method=RequestMethod.GET)
	public ModelAndView displayProducts() {
		ModelAndView mav = new ModelAndView();
		String url = String.format("%s/%s",productServiceUrl,"products");
        ResponseEntity<Product[]> forEntity = restTemplate.getForEntity(url, Product[].class);
        Product[] products = forEntity.getBody();
        mav.addObject("products", products);
		mav.setViewName("products/browse");
		return mav;
	}
	
	@RequestMapping(value="/products/new", method = RequestMethod.GET)
	public String createProductForm(Model model){
		model.addAttribute("product", new Product());
        String url = String.format("%s/%s",supplierServiceUrl,"suppliers");
        ResponseEntity<Supplier[]> forEntity = restTemplate.getForEntity(url, Supplier[].class);
        Supplier[] suppliers = forEntity.getBody();
		model.addAttribute("suppliers", suppliers);
		return "products/new";
	}
	
	@PostMapping(value = "/products/new")
	public String createProduct(@Valid @ModelAttribute("products") Product product,
                                     BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			model.addAttribute("product",product);
			return "products/new";
		}
        String url = String.format("%s/%s",productServiceUrl,"products");
		ResponseEntity<Product> responseEntity = restTemplate.postForEntity(url,product,Product.class);
//		service.save(product);
		return "redirect:/products/browse";
	}
	
	@RequestMapping(value="/products/edit/{id}", method = RequestMethod.GET)
	public String editStudent(@PathVariable Long id, Model model){
        String url = String.format("%s/products/%s",productServiceUrl,id);
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url,Product.class);
        Product s = responseEntity.getBody();
		if (s != null) {
			model.addAttribute("product", s);
			return "products/edit";
		}
		return "products/browse";
	}
	
	@RequestMapping(value = "/products/edit", method = RequestMethod.POST)
	public String updateStudent(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("product",product);
			return "products/edit";
		}
        String url = String.format("%s/products",productServiceUrl);
        ResponseEntity<Product> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,null,Product.class);
        Product s = responseEntity.getBody();
		return "redirect:/products/browse";
	}
}
