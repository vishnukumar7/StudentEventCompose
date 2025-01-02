package com.app.studentevent.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.EventNote
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val routes : String){
    data object SplashView : Routes("Splash")
    data object HomeView : Routes("Home")
    data object AdvertiseView : Routes("Advertise")
    data object LoginView : Routes("Login")
    data object SignUpView : Routes("SignUp")
}

sealed class HomePageRoute(val title: String,val icon: ImageVector){
    data object EventsView : HomePageRoute("Events", Icons.Outlined.EventAvailable)
    data object TODOView : HomePageRoute("ToDo",Icons.AutoMirrored.Outlined.EventNote)
    data object ExploreView : HomePageRoute("Explore",Icons.Outlined.Explore)
    data object Stories : HomePageRoute("Stories",Icons.Outlined.PhotoCamera)
}

