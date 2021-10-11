package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 다중 상속: I가 I를 상속할 땐 extends를 사용한다.
// JpaRepository: 스프링 데이터 JPA가 구현제을 자동으로 생성해서 스프링빈으로 등록한다.
public interface SpringDataJPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}