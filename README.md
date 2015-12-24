<a href='https://ci.my.to:9090/job/WizTest-SpringBoard/'><img src='https://ci.my.to:9090/buildStatus/icon?job=WizTest-SpringBoard'></a>

서울시 사물인터넷 메이커 스페이스 위즈에서 제공하는 Spring 동영상 강좌를 듣고 관련 소스를 깃허브에 커밋, 개인 CI서버인 젠킨스를 통해 테스트 후, 개인 웹 아파치서버에 배포하는 테스트를 하는 지극히 아무것도 없는 레파지토리.

#### MVC 게시판
[https://java.my.to/code/list](https://java.my.to/code/list) 로 배포 적용 테스트 확인 가능
간단한 등록, 수정, 삭제 기능이 있는 게시판

#### Transaction 테스트
[https://java.my.to/code/buy_ticket](https://java.my.to/code/buy_ticket) 로 배포 적용 테스트 확인 가능
* 4장까지 구매 가능하며, 4장 이상일 경우, 데이터베이스 체크에 의해 insert 실패 되며, 결제 테이블과 발권 테이블에 데이터가 들어가지 않는 것을 테스트하는 내용