# Stable Hash

Java 기반의 안정적인 해시 생성 유틸리티입니다.  
입력 값이 같으면 언제나 동일한 해시 값을 반환하며, `Object.hashCode()`의 불안정한 특성을 대체할 수 있습니다.

## ✅ 목적 및 특징

- JVM 환경과 관계없이 **항상 동일한 해시값** 생성
- `Object.hashCode()`나 `System.identityHashCode()`의 불안정성 회피
- JSON 직렬화 후 SHA-256 기반 해시 생성으로 **안정성과 충돌 회피율** 확보
- 캐시 키, 로그 추적 ID, 데이터 무결성 검증 등에 활용 가능

---

## 🧩 기술 스택

- Java 17
- Jackson (ObjectMapper)
- SHA-256 (MessageDigest)

---

## 💡 사용 예시

### 문자열 해시

```java
String input = "example";
String hash = StableHashUtil.hashString(input);
// "50d858e78227b1e301c1d24a8a55328c19e14c7f927c0a2e160e7b8d82b79947"
```

### 객체 해시

```java
Sample sample = new Sample("hello", 123);
String hash = StableHashUtil.hashObject(sample);
```

### Map 해시

```java
Map<String, Object> data = Map.of("id", 1, "name", "test");
String hash = StableHashUtil.hashObject(data);
```

---

## 🔐 해시 알고리즘 설명

- `Jackson ObjectMapper`로 입력 데이터를 JSON 문자열로 직렬화
- 이후 `SHA-256` 알고리즘으로 해시 생성
- **동일한 입력 객체**는 언제나 **동일한 해시 결과**를 가짐
- `null`, 순서 다른 Map 등 예외적인 케이스도 JSON 변환을 통해 정규화함

---

## ⚖️ 다른 해시 알고리즘과 비교

| 알고리즘   | 특징                                           | 안정성     | 속도       | 해시 길이 |
|------------|------------------------------------------------|------------|------------|------------|
| `hashCode()` | JVM 기반, 불안정, 런타임마다 달라질 수 있음         | ❌ 낮음     | ✅ 빠름     | 32비트     |
| `MurmurHash` | 속도 빠르고 충돌 적지만, 직접 구현 필요                | ⚠️ 보통     | ✅ 빠름     | 32/128비트 |
| `FNV-1a`     | 단순하고 빠르지만 충돌 가능성 있음                   | ⚠️ 보통     | ✅ 빠름     | 32/64비트  |
| **StableHash (SHA-256)** | 암호학적 안정성, 동일 입력 항상 동일 결과 | ✅ 매우 높음 | ❌ 비교적 느림 | 256비트    |

> ⚠️ *이 유틸은 속도보다 안정성과 일관성이 중요한 곳에 적합합니다.*

---

## 📦 라이브러리 의존성 추가

현재는 독립 실행용 유틸리티 클래스이며, 추후 별도 라이브러리로 배포할 계획입니다.  
직접 소스 복사해서 사용할 수 있으며, 다음 구조를 프로젝트에 추가하세요:

```text
src/
└── main/java/com/stablehash/
    ├── StableHashUtil.java
    └── Sample.java
```

> 배포용으로 jar로 사용하고 싶다면, 아래와 같이 Maven 설정도 가능하게 만들 수 있습니다:

<details>
<summary>Maven (예정)</summary>

```xml
<dependency>
  <groupId>com.seunggulee</groupId>
  <artifactId>stable-hash</artifactId>
  <version>1.0.0</version>
</dependency>
```

</details>

---

## 🧪 테스트 실행

```bash
./mvnw test
```

---

## ⚠️ 사용 시 주의사항

- **순서가 중요한 컬렉션**(`List`, `Map`)의 경우 직렬화 순서가 해시값에 영향을 줄 수 있습니다.
- `ObjectMapper` 설정에 따라 출력 포맷이 달라질 수 있으니, 설정을 **고정**하여 사용하는 것이 안전합니다.
- **동일 객체라도 직렬화 방식에 따라 해시가 다르게 나올 수 있으니** 안정된 `ObjectMapper` 사용 권장

---

## 📌 활용 사례

- 캐시 키 생성 (입력 파라미터 → 해시 → Redis key)
- 동일 요청 여부 판별 (ex. 중복 요청 감지)
- 로그 추적용 ID 생성
- JSON 객체 변경 감지

---

## 👨‍💻 개발자

- [seunggulee1007](https://github.com/seunggulee1007)
