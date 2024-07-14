## Kernel360 사전 과제

Course 03 - CH00 ~ CH 10

### TODO

- [x] Validation
    - 간단한 `YearMonth` Validator 만들어보기
- [x] Memory DB
    - 어플리케이션 내 메모리에 상주하는 DBRespository 만들기
- [x] JPA 적용
    - MySQL 을 활용한 DB 사용 / 준비
        - Docker compose 를 활용하여 MySQL 올리기
        - MySQL Workbench 를 활용하여 DB 접속 확인
        - MySQL 에서 사용할 DB, Table 생성
        - .env 활용하여 환경변수 관리
    - JPA 를 통해서 DB 연동하기
        - JPA 에 맞게 Entity 수정
        - DTO 를 활용하여 Entity 와 Controller 간의 데이터 전달
        - Repository 를 통한 DB 접근 / MethodQuery 활용

### Convention

- 코드 컨벤션의 경우 Naver Hackday Code Convention을 따름
    - https://naver.github.io/hackday-conventions-java/
    - `./naver-intellij-formatter.xml` 을 formatter 로 활용
    - `./naver-checkstyle-rules.xml` 을 checkstyle 로 활용
        - `suppression` 의 경우 `./naver-checkstyle-suppressions.xml`
    - 자동화된 툴로 잡히지 않는 일부 컨벤션의 경우 위 링크에서 언급하고 있으니 주의해야함.

- git commit message에 대한 가이드
    - [AngularJS Commit Message Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)
    - [좋은 git 커밋 메시지를 작성하기 위한 7가지 약속](https://meetup.nhncloud.com/posts/106)

### IntelliJ 설정

- [IntelliJ 관련 삽질 기록](https://life.photogrammer.me/intellj-settings-memo/)

### NOTE

- learn_spring 에서는 모두 구현하지 않음
- 일부 테스트 필요하다고 생각한 부분은 테스트 코드 작성함.
    - `MemoryDbRepository`
    - `Validators`
    - `UserMemoryDbRepository`
