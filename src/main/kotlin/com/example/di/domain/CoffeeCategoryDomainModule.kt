package com.example.di.domain

import com.example.domain.usecase.coffee_category.UseAddCoffeeCategory
import com.example.domain.usecase.coffee_category.UseDeleteCoffeeCategory
import com.example.domain.usecase.coffee_category.UseEditCoffeeCategory
import com.example.domain.usecase.coffee_category.UseGetCoffeeCategories
import org.koin.dsl.module

val coffeeCategoryDomainModule = module {

    factory<UseAddCoffeeCategory> {
        UseAddCoffeeCategory(get())
    }

    factory<UseDeleteCoffeeCategory> {
        UseDeleteCoffeeCategory(get())
    }

    factory<UseEditCoffeeCategory> {
        UseEditCoffeeCategory(get())
    }

    factory<UseGetCoffeeCategories> {
        UseGetCoffeeCategories(get())
    }

}