package com.vadim.lakes.domain.repositories;

import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.domain.specifications.BaseSpecification;

public interface ILakesRepository extends BaseRepository<Lake> {
    Lake queryOne(BaseSpecification specification);
}
