package yep.greenFire.greenfirebackend.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.member.dto.response.MemberResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminMemberService {

    private final MemberRepository memberRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("memberCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<MemberResponse> getAdminMembers(Integer page) {

        Page<Member> members = memberRepository.findAll(getPageable(page));

        return members.map(MemberResponse::from);
    }
}
