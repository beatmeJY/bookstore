230401
1. 회원가입 로직 개발 완료
2. QueryDsl 도입 중.

230227
1. 수기로 작성한 Exception 핸들러 작성(예외처리 시 사용자 에러는 400 에러로 Resolver를 통해 변환할 것임.) 
2. Member, MemberInfo 테스트 로직 변경. 

230223
1. user -> member 로 변경 / 패키지명 member -> user 로 변경 (user를 도메인으로 둠으로써 로그인 등도 포함 시키기 위함)

230222
1. 도메인 주도 설계를 위한 feign 클라이언트 도입중. (내부 서버 API 연동을 통해 서로 로직에 관여하지 않고 사용만 함.)
  - 일단 내부적 Feign 연결 성공!
2. 도메인 주도 설계를 위해 ERD를 모두 변경중. (유저 -> 유저와 유저정보, 패스워드 등 모두 분리하기 위해 재 설계중)