------------------- 유저 생성 --------------------
CREATE USER spring IDENTIFIED BY spring;

GRANT CONNECT, RESOURCE TO spring;
GRANT CREATE VIEW TO spring;

--------------------------------------------------
--------------     MEMBER 관련	------------------	
--------------------------------------------------
DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER (
  ID VARCHAR2(30) PRIMARY KEY,
  PWD VARCHAR2(100) NOT NULL,
  NAME VARCHAR2(15) NOT NULL,
  EMAIL VARCHAR2(100),
  GENDER VARCHAR2(1) CHECK (GENDER IN('M', 'F')),
  AGE NUMBER,
  PHONE VARCHAR2(13),
  ADDRESS VARCHAR2(100),
  ENROLL_DATE DATE DEFAULT SYSDATE,
  UPDATE_DATE DATE DEFAULT SYSDATE,
  M_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(M_STATUS IN('Y', 'N'))
);

COMMENT ON COLUMN MEMBER.ID IS '회원아이디';
COMMENT ON COLUMN MEMBER.PWD IS '회원비밀번호';
COMMENT ON COLUMN MEMBER.NAME IS '회원이름';
COMMENT ON COLUMN MEMBER.EMAIL IS '회원이메일';
COMMENT ON COLUMN MEMBER.GENDER IS '회원성별';
COMMENT ON COLUMN MEMBER.AGE IS '회원나이';
COMMENT ON COLUMN MEMBER.PHONE IS '회원전화번호';
COMMENT ON COLUMN MEMBER.ADDRESS IS '회원주소';
COMMENT ON COLUMN MEMBER.ENROLL_DATE IS '회원가입날짜';
COMMENT ON COLUMN MEMBER.UPDATE_DATE IS '회원수정날짜';
COMMENT ON COLUMN MEMBER.M_STATUS IS '회원상태값';

INSERT INTO MEMBER 
VALUES ('admin', '1234', '관리자', 'admin@ict.or.kr', 'F', 30, '010-1111-2222', '서울시 강남구 역삼동', '20180101', '20180101', DEFAULT);

INSERT INTO MEMBER 
VALUES ('user01', 'pass01', '홍길동', 'user01@ict.or.kr', 'M', 25, '010-3333-4444', '서울시 양천구 목동', '20180201', '20180202', DEFAULT);

INSERT INTO MEMBER 
VALUES ('user02', 'pass02', '김말똥', 'user02@ict.or.kr', 'F', 23, '010-5555-6666', '서울시 강서구 마곡', '20180301', '20180302', DEFAULT);

COMMIT;

----------------------------------------------------
----------------     NOTICE 관련        -----------------	
----------------------------------------------------
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_NID;

CREATE TABLE NOTICE (
  NID NUMBER PRIMARY KEY,
  NTITLE VARCHAR2(30) NOT NULL,
  NWRITER VARCHAR2(30),
  NCONTENT VARCHAR2(200),
  N_CREATE_DATE DATE DEFAULT SYSDATE,
  N_MODIFY_DATE DATE DEFAULT SYSDATE,
  FILE_PATH VARCHAR2(300),
  FOREIGN KEY (NWRITER) REFERENCES MEMBER(ID) ON DELETE CASCADE
);

COMMENT ON COLUMN NOTICE.NID IS '공지사항번호';
COMMENT ON COLUMN NOTICE.NTITLE IS '공지사항제목';
COMMENT ON COLUMN NOTICE.NWRITER IS '공지사항작성자아이디';
COMMENT ON COLUMN NOTICE.NCONTENT IS '공지사항내용';
COMMENT ON COLUMN NOTICE.N_CREATE_DATE IS '공지사항작성날짜';
COMMENT ON COLUMN NOTICE.N_MODIFY_DATE IS '공지사항수정날짜';
COMMENT ON COLUMN NOTICE.FILE_PATH IS '첨부파일경로';

CREATE SEQUENCE SEQ_NID;

INSERT INTO NOTICE VALUES (SEQ_NID.NEXTVAL, '관리자 공지', 'admin',
                          '공지서비스를 게시합니다.  많이 이용해 주세요', 
                          '20180201', '20180201', NULL);
                          
INSERT INTO NOTICE VALUES (SEQ_NID.NEXTVAL, '공지서비스 오픈 환영', 'admin',
                          '드디어 오픈되었군요. 많이 이용하겠습니다.', 
                          '20180201', '20180201', NULL);
                          
INSERT INTO NOTICE VALUES (SEQ_NID.NEXTVAL, '공지서비스 이용 안내', 'admin',
                          '공지서비스는 회원만 이용할 수 있습니다. 회원 가입하세요.', 
                          '20180201', '20180201', NULL);

COMMIT;


----------------------------------------------------
----------------     BOARD 관련        -----------------	
----------------------------------------------------
DROP TABLE BOARD CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_BID;

CREATE TABLE BOARD(
  BID NUMBER PRIMARY KEY,
  BTITLE VARCHAR2(100) NOT NULL,
  BWRITER VARCHAR2(15),
  BCONTENT VARCHAR2(4000),
  ORIGINAL_FILENAME VARCHAR2(100),
  RENAME_FILENAME VARCHAR2(100),
  BCOUNT NUMBER DEFAULT 0,
  B_CREATE_DATE DATE,
  B_MODIFY_DATE DATE,
  B_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (B_STATUS IN('Y', 'N')),
  FOREIGN KEY (BWRITER) REFERENCES MEMBER(ID) ON DELETE CASCADE
);

COMMENT ON COLUMN BOARD.BID IS '게시글번호';
COMMENT ON COLUMN BOARD.BTITLE IS '게시글제목';
COMMENT ON COLUMN BOARD.BWRITER IS '게시글작성자 아이디';
COMMENT ON COLUMN BOARD.BCONTENT IS '게시글내용';
COMMENT ON COLUMN BOARD.ORIGINAL_FILENAME IS '첨부파일원래이름';
COMMENT ON COLUMN BOARD.RENAME_FILENAME IS '첨부파일변경이름';
COMMENT ON COLUMN BOARD.BCOUNT IS '게시글조회수';
COMMENT ON COLUMN BOARD.B_CREATE_DATE IS '게시글올린날짜';
COMMENT ON COLUMN BOARD.B_MODIFY_DATE IS '게시글수정한날짜';
COMMENT ON COLUMN BOARD.B_STATUS IS '게시글상태값';


CREATE SEQUENCE SEQ_BID;

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '관리자 게시글', 'admin', 
       '저희 사이트를 이용해 주셔서 감사합니다.', NULL, NULL, 
       DEFAULT, '20180210', '20180210', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'MVC Model2 패턴이란', 'user01', 
       '웹 애플리케이션 설계 방식 중 하나입니다.', NULL, NULL, 
       DEFAULT, '20180211', '20180211', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '설계방식 2', 'user02', 
       '웹 애플리케이션 설계 방식 중 두번째 방식은 각 서블릿 구동 앞에 First Controller 를 두는 것입니다..', 
       NULL, NULL,  DEFAULT, '20180212', '20180212', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '설계방식3', 'user02', 
       '웹 애플리케이션 설계 방식 중 세번째 방식은 Front Controller 다음에 연결되는 컨트롤러들을 서블릿이 아닌 자바 클래스로 작성해서 연결하는 방식입니다.', 
       NULL, NULL, DEFAULT, '20180220', '20180220', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'MVC Model1 패턴', 'user01', 
       '웹 애플리케이션 설계 방식 중 JSP 파일이 뷰와 컨트롤러 두가지 다를 처리하는 방식입니다.', 
        NULL, NULL, DEFAULT, '20180220', '20180220', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'JSP란', 'user02', 'Java Server Page 를 말함', NULL, NULL, 
       DEFAULT, '20180221', '20180221', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'Servlet 이란', 'admin', '서버에서 구동되는 웹 규약이 적용된 Java EE 모듈이 제공하는 서비스 처리용 클래스임.', 
       NULL, NULL, DEFAULT, '20180221', '20180221', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'JSP Elements 들', 'admin', 
       'Derective(지시자) 태그, Decleation(선언) 태그, Scriptlet 태그, Expression 태그, Comment 태그가 있다.', 
      NULL, NULL, DEFAULT, '20180224', '20180225', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'HTML5', 'user02', 
       '새로운 웹 표준기술로 모든 디바이스 장치와 브라우저에서 동일하게 작동되는 웹 페이지를 만들기 위한 기술을 제공한다.', 
       NULL, NULL, DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'CSS3', 'user02', '웹 페이지를 꾸미기 위한 스타일시트로 HTML5 버전에 맞추어 속성들이 업그레이드 되었다.', 
       NULL, NULL, DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'JavaScript 란', 'user01', '웹 표준 2.0 에서 새로 추가된 강력한 API 들을 제공한다.', 
       NULL, NULL, DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'jQuery 란', 'admin', 
       '자바스크립트 오픈 소스 라이브러리의 하나로 html 요소들을 css 선택자를 이용하여 쉽게 선택할 수 있는 기능들을 제공한다..', 
       NULL, NULL, DEFAULT, '20181021', '20181021', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 'ajax 란', 'admin', 'asynchronos javascript and xml 의 줄임말로 서버의 서블릿과 직접 통신하는 자바스크립트 기술이다.', 
       NULL, NULL, DEFAULT, '20181021', '20181021', DEFAULT);
       
INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '필터(Filter) 란', 'user02', '클라이언트 요청한 서비스가 서블릿으로 전달되기 전에 먼저 구동되는 클래스이다.', 
       NULL, NULL, DEFAULT, '20181021', '20181021', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, '래파(Wrapper) 란', 'user01', '필터가 나꿔챈 요청에 대한 데이터 처리를 담당하는 클래스이다.', 
       NULL, NULL, DEFAULT, '20181021', '20181021', DEFAULT);



----------------------------------------------------
---------------     REPLY 관련         -------------------	
----------------------------------------------------
DROP TABLE REPLY CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_RID;

CREATE TABLE REPLY(
  RID NUMBER PRIMARY KEY,
  RCONTENT VARCHAR2(400),
  REF_BID NUMBER,
  RWRITER VARCHAR2(15),
  R_CREATE_DATE DATE,
  R_MODIFY_DATE DATE,
  R_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (R_STATUS IN ('Y', 'N')),
  FOREIGN KEY (REF_BID) REFERENCES BOARD ON DELETE CASCADE, 
  FOREIGN KEY (RWRITER) REFERENCES MEMBER(ID) ON DELETE CASCADE 
);

COMMENT ON COLUMN REPLY.RID IS '댓글번호';
COMMENT ON COLUMN REPLY.RCONTENT IS '댓글내용';
COMMENT ON COLUMN REPLY.REF_BID IS '참조게시글번호';
COMMENT ON COLUMN REPLY.RWRITER IS '댓글작성자아이디';
COMMENT ON COLUMN REPLY.R_CREATE_DATE IS '댓글작성날짜';
COMMENT ON COLUMN REPLY.R_MODIFY_DATE IS '댓글수정날짜';
COMMENT ON COLUMN REPLY.R_STATUS IS '댓글상태값';

CREATE SEQUENCE SEQ_RID;

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '첫번째 댓글입니다.', 1, 'admin', '20180213', '20180213', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '첫번째 댓글입니다.', 13, 'user01', '20181030', '20181030', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '두번째 댓글입니다.', 13, 'user02', '20181030', '20181030', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '마지막 댓글입니다.', 13, 'admin', '20181030', '20181030', DEFAULT);



COMMIT;
