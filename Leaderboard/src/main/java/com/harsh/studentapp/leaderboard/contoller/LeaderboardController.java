package com.harsh.studentapp.leaderboard.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.studentapp.leaderboard.models.Leaderboard;
import com.harsh.studentapp.leaderboard.service.LeaderboardService;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {
	
	
	Logger log= LoggerFactory.getLogger(LeaderboardController.class);
	
	
    @Autowired
    LeaderboardService leaderboardService;
    @GetMapping("/top10") 
    @Scheduled(fixedDelay = 60000)
    public List<Leaderboard> show()
    {	
    	log.info("scheduled leaderboard");
    	leaderboardService.delete();
    	leaderboardService.add();
    	return(leaderboardService.top10());	  	
    }

    
    
     
}
