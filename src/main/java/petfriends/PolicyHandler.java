package petfriends;

import petfriends.config.KafkaProcessor;
import petfriends.dogwalkerschedule.dto.Created;
import petfriends.dogwalkerschedule.dto.ScheduleRegistered;
import petfriends.dogwalkerschedule.model.DogWalkerSchedule;
import petfriends.dogwalkerschedule.model.ReservedYn;
import petfriends.dogwalkerschedule.repository.DogWalkerScheduleRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    
    @Autowired
    DogWalkerScheduleRepository dogWalkerScheduleRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCancelled_(@Payload ScheduleRegistered orderCancelled){

    	if(orderCancelled.isMe()){
            System.out.println("##### listener  : " + orderCancelled.toJson());
            
            //List<Payment> list = paymentRepository.findByOrderId(orderCancelled.getId()); // mariadb  추가하면서 주석
            
            //for(Payment payment : list){
            	// payment.setCancelYn("Y"); // 테이블 변경하면서 주석처리 2202.06.27
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                // view 레파지 토리에 save
            //	paymentRepository.save(payment);
            //}
            
        }
    }

    //예약 생성되엇을 때
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayed_(@Payload Created created)
    {
        if(created.isMe()){
            Optional<DogWalkerSchedule> dogWalkerScheduleOptional = dogWalkerScheduleRepository.findById(created.getDogwalkerScheduleId());

            if(dogWalkerScheduleOptional.isPresent()) {
                DogWalkerSchedule dogWalkerSchedule = dogWalkerScheduleOptional.get();
                dogWalkerSchedule.setReservedYn(ReservedYn.Y);
                dogWalkerScheduleRepository.save(dogWalkerSchedule);
            }else{
                new RuntimeException("해당 도그워커 스케줄 ID가 존재하지 않습니다.");
            }
        }
    }

    //예약취소 되었을 때 -> 결재로 부터 업데이트 받기

}
