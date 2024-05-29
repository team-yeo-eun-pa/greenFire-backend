//package yep.greenFire.greenfirebackend.admin.notice.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;
//import yep.greenFire.greenfirebackend.admin.notice.domain.repository.NoticeRepository;
//import yep.greenFire.greenfirebackend.admin.notice.domain.type.NoticeStatusType;
//import yep.greenFire.greenfirebackend.admin.notice.dto.request.NoticeCreateRequest;
//import yep.greenFire.greenfirebackend.admin.notice.dto.request.NoticeUpdateRequest;
//import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticeResponse;
//import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticesResponse;
//import yep.greenFire.greenfirebackend.admin.notice.dto.response.MemberNoticeResponse;
//import yep.greenFire.greenfirebackend.admin.notice.dto.response.MemberNoticesResponse;
//import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
//import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
//
//import static yep.greenFire.greenfirebackend.admin.notice.domain.type.NoticeStatusType.DELETE;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class NoticeService {
//
//    private final NoticeRepository noticeRepository;
//    private final AdminMemberService adminMemberService;
//
//    private Pageable getPageable(final Integer page) {
//        return PageRequest.of(page - 1, 10, Sort.by("noticeCode").descending());
//    }
//
//    @Transactional(readOnly = true)
//    public Page<AdminNoticesResponse> getAdminNotices(final Integer page) {
//
//        Page<Notice> notices = noticeRepository.findByNoticeStatusNot(getPageable(page), DELETE);
//
//        return notices.map(AdminNoticesResponse::from);
//    }
//
//
//    @Transactional(readOnly = true)
//    public AdminNoticeResponse getAdminNotice(final Long noticeCode) {
//
//        Notice notice = noticeRepository.findByNoticeCodeAndNoticeStatusNot(noticeCode, DELETE)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE_CODE));
//
//        return AdminNoticeResponse.from(notice);
//    }
//
//    public Integer save(NoticeCreateRequest noticeRequest, Integer memberCode) {
//
//
//        final Notice newNotice = Notice.of(
//                noticeRequest.getNoticeTitle(),
//                noticeRequest.getNoticeContent(),
//                noticeRequest.getNoticeFixable(),
//                memberCode
//        );
//        Notice notice = noticeRepository.save(newNotice);
//        return notice.getNoticeCode();
//    }
//
//
//    public void modify(Long noticeCode, NoticeUpdateRequest noticeUpdateRequest){
//        Notice notice = noticeRepository.findByNoticeCodeAndNoticeStatusNot(noticeCode, DELETE)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE_CODE));
//
//        notice.modify(
//                noticeUpdateRequest.getNoticeTitle(),
//                noticeUpdateRequest.getNoticeContent()
//        );
//    }
//
//    public void remove(Long noticeCode) {
//
//        noticeRepository.deleteById(noticeCode);
//    }
//
//    public Page<MemberNoticesResponse> getMemberNotices(final Integer page, final Integer noticeCode) {
//
//        Page<Notice> notices = null;
//        if(noticeCode != null) {
//            notices = noticeRepository.findByNoticeTitleAndNoticeStatus(getPageable(page), noticeCode, NoticeStatusType.ACTIVE);
//
//        }
//
//        return notices.map(MemberNoticesResponse::from);
//    }
//
//    @Transactional(readOnly = true)
//    public MemberNoticeResponse getMemberNotice(Long noticeCode) {
//
//        Notice notice = noticeRepository.findByNoticeCodeAndNoticeStatusNot(noticeCode, DELETE)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE_CODE));
//
//        return MemberNoticeResponse.from(notice);
//    }
//}
