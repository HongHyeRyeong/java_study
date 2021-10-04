package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // 직접 객체를 생성하는 대신(@BeforeEach) 스프링 컨테이너로부터 받아온다.(@Autowired)
    // 왜냐하면, 테스트 코드를 작성할 땐 제일 편한 방법을 사용하는 것이 좋다.
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository; // 스프링에 등록되어 있는 구현체

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello789");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring456");

        Member member2 = new Member();
        member2.setName("spring456");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}