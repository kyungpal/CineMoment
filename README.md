
# 🎬 CineMoment - 영화 예매/리뷰 게시판 시스템

사용자들이 영화를 예매하거나, 영화 리뷰를 작성하며, 첨부 이미지를 함께 등록 및 수정할 수 있는 웹 애플리케이션입니다.

---

## 🚀 주요 기능

- 🔐 회원가입 / 로그인 / 로그아웃
- 📝 게시글 CRUD (작성, 조회, 수정, 삭제)
- 📸 이미지 업로드 및 수정
- 💬 댓글 및 대댓글 기능
- 🎟️ 영화 예매 기능
- 🧑‍💻 관리자 페이지 (게시글/예매/영화 관리)

---

## 🔧 기술 스택

- **Backend**: Java 8, Spring MVC, MyBatis
- **Frontend**: JSP, JSTL, HTML/CSS, JavaScript
- **Database**: OracleDB
- **File Upload**: MultipartFile, Apache Commons FileUpload
- **Build Tool**: Maven

---

## 🧩 아키텍처

- MVC 패턴 기반의 Spring 구조  
  → Controller → Service → DAO → MyBatis Mapper → DB  
- VO 객체를 통해 데이터 전달  
- 게시글 + 이미지 업로드 & 수정 기능 포함

---
## 🎫 ERD

![CINEMOMENT (1)](https://github.com/user-attachments/assets/1d0a669d-977d-4678-befe-f08ff96f4907)

---

## 🖼️ 담당한 개발 부분

### 📝 게시글 수정 기능
- 게시글과 첨부 이미지 수정 기능 구현
- MultipartHttpServletRequest를 통해 이미지 파일 리스트를 List<ImageFileVO>로 받아 처리
- 각 이미지의 정보를 반복문을 통해 설정 (boardNO, reg_id 등)
- 새 이미지 업로드 시 기존 이미지 삭제 후 DB 및 로컬에 반영
- 수정 데이터는 Map<String, Object>로 만들어 boardService.modifyReview()에 전달

---

## 💬 한줄평 및 대댓글 기능

- 로그인 여부와 관계없이 닉네임, 비밀번호, 내용으로 작성 가능
- 유효성 검사 후 Ajax로 서버에 등록 요청
- 부모 댓글: `ONELINEREVIEWNO = PARENT_ONELINEREVIEWNO`
- 대댓글: `ONELINEREVIEWNO ≠ PARENT_ONELINEREVIEWNO`
- 부모 댓글 아래 자식 댓글 계층 구조 출력 구현
- 대댓글 입력창 토글 및 등록 Ajax 처리
- 삭제 시 비밀번호 확인 Ajax 처리
- jQuery 기반의 이벤트 처리

---

## 🎟️ 예매 기능 로직 개선

### ✅ 구조 개선
- 기존 좌석 관리 중심 구조에서 스케줄 + 좌석 기반 예매 시스템으로 리팩토링
- 상영 스케줄(schedule), 좌석(seat), 좌석 현황(seat_status) 테이블 새로 설계
- 예매 시 스케줄 ID + 좌석 ID 함께 저장하여 정확한 좌석 관리
- 예매 취소 시 seat_status 동기화로 일관성 유지

### ✅ 전체 회원 예매 내역 조회
- 관리자 페이지에서 전체 예매 내역 확인 가능
- orderService.selectAllOrderAndSeatStatus()로 예매 + 좌석 정보 조회
- 뷰에서 표 형태로 예매 정보 출력

### ✅ 관리자 예매 취소 기능
- 예매번호, 스케줄 ID, 좌석 ID로 취소 요청 처리
- 예매 테이블에서 기록 삭제 및 seat_status 좌석 상태 복구
- 성공 시 알림창 후 페이지 이동

---

## ✅ 티켓팅 폼 진입 시 검증 로직 구현

### 파라미터 검증
- seat_id, seatNum, schedule_id가 null이거나 유효하지 않으면 메인 페이지로 리다이렉트

### 세션 값 검증
- movieTitle, movie_place가 세션에 없으면 메인 페이지로 리다이렉트 (잘못된 접근 판단)

---

## 🧠 프로젝트를 통해 배운 점

- 비동기 Ajax 처리와 댓글 계층 구조 설계
- 예매 좌석 상태의 정확한 동기화 로직 구성
- 사용자 UX를 고려한 검증 처리 및 예외 상황 핸들링
