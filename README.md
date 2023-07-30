# 🌱 Ewha-Day, E-Day
> EFUB 3rd SWS(Summer Web Surf) 2팀 E-Day Project

![](https://velog.velcdn.com/images/chhaewxn/post/b7a59ccb-3ef9-474e-9cea-616ace518712/image.png)

<br>

## 🪴 프로젝트 설명
### 🕊️ 예비 이화인을 위한 퀴즈 서비스 
> 예비 벗들이 개강 D-7부터 하루하루 열리는 퀴즈를 맞추며 이화여대에 대해 알아가는 퀴즈+정보 사이트입니다. 이화인으로서 알아두면 쓸모 있는, 알아두면 좋을 지식과 꿀팁들을 받아갈 수 있는 유용한 사이트가 될 것입니다. D-7부터 퀴즈를 하나씩 맞출 때마다 메인 화면에 있는 학교 지도에 색이 입혀지면서, 개강 날에는 색이 모두 칠해진 학교 지도를 얻을 수 있습니다.

### 📆 개발 기간
- 프로젝트 세팅: 2023.07.04. - 2023.07.09.
- API 개발: 2023.07.10. - 2023.07.24.
- 배포 및 API 연결: 2023.07.25 - 2023.08.

<br>

## 👩‍💻 팀원 소개
|권민아|최윤지|송채원|이한나|
|:-:|:-:|:-:|:-:|
|<img src="https://avatars.githubusercontent.com/u/71026706?v=4"/>|<img src="https://avatars.githubusercontent.com/u/100260416?v=4"/>|<img src="https://avatars.githubusercontent.com/u/96541582?v=4"/>|<img src="https://avatars.githubusercontent.com/u/89291223?v=4"/>
|[@mingulmangul](https://github.com/mingulmangul)|[@choiyounji](https://github.com/choiyounji)|[@chhaewxn](https://github.com/chhaewxn)|[@hannah0226](https://github.com/hannah0226)| 
|프로젝트 세팅<br>엔티티 생성<br>CI/CD 환경 구축<br>RDS 환경 구축<br>프로젝트 배포<br>S3 Bucket 생성<br>DB 설계 및 삽입 SQL문 작성|OAuth JWT API 개발<br>카카오 회원가입 및 로그인 API 개발<br>카카오 로그인 서버 연결<br>API 명세서 작성<br>유저 정보 저장 DB 설계<br>Swagger API Docs 세팅|API 명세서 작성<br>추가정보 API 개발<br>문의사항 API 개발<br>칭호 API 개발<br>데이터 삽입 SQL문 작성<br>S3 Bucket 파일 업로드|API 명세서 작성<br>퀴즈 내용 보기 API 개발<br>퀴즈 정답 확인 API 개발<br>Swagger API Docs 세팅

<br>

### 📚 APIs
| View            | Method | Detail | Developer |
|:-----------------:|:--------:|:--------:|:-----------:|
| 사용자 API |  POST  | 사용자 로그인 |   윤지   |
|  |  GET  | 사용자 정보 가져오기 |   윤지   |
| 퀴즈 API |  GET  | 퀴즈 내용 보기 |   한나   |
|  |  POST  | 퀴즈 정답 선택 |   한나   |
| 문의 API |  POST  | 문의사항 작성하기 |   채원   |
| 추가정보 API |  GET  | 추가정보 페이지 보기 |   채원   |
| 칭호 API |  GET  | 칭호 페이지 보기 |   채원   |

<br>

## 📌 Commit Convention

### [TAG] 메시지

| 태그 이름  |                               설명                                |
| :--------: | :---------------------------------------------------------------: |
|   feat    |                         새로운 기능 추가                  |
|   fix     |                          버그, 오류 수정                  |
|   style   |      코드 포맷팅, 오타 수정, 주석 수정 및 삭제 등          |
|   docs    |                  문서 수정                                |
|  chore    |              빌드 및 패키지 수정 및 삭제                   |
|  refactor |       코드 리팩토링              |
|  setting  |       환경설정                   |

### 🪵 Branch Strategy

1. issue 생성
2. local - feature/~ 에서 각자 기능 작업
3. remote - feature/~ 에 Push
4. remote - develop 으로 Pull Request 
5. 코드 리뷰 후 remote - develop Merge
6. remote - develop 에 Merge 될 때 local - develop pull 받아 최신 상태 유지

<br>


## ⚙️ 기술 아키텍쳐 
### 사용 스택
| 통합 개발 환경 | IntelliJ |
| --- | --- |
| Spring 버전 | 2.7.11 |
| 데이터베이스 | AWS RDS(MySQL) |
| 배포 | AWS EC2(Ubuntu), S3, CodeDepoly |
| Project 빌드 관리 도구 | Gradle |
| CI/CD 툴 | Github Actions |
| ERD 다이어그램 툴 | MySQLWorkbench |
| Java version | Java 11  |

<br>

### 아키텍쳐 구조
![](https://velog.velcdn.com/images/chhaewxn/post/b8fcf36c-6f4c-44b8-8a15-67d880553b15/image.png)

<br>

## ☁️ ERD 
![](https://velog.velcdn.com/images/chhaewxn/post/6976bd24-ca03-405f-9083-02c320313816/image.png)

<br>

## 📁 프로젝트 폴더 구조
```
📂 src/main/java/efub/eday
    └── edayback
        ├── domain
        │   ├── day
        │   │    ├── dday
        |   |    |   ├── entity
        │   │    │   └── repository 
        │   │    ├── info
        |   |    |   ├── controller
        │   │    │   ├── dto
        │   │    │   ├── entity
        │   │    │   ├── repository
        │   │    │   └── service 
        │   │    ├── quiz
        |   |    |   ├── controller
        │   │    │   ├── dto
        │   │    │   ├── entity
        │   │    │   ├── repository
        │   │    │   └── service 
        │   │    └── title
        |   |         ├── controller
        │   │         ├── dto
        │   │         ├── entity
        │   │         ├── repository
        │   │         └── service 
        │   ├── global
        │   │    └── exception
        │   ├── member
        |   |    ├── auth
        │   │    ├── controller
        │   │    ├── dto
        │   │    ├── entity
        │   │    ├── repository
        │   │    └── service 
        │   └── query
        |        ├── controller
        │        ├── dto
        │        ├── entity
        │        ├── repository
        │        └── service 
        └── global
               ├── config
               ├── feign
               └── jwt
```


