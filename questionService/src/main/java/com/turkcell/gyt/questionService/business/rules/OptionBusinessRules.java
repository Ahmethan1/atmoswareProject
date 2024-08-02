package com.turkcell.gyt.questionService.business.rules;

import com.turkcell.gyt.questionService.business.messages.OptionMessages;
import com.turkcell.gyt.questionService.core.business.abstracts.MessageService;
import com.turkcell.gyt.questionService.core.utility.exceptions.types.BusinessException;
import com.turkcell.gyt.questionService.dataAccess.OptionRepository;
import com.turkcell.gyt.questionService.entity.OptionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OptionBusinessRules {
    private OptionRepository optionRepository;
    private MessageService messageService;

    public OptionEntity isOptionExistById(UUID id) {
        Optional<OptionEntity> optionEntity = this.optionRepository.findById(id);

        if (optionEntity.isEmpty()) {
            throw new BusinessException(messageService.getMessage(OptionMessages.OPTION_ID_NOT_FOUND));
        }
        return optionEntity.get();
    }

    public OptionEntity isCatalogAlreadyDeleted(UUID id){

        OptionEntity optionEntity = this.isOptionExistById(id);

        if(optionEntity.getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(OptionMessages.OPTION_ALREADY_DELETED));
        }

        return optionEntity;
    }

}
