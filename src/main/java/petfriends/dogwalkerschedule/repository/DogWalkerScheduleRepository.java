package petfriends.dogwalkerschedule.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import petfriends.dogwalkerschedule.model.DogWalkerSchedule;

public interface DogWalkerScheduleRepository extends JpaRepository<DogWalkerSchedule, Long>{

    List<DogWalkerSchedule> findAllByUserId(String userId);
    
}