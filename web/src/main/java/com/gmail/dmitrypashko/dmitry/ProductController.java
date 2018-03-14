package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gmail.dmitrypashko.dmitry.model.Product;
import com.gmail.dmitrypashko.dmitry.modelDTO.AppUserPrincipal;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ProductValidator productValidator;


    @RequestMapping(value = "/admin/addProduct/page/{pagination}", method = RequestMethod.POST)
    public String addProduct(HttpServletRequest req, @ModelAttribute("product") Product product, BindingResult bindingResult, @PathVariable Integer pagination) throws IOException {
        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            req.setAttribute("paginations", productService.getPaginationList());
            req.setAttribute("products", productService.getAllProduct(pagination - 1));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
            req.setAttribute("role", user.getUser().getRole().name());
            return "add_product";
        }
        productService.addProduct(product);
        return "redirect:/admin/addProduct/page/{pagination}";
    }

    @RequestMapping(value = "/admin/addProduct/page/{pagination}", method = RequestMethod.GET)
    public String addProductGet(HttpServletRequest req, Model model, @PathVariable Integer pagination) throws IOException {
        model.addAttribute("product", new Product());
        req.setAttribute("paginations", productService.getPaginationList());
        req.setAttribute("products", productService.getAllProduct(pagination - 1));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("role", user.getUser().getRole().name());
        return "add_product";
    }


    @RequestMapping(value = "/admin/deleteProduct", method = RequestMethod.POST)
    public String deleteProduct(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String idProduct = req.getParameter("idProduct");
        productService.deleteProduct(Long.parseLong(idProduct));
        return "redirect:/admin/product/page/1";
    }

    @RequestMapping(value = "/admin/deleteProduct", method = RequestMethod.GET)
    public String deleteProductGet(HttpServletRequest req) throws IOException {
        return "redirect:/admin/product/page/1";
    }


    @RequestMapping(value = "/admin/product/page/{pagination}", method = RequestMethod.GET)
    public String adminProductGet(HttpServletRequest req, @PathVariable Integer pagination) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("role", user.getUser().getRole().name());
        req.setAttribute("paginations", productService.getPaginationList());
        req.setAttribute("product", productService.getAllProduct(pagination - 1));
        return "admin_product";
    }

    @RequestMapping(value = "/user/product/page/{pagination}", method = RequestMethod.GET)
    public String userProductGet(HttpServletRequest req, @PathVariable Integer pagination) throws IOException {
        req.setAttribute("paginations", productService.getPaginationList());
        req.setAttribute("product", productService.getAllProduct(pagination - 1));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("email", user.getUser().getEmail());
        return "product";
    }
}
