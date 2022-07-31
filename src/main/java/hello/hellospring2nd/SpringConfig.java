package hello.hellospring2nd;

import hello.hellospring2nd.repository.MemberRepository;
import hello.hellospring2nd.repository.MemoryMemberRepository;
import hello.hellospring2nd.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
