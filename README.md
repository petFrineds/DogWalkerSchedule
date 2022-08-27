---------------------------------------------------
1. maria 설치 및 테이블 생성(예제에는 id/passwd : root/1234 , 변경은 application.yml에서 하면 됨. )
---------------------------------------------------
테이블스페이스 : petfriends


/*주의사항*/
테이블생성 Script: 

CREATE TABLE  dogwalkerschedule(   
    dogwalker_schedule_id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    dogwalker_id VARCHAR(50) NULL DEFAULT NULL,
    dogwalker_name VARCHAR(50) NULL DEFAULT NULL,
    reserved_start_time DATETIME NULL DEFAULT NULL,
    reserved_end_time DATETIME NULL DEFAULT NULL,
    walking_place VARCHAR(50) NULL DEFAULT NULL,
    reserved_yn VARCHAR(1) NULL DEFAULT NULL,
    career VARCHAR(500) NULL DEFAULT NULL,
    amount DOUBLE NULL DEFAULT NULL,
    reg_date DATETIME NULL DEFAULT NULL
 ) COLLATE='utf8mb4_general_ci' ENGINE=InnoDB ;  

insert샘플: 
insert into dogwalkerschedule (dogwalker_id, dogwalker_name, reserved_start_time, reserved_end_time, walking_place, reserved_yn, career, amount, reg_date) 
values ("geny_id", "geny", "2022-08-30 19:00", "2022-08-30 21:00", "서울시_관악구", null, "시츄 10년동안 길러봄", 40000, "2022-08-27 14:34:00");

---------------------------------------------------  
2. kafka설치  
---------------------------------------------------  
참고사이트 : http://www.msaschool.io/operation/implementation/implementation-seven/  

--------------------------------------------------  
3. Payment(mariadb), Shop(hsqldb) 실행 및 테스트  
--------------------------------------------------  
1) Payment에서 아래와 같이 api 통해 데이터 생성하면, mariadb[payment테이블]에 데이터 저장되고, message publish.  
    - 데이터생성(postman사용) : POST http://localhost:8082/payments/   
                              { "reservedId": "202203271311", "userId": "soya95", "amount": "10000", "payDate": "2019-03-10 10:22:33.102" }  

    - 조회 : GET http://localhost:8082/payments/1  

3) Shop에서 message 받아와 저장 ( 아래 PloycyHandler.java가 실행됨 )  

--------------------------------------------------  
4. 구조  
   -controller  
   -service  
   -repository  
   -dto  
   -model  
   -config : KafkaProcessor.java, WebConfig.java(CORS적용)  
--------------------------------------------------  
5. API  
   해당ID의 결제내역조회 : GET http://localhost:8082/payments/{userId}   
--------------------------------------------------  
