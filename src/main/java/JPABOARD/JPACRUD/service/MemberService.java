package JPABOARD.JPACRUD.service;

import JPABOARD.JPACRUD.domain.Address;
import JPABOARD.JPACRUD.domain.Member;
import JPABOARD.JPACRUD.dto.MemberSessionDto;
import JPABOARD.JPACRUD.repository.MemberRepository;
import JPABOARD.JPACRUD.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(Member member){
        validMember(member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }

    private void validMember(Member member) {
        List<Member> byName = memberRepository.findAllByNickname(member.getNickname());
        if(!byName.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional
    public void updateMember(Long memberId, String password, Address address){

        Optional<Member> member = memberRepository.findById(memberId);
        member.get().changMember(address);
    }

    public boolean isMe(Long memberId, Long authentiId){
        return Objects.equals(memberId, authentiId);
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        if(memberRepository.findByNickname(nickname) == null) {
            throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
        }
        Member member = memberRepository.findByNickname(nickname);
        httpSession.setAttribute("member", new MemberSessionDto(member));

        return new MemberDetails(member);
    }
}
