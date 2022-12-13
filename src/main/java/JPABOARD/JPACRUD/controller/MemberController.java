package JPABOARD.JPACRUD.controller;

import JPABOARD.JPACRUD.domain.Address;
import JPABOARD.JPACRUD.domain.Member;
import JPABOARD.JPACRUD.repository.MemberRepository;
import JPABOARD.JPACRUD.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 회원 가입 폼
     */
    @GetMapping("/new-member")
    public String createForm() {
        return "members/new-member";
    }

    /**
     * 회원 가입
     */
    @PostMapping("/new-member")
    public String createMember(@Valid MemberForm memberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "members/new-member";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member(memberForm.getName(), memberForm.getNickname(), memberForm.getPassword(), address);

        memberService.join(member);
        return "redirect:/";
    }

    /**
     * 회원 리스트 목록
     */
    @GetMapping("/member-list")
    public String memberList(Model model){
        model.addAttribute("list", memberRepository.findAll());
        return "members/memberList";
    }

    /**
     * 회원 삭제
     */
    @PostMapping("/member/{memberId}/delete")
    public String memberDelete(@PathVariable("memberId") Long memberId){
        memberRepository.deleteById(memberId);
        return "redirect:/";
    }
}
