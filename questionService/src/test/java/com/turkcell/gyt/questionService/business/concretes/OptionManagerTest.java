package com.turkcell.gyt.questionService.business.concretes;

import com.turkcell.gyt.common.Exam.OptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.request.CreateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.request.UpdateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.response.*;
import com.turkcell.gyt.questionService.business.rules.OptionBusinessRules;
import com.turkcell.gyt.questionService.core.utility.mapper.OptionMapper;
import com.turkcell.gyt.questionService.dataAccess.OptionRepository;
import com.turkcell.gyt.questionService.entity.OptionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class OptionManagerTest {

    @InjectMocks
    private OptionManager optionManager;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private OptionMapper optionMapper;

    @Mock
    private OptionBusinessRules optionBusinessRules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {

        CreateOptionRequest createOptionRequest = new CreateOptionRequest();
        OptionEntity optionEntity = new OptionEntity();
        CreatedOptionResponse createdOptionResponse = new CreatedOptionResponse();

        when(optionMapper.createOptionRequestToOptionEntity(createOptionRequest)).thenReturn(optionEntity);
        when(optionMapper.optionEntityToCreatedOptionResponse(optionEntity)).thenReturn(createdOptionResponse);

        when(optionRepository.save(optionEntity)).thenReturn(optionEntity);

        CreatedOptionResponse result = optionManager.add(createOptionRequest);

        assertThat(result).isEqualTo(createdOptionResponse);

        verify(optionMapper).createOptionRequestToOptionEntity(createOptionRequest);
        verify(optionMapper).optionEntityToCreatedOptionResponse(optionEntity);
        verify(optionRepository).save(optionEntity);
    }
    @Test
    void testGetAll() {

        Pageable pageable = Pageable.unpaged(); // veya belirli bir Pageable
        OptionEntity optionEntity1 = new OptionEntity();
        OptionEntity optionEntity2 = new OptionEntity();
        GetAllOptionResponse response1 = new GetAllOptionResponse();
        GetAllOptionResponse response2 = new GetAllOptionResponse();

        Page<OptionEntity> optionEntities = new PageImpl<>(List.of(optionEntity1, optionEntity2), pageable, 2);
        when(optionRepository.findAllByOrderByIdAsc(pageable)).thenReturn(optionEntities);

        when(optionMapper.optionEntityToGetAllOptionResponse(optionEntity1)).thenReturn(response1);
        when(optionMapper.optionEntityToGetAllOptionResponse(optionEntity2)).thenReturn(response2);

        Page<GetAllOptionResponse> result = optionManager.getAll(pageable);

        assertThat(result.getContent()).isEqualTo(Arrays.asList(response1, response2));

        verify(optionRepository).findAllByOrderByIdAsc(pageable);
        verify(optionMapper).optionEntityToGetAllOptionResponse(optionEntity1);
        verify(optionMapper).optionEntityToGetAllOptionResponse(optionEntity2);
    }
    @Test
    void testUpdate() {

        UpdateOptionRequest updateOptionRequest = new UpdateOptionRequest();
        OptionEntity optionEntity = new OptionEntity();
        UpdatedOptionResponse updatedOptionResponse = new UpdatedOptionResponse();

        when(optionBusinessRules.isCatalogAlreadyDeleted(updateOptionRequest.getId())).thenReturn(optionEntity);

        when(optionMapper.updatedOptionRequestToOptionEntity(updateOptionRequest)).thenReturn(optionEntity);
        when(optionMapper.optionEntityToUpdatedOptionResponse(optionEntity)).thenReturn(updatedOptionResponse);

        when(optionRepository.save(optionEntity)).thenReturn(optionEntity);

        UpdatedOptionResponse result = optionManager.update(updateOptionRequest);

        assertThat(result).isEqualTo(updatedOptionResponse);

        verify(optionBusinessRules).isCatalogAlreadyDeleted(updateOptionRequest.getId());
        verify(optionMapper).updatedOptionRequestToOptionEntity(updateOptionRequest);
        verify(optionMapper).optionEntityToUpdatedOptionResponse(optionEntity);
        verify(optionRepository).save(optionEntity);
    }
    @Test
    void testGetById() {

        UUID id = UUID.randomUUID();
        OptionEntity optionEntity = new OptionEntity();
        GetByIdOptionResponse getByIdOptionResponse = new GetByIdOptionResponse();

        when(optionBusinessRules.isCatalogAlreadyDeleted(id)).thenReturn(optionEntity);

        when(optionRepository.findById(id)).thenReturn(Optional.of(optionEntity));

        when(optionMapper.optionEntityToGetByIdOptionResponse(optionEntity)).thenReturn(getByIdOptionResponse);

        GetByIdOptionResponse result = optionManager.getById(id);

        assertThat(result).isEqualTo(getByIdOptionResponse);

        verify(optionBusinessRules).isCatalogAlreadyDeleted(id);
        verify(optionRepository).findById(id);
        verify(optionMapper).optionEntityToGetByIdOptionResponse(optionEntity);
    }
    @Test
    void testDelete() {

        UUID id = UUID.randomUUID();
        OptionEntity optionEntity = new OptionEntity();

        when(optionBusinessRules.isCatalogAlreadyDeleted(id)).thenReturn(optionEntity);

        optionManager.delete(id);

        assertThat(optionEntity.getDeletedDate()).isNotNull();

        verify(optionBusinessRules).isCatalogAlreadyDeleted(id);
        verify(optionRepository).save(optionEntity);
    }

    @Test
    void testGetOptionsByQuestionId() {

        UUID questionId = UUID.randomUUID();
        OptionEntity optionEntity1 = new OptionEntity();
        OptionEntity optionEntity2 = new OptionEntity();
        OptionResponse optionResponse1 = new OptionResponse();
        OptionResponse optionResponse2 = new OptionResponse();

        when(optionRepository.findOptionsByQuestionEntity_Id(questionId)).thenReturn(List.of(optionEntity1, optionEntity2));

        when(optionMapper.optionEntityToOptionResponse(optionEntity1)).thenReturn(optionResponse1);
        when(optionMapper.optionEntityToOptionResponse(optionEntity2)).thenReturn(optionResponse2);

        List<OptionResponse> result = optionManager.getOptionsByQuestionId(questionId);

        assertThat(result).isEqualTo(Arrays.asList(optionResponse1, optionResponse2));

        verify(optionRepository).findOptionsByQuestionEntity_Id(questionId);
        verify(optionMapper).optionEntityToOptionResponse(optionEntity1);
        verify(optionMapper).optionEntityToOptionResponse(optionEntity2);
    }

    @Test
    void testGetOptionById() {

        UUID id = UUID.randomUUID();
        OptionEntity optionEntity = new OptionEntity();
        GetOptionByIdResponse getOptionByIdResponse = new GetOptionByIdResponse();

        when(optionBusinessRules.isOptionExistById(id)).thenReturn(optionEntity);

        when(optionMapper.optionEntityToGetOptionByIdResponse(optionEntity)).thenReturn(getOptionByIdResponse);

        GetOptionByIdResponse result = optionManager.getOptionById(id);

        assertThat(result).isEqualTo(getOptionByIdResponse);

        verify(optionBusinessRules).isOptionExistById(id);
        verify(optionMapper).optionEntityToGetOptionByIdResponse(optionEntity);
    }
}