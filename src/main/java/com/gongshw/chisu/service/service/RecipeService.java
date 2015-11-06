/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.gongshw.chisu.service.model.Recipe;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * @author gongshiwei
 */
@JsonRpcService("RecipeService.json")
public interface RecipeService {
    List<Recipe> getRecipeByCreatorEmail(@RequestParam("creatorEmail") String creatorEmail);

    Date getPlanSendTime(Date receiveTime, Recipe recipe);
}
