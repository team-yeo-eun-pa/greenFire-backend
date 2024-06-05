package yep.greenFire.greenfirebackend.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    /*
     * 401: Unauthorized
     * 403: Forbidden
     * 404: Not Found
     * 405: Method Not Allowed
     * 409: Conflict
     */

    // 401 Error
    FAIL_TO_UPLOAD_FILE(401, "파일 저장에 실패하였습니다."),
    FAIL_TO_DELETE_FILE(401, "파일 삭제에 실패하였습니다."),
    FAIL_LOGIN(401, "로그인에 실패하였습니다."),
    NOT_FOUND_REFRESH_TOKEN(401, "해당 리프레시 토큰이 유효하지 않습니다."),
    UNAUTHORIZED(401, "인증 되지 않은 요청입니다."),

    // 403 Error
//    STORE_ACCESS_DENIED(403, "해당 스토어 조회 권한이 없습니다."),
//    STORE_CLOSED(403, "운영이 중단된 스토어입니다.");
    ACCESS_DENIED(403, "허가 되지 않은 요청입니다."),

    // 404 Error
    NOT_FOUND_CATEGORY_CODE(404, "카테고리 코드가 존재하지않습니다."),
    NOT_FOUND_ADMIN_CODE(404, "관리자 코드에 해당하는 회원코드가 존재하지 않습니다."),
    NOT_FOUND_MEMBER_CODE(404, "회원 코드에 해당하는 회원코드가 존재하지 않습니다."),
    NOT_FOUND_REPORT_CODE(404, "신고 코드에 해당하는 신고가 존재하지 않습니다."),
    NOT_FOUND_NOTICE_CODE(404, "공지사항 코드에 해당하는 공지사항이 존재하지 않습니다."),
    NOT_FOUND_VALID_ORDER(404, "유효한 주문 건이 아닙니다."),
    NOT_FOUND_PRODUCT_CODE(404, "상품 코드에 해당하는 상품이 존재하지 않습니다." ),

    // 409 Error
    NOT_ENOUGH_STOCK(409, "재고 부족으로 주문 불가합니다."),
    ALREADY_EXIST_REVIEW(409, "해당 주문 건에 이미 작성 된 리뷰가 있습니다.");

    private final int code;
    private final String message;

}
