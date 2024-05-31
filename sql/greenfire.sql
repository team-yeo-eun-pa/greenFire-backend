-- 회원 관련

CREATE TABLE `tbl_member`
(
    `member_code`        BIGINT AUTO_INCREMENT NOT NULL COMMENT '회원 코드',

    `member_id`          VARCHAR(255)          NOT NULL COMMENT '아이디',
    `member_password`    VARCHAR(500)          NOT NULL COMMENT '비밀번호',

    `member_nickname`    VARCHAR(100) COMMENT '닉네임',
    `member_name`        VARCHAR(100)          NOT NULL COMMENT '이름',
    `member_email`       VARCHAR(100)          NOT NULL COMMENT '이메일',
    `member_phone`       VARCHAR(255)          NOT NULL COMMENT '연락처',

    `sns_type`           VARCHAR(255) COMMENT '소셜 계정 종류',
    `refresh_token`      VARCHAR(300) COMMENT '리프레시 토큰',

    `report_count`       BIGINT COMMENT '신고 횟수',
    `suspended_end_date` DATETIME COMMENT '정지 해제 일자',

    `member_role`        VARCHAR(10)           NOT NULL COMMENT '회원 등급',
    `member_status`      VARCHAR(10)           NOT NULL COMMENT '회원 상태',
    `regist_date`        DATETIME              NOT NULL COMMENT '가입 일자',
    `quit_date`          DATETIME COMMENT '탈퇴 일자',
    PRIMARY KEY (`member_code`)
) COMMENT = '회원';


-- 판매자 관련

CREATE TABLE `tbl_seller`
(
    `seller_code`               BIGINT AUTO_INCREMENT NOT NULL COMMENT '판매 회원 코드',
    `member_code`               BIGINT                NOT NULL COMMENT '회원 코드',

    `store_name`                VARCHAR(255)          NOT NULL COMMENT '상호명',
    `store_representative_name` VARCHAR(255)          NOT NULL COMMENT '대표자 이름',
    `business_number`           VARCHAR(255)          NOT NULL COMMENT '사업자번호',
    `mos_number`                VARCHAR(255)          NOT NULL COMMENT '통신판매업번호',
    `business_img`              VARCHAR(255)          NOT NULL COMMENT '사업자등록증',

    `apply_content`             VARCHAR(10000)        NOT NULL COMMENT '지원서',
    `store_type`                VARCHAR(255)          NOT NULL COMMENT '전문 분야',
    `apply_datetime`            DATETIME              NOT NULL COMMENT '신청 일자',
    `apply_processing_date`     DATETIME COMMENT '처리 일자',
    `apply_cancel_date`         DATETIME COMMENT '신청 취소 일자',
    `reject_reason`             VARCHAR(255) COMMENT '반려 사유',
    `apply_status`              VARCHAR(10)           NOT NULL DEFAULT 'CHECKING' COMMENT '처리 상태',
    PRIMARY KEY (`seller_code`)
) COMMENT = '판매 회원';

-- 스토어 관련

CREATE TABLE `tbl_store`
(
    `store_code`              BIGINT AUTO_INCREMENT NOT NULL COMMENT '스토어 코드',
    `seller_code`             BIGINT                NOT NULL COMMENT '판매 회원 코드',

    `store_name`              VARCHAR(255)          NOT NULL COMMENT '스토어 이름',
    `store_info`              VARCHAR(255)          NOT NULL COMMENT '스토어 소개',
    `address_zonecode`        VARCHAR(255)          NOT NULL COMMENT '우편번호',
    `address_type`            VARCHAR(10)           NOT NULL COMMENT '기본 주소 타입',
    `address`                 VARCHAR(255) COMMENT '기본 주소',
    `address_detail`          VARCHAR(255) COMMENT '상세 주소',
    `delivery_amount`         BIGINT                NOT NULL COMMENT '배송비',
    `free_delivery_condition` BIGINT                NOT NULL COMMENT '무료 배송 조건',

    `report_count`            BIGINT COMMENT '신고 횟수',
    `suspended_end_date`      DATETIME COMMENT '정지 해제 일자',

    `store_status`            VARCHAR(10) COMMENT '스토어 상태',
    PRIMARY KEY (`store_code`)
) COMMENT = '스토어';


-- 상품 관련

CREATE TABLE `tbl_product`
(
    `product_code`    BIGINT AUTO_INCREMENT NOT NULL COMMENT '상품 코드',
    `category_code`   BIGINT                NOT NULL COMMENT '카테고리 코드',
    `store_code`      BIGINT                NOT NULL COMMENT '스토어 코드',

    `product_name`    VARCHAR(255)          NOT NULL COMMENT '상품명',
    `stock`           BIGINT                NOT NULL COMMENT '상품 재고',
    `sellable_status` VARCHAR(5)            NOT NULL COMMENT '판매 가능 여부',
    `regist_date`     DATETIME              NOT NULL COMMENT '상품 등록일',
    PRIMARY KEY (`product_code`)
) COMMENT = '상품';

-- 상품 옵션 관련

CREATE TABLE `tbl_product_option`
(
    `option_code`            BIGINT AUTO_INCREMENT NOT NULL COMMENT '상품 옵션 코드',
    `product_code`           BIGINT                NOT NULL COMMENT '상품 코드',

    `option_name`            VARCHAR(255)          NOT NULL COMMENT '상품 옵션 이름',
    `option_appear_activate` VARCHAR(5)            NOT NULL COMMENT '상품 옵션 조회 가능 여부',
    `option_price`           BIGINT                NOT NULL COMMENT '옵션 가격',
    `option_stock`           BIGINT COMMENT '옵션 재고 수량',
    PRIMARY KEY (`option_code`)
) COMMENT = '상품 옵션';

-- 찜 관련

CREATE TABLE `tbl_wishlist`
(
    `wishlist_code` BIGINT AUTO_INCREMENT NOT NULL COMMENT '찜 코드',
    `member_code`   BIGINT                NOT NULL COMMENT '회원 코드',
    `product_code`  BIGINT COMMENT '상품 코드',
    `store_code`    BIGINT COMMENT '스토어 코드',

    `wishlist_sort` VARCHAR(255)          NOT NULL COMMENT '찜 분류',
    PRIMARY KEY (`wishlist_code`)
) COMMENT = '찜';

-- 장바구니 관련

CREATE TABLE `tbl_cart`
(
    `cart_code`     BIGINT AUTO_INCREMENT NOT NULL COMMENT '장바구니 코드',
    `member_code`   BIGINT                NOT NULL COMMENT '회원 코드',
    `option_code`   BIGINT                NOT NULL COMMENT '상품 옵션 코드',

    `cart_quantity` BIGINT                NOT NULL COMMENT '수량',
    PRIMARY KEY (`cart_code`)
) COMMENT = '장바구니';

-- 카테고리 관련

CREATE TABLE `tbl_category`
(
    `category_code`     BIGINT AUTO_INCREMENT NOT NULL COMMENT '카테고리 코드',
    `category_title`    VARCHAR(255)          NOT NULL COMMENT '카테고리 명',
    `ref_category_code` BIGINT COMMENT '상위 카테고리 코드',
    PRIMARY KEY (`category_code`)
) COMMENT = '카테고리';


-- 리뷰 관련

CREATE TABLE `tbl_review`
(
    `review_code`         BIGINT AUTO_INCREMENT NOT NULL COMMENT '후기 코드',
    `order_detail_code`   BIGINT                NOT NULL COMMENT '주문 상세 코드',

    `review_content`      VARCHAR(1000)         NOT NULL COMMENT '후기 내용',
    `review_date`         DATETIME              NOT NULL COMMENT '작성 일자',
    `review_status`       VARCHAR(10)           NOT NULL DEFAULT 'ACTIVE' COMMENT '후기 상태',
    `modify_date`         DATETIME COMMENT '수정 일자',
    `delete_date`         DATETIME COMMENT '삭제 일자',
    `is_reply`            VARCHAR(5) COMMENT '후기 답변',
    `review_reply_status` VARCHAR(3)                     DEFAULT 'N' COMMENT '후기 답변 상태',
    PRIMARY KEY (`review_code`)
) COMMENT = '후기';


-- 배송 관련

CREATE TABLE `tbl_delivery`
(
    `delivery_code`      BIGINT AUTO_INCREMENT NOT NULL COMMENT '배송 코드',
    `store_order_code`   BIGINT                NOT NULL COMMENT '스토어별 주문 코드',

    `delivery_company`   VARCHAR(255)          NOT NULL COMMENT '배송사',
    `transport_number`   BIGINT                NOT NULL COMMENT '운송장 번호',
    `delivery_date`      DATETIME              NOT NULL COMMENT '배송 일자',
    `delivery_done_date` DATETIME              NOT NULL COMMENT '배송 완료 일자',

    `delivery_type`      VARCHAR(255)          NOT NULL COMMENT '배송타입',
    `delivery_status`    VARCHAR(20)           NOT NULL COMMENT '배송 상태',
    PRIMARY KEY (`delivery_code`)
) COMMENT = '배송';

-- 배송지 관련

CREATE TABLE `tbl_delivery_address`
(
    `delivery_address_code` BIGINT AUTO_INCREMENT NOT NULL COMMENT '배송지 코드',
    `member_code`           BIGINT                NOT NULL COMMENT '회원 코드',

    `delivery_address_name` VARCHAR(255)          NOT NULL COMMENT '배송지명',
    `receiver_name`         VARCHAR(255)          NOT NULL COMMENT '수령자',
    `contact_number`        VARCHAR(255)          NOT NULL COMMENT '연락처',

    `address_zonecode`      VARCHAR(255)          NOT NULL COMMENT '우편번호',
    `address_type`          VARCHAR(10)           NOT NULL COMMENT '기본 주소 타입',
    `address`               VARCHAR(255) COMMENT '기본 주소',
    `address_detail`        VARCHAR(255) COMMENT '상세 주소',
    `delivery_request`      VARCHAR(255) COMMENT '배송 요청사항',

    `is_ordinary_address`   BOOLEAN               NOT NULL DEFAULT false COMMENT '기본 배송지 여부',
    PRIMARY KEY (`delivery_address_code`)
) COMMENT = '배송지';

-- 주문 관련

CREATE TABLE `tbl_order`
(
    `order_code`            BIGINT AUTO_INCREMENT NOT NULL COMMENT '주문 코드',
    `member_code`           BIGINT                NOT NULL COMMENT '회원 코드',

    `receiver_name`         VARCHAR(255)          NOT NULL COMMENT '수령자',
    `contact_number`        VARCHAR(255)          NOT NULL COMMENT '연락처',

    `address_zonecode`      VARCHAR(255)          NOT NULL COMMENT '우편번호',
    `address_type`          VARCHAR(10)           NOT NULL COMMENT '기본 주소 타입',
    `address`               VARCHAR(255) COMMENT '기본 주소',
    `address_detail`        VARCHAR(255) COMMENT '상세 주소',
    `delivery_request`      VARCHAR(255) COMMENT '배송 요청사항',

    `total_order_amount`    BIGINT                NOT NULL COMMENT '전체 주문 금액',
    `total_discount_amount` BIGINT                NOT NULL COMMENT '전체 할인 금액',
    `total_delivery_amount` BIGINT                NOT NULL COMMENT '전체 배송비',
    `total_real_payment`    BIGINT                NOT NULL COMMENT '전체 실결제 금액',

    `is_order_cancel`       BOOLEAN               NOT NULL DEFAULT false COMMENT '주문 취소 여부',
    `is_partial_cancel`     BOOLEAN               NOT NULL DEFAULT false COMMENT '부분 취소 여부',

    `order_date`            DATETIME COMMENT '주문 일자',
    `cancel_date`           DATETIME COMMENT '취소 일자',
    PRIMARY KEY (`order_code`)
) COMMENT = '주문';

-- 주문 관련

CREATE TABLE `tbl_store_order`

(
    `store_order_code` BIGINT      NOT NULL AUTO_INCREMENT COMMENT '스토어별 주문 코드',
    `order_code`       BIGINT      NOT NULL COMMENT '주문 코드',
    `store_code`       BIGINT      NOT NULL COMMENT '스토어 코드',

    `order_status`     VARCHAR(10) NOT NULL COMMENT '주문 상태',

    `order_amount`     BIGINT      NOT NULL COMMENT '주문 금액',
    `discount_amount`  BIGINT      NOT NULL COMMENT '할인 금액',
    `delivery_amount`  BIGINT      NOT NULL COMMENT '배송비',
    `real_payment`     BIGINT      NOT NULL COMMENT '실결제 금액',

    `completion_date`  DATETIME COMMENT '주문 완료 일자',
    PRIMARY KEY (`store_order_code`)
) COMMENT = '스토어별 주문';

-- 주문 상세 관련

CREATE TABLE `tbl_order_detail`
(
    `order_detail_code` BIGINT  NOT NULL AUTO_INCREMENT COMMENT '주문 상세 코드',
    `option_code`       BIGINT  NOT NULL COMMENT '상품 옵션 코드',
    `store_order_code`  BIGINT  NOT NULL COMMENT '스토어별 주문 코드',

    `option_price`      BIGINT  NOT NULL COMMENT '상품 판매 가격',
    `order_quantity`    BIGINT  NOT NULL COMMENT '주문 수량',

    `is_coupon_used`    BOOLEAN NOT NULL DEFAULT false COMMENT '쿠폰 사용 여부',
    `is_order_cancel`   BOOLEAN NOT NULL DEFAULT false COMMENT '주문 취소 여부',
    PRIMARY KEY (`order_detail_code`)
) COMMENT = '주문상세';

-- 반품,교환 관련

CREATE TABLE `tbl_refund_exchange`
(
    `refund_exchange_code` BIGINT AUTO_INCREMENT NOT NULL COMMENT '반품교환 코드',
    `order_detail_code`    BIGINT                NOT NULL COMMENT '주문 상세 코드',

    `request_type`         VARCHAR(255)          NOT NULL COMMENT '요청 유형(반품/교환)',
    `request_reason`       VARCHAR(255)          NOT NULL COMMENT '요청 사유',
    `request_date`         DATETIME              NOT NULL COMMENT '요청 일자',

    `withdraw_name`        VARCHAR(255)          NOT NULL COMMENT '발송자',
    `contact_number`       VARCHAR(255)          NOT NULL COMMENT '연락처',

    `address_zonecode`     VARCHAR(255)          NOT NULL COMMENT '우편번호',
    `address_type`         VARCHAR(10)           NOT NULL COMMENT '기본 주소 타입',
    `address`              VARCHAR(255) COMMENT '기본 주소',
    `address_detail`       VARCHAR(255) COMMENT '상세 주소',
    `delivery_request`     VARCHAR(255) COMMENT '배송 요청사항',

    `attributable_clause`  VARCHAR(255)          NOT NULL COMMENT '귀책 구분',

    `order_change_status`  VARCHAR(255) COMMENT '처리 상태',
    `rejection_reason`     VARCHAR(255) COMMENT '거절 사유',
    `rejection_date`       DATETIME COMMENT '거절 일자',
    `completion_date`      DATETIME COMMENT '완료 일자',

    PRIMARY KEY (`refund_exchange_code`)
) COMMENT = '반품교환';


-- 쿠폰 관련

CREATE TABLE `tbl_coupon_creation`
(
    `coupon_creation_code`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '쿠폰 제작 코드',
    `store_code`                   BIGINT       NOT NULL COMMENT '스토어 코드',

    `coupon_name`                  VARCHAR(255) NOT NULL COMMENT '쿠폰명',
    `discount_rate`                DOUBLE       NOT NULL COMMENT '할인률',
    `min_purchase_price`           BIGINT       NOT NULL COMMENT '구매 최소 금액',
    `max_discount`                 BIGINT       NOT NULL COMMENT '최대 할인액',
    `available_date`               DATETIME     NOT NULL COMMENT '사용 가능 기간',
    `is_applicable_to_allProducts` BOOLEAN      NOT NULL DEFAULT false COMMENT '전체 상품 적용 여부',
    `create_date`                  DATETIME     NOT NULL COMMENT '제작 일자',

    `total_coupons_issued`         BIGINT       NOT NULL DEFAULT 0 COMMENT '발행된 개수',
    `total_coupons_used`           BIGINT       NOT NULL DEFAULT 0 COMMENT '사용된 개수',

    `coupon_statue`                VARCHAR(255) NOT NULL COMMENT '쿠폰 상태',
    PRIMARY KEY (`coupon_creation_code`)
) COMMENT = '쿠폰 제작';


-- 쿠폰 적용 관련

CREATE TABLE `tbl_coupon_product`
(
    `coupon_product_code`  BIGINT NOT NULL COMMENT '쿠폰 적용 상품 코드',
    `coupon_creation_code` BIGINT COMMENT '쿠폰 제작 코드',
    `product_code`         BIGINT COMMENT '상품 코드',
    PRIMARY KEY (`coupon_product_code`)
) COMMENT = '쿠폰 적용 상품';


-- 쿠폰 관련

CREATE TABLE `tbl_coupon`
(
    `coupon_code`          BIGINT  NOT NULL AUTO_INCREMENT COMMENT '쿠폰 코드',
    `coupon_creation_code` BIGINT  NOT NULL COMMENT '쿠폰 제작 코드',
    `member_code`          BIGINT  NOT NULL COMMENT '회원 코드',

    `publication_date`     DATETIME COMMENT '발행 일자',
    `expiration_date`      DATETIME COMMENT '만료 일자',
    `is_coupon_used`       BOOLEAN NOT NULL DEFAULT false COMMENT '쿠폰 사용 여부',
    `order_detail_code`    BIGINT           DEFAULT NULL COMMENT '주문 상세 코드',
    `used_date`            DATETIME COMMENT '사용 일자',
    PRIMARY KEY (`coupon_code`)
) COMMENT = '쿠폰';


-- 결제 관련

CREATE TABLE `tbl_payment`
(
    `payment_code`        BIGINT AUTO_INCREMENT NOT NULL COMMENT '결제 코드',
    `order_code`          BIGINT                NOT NULL COMMENT '주문 코드',

    `payment_price`       BIGINT                NOT NULL COMMENT '결제 금액',
    `payment_way`         VARCHAR(255)          NOT NULL COMMENT '결제 수단',

    `payment_date`        DATETIME              NOT NULL COMMENT '결제일',
    `payment_cancel_date` DATETIME COMMENT '결제 취소일',

    `payment_status`      VARCHAR(10)           NOT NULL COMMENT '결제 상태',
    PRIMARY KEY (`payment_code`)
) COMMENT = '결제';


-- 정산 관련

CREATE TABLE `tbl_account`
(
    `account_code`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '계좌 코드',
    `seller_code`          BIGINT       NOT NULL COMMENT '판매 회원 코드',

    `bank_name`            VARCHAR(255) NOT NULL COMMENT '은행명',
    `account_number`       BIGINT       NOT NULL COMMENT '계좌 번호',
    `account_holder`       VARCHAR(255) NOT NULL COMMENT '예금주',

    `is_account_activated` BOOLEAN      NOT NULL DEFAULT true COMMENT '계좌 활성화 여부',
    PRIMARY KEY (`account_code`)
) COMMENT = '계좌';

-- 정산 이력 관련

CREATE TABLE `tbl_settlement_history`
(
    `settlement_history_code` BIGINT AUTO_INCREMENT NOT NULL COMMENT '정산 코드',
    `settlement_date`         DATETIME              NOT NULL COMMENT '정산일자',
    `settlement_detailed`     VARCHAR(255)          NOT NULL COMMENT '명세서',
    `payment_price`           BIGINT COMMENT '결제 금액',
    `settlement_status`       CHAR(20)              NOT NULL DEFAULT 'NON_PAYMENT' COMMENT '처리 상태',
    `account_code`            BIGINT                NOT NULL COMMENT '계좌 코드',
    PRIMARY KEY (`settlement_history_code`)
) COMMENT = '정산이력';

-- 정산 결제 내역 관련

CREATE TABLE `tbl_settlement_payment`
(
    `settlement_payment_code` BIGINT AUTO_INCREMENT NOT NULL COMMENT '정산 내역 코드',
    `payment_code`            BIGINT                NULL COMMENT '결제 코드',
    `settlement_history_code` BIGINT                NOT NULL COMMENT '정산 이력 코드',
    PRIMARY KEY (`settlement_payment_code`)
) COMMENT = '정산 결제 내역';


-- 초록불 찾기 관련

CREATE TABLE `tbl_map`
(
    `shop_code`        BIGINT AUTO_INCREMENT NOT NULL COMMENT '추천 매장 코드',
    `shop_type`        VARCHAR(255)          NOT NULL COMMENT '매장 종류',
    `shop_info`        VARCHAR(1000)         NOT NULL COMMENT '매장 소개',
    `shop_hours`       VARCHAR(255) COMMENT '운영 시간',
    `address_zonecode` VARCHAR(255)          NOT NULL COMMENT '우편번호',
    `address_type`     VARCHAR(10)           NOT NULL COMMENT '기본 주소 타입',
    `address`          VARCHAR(255) COMMENT '기본 주소',
    `address_detail`   VARCHAR(255) COMMENT '상세 주소',
    PRIMARY KEY (`shop_code`)
) COMMENT = '초록불 찾기';


-- 챌린지 관련

CREATE TABLE `tbl_tag`
(
    `tag_code`    BIGINT AUTO_INCREMENT NOT NULL COMMENT '태그 코드',
    `tag_content` VARCHAR(255)          NOT NULL COMMENT '태그 내용',
    PRIMARY KEY (`tag_code`)
) COMMENT = '태그';

-- 챌린지 인증 관련

CREATE TABLE `tbl_challenge_certify`
(
    `certify_code`        BIGINT AUTO_INCREMENT NOT NULL COMMENT '인증 코드',
    `challenge_code`      BIGINT                NOT NULL COMMENT '챌린지 코드',
    `member_code`         BIGINT                NOT NULL COMMENT '작성자 코드',
    `certify_content`     VARCHAR(255)          NOT NULL COMMENT '인증 포스팅 내용',
    `certify_write_date`  DATETIME              NOT NULL COMMENT '작성일',
    `certify_modify_date` DATETIME COMMENT '수정일',
    `certify_cancel_date` DATETIME COMMENT '삭제일',
    `certify_status`      VARCHAR(15)           NOT NULL DEFAULT 'ACTIVE' COMMENT '포스팅 상태',
    PRIMARY KEY (`certify_code`)
) COMMENT = '인증 포스팅';

-- 챌린지 정보 관련

CREATE TABLE `tbl_challenge_info`
(
    `challenge_code`        BIGINT       NOT NULL COMMENT '챌린지 코드',
    `member_code`           BIGINT       NOT NULL COMMENT '챌린지 담당자',
    `tag_code`              BIGINT       NOT NULL COMMENT '태그 코드',
    `challenge_name`        VARCHAR(50)  NOT NULL COMMENT '챌린지 이름',
    `challenge_status`      VARCHAR(15)  NOT NULL DEFAULT 'ACTIVE' COMMENT '챌린지 상태',
    `challenge_content`     VARCHAR(255) NOT NULL COMMENT '챌린지 내용',
    `challenge_start_date`  DATETIME     NOT NULL COMMENT '챌린지 시작일',
    `challenge_end_date`    DATETIME     NOT NULL COMMENT '챌린지 종료일',
    `challenge_modify_date` DATETIME COMMENT '챌린지 수정일',
    `challenge_delete_date` DATETIME COMMENT '챌린지 삭제일',
    PRIMARY KEY (`challenge_code`)
) COMMENT = '챌린지';

-- 챌린지 참여자 관련

CREATE TABLE `tbl_joiner`
(
    `joiner_code`    BIGINT AUTO_INCREMENT NOT NULL COMMENT '참여자코드',
    `member_code`    BIGINT                NOT NULL COMMENT '회원 코드',
    `challenge_code` BIGINT                NOT NULL COMMENT '챌린지 코드',
    PRIMARY KEY (`joiner_code`)
) COMMENT = '챌린지 참여자';

-- 챌린지 인증 댓글 관련

CREATE TABLE `tbl_comment`
(
    `comment_code`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '댓글 코드',
    `certify_code`        BIGINT       NOT NULL COMMENT '인증 코드',
    `comment_content`     VARCHAR(255) NOT NULL COMMENT '댓글 내용',
    `comment_status`      VARCHAR(10)  NOT NULL DEFAULT 'ACTIVE' COMMENT '댓글 상태',
    `comment_modify_date` DATETIME     NOT NULL COMMENT '수정 일자',
    `member_code`         BIGINT       NOT NULL COMMENT '댓글 작성자 코드',
    PRIMARY KEY (`comment_code`)
) COMMENT = '인증글 댓글';


-- 공지사항 관련

CREATE TABLE `tbl_notice`
(
    `notice_code`    BIGINT AUTO_INCREMENT NOT NULL COMMENT '공지사항 코드',
    `notice_writer`  BIGINT                NOT NULL COMMENT '작성자 코드',
    `notice_content` VARCHAR(255)          NOT NULL COMMENT '공지사항 내용',
    `notice_title`   VARCHAR(255)          NOT NULL COMMENT '공지사항 제목',
    `notice_fixable` VARCHAR(3)            NOT NULL DEFAULT 'N' COMMENT '공지사항 고정여부',
    `notice_status`  VARCHAR(10)           NOT NULL COMMENT '공지사항 상태',
    `notice_date`    DATE COMMENT '수정일자',
    PRIMARY KEY (`notice_code`)
) COMMENT = '공지사항';


-- 문의 관련

CREATE TABLE `tbl_inquiry`
(
    `inquiry_code`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '문의 코드',
    `member_code`          BIGINT       NOT NULL COMMENT '회원 코드',
    `inquiry_write_date`   DATETIME     NOT NULL COMMENT '작성일',
    `inquiry_status`       VARCHAR(15)  NOT NULL DEFAULT 'PROCESSING' COMMENT '처리상태',
    `inquiry_detail`       VARCHAR(255) NOT NULL COMMENT '문의내용',
    `inquiry_title`        VARCHAR(30)  NOT NULL COMMENT '문의제목',
    `inquiry_modify_date`  DATETIME COMMENT '수정일',
    `inquiry_delete_date`  DATETIME COMMENT '삭제일',
    `inquiry_reply`        VARCHAR(255) COMMENT '문의 답변',
    `inquiry_reply_status` VARCHAR(20)  NOT NULL DEFAULT 'N' COMMENT '문의 답변 상태',
    PRIMARY KEY (`inquiry_code`)
) COMMENT = '문의';

-- 알림 활성화 관련

CREATE TABLE `tbl_alarm_activation`
(
    `alarm_activation_code` BIGINT      NOT NULL AUTO_INCREMENT COMMENT '알림 활성화 코드',
    `alarm_content_code`    BIGINT      NOT NULL COMMENT '알림내용 코드',

    `alarm_activation`      VARCHAR(20) NOT NULL DEFAULT 'DISABLED' COMMENT '활성화 여부',
    PRIMARY KEY (`alarm_activation_code`)
) COMMENT = '알림 활성화';


-- 알림 관련

CREATE TABLE `tbl_alarm`
(
    `alarm_code`         BIGINT AUTO_INCREMENT NOT NULL COMMENT '알림코드',
    `member_code`        BIGINT                NOT NULL COMMENT '회원 코드',
    `alarm_content_code` BIGINT                NOT NULL COMMENT '알림내용 코드',
    `alarm_date`         DATE                  NOT NULL COMMENT '알림 일자',
    PRIMARY KEY (`alarm_code`)
) COMMENT = '알림';


-- 알림 내용 관련

CREATE TABLE `tbl_alarm_content`
(
    `alarm_content_code` BIGINT AUTO_INCREMENT NOT NULL COMMENT '알림내용 코드',
    `alarm_code`         BIGINT                NOT NULL COMMENT '알림코드',

    `alarm_content`      VARCHAR(255)          NOT NULL COMMENT '알림내용',
    PRIMARY KEY (`alarm_content_code`)
) COMMENT = '알림내용';


-- 신고 관련

CREATE TABLE `tbl_report`
(
    `report_code`   BIGINT AUTO_INCREMENT NOT NULL COMMENT '신고 코드',
    `member_code`   BIGINT                NOT NULL COMMENT '신고자',
    `comment_code`  BIGINT COMMENT '댓글 코드',
    `review_code`   BIGINT COMMENT '후기 코드',
    `store_code`    BIGINT COMMENT '스토어 코드',
    `report_reason` VARCHAR(255)          NOT NULL COMMENT '신고 사유',
    `report_date`   DATE                  NOT NULL COMMENT '신고 일자',
    `report_type`   VARCHAR(255) COMMENT '신고 타입',
    PRIMARY KEY (`report_code`)
) COMMENT = '신고';


-- 이미지 관련

CREATE TABLE `tbl_image`
(
    `image_code`       BIGINT AUTO_INCREMENT NOT NULL COMMENT '이미지 코드',
    `member_code`      BIGINT COMMENT '회원 코드',
    `notice_code`      BIGINT COMMENT '공지사항 코드',
    `shop_code`        BIGINT COMMENT '추천 매장 코드',
    `inquiry_code`     BIGINT COMMENT '문의 코드',
    `product_code`     BIGINT COMMENT '상품 코드',
    `review_code`      BIGINT COMMENT '후기 코드',
    `store_code`       BIGINT COMMENT '스토어 코드',
    `report_code`      BIGINT COMMENT '신고 코드',
    `challenge_code`   BIGINT COMMENT '챌린지 코드',
    `certify_code`     BIGINT COMMENT '챌린지 인증 코드',
    `image_url`        VARCHAR(1000)         NOT NULL COMMENT '이미지 url',
    `image_name`       VARCHAR(255) COMMENT '이미지 이름',
    `image_initialise` VARCHAR(1000)         NOT NULL COMMENT '이미지 원본명',
    `image_root`       VARCHAR(1000)         NOT NULL COMMENT '이미지 경로',
    `board_type`       VARCHAR(255)          NOT NULL COMMENT '게시판 구분',
    PRIMARY KEY (`image_code`)
) COMMENT = 'tbl_image';


ALTER TABLE `tbl_member`
    ADD CONSTRAINT `tbl_member_CK` CHECK ( `member_role` IN ('MEMBER', 'SELLER', 'ADMIN') );

ALTER TABLE `tbl_member`
    ADD CONSTRAINT `tbl_member_CK1` CHECK ( `member_status` IN ('ACTIVE', 'STOP', 'PERMANENTLY_SUSPENDED', 'QUIT') );



ALTER TABLE `tbl_seller`
    ADD CONSTRAINT `tbl_seller_fk` FOREIGN KEY (member_code) REFERENCES tbl_member (member_code);

ALTER TABLE `tbl_seller`
    ADD CONSTRAINT `tbl_seller_CK` CHECK ( `apply_status` IN ('CHECKING', 'APPLY', 'REJECT', 'CANCEL'));

ALTER TABLE `tbl_seller`
    ADD CONSTRAINT `tbl_seller_CK1` CHECK ( `store_type` IN ('FOOD', 'KITCHENWARE', 'SUPPLIES', 'NECESSITIES'));



ALTER TABLE `tbl_store`
    ADD CONSTRAINT `tbl_store_fk` FOREIGN KEY (seller_code) REFERENCES tbl_seller (seller_code);

ALTER TABLE `tbl_store`
    ADD CONSTRAINT `tbl_store_CK` CHECK ( `store_status` IN
                                          ('OPEN', 'CLOSED', 'SUSPENDED', 'QUIT', 'PERMANENTLY_SUSPENDED') );



ALTER TABLE `tbl_product`
    ADD CONSTRAINT `tbl_product_fk1` FOREIGN KEY (category_code) REFERENCES tbl_category (category_code);

ALTER TABLE `tbl_product`
    ADD CONSTRAINT `tbl_product_fk2` FOREIGN KEY (store_code) REFERENCES tbl_store (store_code);

ALTER TABLE `tbl_product`
    ADD CONSTRAINT `sellable_status_CK` CHECK ( `sellable_status` IN ('Y', 'N') );



ALTER TABLE `tbl_product_option`
    ADD CONSTRAINT `tbl_product_option_fk` FOREIGN KEY (product_code) REFERENCES tbl_product (product_code);

ALTER TABLE `tbl_product_option`
    ADD CONSTRAINT `option_appear_activate_CK` CHECK ( `option_appear_activate` IN ('Y', 'N'));



ALTER TABLE `tbl_wishlist`
    ADD CONSTRAINT `tbl_wishlist_fk1` FOREIGN KEY (member_code) REFERENCES tbl_member (member_code);

ALTER TABLE `tbl_wishlist`
    ADD CONSTRAINT `tbl_wishlist_fk2` FOREIGN KEY (product_code) REFERENCES tbl_product (product_code);

ALTER TABLE `tbl_wishlist`
    ADD CONSTRAINT `tbl_wishlist_fk3` FOREIGN KEY (store_code) REFERENCES tbl_store (store_code);

ALTER TABLE `tbl_wishlist`
    ADD CONSTRAINT `tbl_wishlist_CK` CHECK ( `wishlist_sort` IN ('PRODUCT', 'STORE') );



ALTER TABLE `tbl_cart`
    ADD CONSTRAINT `tbl_cart_fk1` FOREIGN KEY (member_code) REFERENCES tbl_member (member_code);

ALTER TABLE `tbl_cart`
    ADD CONSTRAINT `tbl_cart_fk2` FOREIGN KEY (option_code) REFERENCES tbl_product_option (option_code);



ALTER TABLE `tbl_review`
    ADD CONSTRAINT `tbl_review_fk` FOREIGN KEY (order_detail_code) REFERENCES tbl_order_detail (order_detail_code);

ALTER TABLE `tbl_review`
    ADD CONSTRAINT `review_status_CK` CHECK ( `review_status` IN ('ACTIVE', 'DELETE', 'MODIFIED') );

ALTER TABLE `tbl_review`
    ADD CONSTRAINT `review_status_CK1` CHECK ( `review_reply_status` IN ('N', 'Y') );



ALTER TABLE `tbl_delivery`
    ADD CONSTRAINT `tbl_delivery_fk` FOREIGN KEY (store_order_code) REFERENCES tbl_store_order (store_order_code);

ALTER TABLE `tbl_delivery`
    ADD CONSTRAINT `tbl_delivery_CK` CHECK ( `delivery_type` IN ('SEND', 'RETURN', 'RESEND') );



ALTER TABLE `tbl_delivery_address`
    ADD CONSTRAINT `tbl_delivery_address_fk` FOREIGN KEY (member_code) REFERENCES tbl_member (member_code);



ALTER TABLE `tbl_order`
    ADD CONSTRAINT `tbl_order_fk` FOREIGN KEY (member_code) REFERENCES tbl_member (member_code);

ALTER TABLE `tbl_order`
    ADD CONSTRAINT `tbl_order_CK` CHECK ( `address_type` IN ('R', 'J'));



ALTER TABLE `tbl_store_order`
    ADD CONSTRAINT `tbl_store_order_fk1` FOREIGN KEY (order_code) REFERENCES tbl_order (order_code);

ALTER TABLE `tbl_store_order`
    ADD CONSTRAINT `tbl_store_order_fk2` FOREIGN KEY (store_code) REFERENCES tbl_store (store_code);

ALTER TABLE `tbl_store_order`
    ADD CONSTRAINT `tbl_store_order_CK` CHECK ( `order_status` IN
                                                ('RECEIVED', 'REJECTED', 'PROCESSING', 'SHIPPED', 'DELIVERED',
                                                 'RETURN_REQUESTED', 'REFUNDED', 'COMPLETED'));



ALTER TABLE `tbl_order_detail`
    ADD CONSTRAINT `tbl_order_detail_fk1` FOREIGN KEY (option_code) REFERENCES tbl_product_option (option_code);

ALTER TABLE `tbl_order_detail`
    ADD CONSTRAINT `tbl_order_detail_fk2` FOREIGN KEY (store_order_code) REFERENCES tbl_store_order (store_order_code);


ALTER TABLE `tbl_refund_exchange`
    ADD CONSTRAINT `tbl_refund_exchange_fk` FOREIGN KEY (order_detail_code) REFERENCES tbl_order_detail (order_detail_code);

ALTER TABLE `tbl_refund_exchange`
    ADD CONSTRAINT `tbl_refund_exchange_CK` CHECK ( `order_change_status` IN ('APPROVAL', 'REJECTION', 'DONE') );

ALTER TABLE `tbl_refund_exchange`
    ADD CONSTRAINT `tbl_refund_exchange_CK1` CHECK ( `request_type` IN ('REFUND', 'EXCHANGE') );

ALTER TABLE `tbl_refund_exchange`
    ADD CONSTRAINT `tbl_refund_exchange_CK2` CHECK ( `attributable_clause` IN ('SELLER', 'BUYER') );



ALTER TABLE `tbl_coupon_creation`
    ADD CONSTRAINT `tbl_coupon_creation_fk` FOREIGN KEY (store_code) REFERENCES tbl_store (store_code);

ALTER TABLE `tbl_coupon_creation`
    ADD CONSTRAINT `tbl_coupon_creation_CK` CHECK ( `coupon_statue` IN ('Active', 'Inactive', 'Deleted') );



ALTER TABLE `tbl_coupon_product`
    ADD CONSTRAINT `tbl_coupon_product_fk1` FOREIGN KEY (coupon_creation_code) REFERENCES tbl_coupon_creation (coupon_creation_code);

ALTER TABLE `tbl_coupon_product`
    ADD CONSTRAINT `tbl_coupon_product_fk2` FOREIGN KEY (product_code) REFERENCES tbl_product (product_code);



ALTER TABLE `tbl_coupon`
    ADD CONSTRAINT `tbl_coupon_fk1` FOREIGN KEY (`coupon_creation_code`) REFERENCES `tbl_coupon_creation` (`coupon_creation_code`);

ALTER TABLE `tbl_coupon`
    ADD CONSTRAINT `tbl_coupon_fk2` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_coupon`
    ADD CONSTRAINT `tbl_coupon_fk3` FOREIGN KEY (`order_detail_code`) REFERENCES `tbl_order_detail` (`order_detail_code`)
        ON DELETE SET NULL;


ALTER TABLE `tbl_payment`
    ADD CONSTRAINT `tbl_payment_fk` FOREIGN KEY (`order_code`) REFERENCES `tbl_order` (`order_code`);

ALTER TABLE `tbl_payment`
    ADD CONSTRAINT `tbl_payment_CK` CHECK ( `payment_status` IN ('PAYMENT', 'CANCEL') );



ALTER TABLE `tbl_account`
    ADD CONSTRAINT `tbl_account_fk` FOREIGN KEY (`seller_code`) REFERENCES `tbl_seller` (`seller_code`);



ALTER TABLE `tbl_settlement_history`
    ADD CONSTRAINT `tbl_settlement_history_fk` FOREIGN KEY (`account_code`) REFERENCES `tbl_account` (`account_code`);

ALTER TABLE `tbl_settlement_history`
    ADD CONSTRAINT `adjustment_status_CK` CHECK ( `settlement_status` IN ('PAYMENT', 'NON_PAYMENT') );



ALTER TABLE `tbl_challenge_certify`
    ADD CONSTRAINT `tbl_challenge_certify_fk1` FOREIGN KEY (`challenge_code`) REFERENCES `tbl_challenge_info` (`challenge_code`);

ALTER TABLE `tbl_challenge_certify`
    ADD CONSTRAINT `tbl_challenge_certify_fk2` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_challenge_certify`
    ADD CONSTRAINT `certify_status_CK` CHECK ( `certify_status` IN ('ACTIVE', 'DELETED', 'MODIFIED') );



ALTER TABLE `tbl_challenge_info`
    ADD CONSTRAINT `tbl_challenge_info_fk1` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_challenge_info`
    ADD CONSTRAINT `tbl_challenge_info_fk2` FOREIGN KEY (`tag_code`) REFERENCES `tbl_tag` (`tag_code`);

ALTER TABLE `tbl_challenge_info`
    ADD CONSTRAINT `challenge_status_CK` CHECK ( `challenge_status` IN ('ACTIVE', 'DELETED', 'MODIFIED') );



ALTER TABLE `tbl_joiner`
    ADD CONSTRAINT `tbl_joiner_fk1` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_joiner`
    ADD CONSTRAINT `tbl_joiner_fk2` FOREIGN KEY (`challenge_code`) REFERENCES `tbl_challenge_info` (`challenge_code`);



ALTER TABLE `tbl_comment`
    ADD CONSTRAINT `tbl_comment_fk1` FOREIGN KEY (`certify_code`) REFERENCES `tbl_challenge_certify` (`certify_code`);

ALTER TABLE `tbl_comment`
    ADD CONSTRAINT `tbl_comment_fk2` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_comment`
    ADD CONSTRAINT `comment_status_CK` CHECK ( `comment_status` IN ('ACTIVE', 'DELETED', 'MODIFIED') );



ALTER TABLE `tbl_notice`
    ADD CONSTRAINT `tbl_notice_fk` FOREIGN KEY (`notice_writer`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_notice`
    ADD CONSTRAINT `tbl_notice_CK` CHECK ( `notice_status` IN ('DELETE', 'ACTIVE', 'MODIFIED') );

ALTER TABLE `tbl_notice`
    ADD CONSTRAINT `notice_fixable_CK` CHECK ( `notice_fixable` IN ('Y', 'N') );



ALTER TABLE `tbl_inquiry`
    ADD CONSTRAINT `tbl_inquiry_fk` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_inquiry`
    ADD CONSTRAINT `tbl_inquiry_CK1` CHECK ( inquiry_status IN ('PROCESSING', 'DONE') );

ALTER TABLE `tbl_inquiry`
    ADD CONSTRAINT `tbl_inquiry_CK2` CHECK ( inquiry_reply_status IN ('N', 'Y') );



ALTER TABLE `tbl_alarm_activation`
    ADD CONSTRAINT `tbl_alarm_activation_fk` FOREIGN KEY (`alarm_content_code`) REFERENCES `tbl_alarm_content` (`alarm_content_code`);

ALTER TABLE `tbl_alarm_activation`
    ADD CONSTRAINT `tbl_alarm_activation_CK` CHECK ( `alarm_activation` IN ('ENABLED', 'DISABLED') );



ALTER TABLE `tbl_alarm`
    ADD CONSTRAINT `tbl_alarm_fk1` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_alarm`
    ADD CONSTRAINT `tbl_alarm_fk2` FOREIGN KEY (`alarm_content_code`) REFERENCES `tbl_alarm_content` (`alarm_content_code`);


ALTER TABLE `tbl_alarm_content`
    ADD CONSTRAINT `tbl_alarm_content_fk` FOREIGN KEY (`alarm_code`) REFERENCES `tbl_alarm` (`alarm_code`);


ALTER TABLE `tbl_report`
    ADD CONSTRAINT `tbl_report_fk1` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_report`
    ADD CONSTRAINT `tbl_report_fk2` FOREIGN KEY (`comment_code`) REFERENCES `tbl_comment` (`comment_code`);

ALTER TABLE `tbl_report`
    ADD CONSTRAINT `tbl_report_fk3` FOREIGN KEY (`review_code`) REFERENCES `tbl_review` (`review_code`);

ALTER TABLE `tbl_report`
    ADD CONSTRAINT `tbl_report_fk4` FOREIGN KEY (`store_code`) REFERENCES `tbl_store` (`store_code`);

ALTER TABLE `tbl_report`
    ADD CONSTRAINT `tbl_report_CK` CHECK ( `report_type` IN ('STORE', 'REVIEW', 'COMMENT') );



ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk1` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk2` FOREIGN KEY (`notice_code`) REFERENCES `tbl_notice` (`notice_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk3` FOREIGN KEY (`shop_code`) REFERENCES `tbl_map` (`shop_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk4` FOREIGN KEY (`inquiry_code`) REFERENCES `tbl_inquiry` (`inquiry_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk5` FOREIGN KEY (`product_code`) REFERENCES `tbl_product` (`product_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk6` FOREIGN KEY (`review_code`) REFERENCES `tbl_review` (`review_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk7` FOREIGN KEY (`store_code`) REFERENCES `tbl_store` (`store_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk8` FOREIGN KEY (`report_code`) REFERENCES `tbl_report` (`report_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk9` FOREIGN KEY (`challenge_code`) REFERENCES `tbl_challenge_info` (`challenge_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `tbl_image_fk10` FOREIGN KEY (`certify_code`) REFERENCES `tbl_challenge_certify` (`certify_code`);

ALTER TABLE `tbl_image`
    ADD CONSTRAINT `image_CK` CHECK ( `board_type` IN
                                      ('NOTICE', 'INQUIRY', 'REVIEW', 'PRODUCT', 'CERTIFY', 'CHALLENGE', 'STORE',
                                       'MEMBER', 'MAP', 'REPORT', 'SCREENSHOT'));


commit;

