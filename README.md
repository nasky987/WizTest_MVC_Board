<a href='https://ci.my.to:9090/job/WizTest-SpringBoard/'><img src='https://ci.my.to:9090/buildStatus/icon?job=WizTest-SpringBoard'></a>

서울시 사물인터넷 메이커 스페이스 위즈에서 제공하는 Spring 동영상 강좌를 듣고 관련 소스를 깃허브에 커밋, 개인 CI서버인 젠킨스를 통해 테스트 후, 개인 웹 아파치서버에 배포하는 테스트를 하는 지극히 아무것도 없는 레파지토리.

#### MVC 게시판
[https://java.my.to/code/list](https://java.my.to/code/list) 로 배포 적용 테스트 확인 가능
간단한 등록, 수정, 삭제 기능이 있는 게시판

#### Transaction 테스트
[https://java.my.to/code/buy_ticket](https://java.my.to/code/buy_ticket) 로 배포 적용 테스트 확인 가능
* 4장까지 구매 가능하며, 4장 이상일 경우, 데이터베이스 체크에 의해 insert 실패 되며, 결제 테이블과 발권 테이블에 데이터가 들어가지 않는 것을 테스트하는 내용

#### Spring Security 테스트
[https://java.my.to/code/login](https://java.my.to/code/login) 로 배포 적용 테스트 확인 가능  
간단한 Spring Security 설정에 대해 알아봄
/login와 /welcome으로 접속하는 url에 대해서 프록시를 이용하여 중간에서 로그인을 하도록 유도함  
커스텀 로그인 페이지를 이용하여 로그인한 사용자의 name값 즉 id를 보여줌.
* /login : user, admin 접근 가능
* /welcome : admin만 접근 가능

사용자 정보  
user : user  
admin : admin
