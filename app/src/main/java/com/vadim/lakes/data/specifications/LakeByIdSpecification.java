package com.vadim.lakes.data.specifications;

import com.vadim.lakes.domain.specifications.JsonSpecification;

public class LakeByIdSpecification implements JsonSpecification {

    private Integer mId;

    public LakeByIdSpecification(Integer id) {
        this.mId = id;
    }

    @Override
    public Integer toJsonQuery() {
        return mId;
    }
}
