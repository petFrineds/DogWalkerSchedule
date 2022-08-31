package petfriends.dogwalkerschedule.view;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petfriends.dogwalkerschedule.model.WalkingPlace;
import petfriends.dogwalkerschedule.model.ReservedYn;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DogWalkScheduleRegisterView {
    private Date reservedStartTime;
    private Date reservedEndTime;
    private WalkingPlace walkingPlace;	// 산책장소
    private double amount;
    private String dogwalkerId;			// 회원ID
    private String dogwalkerName;       // 회원명
	private ReservedYn reservedYn; // 예약여부
}
