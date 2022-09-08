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
    reserved_id BIGINT(20) NULL,
    reserved_yn VARCHAR(1) NULL DEFAULT NULL,
    amount DOUBLE NULL DEFAULT NULL,
    reg_date DATETIME NULL DEFAULT NULL
 ) COLLATE='utf8mb4_general_ci' ENGINE=InnoDB ;  

insert샘플: 
insert into dogwalkerschedule (dogwalker_id, dogwalker_name, reserved_start_time, reserved_end_time, walking_place, reserved_yn, amount, reg_date) 
values ("jam_id", "jam",  "2022-08-30 21:00", "2022-08-30 23:00", "서울_관악구", "N", 40000, "2022-08-27 14:34:00");


insert into dogwalkerschedule (dogwalker_id, dogwalker_name, reserved_start_time, reserved_end_time, walking_place, reserved_yn, amount, reg_date)
values ("jam_id", "jam",  "2022-08-30 21:00", "2022-08-30 24:00", "서울_강동구", "Y", 40000, "2022-08-27 14:34:00");

insert into dogwalkerschedule (dogwalker_id, dogwalker_name, reserved_start_time, reserved_end_time, walking_place, reserved_yn, amount, reg_date)
values ("jam_id", "jam", "2022-08-30 21:00","2022-08-30 20:00",  "서울_강남구", "Y", 40000, "2022-08-27 14:34:00");

---------------------------------------------------  
2. aws 배포 
---------------------------------------------------  
git clone https://github.com/petFrineds/DogwalkerSchedule.git 
//aws ecr create-repository --repository-name dogwalkerschedule-backend --image-scanning-configuration scanOnPush=true --region us-west-2
git pull
docker build -t dogwalkerschedule-backend .
docker tag dogwalkerschedule-backend:latest 811288377093.dkr.ecr.$AWS_REGION.amazonaws.com/dogwalkerschedule-backend:latest
docker push 811288377093.dkr.ecr.us-west-2.amazonaws.com/dogwalkerschedule-backend:latest

aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin 811288377093.dkr.ecr.us-west-2.amazonaws.com/

kubectl delete pods -l app=dogwalkerschedule-backend

kubectl apply -f manifests/dogwalkerschedule-deployment.yaml
kubectl get deploy
kubectl apply -f manifests/dogwalkerschedule-service.yaml
kubectl get deploy

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
