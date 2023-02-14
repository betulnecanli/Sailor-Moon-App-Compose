package com.betulnecanli.sailormoonapp.domain.model

import androidx.annotation.DrawableRes
import com.betulnecanli.sailormoonapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description : String
){
    object First: OnBoardingPage(
        image= R.drawable.baseline_moon,
        title = "Welcome",
        description ="Welcome to the Sailor Moon mobile app, where you can dive into the exciting world of Sailor Moon and experience the thrill of unleashing unique skills with your favorite characters."
    )
    object Second: OnBoardingPage(
        image= R.drawable.baseline_star,
        title = "Explore",
        description =" Explore their amazing abilities and become a part of their journey to save the world from evil forces."
    )
    object Third: OnBoardingPage(
        image= R.drawable.baseline_heart,
        title = "Discover",
        description =" Join us on this adventure and discover the power of the Sailor Scouts!"
    )
}
