/* mybatis DB 생성 */
create database mybatis;

/* user 생성 */
create user 'mybatis'@'localhost' identified by 'mybatis$'
grant all privileges on *.* to 'mybatis'@'localhost';


/* 존재하는 가게 정보 테이블 삭제 */
DROP TABLE SHOP;


/* 가게 정보 테이블 생성 */
CREATE TABLE SHOP (
  SHOP_NO DOUBLE NOT NULL,
  SHOP_NAME VARCHAR(100),
  SHOP_LOCATION LONGTEXT,
  SHOP_STATUS VARCHAR(20),
  CONSTRAINT SHOP_PK PRIMARY KEY (SHOP_NO)
);

/* 가게 정보 초기 데이터 등록 */
INSERT INTO SHOP (SHOP_NO,SHOP_NAME,SHOP_LOCATION,SHOP_STATUS) VALUES (1,'Toy Store','A Tower Seocho dong','Y');
INSERT INTO SHOP (SHOP_NO,SHOP_NAME,SHOP_LOCATION,SHOP_STATUS) VALUES (2,'Play Store','B Tower Seocho dong','Y');
INSERT INTO SHOP (SHOP_NO,SHOP_NAME,SHOP_LOCATION,SHOP_STATUS) VALUES (3,'Mom Store','C Tower Seocho dong','Y');
COMMIT;

/* 존재하는 장난감 테이블 삭제 */
DROP TABLE TOY;

/* 장난감 테이블 생성 */
CREATE TABLE TOY (
  TOY_NO DOUBLE NOT NULL,
  TOY_NAME VARCHAR(100),
  TOY_PRICE DOUBLE,
  SHOP_NO DOUBLE NOT NULL,
  CONSTRAINT TOY_PK PRIMARY KEY (TOY_NO)
);

/* 장난감 테이블 초기 데이터 등록 */
INSERT INTO TOY (TOY_NO,TOY_NAME,TOY_PRICE,SHOP_NO) VALUES (1,'Lego (Model-A100)', 30000, 1);
INSERT INTO TOY (TOY_NO,TOY_NAME,TOY_PRICE,SHOP_NO) VALUES (2,'Lego (Model-A200)', 60000, 1);
INSERT INTO TOY (TOY_NO,TOY_NAME,TOY_PRICE,SHOP_NO) VALUES (3,'Lego (Model-A300)', 90000, 1);
COMMIT;

/* 존재하는 가게 번호 시퀀스 삭제 */
CALL DropSequence('SEQ_SHOP_NO');

/* 가게 번호 시퀀스 생성 */
CALL CreateSequence('SEQ_SHOP_NO', 4, 1);