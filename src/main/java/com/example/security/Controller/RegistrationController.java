package com.example.security.Controller;


import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/signup")
    public ModelAndView registerationForm(){
        return  new ModelAndView("registrationPage","user",new User());
    }

    @Autowired
    UserRepository repository;

    @RequestMapping("user/register")
    public String registerUser(final User user , final BindingResult bindingResult ){
        repository.save(user);

        return "redirect:/login";

    }
}
