package com.example.projetmpisi.runner;

import com.example.projetmpisi.ProjetMpisiApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = ProjetMpisiApplication.class)
public class CucumberTest {
}
