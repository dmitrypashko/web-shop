package com.gmail.dmitrypashko.dmitry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gmail.dmitrypashko.dmitry.model.Product;
import com.gmail.dmitrypashko.dmitry.modelDTO.AppUserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/user/productAddCart", method = RequestMethod.POST)
    public String productAddCart(HttpServletRequest req) throws IOException {
        String idProduct = req.getParameter("idProduct");
        HttpSession session = req.getSession(true);
        List<Product> cart = (ArrayList<Product>) session.getAttribute("productInCart");
        Product product = productService.getProduct(Long.parseLong(idProduct));
        if (cart == null) {
            List<Product> products = new ArrayList<>();
            products.add(product);
            session.setAttribute("productInCart", products);
        } else {
            boolean productAlreadyInCart = false;
            for (Product aCart : cart) {
                if (aCart.getId() == Long.parseLong(idProduct)) {
                    productAlreadyInCart = true;

                }
            }
            if (!productAlreadyInCart) {
                cart.add(product);
            }
        }

        return "redirect:/user/product/page/1";
    }

    @RequestMapping(value = "/user/cart", method = RequestMethod.GET)
    public String cart(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("UTF-8");
        List<Product> products = (ArrayList<Product>) req.getSession().getAttribute("productInCart");
        req.setAttribute("productInCart", products);
        return "cart";
    }

    @RequestMapping(value = "/user/deleteProductInCart", method = RequestMethod.POST)
    public String deleteProductInCart(HttpServletRequest req) throws IOException {
        String[] idProduct = req.getParameterValues("products");
        if (idProduct != null) {
            List<Product> products = (ArrayList<Product>) req.getSession().getAttribute("productInCart");
            for (String product : idProduct) {
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getId() == Long.parseLong(product)) {
                        products.remove(products.get(i));
                    }
                }
            }
            req.getSession().setAttribute("productInCart", products);
        }
        return "redirect:/user/cart";
    }
}
