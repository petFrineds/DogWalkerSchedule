package petfriends.dogwalkerschedule.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "dogwalkerschedule")
public class DogWalkerSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dogwalker_schedule_id")
	private Long id;

	//도그워커의 회원아이디
	@Column(name="dogwalker_id")
	private String dogwalkerId;

	//도그워커의 회원이름
	@Column(name="dogwalker_name")
	private String dogwalkerName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")	@Column(name="reserved_start_time")
	private Date reservedStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")	@Column(name = "reserved_end_time")
	private Date reservedEndTime;

	@Column(name = "walking_place")
	@Enumerated(EnumType.STRING)
	private WalkingPlace walkingPlace; // 산책장소

	@Column(name="reserved_yn")
	@Enumerated(EnumType.STRING)
	private ReservedYn reservedYn; // 예약여부

	@Column(name="reserved_id")
	private Long reservedId;

	//시간에 대한 총금액(단가 아님)
	private double amount;

	@Column(name="reg_date")
	Date regDate;

	@PostPersist
	public void onPostPersist() {
		//

	}

	public static DogWalkerSchedule of(
			String dogwalkerId,
			String dogwalkerName,
			Date reservedStartTime,
			Date reservedEndTime,
			WalkingPlace walkingPlace, // 산책장소
			Double amount, // 예약여부
			ReservedYn reservedYn,
			Date regDate

	) {
		return DogWalkerSchedule.builder()
				.dogwalkerId(dogwalkerId)
				.dogwalkerName(dogwalkerName)
				.reservedStartTime(reservedStartTime)
				.reservedEndTime(reservedEndTime)
				.walkingPlace(walkingPlace)
				.amount(amount)
				.reservedYn(reservedYn)
				.regDate(regDate)
				.build();
	}

}
