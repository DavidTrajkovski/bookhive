package com.wp.bookhive.web.controllers;

import com.wp.bookhive.models.config.oauth2.CustomOAuth2User;
import com.wp.bookhive.models.entities.Invitation;
import com.wp.bookhive.models.entities.User;
import com.wp.bookhive.service.InvitationService;
import com.wp.bookhive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final InvitationService invitationService;

    @GetMapping("/user")    //dali da se stavi @PathVariable za userId?
    public String getUserViewPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if(auth.getPrincipal() instanceof CustomOAuth2User customOAuth2User) {
            user = userService.findByEmail(customOAuth2User.getEmail());
        } else {
            user = (User) auth.getPrincipal();
        }

        List<Invitation> invitations = this.invitationService.findByReceiver(user.getEmail());

        if(!invitations.isEmpty()) {
            model.addAttribute("invitations", invitations);
        }

        model.addAttribute("bodyContent", "landing-page");
        model.addAttribute("home_selected", true);

        return "index";
    }
}
