package com.turkcell.gyt.examService.api.controller;

import com.turkcell.gyt.examService.business.abstracts.ExamService;
import com.turkcell.gyt.examService.business.dtos.request.CreateExamRequest;
import com.turkcell.gyt.examService.business.dtos.request.UpdateExamRequest;
import com.turkcell.gyt.examService.business.dtos.response.CreatedExamResponse;
import com.turkcell.gyt.examService.business.dtos.response.GetAllExamResponse;
import com.turkcell.gyt.examService.business.dtos.response.GetByExamIdResponse;
import com.turkcell.gyt.examService.business.dtos.response.UpdatedExamResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/examservice/api/v1/exam")
public class ExamsController {
    private ExamService examService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedExamResponse add(@Valid @RequestBody CreateExamRequest createExamRequest){
        return this.examService.add(createExamRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public UpdatedExamResponse update(@Valid @RequestBody UpdateExamRequest updateExamRequest){
        return this.examService.update(updateExamRequest);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public Page<GetAllExamResponse> getAll(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.examService.getAll(pageable);
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByExamIdResponse getById(@PathVariable UUID id){
        return this.examService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(UUID id){
        this.examService.delete(id);
    }
}
