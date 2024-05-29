package yep.greenFire.greenfirebackend.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
    /* 기존 코드 번호와 일관성 유지할 것.
     * 1000번대: 파일 관련
     * 2000번대: 관리자 관련
     * 3000번대: 공지사항 관련
     * 4000번대: 인증 관련
     * 5000번대: 주문 관련
     * 6000번대: 리뷰 관련
     */
    FAIL_TO_UPLOAD_FILE(1001, "파일 저장에 실패하였습니다."),
    FAIL_TO_DELETE_FILE(1002, "파일 삭제에 실패하였습니다."),
    NOT_FOUND_CATEGORY_CODE(1003, "카테고리 코드가 존재하지않습니다."),
    NOT_FOUND_ADMIN_CODE(2000, " 관리자 코드에 해당하는 회원코드가 존재하지 않습니다."),
    NOT_FOUND_NOTICE_CODE(3000, "공지사항 코드에 해당하는 공지사항이 존재하지 않습니다."),
    FAIL_LOGIN(4000, "로그인에 실패하였습니다."),
    NOT_FOUND_REFRESH_TOKEN(4001, "해당 리프레시 토큰이 유효하지 않습니다."),
    UNAUTHORIZED(4002, "인증 되지 않은 요청입니다."),
    ACCESS_DENIED(4003, "허가 되지 않은 요청입니다."),
    NOT_ENOUGH_STOCK(5000, "재고 부족으로 주문 불가합니다."),
    NOT_FOUND_VALID_ORDER(5001, "유효한 주문 건이 아닙니다."),
    NOT_FOUND_PRODUCT_CODE(5002, "상품 코드에 해당하는 상품이 존재하지 않습니다." ),
    ALREADY_EXIST_REVIEW(6000, "해당 주문 건에 이미 작성 된 리뷰가 있습니다.");

    private final int code;
    private final String message;
}
