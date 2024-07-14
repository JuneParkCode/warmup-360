# SimpleBoard

kernel 360 사전과제 simple board 구현

## TODO

- [x] Board, Post, Reply API 구현
- [x] JPA 와 MySQL 을 통한 DB 연동
- [x] Reply 의 경우 추상화된 `CRUDAbstract*` 를 활용하여 구현 하기
- [x] `Converter` 를 활용하여 DTO 와 Entity 간의 변환 class 만들기
- [x] `Filter / Interceptor / AOP` 관련한 부분 실습 / 적용
    - `LoggerFilter` : Filter 단에서 로깅 처리하는 법 이해
    - `LoggerInterceptor`: Interceptor 단에서 로깅 처리하는 법 이해
    - `TimeAspect` : AOP 를 활용하여 시간 측정하는 법 이해
- [x] 앞서 배운 예외처리 적용하기

## 학습 메모

### JPA

```java

@OneToMany(
	mappedBy = "board"
)
@Builder.Default // Builder 패턴에서 해당 필드를 기본값으로 설정
@ToString.Exclude
@SQLOrder("id DESC") // 연결된 post 엔티티를 id 역순으로 정렬
@SQLRestriction("status = 'REGISTERED'") // @Where 의 경우 Deprecated.
private List<PostEntity> postList = List.of();
```

- 연관관계 매핑의 경우 `@OneToMany` (1:N) `@ManyToOne` (N:1) `@OneToOne` (1:1) `@ManyToMany` (N:N) 가 있다.
    - `@OneToMany` 의 경우 `mappedBy` 를 통해 양방향 매핑을 할 수 있다.
    - `@ManyToOne` 의 경우 `@JoinColumn` 을 통해 외래키를 지정할 수 있다.
- `Builder.Default` 를 통해 기본값을 설정할 수 있다.
- 연관관계 설정시 주의 할 것들.
    - `toString()` 이 일어날 떄 연관관계가 순환되는 문제가 발생할 수 있다. 이를 막기 위해 `ToString.Exclude` 를 사용한다.
    - `JsonIgnore` 도 마찬가지. 다만, Entity 를 Json 으로 그대로 보내는 것은 좋은 습관이 아니다. DTO 를 활용하자.
- `@SQLOrder` 를 통해 정렬을 할 수 있다.
- `@SQLRestriction` 을 통해 조건을 걸 수 있다.

```java

@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
Pageable pageable
```

- `Pageable` 을 통한 Pagination 또한 가능하다. 이 기본 값을 정의하는 annoation으로 `@PageableDefault` 가 있다.

```java

@Column(columnDefinition = "TEXT")
private String content;
```

- `columnDefinition` 을 통해 컬럼의 타입을 지정할 수 있다.

```yaml
jpa:
  hibernate:
    ddl-auto: validate
```

- `ddl-auto` 에 대해서 주의하자. 해당 프로젝트의 경우 이미 테이블의 형태를 정의한 뒤, 사용하고 있다.
- `validate` 의 경우 Entity 와 DB 의 스키마가 일치하는지 확인한다. 현재의 경우와 같이 이미 DB 에 테이블이 존재하는 경우에 사용한다.
    - 일반적으로 Production 환경에서는 `validate` 또는 `none` 을 사용한다.

```text
create: 기존테이블 삭제 후 다시 생성 (DROP + CREATE)
create-drop: create와 같으나 종료시점에 테이블 DROP
update: 변경분만 반영(운영DB에서는 사용하면 안됨)
validate: 엔티티와 테이블이 정상 매핑되었는지만 확인
none: 사용하지 않음(사실상 없는 값이지만 관례상 none이라고 한다.)
```

### Filter

#### 클라이언트의 실제 요청 읽기

- `HttpEntity` 를 Controller 의 parameter 에서 받아서 받는 방법 (임시)
    - HttpEntity.getBody() 를 통해 실제 요청을 읽을 수 있음.
- `LoggerFilter` 와 같이 Filter 를 사용하는 방법
    - Filter 의 경우 Spring 쪽 context 가 아닌, 그 외부의 context 에서 관리됨.
- `HttpServletRequest` 의 경우 `Body` 부분에 대해서 `stream` 으로 관리하고 있다. 따라서 한번 읽으면 재사용이 불가능하다.
    - 이를 해결하기 위해서 `CachedBodyHttpServletRequest` 를 만들어서 doFilter 에서 이를 교체한다.
    - 해당 객체의 경우 Buffer 에 백업이 가능하다.
    - 주의할 점으로, 여전히 stream 을 읽으면 `response` 에서 마지막에 나갈 때 손실된다. 따라서 복구를 해야한다.
- `Filter` 에 대해서 활용할 때는 늘 "언제 실행되는지" 에 대해서 염두해둘 필요가 있을 것으로 보인다.
