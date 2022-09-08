package petfriends.dogwalkerschedule.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import petfriends.dogwalkerschedule.model.DogWalkerSchedule;

public interface DogWalkerScheduleRepository extends JpaRepository<DogWalkerSchedule, Long>{

    List<DogWalkerSchedule> findAllByDogwalkerId(String userId, Sort sort);

    DogWalkerSchedule findByReservedId(Long reservedId);


}