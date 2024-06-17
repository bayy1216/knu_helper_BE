## 1. 서비스 개요

firebase의 일당 50000회 읽기로는 하루사용자가 만약 100명 이상이라면 잠재적으로 장애가 생길 수 있는 상황이였다.
<br>
이를 해결하기위해 간단한 백엔드 API 서버를 구축하였다.

크롤링을 위해 aws lambda를 사용. 병렬처리를 위해 람다 8개를 사용하였다.(개당 평균 소요시간 3분 / 8분) <br>
매달 662.4GB 사용. 즉, 무료 사용량 400,000GB 대비 0.15% 소모

(약 460분(1일 사용량) * 72MB(메모리) * 20일)

![image](https://github.com/bayy1216/knu_helper_BE/assets/78216059/35816a8f-40f0-4c39-b6ab-e516431cb52f)



## 2. 배포 링크
> APP repositroy : https://github.com/bayy1216/knu_helper
> <br>
> 플레이스토어 : [KNU MATE - 공지사항 한눈에 보기](https://play.google.com/store/apps/details?id=com.reditus.knu_helper)

## 3. 사용기술
- `SpringBoot` `Kotlin` `JPA` 
- `querydsl` `firebase` `jwt`
- `docker-compose` `postgresql`



## 3. 개발기간
2023.11.30 ~ 2023.12.12
