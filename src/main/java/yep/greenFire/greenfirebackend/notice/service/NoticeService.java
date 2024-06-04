package yep.greenFire.greenfirebackend.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.member.service.AdminMemberService;
import yep.greenFire.greenfirebackend.notice.domain.entity.Notice;
import yep.greenFire.greenfirebackend.notice.domain.repository.NoticeRepository;
import yep.greenFire.greenfirebackend.notice.domain.type.NoticeStatusType;
import yep.greenFire.greenfirebackend.notice.dto.request.NoticeCreateRequest;
import yep.greenFire.greenfirebackend.notice.dto.request.NoticeUpdateRequest;
import yep.greenFire.greenfirebackend.notice.dto.response.AdminNoticeResponse;
import yep.greenFire.greenfirebackend.notice.dto.response.AdminNoticesResponse;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final AdminMemberService adminMemberService;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("noticeCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<AdminNoticesResponse> getAdminNotices(final Integer page) {

        Page<AdminNoticesResponse> notices = noticeRepository.getNotices(getPageable(page), NoticeStatusType.ACTIVE);

        return notices;
//        return notices.map(AdminNoticesResponse::from);
    }


    @Transactional(readOnly = true)
    public AdminNoticeResponse getAdminNotice(final Long noticeCode) {

        AdminNoticeResponse notice = noticeRepository.getNotice(noticeCode, NoticeStatusType.ACTIVE)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE_CODE));

        return notice;
//        return AdminNoticeResponse.from(notice);
    }

    public Long save(NoticeCreateRequest noticeRequest, Long memberCode) {


        final Notice newNotice = Notice.of(
                noticeRequest.getNoticeTitle(),
                noticeRequest.getNoticeContent(),
                noticeRequest.getNoticeFixable(),
                memberCode
        );
        Notice notice = noticeRepository.save(newNotice);
        return notice.getNoticeCode();
    }


    public void modify(Long noticeCode, NoticeUpdateRequest noticeUpdateRequest){
        Notice notice = noticeRepository.findByNoticeCodeAndNoticeStatusNot(noticeCode, NoticeStatusType.DELETE)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE_CODE));

        notice.modify(
                noticeUpdateRequest.getNoticeTitle(),
                noticeUpdateRequest.getNoticeContent()
        );
    }

    public void remove(Long noticeCode) {

        noticeRepository.deleteById(noticeCode);
    }

}
