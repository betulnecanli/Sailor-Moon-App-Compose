package com.betulnecanli.sailormoonapp.navigation

sealed class Screen(val route: String){
  //  object Splash :Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home :Screen("home_screen")
    object Details : Screen("details_screen/{chId}"){
        fun passCharacterId(chId : Int) : String {
            return "details_screen/$chId"
        }
    }

    object Search : Screen("search_screen")
}