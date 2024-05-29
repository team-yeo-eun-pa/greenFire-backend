package yep.greenFire.greenfirebackend.admin.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.admin.member.domain.entity.AdminMember;
import yep.greenFire.greenfirebackend.admin.member.domain.repository.AdminMemberRepository;
import yep.greenFire.greenfirebackend.admin.member.dto.response.AdminMemberResponse;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;


@Service
@RequiredArgsConstructor
@Transactional
public class AdminMemberService {

    private final AdminMemberRepository adminMemberRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page -1, 10, Sort.by("memberCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<AdminMemberResponse> getAdminMembers(Integer page) {
        Page<AdminMember> members = adminMemberRepository.findByMemberStatusNot(getPageable(page), MemberStatus.STOP);

        return members.map(AdminMemberResponse::from);
    }
}
