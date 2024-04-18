-- 생성자 Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   위치:        2024-02-21 16:41:03 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE SEQUENCE sq_seq_bbs START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sq_seq_buy_dtl START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sq_seq_buy_mst START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sq_seq_cst START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sq_seq_mng START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sq_seq_pay START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sq_seq_prd START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sq_seq_sle START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE sq_seq_sll START WITH 1 INCREMENT BY 1;

CREATE TABLE tb_bbs (
    seq_bbs        NUMBER(10) NOT NULL,
    seq_bbs_parent NUMBER(10),
    cd_bbs_type    NUMBER(3) NOT NULL,
    cd_bbs_tab     NUMBER(3),
    title          NVARCHAR2(128) NOT NULL,
    contents       NVARCHAR2(2000) NOT NULL,
    flg_top        CHAR(1),
    flg_delete     CHAR(1) NOT NULL,
    file_save      VARCHAR2(256),
    file_orig      VARCHAR2(64),
    dt_reg         DATE NOT NULL,
    register       NUMBER(10) NOT NULL,
    dt_upt         DATE,
    updater        NUMBER(10)
)
LOGGING;

COMMENT ON COLUMN tb_bbs.seq_bbs IS
    '게시판 일련번호';

COMMENT ON COLUMN tb_bbs.seq_bbs_parent IS
    '상위글 일련번호';

COMMENT ON COLUMN tb_bbs.cd_bbs_type IS
    '게시판 구분(공지 사항: 1, 자주 찾는 질문: 2, 고객 문의: 3)';

COMMENT ON COLUMN tb_bbs.cd_bbs_tab IS
    '게시판 탭(1:상품, 2:구매, 3:결제)';

COMMENT ON COLUMN tb_bbs.title IS
    '제목';

COMMENT ON COLUMN tb_bbs.contents IS
    '내용';

COMMENT ON COLUMN tb_bbs.flg_top IS
    '최상위 여부';

COMMENT ON COLUMN tb_bbs.flg_delete IS
    '삭제 여부';

COMMENT ON COLUMN tb_bbs.file_save IS
    '저장 파일명(상태 위치 포함)';

COMMENT ON COLUMN tb_bbs.file_orig IS
    '원본 파일명';

COMMENT ON COLUMN tb_bbs.dt_reg IS
    '등록 일시';

COMMENT ON COLUMN tb_bbs.register IS
    '등록자';

COMMENT ON COLUMN tb_bbs.dt_upt IS
    '수정 일시';

COMMENT ON COLUMN tb_bbs.updater IS
    '수정자';

CREATE INDEX ix1_bbs ON
    tb_bbs (
        title
    ASC )
        LOGGING;

CREATE INDEX ix2_bbs ON
    tb_bbs (
        contents
    ASC )
        LOGGING;

ALTER TABLE tb_bbs ADD CONSTRAINT pk_bbs PRIMARY KEY ( seq_bbs );

CREATE TABLE tb_buy_dtl (
    seq_buy_dtl NUMBER(19) NOT NULL,
    seq_buy_mst NUMBER(19) NOT NULL,
    seq_prd     NUMBER(10) NOT NULL,
    seq_sle     NUMBER(10) NOT NULL,
    price       NUMBER(10) NOT NULL,
    count       NUMBER(3) NOT NULL,
    dt_reg      DATE NOT NULL,
    register    NUMBER(10) NOT NULL
)
LOGGING;

COMMENT ON COLUMN tb_buy_dtl.seq_buy_dtl IS
    '구매 상세 일련번호';

COMMENT ON COLUMN tb_buy_dtl.seq_buy_mst IS
    '구매 일련번호';

COMMENT ON COLUMN tb_buy_dtl.seq_prd IS
    '상품 일련번호';

COMMENT ON COLUMN tb_buy_dtl.price IS
    '상품 가격';

COMMENT ON COLUMN tb_buy_dtl.count IS
    '상품 수량';

COMMENT ON COLUMN tb_buy_dtl.dt_reg IS
    '등록 일시';

COMMENT ON COLUMN tb_buy_dtl.register IS
    '등록자';

CREATE INDEX ix1_buy_dtl ON
    tb_buy_dtl (
        seq_prd
    ASC )
        LOGGING;

ALTER TABLE tb_buy_dtl ADD CONSTRAINT pk_buy_dtl PRIMARY KEY ( seq_buy_dtl );

CREATE TABLE tb_buy_mst (
    seq_buy_mst       NUMBER(19) NOT NULL,
    seq_cst           NUMBER(10) NOT NULL,
    seq_sll           NUMBER(5) NOT NULL,
    buy_info          NVARCHAR2(128) NOT NULL,
    buy_t_count       NUMBER(3) NOT NULL,
    buy_t_price       NUMBER(10) NOT NULL,
    flg_delete        CHAR(1) NOT NULL,
    cd_state_pay      CHAR(1) NOT NULL,
    cd_state_delivery CHAR(1) NOT NULL,
    dt_reg            DATE NOT NULL,
    register          NUMBER(10) NOT NULL,
    dt_upt            DATE,
    updater           NUMBER(5)
)
LOGGING;

COMMENT ON COLUMN tb_buy_mst.seq_buy_mst IS
    '구매 마스터 일련번호';

COMMENT ON COLUMN tb_buy_mst.seq_cst IS
    '구매자 일련번호';

COMMENT ON COLUMN tb_buy_mst.seq_sll IS
    '판매자 일련번호';

COMMENT ON COLUMN tb_buy_mst.buy_info IS
    '구매 정보';

COMMENT ON COLUMN tb_buy_mst.buy_t_count IS
    '구매 총 수량';

COMMENT ON COLUMN tb_buy_mst.buy_t_price IS
    '구매 총 가격';

COMMENT ON COLUMN tb_buy_mst.flg_delete IS
    '삭제 여부(참고: 부분 취소 불가)';

COMMENT ON COLUMN tb_buy_mst.cd_state_pay IS
    '결제 상태 코드(결제 전:N, 결제 완료: Y, 결제 취소:C)';

COMMENT ON COLUMN tb_buy_mst.cd_state_delivery IS
    '배송 상태(P: 준비중, D:배송중, Y: 배송 완료)';

COMMENT ON COLUMN tb_buy_mst.dt_reg IS
    '등록 일시';

COMMENT ON COLUMN tb_buy_mst.register IS
    '등록자';

COMMENT ON COLUMN tb_buy_mst.dt_upt IS
    '수정 일시';

COMMENT ON COLUMN tb_buy_mst.updater IS
    '수정자';

ALTER TABLE tb_buy_mst ADD CONSTRAINT pk_buy_mst PRIMARY KEY ( seq_buy_mst );

CREATE TABLE tb_cst (
    seq_cst   NUMBER(10) NOT NULL,
    id        VARCHAR2(16) NOT NULL,
    passwd    VARCHAR2(64) NOT NULL,
    tc_state  NUMBER(3) NOT NULL,
    cst_nm    VARCHAR2(64) NOT NULL,
    phone     VARCHAR2(64) NOT NULL,
    postcode  VARCHAR2(32) NOT NULL,
    addr1     VARCHAR2(128) NOT NULL,
    addr2     VARCHAR2(128),
    cst_email VARCHAR2(128) NOT NULL,
    flg_sms   CHAR(1) NOT NULL,
    dt_sms    DATE NOT NULL,
    flg_email CHAR(1) NOT NULL,
    dt_email  DATE NOT NULL,
    dt_reg    DATE NOT NULL,
    register  NUMBER(10) NOT NULL,
    dt_upt    DATE,
    updater   NUMBER(10)
)
LOGGING;

COMMENT ON COLUMN tb_cst.seq_cst IS
    '구매자 일련번호';

COMMENT ON COLUMN tb_cst.id IS
    '아이디';

COMMENT ON COLUMN tb_cst.passwd IS
    '비밀번호(해쉬 암호화)';

COMMENT ON COLUMN tb_cst.tc_state IS
    '통합 상태 코드(대기:0, 사용:1, 탈퇴:2, 강퇴:3)';

COMMENT ON COLUMN tb_cst.cst_nm IS
    '성명(대칭키 암호화)';

COMMENT ON COLUMN tb_cst.phone IS
    '연락처(대칭키 암호화)';

COMMENT ON COLUMN tb_cst.postcode IS
    '우편번호(대칭키 암호화)';

COMMENT ON COLUMN tb_cst.addr1 IS
    '주소1(대칭키 암호화)';

COMMENT ON COLUMN tb_cst.addr2 IS
    '주소2(대칭키 암호화)';

COMMENT ON COLUMN tb_cst.cst_email IS
    '이메일(대칭키 암호화)';

COMMENT ON COLUMN tb_cst.flg_sms IS
    'SMS 수신 동의';

COMMENT ON COLUMN tb_cst.dt_sms IS
    'SMS 수신 설정 일시';

COMMENT ON COLUMN tb_cst.flg_email IS
    '이메일 수신 동의';

COMMENT ON COLUMN tb_cst.dt_email IS
    '이메일 수신 설정 일시';

COMMENT ON COLUMN tb_cst.dt_reg IS
    '등록 일시';

COMMENT ON COLUMN tb_cst.register IS
    '등록자';

COMMENT ON COLUMN tb_cst.dt_upt IS
    '수정 일시';

COMMENT ON COLUMN tb_cst.updater IS
    '수정자';

ALTER TABLE tb_cst ADD CONSTRAINT pk_cst PRIMARY KEY ( seq_cst );

ALTER TABLE tb_cst ADD CONSTRAINT uq1_cst UNIQUE ( id );

CREATE TABLE tb_pay (
    seq_pay       NUMBER(10) NOT NULL,
    seq_buy_mst   NUMBER(19) NOT NULL,
    deal_num      VARCHAR2(32) NOT NULL,
    cd_pay_method VARCHAR2(8) NOT NULL,
    flg_success   CHAR(1),
    dt_reg        DATE NOT NULL,
    register      NUMBER(9) NOT NULL
)
LOGGING;

COMMENT ON COLUMN tb_pay.seq_pay IS
    '결제 일련번호';

COMMENT ON COLUMN tb_pay.seq_buy_mst IS
    '구매 마스터 일련번호';

COMMENT ON COLUMN tb_pay.deal_num IS
    '거래 번호(PG 기준)';

COMMENT ON COLUMN tb_pay.cd_pay_method IS
    '결제 수단 코드';

COMMENT ON COLUMN tb_pay.flg_success IS
    '성공: Y, 실패: N';

COMMENT ON COLUMN tb_pay.dt_reg IS
    '등록 일시';

COMMENT ON COLUMN tb_pay.register IS
    '등록자';

ALTER TABLE tb_pay ADD CONSTRAINT pk_pay PRIMARY KEY ( seq_pay );

CREATE TABLE tb_prd (
    seq_prd      NUMBER(10) NOT NULL,
    prd_nm       NVARCHAR2(128) NOT NULL,
    seq_ctg      NUMBER(3),
    desces       NVARCHAR2(1024),
    price_cost   NUMBER(10),
    count_stack  NUMBER(10),
    flg_delete   CHAR(1) NOT NULL,
    cd_state_prd NUMBER(3) NOT NULL,
    dt_reg       DATE NOT NULL,
    register     NUMBER(5) NOT NULL,
    dt_upt       DATE,
    updater      NUMBER(5)
)
LOGGING;

COMMENT ON COLUMN tb_prd.seq_prd IS
    '상품 일련번호';

COMMENT ON COLUMN tb_prd.prd_nm IS
    '상품명';

COMMENT ON COLUMN tb_prd.seq_ctg IS
    '카테고리 일련번호';

COMMENT ON COLUMN tb_prd.desces IS
    '설명';

COMMENT ON COLUMN tb_prd.price_cost IS
    '매입 가격';

COMMENT ON COLUMN tb_prd.count_stack IS
    '재고 수량';

COMMENT ON COLUMN tb_prd.flg_delete IS
    '삭제 여부';

COMMENT ON COLUMN tb_prd.cd_state_prd IS
    '상품 상태 코드(대기:0, 사용중:1, 중지:2)';

COMMENT ON COLUMN tb_prd.dt_reg IS
    '등록 일시';

COMMENT ON COLUMN tb_prd.register IS
    '등록자';

COMMENT ON COLUMN tb_prd.dt_upt IS
    '수정 일시';

COMMENT ON COLUMN tb_prd.updater IS
    '수정자';

ALTER TABLE tb_prd ADD CONSTRAINT pk_prd PRIMARY KEY ( seq_prd );

CREATE TABLE tb_sle (
    seq_sle       NUMBER(10) NOT NULL,
    seq_prd       NUMBER(10) NOT NULL,
    sle_nm        NVARCHAR2(128) NOT NULL,
    cd_where      NUMBER(3),
    cd_ctg_where  VARCHAR2(64),
    img           VARCHAR2(256),
    desces        NVARCHAR2(1024),
    price_sale    NUMBER(10) NOT NULL,
    cd_state_sale NUMBER(3) NOT NULL,
    dt_sale_start DATE,
    dt_sale_end   DATE,
    dt_reg        DATE NOT NULL,
    register      NUMBER(5) NOT NULL,
    dt_upt        DATE,
    updater       NUMBER(5)
)
LOGGING;

COMMENT ON COLUMN tb_sle.seq_sle IS
    '판매 일련번호';

COMMENT ON COLUMN tb_sle.seq_prd IS
    '상품 일련번호';

COMMENT ON COLUMN tb_sle.sle_nm IS
    '판매명';

COMMENT ON COLUMN tb_sle.cd_where IS
    '판매처 코드';

COMMENT ON COLUMN tb_sle.cd_ctg_where IS
    '카테고리 코드(판매처 기준)';

COMMENT ON COLUMN tb_sle.img IS
    '이미지';

COMMENT ON COLUMN tb_sle.desces IS
    '설명';

COMMENT ON COLUMN tb_sle.price_sale IS
    '판매 가격';

COMMENT ON COLUMN tb_sle.cd_state_sale IS
    '판매 상태 코드(0: 판매 대기, 1:판매중, 2:판매 중지)';

COMMENT ON COLUMN tb_sle.dt_sale_start IS
    '판매 시작 일시';

COMMENT ON COLUMN tb_sle.dt_sale_end IS
    '판매 종료 일시';

COMMENT ON COLUMN tb_sle.dt_reg IS
    '등록 일시';

COMMENT ON COLUMN tb_sle.register IS
    '등록자';

COMMENT ON COLUMN tb_sle.dt_upt IS
    '수정 일시';

COMMENT ON COLUMN tb_sle.updater IS
    '수정자';

ALTER TABLE tb_sle ADD CONSTRAINT pk_sle PRIMARY KEY ( seq_sle );

CREATE TABLE tb_sll (
    seq_sll       NUMBER(5) NOT NULL,
    id            VARCHAR2(16) NOT NULL,
    passwd        VARCHAR2(64) NOT NULL,
    tc_state      NUMBER(3) NOT NULL,
    corp_nm       NVARCHAR2(64) NOT NULL,
    corp_num      VARCHAR2(64) NOT NULL,
    ceo           VARCHAR2(64) NOT NULL,
    corp_postcode VARCHAR2(16) NOT NULL,
    corp_addr1    NVARCHAR2(64) NOT NULL,
    corp_addr2    NVARCHAR2(64),
    corp_email    VARCHAR2(128) NOT NULL,
    corp_intro    NVARCHAR2(1024),
    dt_reg        DATE NOT NULL,
    register      NUMBER(5) NOT NULL,
    dt_upt        DATE,
    updater       NUMBER(5)
)
LOGGING;

COMMENT ON COLUMN tb_sll.seq_sll IS
    '판매자 일련번호';

COMMENT ON COLUMN tb_sll.id IS
    '아이디';

COMMENT ON COLUMN tb_sll.passwd IS
    '비밀번호(해쉬 암호화)';

COMMENT ON COLUMN tb_sll.tc_state IS
    '통합 상태 코드(대기:0, 사용:1, 탈퇴:2, 강퇴:3)';

COMMENT ON COLUMN tb_sll.corp_nm IS
    '기업명';

COMMENT ON COLUMN tb_sll.corp_num IS
    '사업자 등록 번호(대칭키 암호화)';

COMMENT ON COLUMN tb_sll.ceo IS
    '대표자명(대칭키 암호화)';

COMMENT ON COLUMN tb_sll.corp_postcode IS
    '사업자 우편번호';

COMMENT ON COLUMN tb_sll.corp_addr1 IS
    '사업자 주소1';

COMMENT ON COLUMN tb_sll.corp_addr2 IS
    '사업자 주소2';

COMMENT ON COLUMN tb_sll.corp_email IS
    '사업자 이메일(대칭키 암호화)';

COMMENT ON COLUMN tb_sll.corp_intro IS
    '소개';

COMMENT ON COLUMN tb_sll.dt_reg IS
    '등록 일시';

COMMENT ON COLUMN tb_sll.register IS
    '등록자';

COMMENT ON COLUMN tb_sll.dt_upt IS
    '수정 일시';

COMMENT ON COLUMN tb_sll.updater IS
    '수정자';

ALTER TABLE tb_sll ADD CONSTRAINT pk_sll PRIMARY KEY ( seq_sll );

ALTER TABLE tb_sll ADD CONSTRAINT uq1_sll UNIQUE ( id );

ALTER TABLE tb_buy_dtl
    ADD CONSTRAINT fk1_buy_dtl FOREIGN KEY ( seq_buy_mst )
        REFERENCES tb_buy_mst ( seq_buy_mst )
    NOT DEFERRABLE;

ALTER TABLE tb_buy_mst
    ADD CONSTRAINT fk1_buy_mst FOREIGN KEY ( seq_cst )
        REFERENCES tb_cst ( seq_cst )
    NOT DEFERRABLE;

ALTER TABLE tb_pay
    ADD CONSTRAINT fk1_pay FOREIGN KEY ( seq_buy_mst )
        REFERENCES tb_buy_mst ( seq_buy_mst )
    NOT DEFERRABLE;

ALTER TABLE tb_sle
    ADD CONSTRAINT fk1_sle FOREIGN KEY ( seq_prd )
        REFERENCES tb_prd ( seq_prd )
    NOT DEFERRABLE;

ALTER TABLE tb_buy_dtl
    ADD CONSTRAINT fk2_buy_dtl FOREIGN KEY ( seq_prd )
        REFERENCES tb_prd ( seq_prd )
    NOT DEFERRABLE;

ALTER TABLE tb_buy_dtl
    ADD CONSTRAINT fk3_sle FOREIGN KEY ( seq_sle )
        REFERENCES tb_sle ( seq_sle )
    NOT DEFERRABLE;



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             3
-- ALTER TABLE                             16
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          9
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
