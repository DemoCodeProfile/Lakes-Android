package com.vadim.lakes.data.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vadim.lakes.data.JsonData;
import com.vadim.lakes.data.specifications.LakeAllSpecification;
import com.vadim.lakes.di.scopes.AppScope;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.domain.repositories.ILakesRepository;
import com.vadim.lakes.domain.specifications.BaseSpecification;
import com.vadim.lakes.domain.specifications.JsonSpecification;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

@AppScope
public class LakesRepositoryJson implements ILakesRepository {

    Gson mGson;
    JsonData mJsonData;

    @Inject
    public LakesRepositoryJson(Gson gson, JsonData jsonData) {
        this.mGson = gson;
        this.mJsonData = jsonData;
    }

    @Override
    public void add(Lake item) {
        //TODO This code block ;)
    }

    @Override
    public void update(Lake item) {
        //TODO This code block ;)
    }

    @Override
    public void remove(Lake item) {
        //TODO This code block ;)
    }

    @Override
    public List<Lake> query(BaseSpecification specification) {
        Type listType = new TypeToken<List<Lake>>() {
        }.getType();
        List<Lake> result = mGson.fromJson(mJsonData.allLakes(), listType);
        return result;
    }

    @Override
    public Lake queryOne(BaseSpecification specification) {
        if (specification != null)
            throw new NullPointerException();

        final JsonSpecification jsonSpecification = (JsonSpecification) specification;
        final List<Lake> lakes = query(null);
        final Integer id = jsonSpecification.toJsonQuery();
        for (Lake lake : lakes) {
            if (lake.getId().equals(id)) {
                return lake;
            }
        }
        return null;
    }
}
