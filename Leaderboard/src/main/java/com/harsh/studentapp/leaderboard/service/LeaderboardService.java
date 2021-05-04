package com.harsh.studentapp.leaderboard.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.harsh.studentapp.leaderboard.contoller.LeaderboardController;
import com.harsh.studentapp.leaderboard.models.Leaderboard;
import com.harsh.studentapp.leaderboard.repository.LeaderboardRepository;

@Service
public class LeaderboardService {
	
	@Autowired  
	LeaderboardRepository leaderboardRepository;

	Logger log= LoggerFactory.getLogger(LeaderboardController.class);
	public void add() {
		leaderboardRepository.addRecord();
		
	}

	public void delete() {
		leaderboardRepository.deleteRecord();
		
	}
	@KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
		    containerFactory = "marksKafkaListenerFactory")
	public List<Leaderboard> top10() {	
		log.info("Leaderboardupdated");
		return(leaderboardRepository.getRecords());
		
	}
	 @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
	    public void consume(String message) {
	        log.info("Consumed message: " + message);
	        
	    }


}
