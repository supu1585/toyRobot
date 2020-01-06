package com.toyRobot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toyRobot.model.Robot;

@Repository
public interface RobotCommandRepository extends JpaRepository<Robot, Long>{
	Optional<Robot> findTopByOrderByIdDesc();
}
