package petfriends.dogwalkerschedule.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import petfriends.dogwalkerschedule.model.DogWalkerSchedule;
import petfriends.dogwalkerschedule.model.WalkingPlace;
import petfriends.dogwalkerschedule.repository.DogWalkerScheduleRepository;
import petfriends.dogwalkerschedule.service.DogWalkerScheduleService;
import petfriends.dogwalkerschedule.view.DogWalkScheduleRegisterView;

 @RestController
 public class DogWalkerScheduleController {

	 @Autowired
	 DogWalkerScheduleService dogWalkerScheduleService;

	 @Autowired
	 DogWalkerScheduleRepository dogWalkerScheduleRepository;


	 @GetMapping("/dogwalkerschedules/{dogwalkerId}")
	 public ResponseEntity<List<DogWalkerSchedule>> findAllByUserId(@PathVariable("dogwalkerId") String dogwalkerId) {
		 return ResponseEntity.ok(dogWalkerScheduleService.findAllByDogwalkerId(dogwalkerId));
	 }
	 @GetMapping("/dogwalkerschedules")
	 public ResponseEntity<List<DogWalkerSchedule>> findAllDogWalkerSchedule() {
		 return ResponseEntity.ok(dogWalkerScheduleService.findAllDogWalkerSchedule());
	 }
	 @PostMapping("/dogwalkerschedules")
	 public ResponseEntity<DogWalkerSchedule> registerDogWalkerSchedule(@RequestBody DogWalkerSchedule dogWalkerSchedule) throws Exception {
		 System.out.println(dogWalkerSchedule.toString());
		 DogWalkerSchedule registSchedule = dogWalkerScheduleService.registerDogWalkerSchedule(dogWalkerSchedule);
		 
		 return ResponseEntity.ok(registSchedule);
	 }

	 @DeleteMapping("/dogwalkerschedules/{id}")
	 public HttpStatus deleteDogwalkerSchedule(@PathVariable("id") Long id){
		 dogWalkerScheduleRepository.deleteById(id);

		 return HttpStatus.OK;
	 }



	//산책지역 json 처리 방법 확인필요!
	 @GetMapping("/dogwalkerschedules/walkingPlace")
	public List<String> findAllWalkingPlace(){


		 List<String> walkingPlaces = Stream.of(WalkingPlace.values()).map(Enum::name).collect(Collectors.toList());

		 System.out.println(walkingPlaces);

		 return walkingPlaces;
	 }
 }

 