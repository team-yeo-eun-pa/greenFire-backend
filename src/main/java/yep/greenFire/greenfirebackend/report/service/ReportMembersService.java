package yep.greenFire.greenfirebackend.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportMembersService {

    private final MemberRepository memberRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("memberCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<Member> getMembersByStatus(Integer page) {
        Pageable pageable = getPageable(page);
        List<MemberStatus> statuses = Arrays.asList(MemberStatus.STOP, MemberStatus.PERMANENTLY_SUSPENDED);
        return memberRepository.findByMemberStatusIn(statuses, pageable);
    }
}

