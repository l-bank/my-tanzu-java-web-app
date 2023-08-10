package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	private final VetRepository vetRepository;

	@Autowired
	public HelloController(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@RequestMapping("/")
	public String index() {
		Vet heidiStevens = vetRepository.findById(1005L).orElseThrow();
		return "Greetings from " + heidiStevens.getFirstName() + " " + heidiStevens.getLastName();
	}

}