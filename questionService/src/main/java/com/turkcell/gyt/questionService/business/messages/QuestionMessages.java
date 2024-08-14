package com.turkcell.gyt.questionService.business.messages;

public class QuestionMessages {
    public static final String QUESTION_ID_NOT_FOUND = "QUESTION_ID_NOT_FOUND";
    public static final String QUESTION_ALREADY_DELETED="QUESTION ALREADY DELETED";
    public static final String INVALID_REQUEST_ROLE = "INVALID REQUEST ROLE";
    public static final String OPTION_COUNT_EXCEEDS_LIMIT = "Option count cannot exceed 5. Please ensure the number of options is 5 or fewer.";
    public static final String OPTION_COUNT_BELOW_MINIMUM = "Option count must be at least 2. Please provide at least 2 options.";
    public static final String AT_LEAST_ONE_CORRECT_OPTION_REQUIRED = "There must be at least one correct answer.";

}
