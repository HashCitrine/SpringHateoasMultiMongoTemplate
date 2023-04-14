# Spring Hateoas & Multi Mongo Teplate

## 참고사항
### 1) 실행 시 DB 접속 정보 전달 필요 (datasource.uri)
※ Multi Mongo Connection 설정은 `/config/mongo` 참조
``` yml
<application.yml>

spring:
  data:
    mongodb:
      v1:
        uri: ${datasource.v1.uri}
      v2:
        uri: ${datasource.v2.uri}
```
DB 접속 정보는 어플리케이션 실행 시 인자로 전달
```
java -jar {jar 파일 경로} --datasource.v1.uri=mongodb://{username}:{password}@{ip}:{port}/{db} --datasource.v2.uri=mongodb://{username}:{password}@{ip}:{port}/{db}
```
IDE에서 실행 시에도 인자로 전달하면 동일하게 작동

- **IntelliJ**  
  실행 > 구성 편집 > 애플리케이션의 CLI 인수 : --datasource.v1.uri=mongodb://{username}:{password}@{ip}:{port}/{db} --datasource.v2.uri=mongodb://{username}:{password}@{ip}:{port}/{db}
- **Eclipes**    
  RUN > Run Configurations > Spring Boot App > Arguments > Program arguments : --datasource.v1.uri=mongodb://{username}:{password}@{ip}:{port}/{db} --datasource.v2.uri=mongodb://{username}:{password}@{ip}:{port}/{db}

### 2) Code Style
- [캠퍼스 핵데이 Java 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/) 코드 스타일 적용
- 설정 파일 : naver-checkstyle-rules.xml, .editorconfig  
- 에디터 코드스타일 적용 방법 : 캠퍼스 핵데이 [Appendix D: 편집기 설정](https://naver.github.io/hackday-conventions-java/#editor-config) 부분 참조
- (Extension) [CheckStyle Addon](https://checkstyle-addons.thomasjensen.com/run.html) : 설치 시 에디터 내에서 checkstyle 확인 가능(IntelliJ, Eclipes 지원)

### 3) build.gradle
※ 코드스타일 관련 Plugin 적용(.editorconfig, Checkstyle) : 설정한 코드스타일에 어긋나는 부분이 있을 경우, Build 시 Fail 처리하고 수정이 필요한 부분을 표시

``` groovy
<build.gradle>

plugins {
    ...
    id 'org.ec4j.editorconfig' version '0.0.3'  // .editorconfig 플러그인
    id 'checkstyle'                             // Checkstyle 플러그인
}

/* 캠퍼스 핵데이 Code Style 적용 */
// 인코딩 설정
compileJava.options.encoding = 'UTF-8'
compileTestJava.options.encoding = 'UTF-8'

// .editorconfig 플러그인 설정
editorconfig {
excludes = ['build']
}

check.dependsOn editorconfigCheck

// Checkstyle 플러그인 설정
checkstyle {
maxWarnings = 0 // 규칙이 어긋나는 코드가 하나라도 있을 경우 빌드 fail을 내고 싶다면 이 선언을 추가한다.
configFile = file("${rootDir}/naver-checkstyle-rules.xml")
configProperties = ["suppressionFile": "${rootDir}/naver-checkstyle-suppressions.xml"]
toolVersion = "8.24"  // checkstyle 버전 8.24 이상 선언
}
```
- (콘솔 메시지 예시) 개행 문자(LF) 문제로 build fail 된 경우
```
> Task :editorconfigCheck FAILED
src\main\java\kr\hunternote\api\controller\EquipmentController.java@1,38: Delete 1 character - violates end_of_line = lf, reported by org.ec4j.maven.linters.TextLinter


> Task :checkstyleMain
[ant:checkstyle] [WARN] C:\Users\TAEMOKKIM\IdeaProjects\hunternote-server\src\main\java\kr\hunternote\api\controller\EquipmentController.java:1: [newline-lf] Line must end with LF, not CRLF [RegexpMultiline]
```

※ Gradle 명령어
- checkstyle 검증 : `gradle checkstyleMain`
- .editorconfig 검증 : `gradle editorconfigCheck`
- .editorconfig 설정 내용 소스 적용(설정한 내용으로 소스 수정) : `gradle editorconfigFormat`
- 이전 build 결과 제거 : `gradle clean`
- 프로젝트 build : `gradle build -x test`
  → Test 포함하여 Build 시 DB Connection String 관련 IllegalArgumentException 발생하므로 Test Skip  
  (DB 접속 정보 = datasoure.uri를 어플리케이션 실행 인자로 받고 있기 때문)  

### 4) swagger 접속
``` yml
<application.yml>

springdoc:
  swagger-ui:
    path: /api/swagger
```
`/api/swagger` 주소로 접근 가능 (`/api/swagger-ui`로 Redirect)
