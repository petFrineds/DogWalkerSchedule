package petfriends.dogwalkerschedule.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import petfriends.dogwalkerschedule.model.DogWalkerSchedule;
import petfriends.dogwalkerschedule.model.ReservedYn;
import petfriends.dogwalkerschedule.repository.DogWalkerScheduleRepository;
import petfriends.dogwalkerschedule.view.DogWalkScheduleRegisterView;

@Service
public class DogWalkerScheduleService {
	 
	 @Autowired
	 DogWalkerScheduleRepository dogWalkerScheduleRepository;
	 
	 public List<DogWalkerSchedule> findAllByDogwalkerId(String dogwalkerId, Sort sort) {
		 return dogWalkerScheduleRepository.findAllByDogwalkerId(dogwalkerId, sort);
	 } 
	 public List<DogWalkerSchedule> findAllDogWalkerSchedule(Sort sort){
		 return dogWalkerScheduleRepository.findAll(sort);
	 }
	 // 일지 작성
	 @Transactional
	 public DogWalkerSchedule registerDogWalkerSchedule(DogWalkerSchedule registerData) throws Exception {
		 DogWalkerSchedule dogWalkerScheduleRegister = DogWalkerSchedule.of(
				 registerData.getDogwalkerId(),
				 registerData.getDogwalkerName(),
				 registerData.getReservedStartTime(),
				 registerData.getReservedEndTime(),
				 registerData.getWalkingPlace(),
				 registerData.getAmount(),
				 ReservedYn.N,
				 java.sql.Timestamp.valueOf(LocalDateTime.now()) // regDate
				 );
		 return dogWalkerScheduleRepository.save(dogWalkerScheduleRegister);

	 }
		 
}

