package petfriends;

import lombok.extern.slf4j.Slf4j;
import petfriends.config.KafkaProcessor;
import petfriends.dogwalkerschedule.dto.Created;
import petfriends.dogwalkerschedule.dto.Refunded;
import petfriends.dogwalkerschedule.model.DogWalkerSchedule;
import petfriends.dogwalkerschedule.model.ReservedYn;
import petfriends.dogwalkerschedule.repository.DogWalkerScheduleRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    
    @Autowired
    DogWalkerScheduleRepository dogWalkerScheduleRepository;

    //예약 생성되엇을 때
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayed_(@Payload Created created)
    {
        if(created.isMe()){
            Optional<DogWalkerSchedule> dogWalkerScheduleOptional = dogWalkerScheduleRepository.findById(created.getDogwalkerScheduleId());
            log.info(">>>> Created : " + created.toJson());
            if(dogWalkerScheduleOptional.isPresent()) {
                DogWalkerSchedule dogWalkerSchedule = dogWalkerScheduleOptional.get();
                dogWalkerSchedule.setReservedYn(ReservedYn.Y);
                dogWalkerSchedule.setReservedId(created.getReservedId());
                dogWalkerScheduleRepository.save(dogWalkerSchedule);
            }else{
                new RuntimeException("해당 도그워커 스케줄 ID가 존재하지 않습니다.");
            }
        }
    }

    //예약취소 되었을 때 -> 결재로 부터 업데이트 받기

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRefunded_(@Payload Refunded refunded)
    {
        if(refunded.isMe()){
            DogWalkerSchedule dogWalkerSchedule =
                    dogWalkerScheduleRepository.findByReservedId(refunded.getReservedId());

            if( dogWalkerSchedule != null ) {
                dogWalkerSchedule.setReservedYn(null);
                dogWalkerSchedule.setReservedId(null);
                dogWalkerScheduleRepository.save(dogWalkerSchedule);
            }else{
                new RuntimeException("해당 도그워커 스케줄 ID가 존재하지 않습니다.");
            }
        }
    }
}
