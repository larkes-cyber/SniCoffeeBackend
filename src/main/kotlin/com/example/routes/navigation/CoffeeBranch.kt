package com.example.routes.navigation

sealed class CoffeeBranch(val route:String) {
    object AddCoffeeBranch: UserBranch("/add_coffee")
    object DeleteCoffeeBranch: UserBranch("/delete_coffee")
    object EditCoffeeBranch: UserBranch("/edit_coffee")
    object UploadCoffeePhotoBranch: UserBranch("/upload_coffee_photo")
    object GetCoffeeByCategoryBranch: UserBranch("/get_coffee_by_category")
    object SearchForCoffeeBranch: UserBranch("/search_for_coffee")
    object GetAllCoffee:UserBranch("/get_all_coffee")
    object GetCoffeeImage:UserBranch("/image")



}