package com.turkcell.gyt.questionService.business.concretes;

import com.turkcell.gyt.questionService.business.abstracts.OptionService;
import com.turkcell.gyt.questionService.business.dtos.option.request.CreateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.request.UpdateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.response.CreatedOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.GetAllOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.GetByIdOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.UpdatedOptionResponse;
import com.turkcell.gyt.questionService.business.rules.OptionBusinessRules;
import com.turkcell.gyt.questionService.core.utility.mapper.OptionMapper;
import com.turkcell.gyt.questionService.dataAccess.OptionRepository;
import com.turkcell.gyt.questionService.entity.OptionEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OptionManager implements OptionService {
    private OptionRepository optionRepository;
    private OptionMapper optionMapper;
    private OptionBusinessRules optionBusinessRules;

    @Override
    public CreatedOptionResponse add(CreateOptionRequest createOptionRequest) {

        OptionEntity optionEntity = this.optionMapper.createOptionRequestToOptionEntity(createOptionRequest);
        OptionEntity savedOption = this.optionRepository.save(optionEntity);

        return this.optionMapper.optionEntityToCreatedOptionResponse(savedOption);
    }

    @Override
    public Page<GetAllOptionResponse> getAll(Pageable pageable) {
        Page<OptionEntity> optionEntities = this.optionRepository.findAllByOrderByIdAsc(pageable);

        return optionEntities.map(this.optionMapper::optionEntityToGetAllOptionResponse);

    }

    @Override
    public UpdatedOptionResponse update(UpdateOptionRequest updateOptionRequest) {
        this.optionBusinessRules.isCatalogAlreadyDeleted(updateOptionRequest.getId());

        OptionEntity optionEntity = this.optionMapper.updatedOptionRequestToOptionEntity(updateOptionRequest);
        optionEntity.setUpdatedDate(LocalDateTime.now());

        OptionEntity savedOption = this.optionRepository.save(optionEntity);

        return this.optionMapper.optionEntityToUpdatedOptionResponse(savedOption);
    }

    @Override
    public GetByIdOptionResponse getById(UUID id) {
         this.optionBusinessRules.isCatalogAlreadyDeleted(id);

         Optional<OptionEntity> optionEntity= this.optionRepository.findById(id);

         return this.optionMapper.optionEntityToGetByIdOptionResponse(optionEntity.get());

    }

    @Override
    public void delete(UUID id) {
        OptionEntity optionEntity = this.optionBusinessRules.isCatalogAlreadyDeleted(id);
        optionEntity.setDeletedDate(LocalDateTime.now());

        this.optionRepository.save(optionEntity);


    }
}
