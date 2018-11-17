package edu.cs.mum.gdmstore.uiservice.uiservice.controller;

import edu.cs.mum.gdmstore.uiservice.uiservice.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${supplier-service-url}")
    private String supplierServiceUrl;

    @RequestMapping(value = {"/suppliers", "/suppliers/browse"}, method = RequestMethod.GET)
    public ModelAndView displaySuppliers() {
        ModelAndView mav = new ModelAndView();
        String url = String.format("%s/%s", supplierServiceUrl, "suppliers");
        ResponseEntity<Supplier[]> forEntity = restTemplate.getForEntity(url, Supplier[].class);
        Supplier[] suppliers = forEntity.getBody();
        mav.addObject("suppliers", suppliers);
        mav.setViewName("suppliers/browse");
        return mav;
    }

    @RequestMapping(value = "/suppliers/new", method = RequestMethod.GET)
    public String createSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/new";
    }

    @RequestMapping(value = "/suppliers/new", method = RequestMethod.POST)
    public String createSupplier(@Valid @ModelAttribute("supplier") Supplier supplier,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("supplier", supplier);
            return "suppliers/new";
        }
        String url = String.format("%s/%s", supplierServiceUrl, "suppliers");
        ResponseEntity<Supplier[]> forEntity = restTemplate.postForEntity(url, supplier, Supplier[].class);
        return "redirect:/suppliers/browse";
    }

    @RequestMapping(value = "/suppliers/edit/{id}", method = RequestMethod.GET)
    public String editSupplier(@PathVariable Long id, Model model) {
        String url = String.format("%s/suppliers/%s", supplierServiceUrl, id);
        ResponseEntity<Supplier> forEntity = restTemplate.getForEntity(url, Supplier.class);
        if (forEntity.getBody() != null) {
            model.addAttribute("supplier", forEntity.getBody());
            return "suppliers/edit";
        }
        return "suppliers/browse";
    }

    @RequestMapping(value = "/suppliers/edit", method = RequestMethod.POST)
    public String updateSupplier(@Valid @ModelAttribute("supplier") Supplier supplier,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("supplier", supplier);
            return "suppliers/edit";
        }
        String url = String.format("%s/suppliers", supplierServiceUrl);
        ResponseEntity<Supplier> forEntity = restTemplate.exchange(url,HttpMethod.PUT,null, Supplier.class);

        return "redirect:/suppliers/browse";
    }
}
