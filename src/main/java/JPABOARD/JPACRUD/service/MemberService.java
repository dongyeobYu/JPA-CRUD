package JPABOARD.JPACRUD.service;

import JPABOARD.JPACRUD.domain.Address;
import JPABOARD.JPACRUD.domain.Member;
import JPABOARD.JPACRUD.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validMember(member);
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
        member.get().changMember(password, address);

    }



}
