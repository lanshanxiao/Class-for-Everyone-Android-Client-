package com.wanli.swing.entities;

import java.util.Map;

public class Question {

	private String questionTil;
	private Map<String, String> answer;
	
	public String getQuestionTil() {
		return questionTil;
	}
	public void setQuestionTil(String questionTil) {
		this.questionTil = questionTil;
	}
	public Map<String, String> getAnswer() {
		return answer;
	}
	public void setAnswer(Map<String, String> answer) {
		this.answer = answer;
	}
	
}
