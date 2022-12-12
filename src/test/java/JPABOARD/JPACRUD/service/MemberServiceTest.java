package JPABOARD.JPACRUD.service;

import JPABOARD.JPACRUD.domain.Member;
import JPABOARD.JPACRUD.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입테스트() throws Exception{
        Member member = new Member("동엽", "asdf", "1234");

        Long savedId = memberService.join(member);

        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_체크() throws Exception{
        Member member1 = new Member("동엽", "asdf", "1234");
        Member member2 = new Member("kim", "asdf", "1234");

        memberService.join(member1);

        try{
            memberService.join(member2);
        } catch(IllegalStateException e){
            return;
        }

        Assert.fail("예외 발생 필요");
    }

}