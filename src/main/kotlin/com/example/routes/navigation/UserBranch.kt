package com.example.routes.navigation

sealed class UserBranch(val route:String) {
    object AuthBranch: UserBranch("/auth")
    object RegisterBranch: UserBranch("/register")
    object EditProfileBranch: UserBranch("/edit_profile")
    object ChangeProfilePhotoBranch: UserBranch("/change_profile_photo")
    object AddFavoriteCoffee: UserBranch("/add_favorite_coffee")
    object RemoveFavoriteCoffee: UserBranch("/remove_favorite_coffee")
    object GetUserInfo: UserBranch("/get_user_info")
    object GetUserImage: UserBranch("/image")

}