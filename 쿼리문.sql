DROP TABLE EVENT_DETAIL_IMAGE;
DROP TABLE EVENTBOARD;
DROP TABLE MOVIE_DETAIL_IMAGE;
DROP TABLE MOVIE_DETAILS;
DROP TABLE MOVIE_MEMBER;
DROP TABLE MOVIE_ORDER;
DROP TABLE MOVIE_SEAT;
DROP TABLE MOVIE_SEAT1;
DROP TABLE MOVIE_SEAT2;
DROP TABLE NOTICEBOARD;
DROP TABLE ONELINEREVIEW;
DROP TABLE REVIEW_DETAIL_IMAGE;
DROP TABLE REVIEWBOARD;



CREATE TABLE "EVENT_DETAIL_IMAGE" 
   (	"IMAGE_ID" NUMBER(20,0), 
	"BOARDNO" NUMBER(20,0), 
	"FILENAME" VARCHAR2(50 BYTE), 
	"FILETYPE" VARCHAR2(40 BYTE), 
	"CREDATE" DATE DEFAULT SYSDATE
   );

Insert into EVENT_DETAIL_IMAGE (IMAGE_ID,BOARDNO,FILENAME,FILETYPE,CREDATE) values (1,1,'event1.jpg','detail_image',to_date('25/03/07','RR/MM/DD'));
Insert into EVENT_DETAIL_IMAGE (IMAGE_ID,BOARDNO,FILENAME,FILETYPE,CREDATE) values (2,2,'event2.jpg','detail_image',to_date('25/03/07','RR/MM/DD'));
Insert into EVENT_DETAIL_IMAGE (IMAGE_ID,BOARDNO,FILENAME,FILETYPE,CREDATE) values (3,3,'event3.jpg','detail_image',to_date('25/03/07','RR/MM/DD'));

CREATE TABLE "EVENTBOARD" 
   (	"BOARDNO" NUMBER, 
	"BOARDTITLE" VARCHAR2(200 BYTE), 
	"BOARDWRITEDATE" DATE DEFAULT sysdate
   );

Insert into EVENTBOARD (BOARDNO,BOARDTITLE,BOARDWRITEDATE) values (1,'��ȭ <�º�> ������Ű�� ��Ī',to_date('25/03/07','RR/MM/DD'));
Insert into EVENTBOARD (BOARDNO,BOARDTITLE,BOARDWRITEDATE) values (2,'<���÷���> "IM UPSET!" ����� ��ȸ',to_date('25/03/07','RR/MM/DD'));
Insert into EVENTBOARD (BOARDNO,BOARDTITLE,BOARDWRITEDATE) values (3,'<���� �̽İ� �� ����> 3���� �������� �̺�Ʈ',to_date('25/03/07','RR/MM/DD'));

  CREATE UNIQUE INDEX "SYS_C007760" ON "EVENTBOARD" ("BOARDNO");
  ALTER TABLE "EVENTBOARD" MODIFY ("BOARDTITLE" NOT NULL ENABLE);
  ALTER TABLE "EVENTBOARD" ADD PRIMARY KEY ("BOARDNO") ENABLE;


CREATE TABLE "MOVIE_DETAIL_IMAGE" 
   (	"IMAGE_ID" NUMBER(20,0), 
	"MOVIE_ID" NUMBER(20,0), 
	"FILENAME" VARCHAR2(50 BYTE), 
	"FILETYPE" VARCHAR2(40 BYTE), 
	"CREDATE" DATE DEFAULT SYSDATE
   );

Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (1,1,'89000.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));
Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (2,2,'89519_1000.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));
Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (3,3,'89484_1000.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));
Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (4,4,'89450_1000.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));
Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (5,5,'89058_1000.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));
Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (6,6,'89451_1000.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));
Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (7,7,'86824_1000.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));
Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (8,8,'89548_1000.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));
Insert into MOVIE_DETAIL_IMAGE (IMAGE_ID,MOVIE_ID,FILENAME,FILETYPE,CREDATE) values (9,9,'9999.jpg','detail_image',to_date('25/03/26','RR/MM/DD'));

  CREATE UNIQUE INDEX "SYS_C007730" ON "MOVIE_DETAIL_IMAGE" ("IMAGE_ID");
  ALTER TABLE "MOVIE_DETAIL_IMAGE" ADD PRIMARY KEY ("IMAGE_ID") ENABLE;



CREATE TABLE "MOVIE_DETAILS" 
   (	"MOVIE_ID" NUMBER(20,0), 
	"MOVIE_TITLE" VARCHAR2(100 BYTE), 
	"MOVIE_GENRE" VARCHAR2(50 BYTE), 
	"MOVIE_TIME" VARCHAR2(50 BYTE), 
	"MOVIE_DIRECTOR" VARCHAR2(50 BYTE), 
	"MOVIE_ACTOR" VARCHAR2(50 BYTE), 
	"MOVIE_STORY" VARCHAR2(2000 BYTE), 
	"MOVIE_OPENDAY" VARCHAR2(50 BYTE), 
	"MOVIE_RANK" VARCHAR2(50 BYTE), 
	"MOVIE_PRODUCER" VARCHAR2(50 BYTE), 
	"MOVIE_STATUS" VARCHAR2(50 BYTE) DEFAULT 'y'
	--"MOVIE_KEYWORD" VARCHAR2(100 BYTE)
   );

Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (1,'�º�','���','118��','������','�̺���','���� �ְ� �ٵ� ��ȸ���� ���� ���� ����ڰ� �� ������.<br>
�� ������ �������� �����޴� �״� �ٵ� �ŵ��̶� �Ҹ��� ��âȣ�� ���ڷ� �´´�.<br>
���������� �⼼�� 8���̾ߡ� ���ڿ� �� ���� �Ʒ����� �԰� �ڸ� ����ģ �� ����. <br>
��ΰ� ������ ���� �¸��� �����ߴ� ù ���� ��ῡ�� �������� �� ������ ���Ѻ��� ���, �⼼�� ź ���ڿ��� ��������� ���Ѵ�. �������� �й踦 ���� �������� ���� �º��� ���� �˰� �� ��âȣ �������� Ÿ�� �ºλ��� ������ �ǻ츮�� �ٽ� �ѹ� �ö� ����� �ϰ� �Ǵµ���<br>','2025/03/26','12�� �̻� ������','BH�������θ�Ʈ','y');
Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (2,'�κ�','���/�ڹ̵�','106��','������','������,���Ǽ�,�̵���','"������ �ο��� �ɸ�, ��� ������ �ο���?"<br>
�����ۿ� �𸣴� ��ŸƮ�� ��ǥ â��(������)��
���̹� ȸ�� ��ǥ ����(�ں���)�� �ްŷ� ������ ��ȸ��, ����� ������ ���ѱ��.
���� ȸ���� ������ Ż�ⱸ�� 4�� ���� ���ϴ� ��å����� ������, �ѹ濡 �ں��� Ȯ���ϴ� ��!<br>
������ �κ� �־ �Ѽ� ���� ����� �����(������)�� ���ġ ������ ��Ȳ,
â���� ���� ���� ������� ���������� �ǹ��� ��� �ִ� ���� �ֽ���(���Ǽ�)���� ������
������ �ο� �����ϰ� �Ǵµ�...<br>
��ħ�� �ްŷ��� �̷����� �����忡
�ѳ� �ѽ� ������ ������ ���� ���� �κ�����,
�̵��� ������ �κ� ��������!<br>','2025/04/02','15�� �̻� ������','��ũ�Ͽ콺���۴�','y');
Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (3,'���� �̽İ� �� ����','���','110��','�������� ��Ÿī','�������� ��Ÿī','�츮���� ��ģ������ ���� ȥ�䷯ ��� �� �λ� �ִ� ���� �߹�!<br>
<br>
�� ������ ������ ������ �ް� �ĸ��� ������ �̳밡�ö� ���(�����ð� ��Ÿī)��
� ���� �Ծ��� ������ �� �ٽ� ������ �ʹٴ� ������ Ȳ���� ��Ź�� ����ֱ�� �Ѵ�<br>
��� ���� ��¯����� �Ҹ��� �� ������ ��ü�� ã�� �ܵ������� ���ϴ� �� ��ǳ�� ����
�ѱ��� �̸� �� ������ ���з��� �ҹ� �Ա� �ҵ����� �޴´�
����� �賭�� ���� �ӿ��� �쿬�� ������ �� ���� ������� ������ ������
���� �ñ��� ������ �����ϰ� �Ǵµ�......<br>
<br>
������ �ĸ����� ������ �Ϻ��� �ܵ���, �ѱ� ��ǳ�� �� �������� ���, �ٽ� �Ϻ� �����!
������ �ñ��� ������ ���� ��� ���� ���ִ� ������ ��������!<br>
<br>','2025/03/19','��ü ������','�쵵�ڷ�����','y');
Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (4,'�÷ο�','�ִϸ��̼�','85��','���� ���߷ε�','�����, ��縮Ʈ����, �����̼���','�ĵ��� ������ ��,
������� ������ ���۵ȴ�!<br>
�ΰ��� ��Ҵ� �������� �����ִ� ����,<br>
Ȧ�� ���� ��Ű�� ����̴� ���۽����� ��ȫ����
��ȭ�Ӵ� �ϻ�� �ƴ��ߴ� ������ �Ұ� ����.<br>
����ħ �ٰ��� ���� �迡 �ö�ź ����̴�
�� �ȿ��� ��� ��Ʈ����, ī�ǹٶ�,
���������, �����̼����� ������
������ �������� �غ��ϰ�
���� �̷� �賭�� �ĵ��� ���ĳ�����.','2025/03/19','��ü ������','�帲����Ʃ���','y');
Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (5,'��Ű17','���,��庥ó','137��','����ȣ','�ι�Ʈ��ƾ��,��Ƽ�� ��','������� �� ��° ��Ű�Դϱ�?��<br>
<br>
ģ�� ��Ƽ�𡯿� �Բ� ���� ��ī�� ���԰� �̵� ���� �ž��� ���� ����
�� ������ ���̰ڴٴ� ��ä���ڸ� ���� ������ ������ �ϴ� ����Ű��.
����� ���� �״�, ��ġ�� �����ȡ��� �����༺ ��ô�ܿ���
������ ���� ���ð�, ������ �ٽ� ����Ʈ�Ǵ� �ͽ������� �����Ѵ�.<br>
4���� ���ؿ� �����༺ �������ӿ� ������ �ڿ��� �� ����Ű���� ������ ����ģ�� ��������.
�׿� �Բ�, ����Ű���� �ݺ��Ǵ� ������ ����� ����Ŭ���� �ͼ�������.
�׷��� ����Ű 17���� �����༺�� ����ü�� ��ũ���ۡ��� ���� �� ���� ���⿡�� ���ƿ� ����
�̹� ����Ű 18���� ����Ʈ�Ǿ� �ִ�.
�༺ �� 1�� ���� �ͽ�������� ���� �� ����Ƽ�á� ��Ȳ
�� �� �ϳ��� �׾�� �ϴ� ���� �ӿ� ������ �� ���� ����� ��ٸ��� �־����ϡ�<br>
<br>
���ھ� �װ�, ���� ������<br>','2025/02/28','15�� �̻� ������','������ũ��','y');
Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (6,'����̺� �� Ÿ������','�׼�','100��','���� Ȳ','��ũ ���ݽ�','Ÿ�������� ������ ������ ������ īü�̽�!
����� �� �극��ũ ���� �߰����� ���۵ȴ�! <br>
�ְ��� ����ܼӱ� �� ���, �� �ѷ�(��ũ ���ݽ�).
�͸��� ���������κ��� �غ� �а� ���� �״�
�븸 �ִ��� ����� Mr.��(�� ��)�� ��� ���� Ÿ������ �Ѻ������� �����Ѵ�.<br>
 <br>
��� ���� ��ȹ��� ����Ǵ� ����
���� �տ� ��Ÿ�� ������ ���̼�, ����(�����)
15�� ��, �������� �̰߰� ����ߴ� �׳�
������ ���� ����� ���� �Ƴ��� �� �տ� �� �ִ�.<br>
�Ŵ��� ������ �׵��� �Ѱ�,
���� ��ü�� ������ ���� �Ѱ����� ��������!<br>
�������� RPM!
�극��ũ�� �ʿ� ����!
����, ���ӷ����� �����϶�!<br>','2025/04/11','15�� �̻� ������','����������','e');
Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (7,'�̼����ļ��� ���̳η��ڴ�','�׼�','130��','ũ�������� ������','�� ũ����','�̼� ���ļ��� �ø����� 8��° ��ȭ���� ������ ��ȭ. ������ ���̼� ���ļ���: ���� ���ڴס����� �ٷ� �̾����� �����̴�.<br>','2025/05/23','15�� �̻� ������','��ī�̴��̵��','e');
Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (8,'�ŷ��ѹ� �������ͽ�','�׼�','92��','�Ӵ���','������,����,�̴���','���� �����ϴ� ���ܿ� ���� ȥ���� ���� ����,
Ư���� �ɷ��� ���� ����� �ذ�� ���ŷ��� �㡯 ��
�ٿ�(������), ����(����), �豺(�̴���)��
���� ������ ó���ϴ� ����Ʈ �׼�<br>','2025/04/30','15�� �̻� ������','(��)����ġ��ó��','e');
Insert into MOVIE_DETAILS (MOVIE_ID,MOVIE_TITLE,MOVIE_GENRE,MOVIE_TIME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_STORY,MOVIE_OPENDAY,MOVIE_RANK,MOVIE_PRODUCER,MOVIE_STATUS) values (9,'���� ������ �԰� �;�','���','192��','��Űī�� ��','�ϸ��� �̳���, ŰŸ��� Ÿ���','�����θ� �����̷� ����� ��, �б� �ְ��� �α��� �׳�. <br>
<br>
��� ��, �쿬�� �ֿ� [��������]�� ���� ���� �׳�� ����� �����ϰ� �Ǿ���. 
�� �� ����ۿ� ����. ������ ����� ������ �������� ���<br>
�װ� �ǳ� ������ ���� ���� ���.
�׶� ���� �� ���� �ǹ̸� ���� ���ߴ�. <br>','2025/04/09','12�� �̻� ������','��ȣ','e');

CREATE UNIQUE INDEX "SYS_C007701" ON "MOVIE_DETAILS" ("MOVIE_ID");
ALTER TABLE "MOVIE_DETAILS" ADD PRIMARY KEY ("MOVIE_ID") ENABLE;


CREATE TABLE "MOVIE_MEMBER" 
   (	"MEMBER_ID" VARCHAR2(20 BYTE) PRIMARY KEY, 
	"MEMBER_PW" VARCHAR2(20 BYTE) NOT NULL,
	"MEMBER_NAME" VARCHAR2(10 BYTE) NOT NULL, 
	"MEMBER_HP1" VARCHAR2(10) NOT NULL, 
	"MEMBER_HP2" VARCHAR2(10) NOT NULL, 
	"MEMBER_HP3" VARCHAR2(10) NOT NULL, 
	"JOINDATE" DATE DEFAULT sysdate
   );


Insert into MOVIE_MEMBER (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_HP1,MEMBER_HP2,MEMBER_HP3,JOINDATE) values ('admin','1234','������','010','111','111',to_date('25/04/01','RR/MM/DD'));
Insert into MOVIE_MEMBER (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_HP1,MEMBER_HP2,MEMBER_HP3,JOINDATE) values ('1234','1234','1234','010','1234','1234',to_date('25/04/01','RR/MM/DD'));

  CREATE UNIQUE INDEX "SYS_C007783" ON "MOVIE_MEMBER" ("MEMBER_ID");
  ALTER TABLE "MOVIE_MEMBER" ADD PRIMARY KEY ("MEMBER_ID") ENABLE;

CREATE TABLE "MOVIE_ORDER" 
   (	"MORDER_SEQ_NUM" NUMBER(20,0), 
	"MEMBER_ID" VARCHAR2(20 BYTE), 
	"MOVIE_ID" NUMBER(20,0), 
	"MOVIE_PRICE" NUMBER(20,0), 
	"MOVIE_TITLE" VARCHAR2(100 BYTE), 
	"MOVIE_PLACE" VARCHAR2(100 BYTE), 
	"MOVIE_SEAT_NUMBER" VARCHAR2(100 BYTE), 
	"MOVIE_PEOPLE_QTY" NUMBER(5,0), 
	--"MOVIE_FILENAME" VARCHAR2(60 BYTE), 
	"PAY_METHOD" VARCHAR2(200 BYTE), 
	"CARD_NAME" VARCHAR2(50 BYTE), 
	"CARD_PAY_MONTH" VARCHAR2(20 BYTE), 
	"ORDERD_AGE" VARCHAR2(100 BYTE), 
	"PAY_ORDERER_HP_NUM" VARCHAR2(20 BYTE), 
	"TICKET_NUMBER" NUMBER(30,0), 
	"PAY_ORDER_TIME" DATE DEFAULT sysdate, 
	"CARD_NUMBER" VARCHAR2(200 BYTE), 
	"MOVIE_SCREENING_DATE" VARCHAR2(200 BYTE), 
	"MOVIE_RUNNING_TIME" VARCHAR2(200 BYTE)
   );
    
CREATE SEQUENCE MORDER_SEQ
START WITH 1 -- ���� �ִ밪���� 1 ������ ��
INCREMENT BY 1
NOCACHE
NOCYCLE;

  CREATE UNIQUE INDEX "SYS_C007720" ON "MOVIE_ORDER" ("MORDER_SEQ_NUM");
  ALTER TABLE "MOVIE_ORDER" ADD PRIMARY KEY ("MORDER_SEQ_NUM") ENABLE;


CREATE TABLE "MOVIE_SEAT" 
   (	"MOVIE_PLACE" VARCHAR2(100 BYTE), 
	"MOVIE_SEAT_NUMBER" VARCHAR2(100 BYTE) 
   );

CREATE UNIQUE INDEX "SYS_C007711" ON "MOVIE_SEAT" ("MOVIE_SEAT_NUMBER");
ALTER TABLE "MOVIE_SEAT" ADD PRIMARY KEY ("MOVIE_SEAT_NUMBER") ENABLE;

CREATE TABLE "MOVIE_SEAT1" 
   (	"MOVIE_PLACE" VARCHAR2(100 BYTE), 
	"MOVIE_SEAT_NUMBER" VARCHAR2(100 BYTE)
   );

  CREATE UNIQUE INDEX "SYS_C007721" ON "MOVIE_SEAT1" ("MOVIE_SEAT_NUMBER");
  ALTER TABLE "MOVIE_SEAT1" ADD PRIMARY KEY ("MOVIE_SEAT_NUMBER") ENABLE;

CREATE TABLE "MOVIE_SEAT2" 
   (	"MOVIE_PLACE" VARCHAR2(100 BYTE), 
	"MOVIE_SEAT_NUMBER" VARCHAR2(100 BYTE)

   );
  CREATE UNIQUE INDEX "SYS_C007722" ON "MOVIE_SEAT2" ("MOVIE_SEAT_NUMBER");
  ALTER TABLE "MOVIE_SEAT2" ADD PRIMARY KEY ("MOVIE_SEAT_NUMBER") ENABLE;

CREATE TABLE "NOTICEBOARD" 
   (	"BOARDNO" NUMBER, 
	"BOARDTITLE" VARCHAR2(200 BYTE), 
	"BOARDCONTENT" VARCHAR2(4000 BYTE), 
	"BOARDWRITEDATE" DATE DEFAULT sysdate, 
	"MEMBER_ID" VARCHAR2(50 BYTE)
   );
   
Insert into NOTICEBOARD (BOARDNO,BOARDTITLE,BOARDCONTENT,BOARDWRITEDATE,MEMBER_ID) values (1,'�����׽�Ʈ1','�ȳ��ϼ���',to_date('25/03/25','RR/MM/DD'),'admin');

  CREATE UNIQUE INDEX "SYS_C007756" ON "NOTICEBOARD" ("BOARDNO");
  ALTER TABLE "NOTICEBOARD" ADD PRIMARY KEY ("BOARDNO") ENABLE;
  ALTER TABLE "NOTICEBOARD" MODIFY ("MEMBER_ID" NOT NULL ENABLE);
  ALTER TABLE "NOTICEBOARD" MODIFY ("BOARDCONTENT" NOT NULL ENABLE);
  ALTER TABLE "NOTICEBOARD" MODIFY ("BOARDTITLE" NOT NULL ENABLE);

CREATE TABLE "ONELINEREVIEW" 
   (	"ONELINEREVIEWNO" NUMBER, 
	"MOVIE_ID" NUMBER, 
	"ID" VARCHAR2(50 BYTE), 
    "PASSWORD" VARCHAR2 (100 BYTE),
	"CONTENT" VARCHAR2(100 BYTE),
    "PARENT_ONELINEREVIEWNO" NUMBER
   );
update ONELINEREVIEW set parent_onelinereviewno = onelinereviewno where parent_onelinereviewno is null;
   
  CREATE UNIQUE INDEX "SYS_C007848" ON "ONELINEREVIEW" ("ONELINEREVIEWNO");
  ALTER TABLE "ONELINEREVIEW" ADD PRIMARY KEY ("ONELINEREVIEWNO") ENABLE;

CREATE TABLE "REVIEW_DETAIL_IMAGE" 
   (	"IMAGE_ID" NUMBER(20,0), 
	"BOARDNO" NUMBER(20,0), 
	"FILENAME" VARCHAR2(50 BYTE), 
	"FILETYPE" VARCHAR2(40 BYTE), 
	"CREDATE" DATE DEFAULT SYSDATE, 
	"REG_ID" VARCHAR2(50 BYTE)
   );
   
CREATE TABLE "REVIEWBOARD" 
   (	"BOARDNO" NUMBER DEFAULT NULL, 
	"BOARDTITLE" VARCHAR2(200 BYTE), 
	"BOARDCONTENT" VARCHAR2(4000 BYTE), 
	"BOARDWRITEDATE" DATE DEFAULT sysdate, 
	"MEMBER_ID" VARCHAR2(50 BYTE), 
	"BOARDVIEW" NUMBER DEFAULT 0, 
	"BOARDPUSH" NUMBER DEFAULT 0, 
	"STARPOINT" NUMBER DEFAULT 0, 
	"MOVIE_ID" NUMBER
   );

  CREATE UNIQUE INDEX "SYS_C007764" ON "REVIEWBOARD" ("BOARDNO");
  ALTER TABLE "REVIEWBOARD" ADD PRIMARY KEY ("BOARDNO") ENABLE;
  ALTER TABLE "REVIEWBOARD" MODIFY ("MEMBER_ID" NOT NULL ENABLE);
  ALTER TABLE "REVIEWBOARD" MODIFY ("BOARDCONTENT" NOT NULL ENABLE);
  ALTER TABLE "REVIEWBOARD" MODIFY ("BOARDTITLE" NOT NULL ENABLE);
  

-- �̺�Ʈ �� �̹����� �̺�Ʈ �Խ��� ����
ALTER TABLE EVENT_DETAIL_IMAGE 
ADD CONSTRAINT FK_EVENT_DETAIL_IMAGE_BOARDNO 
FOREIGN KEY (BOARDNO) REFERENCES EVENTBOARD(BOARDNO) ON DELETE CASCADE;

-- ��ȭ �� �̹����� ��ȭ �� ���� ����
ALTER TABLE MOVIE_DETAIL_IMAGE 
ADD CONSTRAINT FK_MOVIE_DETAIL_IMAGE_MOVIE_ID 
FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE_DETAILS(MOVIE_ID) ON DELETE CASCADE;

-- ��ȭ �ֹ��� ȸ�� ���� ����
ALTER TABLE MOVIE_ORDER 
ADD CONSTRAINT FK_MOVIE_ORDER_MEMBER_ID 
FOREIGN KEY (MEMBER_ID) REFERENCES MOVIE_MEMBER(MEMBER_ID) ON DELETE SET NULL;

-- ��ȭ �ֹ��� ��ȭ ���� ����
ALTER TABLE MOVIE_ORDER 
ADD CONSTRAINT FK_MOVIE_ORDER_MOVIE_ID 
FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE_DETAILS(MOVIE_ID) ON DELETE CASCADE;

-- ���� ����� ��ȭ ���� ����
ALTER TABLE ONELINEREVIEW 
ADD CONSTRAINT FK_ONELINEREVIEW_MOVIE_ID 
FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE_DETAILS(MOVIE_ID) ON DELETE CASCADE;


-- ���� �� �̹����� ���� �Խ��� ����
ALTER TABLE REVIEW_DETAIL_IMAGE 
ADD CONSTRAINT FK_REVIEW_DETAIL_IMAGE_BOARDNO 
FOREIGN KEY (BOARDNO) REFERENCES REVIEWBOARD(BOARDNO) ON DELETE CASCADE;

-- ���� �� �̹����� ȸ�� ���� ����
ALTER TABLE REVIEW_DETAIL_IMAGE 
ADD CONSTRAINT FK_REVIEW_IMAGE_MEMBER_ID 
FOREIGN KEY (REG_ID) REFERENCES MOVIE_MEMBER(MEMBER_ID) ON DELETE SET NULL;

-- ���� �Խ��ǰ� ȸ�� ���� ����
ALTER TABLE REVIEWBOARD 
ADD CONSTRAINT FK_REVIEWBOARD_MEMBER_ID 
FOREIGN KEY (MEMBER_ID) REFERENCES MOVIE_MEMBER(MEMBER_ID) ON DELETE SET NULL;

-- ���� �Խ��ǰ� ��ȭ ���� ����
ALTER TABLE REVIEWBOARD 
ADD CONSTRAINT FK_REVIEWBOARD_MOVIE_ID 
FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE_DETAILS(MOVIE_ID) ON DELETE CASCADE;





-- ���� ������ �θ� ���� ���� (���� ����)
ALTER TABLE ONELINEREVIEW 
ADD CONSTRAINT FK_ONELINEREVIEW_PARENT 
FOREIGN KEY (PARENT_ONELINEREVIEWNO) REFERENCES ONELINEREVIEW(ONELINEREVIEWNO) ON DELETE CASCADE;

ALTER TABLE MOVIE_SEAT 
ADD CONSTRAINT FK_MOVIE_SEAT_PLACE 
FOREIGN KEY (MOVIE_PLACE) 
REFERENCES MOVIE_ORDER(MOVIE_PLACE) ON DELETE CASCADE;

ALTER TABLE MOVIE_SEAT 
ADD CONSTRAINT FK_MOVIE_SEAT_NUMBER 
FOREIGN KEY (MOVIE_SEAT_NUMBER) 
REFERENCES MOVIE_ORDER(MOVIE_SEAT_NUMBER) ON DELETE CASCADE;

ALTER TABLE MOVIE_SEAT1 
ADD CONSTRAINT FK_MOVIE_SEAT1_PLACE 
FOREIGN KEY (MOVIE_PLACE) 
REFERENCES MOVIE_ORDER(MOVIE_PLACE) ON DELETE CASCADE;

ALTER TABLE MOVIE_SEAT1 
ADD CONSTRAINT FK_MOVIE_SEAT1_NUMBER 
FOREIGN KEY (MOVIE_SEAT_NUMBER) 
REFERENCES MOVIE_ORDER(MOVIE_SEAT_NUMBER) ON DELETE CASCADE;

ALTER TABLE MOVIE_SEAT2 
ADD CONSTRAINT FK_MOVIE_SEAT2_PLACE 
FOREIGN KEY (MOVIE_PLACE) 
REFERENCES MOVIE_ORDER(MOVIE_PLACE) ON DELETE CASCADE;

ALTER TABLE MOVIE_SEAT2 
ADD CONSTRAINT FK_MOVIE_SEAT2_NUMBER 
FOREIGN KEY (MOVIE_SEAT_NUMBER) 
REFERENCES MOVIE_ORDER(MOVIE_SEAT_NUMBER) ON DELETE CASCADE;


