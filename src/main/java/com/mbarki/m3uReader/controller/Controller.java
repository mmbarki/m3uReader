package com.mbarki.m3uReader.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("health")
	public String health() {
		return "m3u reader is runing !!";
	}
}
