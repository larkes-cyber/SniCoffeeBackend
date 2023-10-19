package com.example.routes.navigation

sealed class CoffeeCategoryBranch(val route:String) {
    object AddCoffeeCategory: CoffeeCategoryBranch("/add_category")
    object DeleteCoffeeCategory: CoffeeCategoryBranch("/delete_category")
    object EditCoffeeCategory: CoffeeCategoryBranch("/edit_category")
    object GetCoffeeCategories:CoffeeCategoryBranch("/get_categories")

}