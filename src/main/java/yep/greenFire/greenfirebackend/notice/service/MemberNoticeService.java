package yep.greenFire.greenfirebackend.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.notice.domain.entity.Notice;
import yep.greenFire.greenfirebackend.notice.domain.repository.NoticeRepository;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.notice.dto.response.MemberNoticeResponse;
import yep.greenFire.greenfirebackend.notice.dto.response.MemberNoticesResponse;

import static yep.greenFire.greenfirebackend.notice.domain.type.NoticeStatusType.DELETE;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberNoticeService {

    private final NoticeRepository noticeRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("noticeCode").descending());
    }


    public Page<MemberNoticesResponse> getMemberNotices(final Integer page) {

        Page<Notice> notices = noticeRepository.findByNoticeStatusNot(getPageable(page), DELETE);;

        return notices.map(MemberNoticesResponse::from);
    }

    @Transactional(readOnly = true)
    public MemberNoticeResponse getMemberNotice(Long noticeCode) {

        Notice notice = noticeRepository.findByNoticeCodeAndNoticeStatusNot(noticeCode, DELETE)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE_CODE));

        return MemberNoticeResponse.from(notice);
    }
}
