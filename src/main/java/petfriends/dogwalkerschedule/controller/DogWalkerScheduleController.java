package petfriends.dogwalkerschedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import petfriends.dogwalkerschedule.model.DogWalkerSchedule;
import petfriends.dogwalkerschedule.service.DogWalkerScheduleService;
import petfriends.dogwalkerschedule.view.DogWalkScheduleRegisterView;

 @RestController
 public class DogWalkerScheduleController {

	 @Autowired
	 DogWalkerScheduleService dogWalkerScheduleService;
	 
	 @GetMapping("/dogWalkerSchedule/{userId}")
	 public List<DogWalkerSchedule> findAllByUserId(@PathVariable("userId") String userId) {
		 return dogWalkerScheduleService.findAllByUserId(userId);
	 }
	 @GetMapping("/dogWalkerSchedule")
	 public List<DogWalkerSchedule> findAllDogWalkerSchedule() {
		 return dogWalkerScheduleService.findAllDogWalkerSchedule();
	 }
	 @PostMapping("/dogWalkerSchedule")
	 public ResponseEntity<DogWalkerSchedule> registerDogWalkerSchedule(@RequestBody DogWalkScheduleRegisterView registerData) throws Exception {
		 System.out.println(registerData);
		 DogWalkerSchedule registSchedule = dogWalkerScheduleService.registerDogWalkerSchedule(registerData);
		 
		 return ResponseEntity.ok(registSchedule);
		 
	 }
 }

 