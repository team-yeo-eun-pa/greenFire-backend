package yep.greenFire.greenfirebackend.report.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;
import yep.greenFire.greenfirebackend.report.dto.response.ReportsVO;

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
    public Page<ReportsVO> getMembersByStatus(Integer page) {
        Pageable pageable = getPageable(page);
        List<MemberStatus> statuses = Arrays.asList(MemberStatus.STOP, MemberStatus.PERMANENTLY_SUSPENDED);
        return memberRepository.findReportVOByMemberStatusIn(statuses, pageable);
    }
}
