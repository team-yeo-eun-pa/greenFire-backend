package yep.greenFire.greenfirebackend.user.member.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Map;

@Service
public class MemberService {
    public Map<String, Object> selectCsList(Map<String, String> searchMap, int page) {

        Member member = memberRepository.findByCsCode()

    }
}
