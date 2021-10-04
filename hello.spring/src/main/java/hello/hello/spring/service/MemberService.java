package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // MemberRepository 인자로 받는 생성자 구현하여 동일한 메모리 객체를 사용하도록 변경하자
    // DI: MemberService 입장에서 보면 외부에서 객체를 정해준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 비즈니스 로직: 같은 이름인 회원은 안된다.
    private void validateDuplicateMember(Member member) {
        // 반환 값을 Optional 인자로 받아 사용할 수도 있지만,
        // 반환받지 않고 바로 사용하는 방법을 권장한다.
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> { // null이 아닌 값이 존재한다면 {} 메소드 실행
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        // Optional 값을 꺼낼 땐 get()을 사용하는 방법도 있지만,
        // orElseGet()을 통해 값이 있다면 꺼내고 없다면 안에 메소드를 실행하는 방법을 많이 쓴다.
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}