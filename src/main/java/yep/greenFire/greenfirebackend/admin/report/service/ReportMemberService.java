package yep.greenFire.greenfirebackend.admin.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.user.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.user.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.user.member.dto.response.MemberResponse;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

@Service
@RequiredArgsConstructor
public class ReportMemberService {

    private final MemberRepository memberRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page -1, 10, Sort.by("memberCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<MemberResponse> getReportedMember(Integer page) {
        Page<Member> members = memberRepository.findByMemberStatus(getPageable(page), MemberStatus.STOP, MemberStatus.PERMANENTLY_SUSPENDED);

        return members.map(MemberResponse::from);
    }
}
