/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service.impl;

import static java.lang.String.format;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gongshw.chisu.service.mapper.RecipeMapper;
import com.gongshw.chisu.service.model.Recipe;

/**
 * @author gongshiwei
 */
@Service
public class RecipeServiceImpl implements com.gongshw.chisu.service.service.RecipeService {

    @Autowired
    private RecipeMapper recipeMapper;

    @Value("${chisu.email.mockDelay:true}")
    private boolean mockDelay;

    @Override
    public List<Recipe> getRecipeByCreatorEmail(@RequestParam("creatorEmail") String creatorEmail) {
        return recipeMapper.getRecipeByCreatorEmail(creatorEmail);
    }

    @Override
    public Date getPlanSendTime(Date receiveTime, Recipe recipe) {
        if (mockDelay) {
            return receiveTime;
        }
        DateTime receiveDateTime = new DateTime(receiveTime);
        long strategyId = recipe.getDelayStrategyId();
        if (strategyId == 0) {
            return receiveDateTime.plusWeeks(1).toDate();
        }
        throw new IllegalArgumentException(format("only support DelayStrategyId 0, but %d found", strategyId));
    }
}
