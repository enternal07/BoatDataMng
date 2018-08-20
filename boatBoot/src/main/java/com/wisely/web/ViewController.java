package com.wisely.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.dao.PersonRepository;
import com.wisely.domain.Person;

@Controller
public class ViewController {
	
	
	 @RequestMapping("/test")
		public String test(Model model){
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
	 @RequestMapping("/index11")
		public String index(Model model){
		
			return "index";
		}
	
	

}
