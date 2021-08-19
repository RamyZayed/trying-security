package com.example.security.Controller;


import com.example.security.entity.User;
import com.example.security.entity.VerificationToken;
import com.example.security.repository.TokenRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/signup")
    public ModelAndView registerationForm(){

        return  new ModelAndView("registrationPage","user",new User());

    }

    @Autowired
    UserRepository repository;
    @Autowired
    TokenRepository tokenRepo;

    @Autowired
    PasswordEncoder encoder;


    @RequestMapping("user/register")
    public String registerUser(final User user , final BindingResult bindingResult , final HttpServletRequest request){

        user.setEnabled(false);
        user.setPassword(encoder.encode("pass"));
        repository.save(user);

        final String token = UUID.randomUUID().toString();

        final VerificationToken myToken = new VerificationToken(token,user);

        tokenRepo.save(myToken);

        final String appUrl = "http://"+request.getServerName() + ":"+request.getServerPort() + request.getContextPath();
        //sendVerificationEmail(user,token,appUrl);

        return "redirect:/login";

    }

    @RequestMapping("/registerationConfirm")
    public String confirmRegister(final Model model , @RequestParam("token") final String token , final RedirectAttributes redirectAttributes){
        final VerificationToken verificationToken = tokenRepo.findByToken(token);
        final User user = verificationToken.getUser();

        user.setEnabled(true);
        repository.save(user);

        redirectAttributes.addFlashAttribute("messeage","Your Account has been verified");
        return "redirect:/login";
    }

    @PostMapping("/user/resetPassword")
    @ResponseBody
    public void resetPassword(HttpServletRequest request,@RequestParam String email , RedirectAttributes redirectAttributes){
        final User user  = repository.findByEmail(email);
        if(user != null){
            final String token = UUID.randomUUID().toString();
          // final PasswordResetToken  mytoken = new PasswordResetToken(token , user);
         //   passwordTokenRepository.save(mytoken);
            //send email (token,user);
        }

    }


    @GetMapping("/user/profile")
    public String idk (){
        return "profile";
    }
}
