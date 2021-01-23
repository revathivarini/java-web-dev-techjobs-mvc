package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchTerm", "all");
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String displaySearchResults(Model model, @RequestParam String searchType, String searchTerm){
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType", searchType);
        ArrayList<Job> jobs;
        if(searchType.equals("all") || searchType.equals("")){
            jobs = JobData.findAll();
            model.addAttribute("title","All jobs");
        }else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
    return "search";
    }
}
