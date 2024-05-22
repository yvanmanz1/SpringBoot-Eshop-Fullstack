package com.example.ecommfullstack.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommfullstack.Models.AppUserRole;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String showHomePage(){
		return "index";
	}
	
	@GetMapping("/auth")
	public String showAuthPage(){
		return "authentication";
	}
	
	@GetMapping("/ordered")
	public String showOrdered(){
		return "ordered";
	}
	
	@GetMapping("/admin/prod")
	public String showAdminPage(){
		return "admin";
	}
	
//	@PreAuthorize("hasRole('AppUserRole.ADMIN')") 
	@GetMapping("/admin/landing")
	public String showAdminLanding(){
		return "landingadmin";
	}
	
	@GetMapping("/homepage")
	public String showHomePae(){
		return "homepage";
	}
	
	@GetMapping("/basketcart")
	public String showCart(){
		return "basket";
	}
	
	@GetMapping("/shopping")
	public String showShoppingPage(){
		return "shop";
	}
	
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Perform logout actions (optional)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
	
	@GetMapping("/login?logout")
	public String showAuthPge(){
		return "authentication";
	}

}
