package JPABOARD.JPACRUD.repository;

import JPABOARD.JPACRUD.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository  extends JpaRepository<Member, Long> {

    List<Member> findAllByNickname(String nickname);
    Member findByNickname(String nickname);

//    @Override
//    <S extends Member> S save(S entity);
}
