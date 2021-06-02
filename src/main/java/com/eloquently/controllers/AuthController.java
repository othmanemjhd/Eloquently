package com.eloquently.controllers;

import com.eloquently.model.FacebookAuth;
import com.eloquently.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServices authServices;

    @PostMapping("login/facebook")
    public String loginfacebook(@RequestBody @Valid String facebookAuthModel) {
        FacebookAuth fbAuth=new FacebookAuth(facebookAuthModel);
       return authServices.loginfacebook(fbAuth);
    }

    @PostMapping("sign_in/facebook")
    public String inscriptionfacebook(@RequestBody @Valid String facebookAuthModel) {
        FacebookAuth fbAuth=new FacebookAuth(facebookAuthModel);
       return authServices.inscriptionfacebook(fbAuth);
    }

    @PostMapping("login/local")
    public String loginLocal(@RequestBody @Valid String email,@RequestBody @Valid String passWord) { ;
        //return authServices.loginlocal(email,passWord);
        return null;
    }


}
