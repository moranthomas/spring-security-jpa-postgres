package com.moran.spring.securitydemo.controllers;

import com.moran.spring.securitydemo.services.JythonService;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageRestController {

	@GetMapping("/")
	public String home() {
		return ("<h1> Welcome to ImmunoBlock Home Page </h1>");
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return ("<h1> Welcome To The Command Center !!! </h1>");
	}

	@GetMapping("/patient")
	public String patient() {
		return ("<h1> Welcome Patient !!! </h1>");
	}

	@GetMapping("/researcher")
	public String researcher() {
		return ("<h1> Welcome Researcher !!! </h1>");
	}

	@Autowired
    JythonService jythonService;

    @GetMapping("/python")
    public String executePythonCode() {
        InputStream initInputStream = this.getClass().getClassLoader().getResourceAsStream("python/init.py");
        jythonService.execScriptAsInputStream(initInputStream);
        return jythonService.execMethodInPyClass("main", "Main", "launchAndDebug");
    }

}
