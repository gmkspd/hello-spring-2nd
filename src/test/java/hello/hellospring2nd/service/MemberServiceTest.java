package hello.hellospring2nd.service;

import hello.hellospring2nd.domain.Member;
import hello.hellospring2nd.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        // 이러한 상황이 주어짐
        Member member = new Member();
        member.setName("spring");

        // when
        // 이걸 실행했을 때
        Long savedId = memberService.join(member);

        // then
        // 이게 나와야해
        Member findMember = memberService.findOne(savedId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());

    }

    @Test
    public void duplicateTest() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // then
//        (1)
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

//        (2)
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)
        );

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}