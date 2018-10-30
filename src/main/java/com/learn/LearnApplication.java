package com.learn;

import com.learn.domain.info;
import com.learn.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@SpringBootApplication
public class LearnApplication {
	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}
}
