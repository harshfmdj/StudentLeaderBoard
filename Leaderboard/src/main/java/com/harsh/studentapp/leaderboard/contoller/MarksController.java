package com.harsh.studentapp.leaderboard.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.studentapp.leaderboard.models.Marks;
import com.harsh.studentapp.leaderboard.service.MarksService;

@RestController
public class MarksController {
	@Autowired  
	MarksService marksService;  
	@Autowired
    private KafkaTemplate<String, Marks> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example_json";
	
	@GetMapping("/Marks")  
	private List<Marks> getAllMarks()   
	{  
	return marksService.getAllMarks();  
	}  

	@GetMapping("/Marks/{marksid}")  
	private Marks getMarks(@PathVariable("marksid") int Marksid)   
	{  
	return marksService.getMarksById(Marksid);  
	}  
	@DeleteMapping("/Marks/{marksid}")  
	private void deleteBook(@PathVariable("marksid") int marksid)   
	{  
      marksService.delete(marksid);  
      kafkaTemplate.send(TOPIC, marksService.getMarksById(marksid));
      
	}  
	@PostMapping("/marks")  
	private int saveBook(@RequestBody Marks marks)   
	{  
	marksService.saveOrUpdate(marks);  
	kafkaTemplate.send(TOPIC, marks);
	return marks.getMarksid();  
	}  
	@PutMapping("/marks")  
	private Marks update(@RequestBody Marks marks)   
	{  
	marksService.saveOrUpdate(marks);  
	kafkaTemplate.send(TOPIC, marks);
	return marks;  
	}  

	
}