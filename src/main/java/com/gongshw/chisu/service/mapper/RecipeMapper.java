/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gongshw.chisu.service.model.Recipe;

/**
 * @author gongshiwei
 */
public interface RecipeMapper {
    List<Recipe> getRecipeByCreatorEmail(@Param("email") String email);

    Recipe getRecipeById(@Param("id") long id);
}
