package yep.greenFire.greenfirebackend.admin.notice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;
import yep.greenFire.greenfirebackend.admin.notice.domain.type.NoticeStatusType;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticesResponse;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Page<Notice> findByNoticeStatusNot(Pageable pageable, NoticeStatusType noticeStatusType);

    @EntityGraph(attributePaths = {"noticeWriter"})
    Optional<Notice> findByNoticeCodeAndNoticeStatusNot(Long noticeCode, NoticeStatusType noticeStatus);

    Page<Notice> findByNoticeTitleAndNoticeStatus(Pageable pageable, int noticeCode, NoticeStatusType noticeStatusType);

//    Optional<Notice> findByNoticeCodeAndNoticeStatusNot(Long noticeCode, NoticeStatusType noticeStatusType);
}
