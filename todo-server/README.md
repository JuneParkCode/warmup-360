# TODO-SERVER

Kernel 360 사전과제 - 부록 1

## 프로젝트 설명

- Todo API Application
- 사용자가 할 일을 관리할 수 있는 어플리케이션 서버 개발

## 사용 기술

- Spring Boot 3.3.1
- Spring Data JPA
- H2 Database
- Git

## TODO

### 기능

- 할 일 CRUD
- 할 일의 완료 상태 표시 및 변경

### DB Table

**TASK**

|   Column    |     Type      | Description |
|:-----------:|:-------------:|:-----------:|
|     id      |     Long      |     PK      |
|    title    |    String     |   할 일 제목    |
| description |    String     |   할 일 설명    |
|  due_date   | LocalDateTime |     마감일     |
|   status    |    String     |   할 일 상태    |
| created_at  |   TimeStamp   |   할 일 생성일   |
| updated_at  |   TimeStamp   |   할 일 수정일   |

### API

- 요청과 응답은 JSON
- 표준 HTTP 상태코드로 표시

#### 할 일 관리

| Method |      Endpoint      |   Description   |
|:------:|:------------------:|:---------------:|
|  GET   |       /tasks       |    할 일 목록 조회    |
|  POST  |       /tasks       |     할 일 생성      |
|  GET   |    /tasks/{id}     |  특정 할 일 상세 조회   |
|  PUT   |    /tasks/{id}     |   특정  할 일 수정    |
| PATCH  | /tasks/{id}/status | 특정 할 일 완료 상태 변경 |
| DELETE |    /tasks/{id}     |    특정 할 일 삭제    |

#### 필터링

| Method |         Endpoint         | Description  |
|:------:|:------------------------:|:------------:|
|  GET   | /tasks?dueDate={dueDate} |   마감일로 필터링   |
|  GET   |  /tasks?status={status}  |   상태로 필터링    |
|  GET   |      /tasks/status       | 할 일의 상태목록 조회 |
