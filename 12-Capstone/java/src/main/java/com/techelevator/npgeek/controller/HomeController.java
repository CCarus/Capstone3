package com.techelevator.npgeek.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.models.park.Park;
import com.techelevator.npgeek.models.park.ParkDao;
import com.techelevator.npgeek.models.survey.SurveyResult;
import com.techelevator.npgeek.models.survey.SurveyResultDao;
import com.techelevator.npgeek.models.weather.Weather;
import com.techelevator.npgeek.models.weather.WeatherDao;

@Controller
public class HomeController {

	@Autowired
	private ParkDao parkDao;

	@RequestMapping("/")
	public String displayHomePage(ModelMap map) {
		List<Park> parks = new ArrayList<>();

		parks = parkDao.getAllParks();
		map.put("parks", parks);

		return "homepage";
	}
}
