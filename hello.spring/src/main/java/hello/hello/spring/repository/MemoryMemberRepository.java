package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 동시성 문제가 고려되어 있지 않음
    // 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // name은 이미 고객이 등록한 상태이다.
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null이 반환될 가능성이 있으므로 Optional로 감싼다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // values().stream(): value 값을 loop 한다.
        // filter(member -> member.getName().equals(name)): 람다 형식으로 필터링한다.
        // findAny(): 하나라도 찾으면 Optional 상태로 반환한다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // 실무에서는 List를 많이 사용한다.
        return new ArrayList<>(store.values());
    }

    public  void clearStore(){
        store.clear();
    }
}
