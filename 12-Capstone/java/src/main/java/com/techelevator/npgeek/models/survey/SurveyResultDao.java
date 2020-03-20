package com.techelevator.npgeek.models.survey;

import java.util.List;

public interface SurveyResultDao {
	
	List<SurveyResult> getFavoriteSurveys();
	
	SurveyResult saveNewSurvey(SurveyResult survey);
	
	SurveyResult getSurveyById(Long id);
	
}
