package com.telle.budget.category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto mapToDto(Category category);

    Category mapToEntity(CategoryDto categoryDto);
}
