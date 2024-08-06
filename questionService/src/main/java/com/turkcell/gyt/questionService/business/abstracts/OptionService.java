package com.turkcell.gyt.questionService.business.abstracts;

import com.turkcell.gyt.questionService.business.dtos.option.request.CreateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.request.UpdateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.response.CreatedOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.GetAllOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.GetByIdOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.UpdatedOptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface OptionService {
    CreatedOptionResponse add(CreateOptionRequest createOptionRequest);
    Page<GetAllOptionResponse> getAll(Pageable pageable);
    UpdatedOptionResponse update(UpdateOptionRequest updateOptionRequest);
    GetByIdOptionResponse getById(UUID id);
    void delete(UUID id);
}
