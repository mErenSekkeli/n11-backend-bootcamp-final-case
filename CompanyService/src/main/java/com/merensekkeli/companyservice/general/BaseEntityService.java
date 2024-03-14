package com.merensekkeli.companyservice.general;

import com.merensekkeli.companyservice.exception.ItemNotFoundException;
import lombok.Getter;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public abstract class BaseEntityService<E extends BaseEntity, R extends SolrCrudRepository<E, Long>> {

    private final R repository;

    protected BaseEntityService(R repository) {
        this.repository = repository;
    }

    public E save(E entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
        }

        LocalDateTime now = LocalDateTime.now();
        if (entity.getId() == null) {
            baseAdditionalFields.setCreateDate(now);
        }

        baseAdditionalFields.setUpdateDate(now);
        entity.setBaseAdditionalFields(baseAdditionalFields);

        entity = repository.save(entity);
        return entity;
    }

    public Iterable<E> findAll() {
        return repository.findAll();
    }

    public E findByIdWithControl(Long id) {
        Optional<E> optionalE = repository.findById(id);
        if (optionalE.isPresent()) {
            return optionalE.get();
        } else {
            throw new ItemNotFoundException("Data Has Not Found! with id: " + id);
        }
    }

    public Optional<E> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
