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
    NOT_FOUND_MEMBER_ID(404, "해당 아이디가 존재하지 않습니다."),
    NOT_FOUND_SELLERS_STORE_CODE(404, "해당 스토어가 유효하지 않습니다."),
    NOT_FOUND_CATEGORY_CODE(404, "카테고리 코드가 존재하지않습니다."),
    NOT_FOUND_ADMIN_CODE(404, "관리자 코드에 해당하는 회원코드가 존재하지 않습니다."),
    NOT_FOUND_MEMBER_CODE(404, "회원 코드에 해당하는 회원코드가 존재하지 않습니다."),
    NOT_FOUND_REPORT_CODE(404, "신고 코드에 해당하는 신고가 존재하지 않습니다."),
    NOT_FOUND_NOTICE_CODE(404, "공지사항 코드에 해당하는 공지사항이 존재하지 않습니다."),
    NOT_FOUND_DELIVERY_CODE(404, "배송지 코드에 해당하는 배송지가 존재하지 않습니다."),
    NOT_FOUND_VALID_ORDER(404, "유효한 주문 건이 아닙니다."),
    NOT_FOUND_VALID_DELIVERY(404, "유효한 배송지가 아닙니다."),
    NOT_FOUND_PRODUCT_CODE(404, "상품 코드에 해당하는 상품이 존재하지 않습니다." ),
    NOT_FOUND_CHECKING_STATUS(404, "대기 중인 신청이 아니므로 수정할 수 없습니다."),
    NOT_FOUND_APPLY_CODE(404, "대기 중인 신청만 취소할 수 있습니다."),
    INVALID_STATUS_CHANGE(404, "대기 중이 아닌 신청입니다."),
    NOT_FOUND_SELLER_CODE(404, "신청 코드를 찾을 수 없습니다."),

    // 409 Error
    NOT_ENOUGH_STOCK(409, "재고 부족으로 주문 불가합니다."),
    ALREADY_EXIST_REVIEW(409, "해당 주문 건에 이미 작성 된 리뷰가 있습니다."),
    ORDER_ALREADY_REJECTED(409, "이미 거절된 주문입니다."),
    HAS_ACTIVE_ORDERS(409, "정지할 수 없는 상태의 주문 건이 존재합니다.");

    private final int code;
    private final String message;

}
