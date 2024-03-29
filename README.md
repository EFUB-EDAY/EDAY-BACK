# 🌱 Ewha-Day, E-Day

> EFUB 3rd SWS(Summer Web Surf) 2팀 E-Day Project

![](https://velog.velcdn.com/images/chhaewxn/post/b7a59ccb-3ef9-474e-9cea-616ace518712/image.png)

<br>

## 🪴 프로젝트 설명

<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/db9ee448-cd51-495c-ae32-254d4f867c0f" width=300 />

<details>
<summary>스크린샷</summary>
<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/cf71d72d-c4fc-4e1f-8a02-8fdfa17f7c3e" width="300">

<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/2de6a9c2-4ca8-4513-8c72-8f299fcfc02a" width="300">
<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/a15b39b1-ddb5-47a2-8061-dd10cd51982f" width="300">

<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/a02b81f3-4442-45c5-af89-2b51731c820d" width="300" />
<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/f8fc7d2c-f908-41e8-ac94-2e6fc7e60a77" width="300" />

<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/f7ca2fd4-ec8b-4fbf-8b4d-66e383b21229" width="300" />
<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/a1d77b0e-b4cd-42c1-98b4-b29eeb5b7195" width="300" />
<img src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/ca8b677f-cfe2-44c8-9583-f769fb28b562" width="300" />
</details>

### 🕊️ 예비 이화인을 위한 퀴즈 서비스

> 예비 벗들이 개강 D-7부터 하루하루 열리는 퀴즈를 맞추며 이화여대에 대해 알아가는 퀴즈+정보 사이트입니다. 이화인으로서 알아두면 쓸모 있는, 알아두면 좋을 지식과 꿀팁들을 받아갈 수 있는 유용한 사이트가 될
> 것입니다. D-7부터 퀴즈를 하나씩 맞출 때마다 메인 화면에 있는 학교 지도에 색이 입혀지면서, 개강 날에는 색이 모두 칠해진 학교 지도를 얻을 수 있습니다.

### 📆 개발 기간

- 프로젝트 세팅: 2023.07.04. - 2023.07.09.
- API 개발: 2023.07.10. - 2023.07.24.
- 배포 및 API 연결: 2023.07.25 - 2023.08.

<br>

## 👩‍💻 팀원 소개

|                                            권민아                                            |                                최윤지                                 |                                     송채원                                     |                                이한나                                |
|:-----------------------------------------------------------------------------------------:|:------------------------------------------------------------------:|:---------------------------------------------------------------------------:|:-----------------------------------------------------------------:|
|             <img src="https://avatars.githubusercontent.com/u/71026706?v=4"/>             | <img src="https://avatars.githubusercontent.com/u/100260416?v=4"/> |      <img src="https://avatars.githubusercontent.com/u/96541582?v=4"/>      | <img src="https://avatars.githubusercontent.com/u/89291223?v=4"/> 
|                     [@mingulmangul](https://github.com/mingulmangul)                      |            [@choiyounji](https://github.com/choiyounji)            |                  [@chhaewxn](https://github.com/chhaewxn)                   |           [@hannah0226](https://github.com/hannah0226)            | 
| 프로젝트 세팅 및 엔티티 생성<br>CI/CD 환경 구축<br>카카오 OAuth 로그인 개발<br>사용자 정보 조회 API 개발<br>DB 설계 및 데이터 구축 |   카카오 OAuth 로그인 개발<br>사용자 API 개발<br>API 명세서 작성<br>유저 정보 저장 DB 설계   | 추가정보 API 개발<br>문의사항 API 개발<br>사용자 칭호 API 개발<br>데이터 삽입 SQL문 작성<br>API 명세서 작성 |         퀴즈 내용 보기 API 개발<br>퀴즈 정답 확인 API 개발<br>API 명세서 작성          

<br>

### 📚 APIs

|   View   | Method |   Detail    | Developer |
|:--------:|:------:|:-----------:|:---------:|
| 사용자 API  |  POST  |   사용자 로그인   |   윤지/민아   |
|          |  GET   |  사용자 정보 보기  |   윤지/민아   |
|  퀴즈 API  |  GET   |  퀴즈 내용 보기   |    한나     |
|          |  POST  |  퀴즈 정답 선택   |    한나     |
|  문의 API  |  POST  |  문의사항 작성하기  |    채원     |
| 추가정보 API |  GET   | 추가정보 페이지 보기 |    채원     |
|  칭호 API  |  GET   |  칭호 페이지 보기  |    채원     |

<br>

## 📌 Commit Convention

### [TAG] 메시지

|  태그 이름   |             설명              |
|:--------:|:---------------------------:|
|   feat   |          새로운 기능 추가          |
|   fix    |          버그, 오류 수정          |
|  style   | 코드 포맷팅, 오타 수정, 주석 수정 및 삭제 등 |
|   docs   |            문서 수정            |
|  chore   |      빌드 및 패키지 수정 및 삭제       |
| refactor |           코드 리팩토링           |
| setting  |            환경설정             |

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

| 통합 개발 환경         | IntelliJ                        |
|------------------|---------------------------------|
| Spring 버전        | 2.7.11                          |
| 데이터베이스           | AWS RDS(MySQL)                  |
| 배포               | AWS EC2(Ubuntu), S3, CodeDepoly |
| Project 빌드 관리 도구 | Gradle                          |
| CI/CD 툴          | Github Actions                  |
| ERD 다이어그램 툴      | ERD Cloud                       |
| Java version     | Java 11                         |

<br>

### 아키텍쳐 구조

<img width="1029" alt="image" src="https://github.com/EFUB-EDAY/EDAY-BACK/assets/71026706/357a011f-3628-453a-9f40-9c73a5545adc">

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


