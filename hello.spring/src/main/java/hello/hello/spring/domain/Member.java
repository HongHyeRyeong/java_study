package hello.hello.spring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id // 기본키(pk)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 생성하는 key(id)라는 뜻
    private Long id; // 시스템이 저장한 임의의 값

    // @Column(name = "username"): 만약 DB의 컬럼 명이 다르다면 직접 설정
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}