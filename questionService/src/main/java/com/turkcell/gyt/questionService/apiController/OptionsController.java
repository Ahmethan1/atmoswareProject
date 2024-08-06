package com.turkcell.gyt.questionService.apiController;

import com.turkcell.gyt.questionService.business.abstracts.OptionService;
import com.turkcell.gyt.questionService.business.dtos.option.request.CreateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.request.UpdateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.response.CreatedOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.GetAllOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.GetByIdOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.UpdatedOptionResponse;
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
@RequestMapping("/optionservice/api/v1/options")
public class OptionsController {
    private OptionService optionService;

    @PostMapping("/addoptions")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedOptionResponse add(@Valid @RequestBody CreateOptionRequest createOptionRequest) {

        return this.optionService.add(createOptionRequest);

    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<GetAllOptionResponse> getAll(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.optionService.getAll(pageable);
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdOptionResponse getById(@PathVariable UUID id) {
        return this.optionService.getById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedOptionResponse update(@Valid @RequestBody UpdateOptionRequest updateOptionRequest) {

        return this.optionService.update(updateOptionRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void delete(@PathVariable UUID id) {
        this.optionService.delete(id);
    }

}
