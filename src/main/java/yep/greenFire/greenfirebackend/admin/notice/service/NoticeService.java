package yep.greenFire.greenfirebackend.admin.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;
import yep.greenFire.greenfirebackend.admin.notice.domain.repository.NoticeRepository;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticesResponse;

import static yep.greenFire.greenfirebackend.admin.notice.domain.type.NoticeStatusType.DELETE;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("noticeCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<AdminNoticesResponse> getAdminNotice(final Integer page) {

        Page<Notice> notices = noticeRepository.findByNoticeStatusNot(getPageable(page), DELETE);

        return notices.map(AdminNoticesResponse::from);
    }
}
