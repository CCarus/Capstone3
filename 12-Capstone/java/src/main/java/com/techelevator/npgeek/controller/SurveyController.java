package com.techelevator.npgeek.controller;

import java.util.List;

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

import com.techelevator.npgeek.models.survey.SurveyResult;
import com.techelevator.npgeek.models.survey.SurveyResultDao;

@Controller
public class SurveyController {
	
	@Autowired
	private SurveyResultDao surveyResultDao;
	
	@RequestMapping(path="/survey", method=RequestMethod.GET) 
	public String displaySurvey(ModelMap map) {
		if (!map.containsAttribute("surveyResult")) {
			map.put("surveyResult", new SurveyResult());
		}
		
		return "survey";
		}
	
	@RequestMapping(path="/processSurvey", method=RequestMethod.POST)
	public String submitSurvey(@Valid @ModelAttribute SurveyResult surveyResult,
			BindingResult result, RedirectAttributes flash,
			@RequestParam String parkCode, @RequestParam String email,
			@RequestParam String state, @RequestParam String activityLevel) {
		
		flash.addFlashAttribute("surveyResult", surveyResult);	
		
		if(result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "surveyResult", result);
			return "redirect:/survey";
		}
		
		SurveyResult survey = new SurveyResult();
		
		survey.setParkCode(parkCode);
		survey.setEmail(email);
		survey.setState(state);
		survey.setActivityLevel(activityLevel);

		//saves new survey
		surveyResultDao.saveNewSurvey(survey);
		
		
		//redirects to list of other surveys
		return "redirect:/favoriteParks";
	}
	
	@RequestMapping("/favoriteParks")
	public String displayFavoriteParks(ModelMap map) {
		List<SurveyResult> surveys = surveyResultDao.getFavoriteSurveys();
		map.put("surveys", surveys);
		return "favoriteParks";
	}
	

}
