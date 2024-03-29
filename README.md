## 1. 서비스 개요

firebase의 일당 50000회 읽기로는 하루사용자가 만약 100명 이상이라면 잠재적으로 장애가 생길 수 있는 상황이였다.
<br>
이를 해결하기위해 간단한 백엔드 API 서버를 구축하였다.

크롤링은 aws lambda를 사용하였다. 병렬처리를 위해 람다 8개를 사용. 하나당 평균 소요시간 3분 / 8분 <br>
매달 약 460분(1일 사용량) * 72MB(메모리) * 20일 -> 662.4GB (무료 사용량 400,000GB보다 낮다)
![image](https://github.com/bayy1216/knu_helper_BE/assets/78216059/fcea46ef-957a-4cbc-8c46-88b65042f8de)


## 2. 배포 링크
> APP repositroy : https://github.com/bayy1216/knu_helper
> <br>
> 플레이스토어 : [KNU MATE - 공지사항 한눈에 보기](https://play.google.com/store/apps/details?id=com.reditus.knu_helper)

## 3. 프로젝트 구조
```bash
├── KnumateApplication.kt
├── controller
│   ├── AuthController.kt
│   ├── NoticeController.kt
│   └── UserController.kt
├── core
│   ├── annotation
│   │   ├── TokenUserId.kt
│   │   └── TokenUserRole.kt
│   ├── config
│   │   ├── FirebaseConfig.kt
│   │   └── WebConfig.kt
│   ├── exception
│   │   ├── CommonExceptionHandler.kt
│   │   ├── ErrorResponse.kt
│   │   ├── GetCallingClass.kt
│   │   ├── JwtException.kt
│   │   └── JwtExceptionHandler.kt
│   ├── postconstructor
│   │   └── MakeUser.kt
│   └── resolver
│       └── TokenResolver.kt
├── domain
│   ├── common
│   │   └── BaseTimeEntity.kt
│   ├── notice
│   │   ├── Notice.kt
│   │   ├── NoticeRepository.kt
│   │   ├── Site.kt
│   │   └── SiteCategory.kt
│   └── user
│       ├── User.kt
│       ├── UserRepository.kt
│       ├── UserRole.kt
│       ├── UserSubscribedSite.kt
│       └── UserSubscribedSiteRepository.kt
├── dto
│   ├── auth
│   │   ├── request
│   │   │   └── UuidSignupRequest.kt
│   │   └── response
│   │       ├── AccessTokenResponse.kt
│   │       └── TokenResponse.kt
│   ├── common
│   │   └── PagingResponse.kt
│   ├── notice
│   │   ├── request
│   │   │   ├── CreateNoticeRequest.kt
│   │   │   └── UpdateNoticeRequest.kt
│   │   └── response
│   │       ├── NoticeDto.kt
│   │       └── NoticeInfoResponse.kt
│   └── user
│       ├── request
│       │   ├── DeleteUserSubscribedSiteRequest.kt
│       │   └── UserSubscribeSiteRequest.kt
│       └── response
│           └── UserSubscribedSiteResponse.kt
├── scheduler
│   └── FcmScheduler.kt
├── service
│   ├── AuthService.kt
│   ├── FcmService.kt
│   ├── NoticeService.kt
│   └── UserService.kt
└── utils
    ├── DataUtils.kt
    ├── ExceptionUtils.kt
    ├── FcmUtils.kt
    └── JwtUtils.kt
```

## 3. 개발기간
2023.11.30 ~ 2023.12.12
