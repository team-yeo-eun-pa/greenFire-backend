package yep.greenFire.greenfirebackend.notice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.notice.domain.entity.Notice;
import yep.greenFire.greenfirebackend.notice.domain.type.NoticeStatusType;
import yep.greenFire.greenfirebackend.notice.dto.response.AdminNoticeResponse;
import yep.greenFire.greenfirebackend.notice.dto.response.AdminNoticesResponse;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Page<Notice> findByNoticeStatusNot(Pageable pageable, NoticeStatusType noticeStatusType);

    @Query(
            value = "SELECT " +
                    " new yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticesResponse(" +
                    " N.noticeCode,N.noticeTitle, N.noticeFixable,N.noticeDate,N.noticeWriter,M.memberName)" +
                    " FROM" +
                    " Notice N" +
                    " LEFT JOIN" +
                    " Member M" +
                    " ON" +
                    " M.memberCode = N.noticeWriter" +
                    " WHERE" +
                    " N.noticeStatus = :noticeStatusType"
    )
    Page<AdminNoticesResponse> getNotices(Pageable pageable, @Param("noticeStatusType") NoticeStatusType noticeStatusType);


    @Query(
            value = " SELECT" +
                    " new yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticeResponse(" +
                    " N.noticeCode, N.noticeTitle, N.noticeContent, N.noticeDate, N.noticeWriter, M.memberName)" +
                    " FROM " +
                    " Notice N" +
                    " LEFT JOIN " +
                    " Member M" +
                    " ON" +
                    " M.memberCode = N.noticeWriter" +
                    " WHERE " +
                    " N.noticeStatus = :noticeStatusType" +
                    " AND " +
                    " N.noticeCode = :noticeCode"
    )
//    @EntityGraph(attributePaths = {"noticeWriter"})
    Optional<AdminNoticeResponse> getNotice(@Param("noticeCode")Long noticeCode, @Param("noticeStatusType") NoticeStatusType noticeStatus);

    @EntityGraph(attributePaths = {"noticeWriter"})
    Optional<Notice> findByNoticeCodeAndNoticeStatusNot(Long noticeCode, NoticeStatusType noticeStatus);

    Page<Notice> findByNoticeTitleAndNoticeStatusNot(Pageable pageable, Long noticeCode, NoticeStatusType noticeStatusType);

//    Optional<Notice> findByNoticeCodeAndNoticeStatusNot(Long noticeCode, NoticeStatusType noticeStatusType);
}
