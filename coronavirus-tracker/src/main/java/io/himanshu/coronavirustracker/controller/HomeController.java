package io.himanshu.coronavirustracker.controller;

import java.util.List;

import io.himanshu.coronavirustracker.Services.coronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.himanshu.coronavirustracker.models.LocationStats;

@Controller
public class HomeController {
	
	@Autowired
	coronaVirusDataService coroVirusDataService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStats> allStats = coroVirusDataService.getAllStats();
		int totalReportedCases=allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases=allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalNewCases",totalNewCases);
		
		
		
		
		return "home";
	}
	
	

}
