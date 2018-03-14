package com.gmail.dmitrypashko.dmitry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gmail.dmitrypashko.dmitry.model.User;
import com.gmail.dmitrypashko.dmitry.modelDTO.AppUserPrincipal;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/user/addOrder", method = RequestMethod.GET)
    public String addOrderGet() throws IOException {
        return "redirect:/user/cart";
    }

    @RequestMapping(value = "/user/addOrder", method = RequestMethod.POST)
    public String addOrder(HttpServletRequest req) throws IOException {
        String[] productId = req.getParameterValues("productId");
        String[] quantity = req.getParameterValues("quantity");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal userPrincipal = (AppUserPrincipal) auth.getPrincipal();
        User user = userPrincipal.getUser();
        if (productId != null && quantity != null) {
            orderService.saveOrder(productId, quantity, user);
            req.getSession().removeAttribute("productInCart");
        }
        return "redirect:/user/cart";
    }

    @RequestMapping(value = "/user/orders/page/{pagination}", method = RequestMethod.GET)
    public String showOrders(HttpServletRequest req, @PathVariable Integer pagination) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal userPrincipal = (AppUserPrincipal) auth.getPrincipal();
        User user = userPrincipal.getUser();
        req.setAttribute("paginations", orderService.getPaginationList());
        req.setAttribute("orders", orderService.showOrdersByUserEmail(user.getEmail(), pagination - 1));
        return "order";
    }

    @RequestMapping(value = "/admin/orders/page/{pagination}", method = RequestMethod.GET)
    public String showAllOrders(HttpServletRequest req, @PathVariable Integer pagination) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("role", user.getUser().getRole().name());
        req.setAttribute("paginations", orderService.getPaginationList());
        req.setAttribute("orders", orderService.showOAllOrders(pagination - 1));
        return "admin_order";
    }

    @RequestMapping(value = "/admin/changeStatusOrder", method = RequestMethod.POST)
    public String changeStatusOrder(HttpServletRequest req) throws IOException {
        String status = req.getParameter("status");
        String id = req.getParameter("orderId");
        orderService.changeStatus(status, Long.parseLong(id));
        return "redirect:/admin/orders/page/1";
    }


}
