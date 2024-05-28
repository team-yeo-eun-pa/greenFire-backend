package yep.greenFire.greenfirebackend.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
    FAIL_TO_UPLOAD_FILE(1001, "파일 저장에 실패하였습니다."),
    FAIL_TO_DELETE_FILE(1002, "파일 삭제에 실패하였습니다."),
    NOT_FOUND_ADMIN_CODE(2000, " 관리자 코드에 해당하는 회원코드가 존재하지 않습니다."),
    NOT_FOUND_NOTICE_CODE(3000, "공지사항 코드에 해당하는 공지사항이 존재하지 않습니다."),
    FAIL_LOGIN(4000, "로그인에 실패하였습니다."),
    NOT_FOUND_REFRESH_TOKEN(4001, "해당 리프레시 토큰이 유효하지 않습니다."),
    UNAUTHORIZED(4002, "인증 되지 않은 요청입니다."),
    ACCESS_DENIED(4003, "허가 되지 않은 요청입니다."),
    NOT_ENOUGH_STOCK(5000, "재고 부족으로 주문 불가합니다."),
    NOT_FOUND_VALID_ORDER(5001, "유효한 주문 건이 아닙니다."),
    ALREADY_EXIST_REVIEW(6000, "해당 주문 건에 이미 작성 된 리뷰가 있습니다.");
    //NOT_FOUND_CS_CODE(7000, "문의 목록을 불러올 수 없습니다.");

    private final int code;
    private final String message;
}
