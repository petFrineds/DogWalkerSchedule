package petfriends.dogwalkerschedule.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petfriends.dogwalkerschedule.model.DogWalkerSchedule;
import petfriends.dogwalkerschedule.repository.DogWalkerScheduleRepository;
import petfriends.dogwalkerschedule.view.DogWalkScheduleRegisterView;

@Service
public class DogWalkerScheduleService {
	 
	 @Autowired
	 DogWalkerScheduleRepository dogWalkerScheduleRepository;
	 
	 public List<DogWalkerSchedule> findAllByUserId(String userId) {
		 return dogWalkerScheduleRepository.findAllByUserId(userId);
	 } 
	 public List<DogWalkerSchedule> findAllDogWalkerSchedule(){
		 return dogWalkerScheduleRepository.findAll();
	 }
	 // 일지 작성
	 @Transactional
	 public DogWalkerSchedule registerDogWalkerSchedule(DogWalkScheduleRegisterView registerData) throws Exception {
		 DogWalkerSchedule dogWalkerScheduleRegister = DogWalkerSchedule.of(
				 registerData.getUserId(),
				 registerData.getUserName(),
				 registerData.getAvgScore(),
				 registerData.getCareer(),
				 registerData.getReservedStartTime(), 
				 registerData.getReservedEndTime(), 
				 registerData.getWalkingPlace(),
				 registerData.getPrice(),
				 registerData.getReservedYn());
	
		 
		 return dogWalkerScheduleRepository.save(dogWalkerScheduleRegister);
		 
	 }
		 
}

