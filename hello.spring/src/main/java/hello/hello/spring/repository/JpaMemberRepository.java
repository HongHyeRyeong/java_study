package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // insert 쿼리, DB 저장, SetID 등 모든 기능을 JPA가 처리한다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // pk 기반이 아니라 쿼리 작성이 필요하다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 객체(엔티티) 자체를 select 한다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}