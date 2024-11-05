package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController{

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
@PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam(required = false) String searchTerm){
ArrayList<Job> jobs;
if(searchType.equals("all") && searchTerm.isEmpty()){
    jobs= JobData.findAll();
    model.addAttribute("title", "All Jobs");
} else {
    jobs = JobData.findByColumnAndValue(searchType, searchTerm);
    model.addAttribute("title", "Jobs With" + " " + searchType + ": " + searchTerm);
    model.addAttribute("searchTerm", searchTerm);
}
    model.addAttribute("jobs", jobs);
    model.addAttribute("columns", ListController.columnChoices);
    model.addAttribute("searchType", searchType);
    model.addAttribute("actions", actionChoices);
    return "search";
}
}


