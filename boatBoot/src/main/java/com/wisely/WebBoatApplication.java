package com.wisely;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisely.domain.Person;
import com.wisely.support.CustomRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class WebBoatApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(WebBoatApplication.class, args);
        
    }
    @RequestMapping("/")
	public String index(Model model){
		Person single = new Person("aa",11);
		
		List<Person> people = new ArrayList<Person>();
		Person p1 = new Person("xx",11);
		Person p2 = new Person("yy",22);
		Person p3 = new Person("zz",33);
		people.add(p1);
		people.add(p2);
		people.add(p3);
		
		model.addAttribute("singlePerson", single);
		model.addAttribute("people", people);
		
		return "index";
	}
 
}
