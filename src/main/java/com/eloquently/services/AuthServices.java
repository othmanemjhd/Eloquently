package com.eloquently.services;

import com.eloquently.dao.AbonneeDao;
import com.eloquently.dao.CreateurDao;
import com.eloquently.model.*;
import com.projet.jee.projet.model.*;
import com.eloquently.security.JwtTokenProvider;
import enumer.MethodAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class AuthServices {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String FACEBOOK_AUTH_URL = "https://graph.facebook.com/me?fields=email,first_name,last_name&access_token=%s";
    @Autowired
    private CreateurDao createurDao;
    @Autowired
    private AbonneeDao abonneeDao;
    @Autowired
    private WebClient webClient;
    @Autowired
    private JwtTokenProvider tokenProvider;

   public String loginfacebook(FacebookAuth facebookAuthModel) {
        String templateUrl = String.format(FACEBOOK_AUTH_URL, facebookAuthModel.getAuthToken());
        FacebookUserModel facebookUserModel = webClient.get().uri(templateUrl).retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    throw new ResponseStatusException(clientResponse.statusCode(), "facebook login error");
                })
                .bodyToMono(FacebookUserModel.class)
                .block();

        final Createur userOptional = createurDao.findByEmail(facebookUserModel.getEmail());
        final Abonnee abonneeOptional = abonneeDao.findByEmail(facebookUserModel.getEmail());
        if(userOptional!=null) {
            if ((userOptional.getLoginMethodEnum() != MethodAuth.FACEBOOK)) {
                return "cet utilisateur n'est pas inscrit a travers facebook";
            }

            UserPrincipal userPrincipal = new UserPrincipal(userOptional);
            String jwt = tokenProvider.generateTokenWithPrinciple(userPrincipal);
            return "connecion reussi";
        }
        else if(abonneeOptional!=null) {
            if (abonneeOptional.getLoginMethodEnum() != MethodAuth.FACEBOOK)
                return "cet utilisateur n'est pas inscrit a travers facebook";


            UserPrincipal userPrincipal= new UserPrincipal(abonneeOptional);
            String jwt = tokenProvider.generateTokenWithPrinciple(userPrincipal);
            return "connecion reussi";
        }
       return "aucun utilisateur n'existe avec ce compte";
    }

    public String inscriptionfacebook(FacebookAuth facebookAuthModel){
        String templateUrl = String.format(FACEBOOK_AUTH_URL, facebookAuthModel.getAuthToken());
        FacebookUserModel facebookUserModel = webClient.get().uri(templateUrl).retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    throw new ResponseStatusException(clientResponse.statusCode(), "facebook signh in error");
                })
                .bodyToMono(FacebookUserModel.class)
                .block();
        final Abonnee userOptional =abonneeDao.findByEmail(facebookUserModel.getEmail());
            final Abonnee user = new Abonnee(facebookUserModel.getEmail(), MethodAuth.FACEBOOK);
            abonneeDao.save(user);
            final UserPrincipal userPrincipal = new UserPrincipal(user);
            String jwt = tokenProvider.generateToken(userPrincipal);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/v1/user/{username}")
                    .buildAndExpand(facebookUserModel.getFirstName()).toUri();

            return location+jwt;

            //return " un utilisateur existe avec ce compte facebook";

    }
    public String loginlocal(String email,String passWord){
       Createur user=createurDao.findByEmail(email);
        Abonnee abonnee=abonneeDao.findByEmail(email);
        if(user!=null){
            if(user.getEmail().equals(email))
                return "conenxion reussi";
            else return "mot de passe incorrecte";
        }
        else if(abonnee!=null){
            if(user.getEmail().equals(email))
                return "conenxion reussi";
            else return "mot de passe incorrecte";
        }
        return "email incorrect";
    }
}
