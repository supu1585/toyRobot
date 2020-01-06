package com.toyRobot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "robot")
public class Robot {

	public Robot() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(generator = "robot_generator")
	@SequenceGenerator(name = "robot_generator", sequenceName = "robot_sequence", initialValue = 1000)
	private Long id;

	@Column(name = "X_ORDINATE")
	private Integer xCordinate;

	@Column(name = "Y_ORDINATE")
	private Integer yCordinate;

	@Enumerated(EnumType.STRING)
	private Direction direction;

	public Robot(Integer xCordinate, Integer yCordinate, Direction direction) {
		this.xCordinate = xCordinate;
		this.yCordinate = yCordinate;
		this.direction = direction;
	}

	public boolean isOnTable() {
		return xCordinate != null && yCordinate != null && direction != null;
	}

	public String getCurrentStatus() {
		return String.join(", ", xCordinate.toString(), yCordinate.toString(), direction.toString());
	}

	public String getDirectionStatus() {
		return String.join("", "Direction set as ", direction.toString());
	}

	public Integer getxCordinate() {
		return xCordinate;
	}

	public void setxCordinate(Integer xCordinate) {
		this.xCordinate = xCordinate;
	}

	public Integer getyCordinate() {
		return yCordinate;
	}

	public void setyCordinate(Integer yCordinate) {
		this.yCordinate = yCordinate;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
