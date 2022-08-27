package petfriends.dogwalkerschedule.dto;

import java.sql.Timestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import petfriends.AbstractEvent;
import petfriends.dogwalkerschedule.model.WalkingPlace;

public class ScheduleRegistered extends AbstractEvent {
	private Long id;
    private Long userId;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private Timestamp reservedStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private Timestamp reservedEndTime;
    @Enumerated(EnumType.STRING)
    private WalkingPlace walkingPlace;	// 산책장소
    
    private int career;
    private int price;
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

	public Timestamp getReservedStartTime() {
		return reservedStartTime;
	}

	public void setReservedStartTime(Timestamp reservedStartTime) {
		this.reservedStartTime = reservedStartTime;
	}

	public Timestamp getReservedEndTime() {
		return reservedEndTime;
	}

	public void setReservedEndTime(Timestamp reservedEndTime) {
		this.reservedEndTime = reservedEndTime;
	}

	public WalkingPlace getWalkingPlace() {
		return walkingPlace;
	}

	public void setWalkingPlace(WalkingPlace walkingPlace) {
		this.walkingPlace = walkingPlace;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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