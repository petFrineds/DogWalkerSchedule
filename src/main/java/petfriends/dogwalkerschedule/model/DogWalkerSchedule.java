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
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import petfriends.dogwalkerschedule.dto.ScheduleRegistered;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dogwalkerschedule")
public class DogWalkerSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dogWalkerSchedule_id")
	private Long id;
	private Long userId;
	private String userName;
	private Double avgScore;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone="GMT+9")
	private Date reservedStartTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone="GMT+9")
	private Date reservedEndTime;
	@Enumerated(EnumType.STRING)
	private WalkingPlace walkingPlace; // 산책장소
	@Enumerated(EnumType.STRING)
	private reservedYn reservedYn; // 예약여부
	private int career;
	private int price;
	@PostPersist
	public void onPostPersist() {
		ScheduleRegistered scheduleRegistered = new ScheduleRegistered();
		BeanUtils.copyProperties(this, scheduleRegistered);
		scheduleRegistered.publishAfterCommit();
	}

	public static DogWalkerSchedule of(Long userId, 
			String userName, 
			Double avgScore, 
			int career, 
			Date reservedStartTime,
			Date reservedEndTime,
			WalkingPlace walkingPlace, // 산책장소
			int price, // 예약여부
			reservedYn reservedYn 

	) {
		return DogWalkerSchedule.builder()
				.userId(userId)
				.userName(userName)
				.avgScore(avgScore)
				.career(career)
				.reservedStartTime(reservedStartTime)
				.reservedEndTime(reservedEndTime)
				.walkingPlace(walkingPlace)
				.price(price)
				.reservedYn(reservedYn).build();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public Date getReservedStartTime() {
		return reservedStartTime;
	}

	public void setReservedStartTime(Date reservedStartTime) {
		this.reservedStartTime = reservedStartTime;
	}

	public Date getReservedEndTime() {
		return reservedEndTime;
	}

	public void setReservedEndTime(Date reservedEndTime) {
		this.reservedEndTime = reservedEndTime;
	}

	public WalkingPlace getWalkingPlace() {
		return walkingPlace;
	}

	public void setWalkingPlace(WalkingPlace walkingPlace) {
		this.walkingPlace = walkingPlace;
	}

	public reservedYn getReservedYn() {
		return reservedYn;
	}

	public void setReservedYn(reservedYn reservedYn) {
		this.reservedYn = reservedYn;
	}

	public int getCareer() {
		return career;
	}

	public void setCareer(int career) {
		this.career = career;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
