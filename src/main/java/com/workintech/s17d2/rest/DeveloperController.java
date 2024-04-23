package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class DeveloperController {
    public Map<Integer, Developer> developers;
    private Taxable taxable;

    @PostConstruct
    public void init(){
        developers=new HashMap<>();

        developers.put(1,new Developer(1,"Ali",50000,Experience.JUNIOR));
        developers.put(2,new Developer(2,"Doğancan",80000,Experience.SENIOR));
    }



    @Autowired
    public DeveloperController(@Qualifier("developerTax") Taxable taxable) {
        this.taxable = taxable;
    }



    @GetMapping("/developers")
    public List<Developer> getDevelopers(){
        return developers.values().stream().toList();
    }

    @GetMapping("/developers/{id}")
    public Developer getDeveloper(@PathVariable int id){
        return developers.get(id);
    }

    @PostMapping("/developers")
    public Developer save(@RequestBody Developer developer){
        developers.put(developer.getId(),developer);
        return developer;
    }

    @PutMapping("/developers/{id}")
    public Developer update(@PathVariable int id,@RequestBody Developer developer){
        developers.put(id,new Developer(developer.getId(),developer.getName(),developer.getSalary(),developer.getExperience()));
        return developer;
    }

    @DeleteMapping("/developers/{id}")
    public Developer delete(@PathVariable int id){
        Developer developer=developers.get(id);
        developers.remove(id,developer);
        return developer;
    }
}