package JPABOARD.JPACRUD.controller;

import JPABOARD.JPACRUD.security.MemberDetails;
import JPABOARD.JPACRUD.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal MemberDetails memberDetails, Model model){
        if(memberDetails != null) {
            model.addAttribute("Me", false);
        } else {
            model.addAttribute("Me", true);
        }
        return "home";
    }
}
