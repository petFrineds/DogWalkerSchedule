package petfriends.dogwalkerschedule.view;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petfriends.dogwalkerschedule.model.WalkingPlace;
import petfriends.dogwalkerschedule.model.reservedYn;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DogWalkScheduleRegisterView {
	
    private Date reservedStartTime;
  
    private Date reservedEndTime;
   
    private WalkingPlace walkingPlace;	// 산책장소
    
    private int career;
    private int price;
    private Long userId;			// 회원ID
	private reservedYn reservedYn; // 예약여부
	private String userName;
	private Double avgScore;
}
