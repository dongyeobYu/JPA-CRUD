package JPABOARD.JPACRUD.controller;

import JPABOARD.JPACRUD.domain.Address;
import JPABOARD.JPACRUD.domain.Member;
import JPABOARD.JPACRUD.repository.MemberRepository;
import JPABOARD.JPACRUD.security.Role;
import JPABOARD.JPACRUD.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 회원 가입 폼
     */
    @GetMapping("/new-member")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/new-member";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
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
        Member member = new Member(memberForm.getName(), memberForm.getNickname(), memberForm.getPassword(), address, Role.ROLE_USER);

        memberService.join(member);
        return "redirect:/";
    }

    /**
     * 회원 리스트 목록
     */
    @GetMapping("/member-list")
    public String memberList(Model model) {
//        Member member = memberRepository.findByNickname(authentication.nickname);
//        Long id = member.getId();
//        model.addAttribute("isMember");


        model.addAttribute("members", memberRepository.findAll());
        return "members/memberList";
    }

    /**
     * 회원 삭제
     */
    @PostMapping("/member/{memberId}/delete")
    public String memberDelete(@PathVariable("memberId") Long memberId) {
        memberRepository.deleteById(memberId);
        return "redirect:/";
    }

    /**
     * 멤버 수정 폼
     */
    @GetMapping("/member/{memberId}/updateMember")
    public String updateMemberForm(@PathVariable("memberId") Long memberId, Model model) {
        Optional<Member> member = memberRepository.findById(memberId);
        Address address = member.get().getAddress();

        MemberForm form = new MemberForm(
                member.get().getName(),
                member.get().getNickname(),
                member.get().getPassword(),
                address.getCity(),
                address.getZipcode(),
                address.getStreet(),
                member.get().getId());

        model.addAttribute("form", form);
        return "members/memberUpdateForm";
    }

    /**
     * 멤버 수정
     */
    @PostMapping("/member/{memberId}/updateMember")
    public String memberUpdate(@PathVariable("memberId") Long memberId, @ModelAttribute("form") MemberForm form){
        Address address = new Address(form.getStreet(), form.getZipcode(), form.getCity());
        memberService.updateMember(memberId, form.getPassword(), address);
        return "redirect:/member-list";
    }
}
