package com.api.sistemasactivos.costumer.service;

import com.api.sistemasactivos.costumer.interfaces.CostumerService;
import com.api.sistemasactivos.costumer.model.Costumer;
import com.api.sistemasactivos.costumer.repository.BaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class CostumerServiceImpl extends BaseServiceImpl<Costumer, Integer> implements CostumerService {
    public CostumerServiceImpl(BaseRepository<Costumer, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public Costumer update(Integer id, Costumer entity) throws Exception {
        try {
            Optional<Costumer> entityOptional = baseRepository.findById(id);
            if (entityOptional.isPresent()) {
                Integer personId = entityOptional.get().getId();
                Date createdAt = entityOptional.get().getCreatedAt();
                entityOptional = Optional.of(entity);
                entityOptional.get().setId(personId);
                entityOptional.get().setCreatedAt(createdAt);
                return baseRepository.save(entityOptional.get());
            }else{
                throw new Exception("No se encontró el registro con ese id");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
