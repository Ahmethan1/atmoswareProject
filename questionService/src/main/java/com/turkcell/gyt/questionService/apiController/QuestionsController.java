package com.turkcell.gyt.questionService.apiController;

import com.turkcell.gyt.questionService.business.abstracts.QuestionService;


import com.turkcell.gyt.questionService.business.dtos.question.request.CreateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.request.UpdateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.response.CreatedQuestionRespnose;
import com.turkcell.gyt.questionService.business.dtos.question.response.GetAllQuestionResponse;
import com.turkcell.gyt.questionService.business.dtos.question.response.GetByIdQuestionResponse;
import com.turkcell.gyt.questionService.business.dtos.question.response.UpdatedQuestionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/questionservice/api/v1/questions")
public class QuestionsController {
    private QuestionService questionService;
    @PostMapping("/addquestions")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedQuestionRespnose add(@Valid @RequestBody CreateQuestionRequest createQuestionRequest, HttpServletRequest request){

     return this.questionService.add(createQuestionRequest,request);

    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<GetAllQuestionResponse> getAll(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.questionService.getAll(pageable);
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdQuestionResponse getById(@PathVariable UUID id) {
        return this.questionService.getById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedQuestionResponse update(@Valid @RequestBody UpdateQuestionRequest updateQuestionRequest, HttpServletRequest request) {

        return this.questionService.update(updateQuestionRequest,request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id, HttpServletRequest request) {
        this.questionService.delete(id,request);
    }

}
