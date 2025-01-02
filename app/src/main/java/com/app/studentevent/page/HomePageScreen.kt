package com.app.studentevent.page

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.studentevent.R
import com.app.studentevent.model.Constants.currentDate
import com.app.studentevent.model.DateView
import com.app.studentevent.model.IconFromRight
import com.app.studentevent.model.RoundedIconView
import com.app.studentevent.util.HomePageRoute
import java.util.Date


@Composable
fun HomePageView() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        HomeBottomBarNavigation(navController = navController)
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomePageRoute.ExploreView.title,
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            composable(HomePageRoute.EventsView.title) {
                EventViewScreen(navController = navController)
            }

            composable(HomePageRoute.TODOView.title) {
                TODOViewScreen(navController = navController)
            }

            composable(HomePageRoute.ExploreView.title) {
                ExploreViewScreen(navController = navController)
            }

            composable(HomePageRoute.Stories.title) {
                StoriesViewScreen(navController = navController)
            }
        }
    }
}


@Composable
fun EventViewScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 10.dp, end = 10.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Filled.GridView,
                    contentDescription = "Grid",
                    tint = MaterialTheme.colorScheme.primary,

                    )
                RoundedIconView(
                    icon = Icons.Filled.Notifications,
                    contentDescription = "Notify",
                )
            }
            Text(
                text = "My Events",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 15.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.details1),
                    contentDescription = "details",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.FillWidth
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .clip(
                            RoundedCornerShape(20.dp)
                        ) // Apply clipping
                        .background(Color.White)
                ) {
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                        ) {
                            Text(
                                text = "Italian Food Festival",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Hill Road, London",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            modifier = Modifier.padding(start = 15.dp, bottom = 15.dp),
                            text = Date().currentDate(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Details,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                                Icon(
                                    imageVector = Icons.Default.BorderColor,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun TODOViewScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "TODO Screen")
    }
}

@Composable
fun ExploreViewScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.GridView,
                    contentDescription = "grid",
                    tint = MaterialTheme.colorScheme.primary
                )
                Row {
                    RoundedIconView(
                        icon = Icons.Default.MyLocation,
                        contentDescription = "notify",
                        modifier = Modifier.padding(end = 10.dp),
                        iconTint = Color.LightGray
                    )
                    RoundedIconView(
                        icon = Icons.Default.FilterAlt,
                        contentDescription = "notify",
                        modifier = Modifier.padding(end = 10.dp),
                        iconTint = Color.LightGray
                    )
                    RoundedIconView(
                        icon = Icons.Default.Notifications,
                        contentDescription = "notify",
                        iconTint = Color.LightGray
                    )
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Featured Events",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 15.dp)
                )
                TextButton(onClick = { }) {
                    Text(text = "View All")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10))
                    .background(Color.White)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.details1),
                            contentDescription = "details",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.FillWidth
                        )
                        IconFromRight()
                        DateView()
                    }
                }
            }
        }
    }
}

@Composable
fun StoriesViewScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Stories Screen")
    }
}

@Composable
fun HomeBottomBarNavigation(navController: NavController) {
    val items = listOf(
        HomePageRoute.EventsView,
        HomePageRoute.TODOView,
        HomePageRoute.ExploreView,
        HomePageRoute.Stories
    )
    val selectedColor = MaterialTheme.colorScheme.primary
    val unSelectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    BottomNavigation(backgroundColor = Color.White, contentColor = Color.Black) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { screen ->
            val isSelected = currentRoute == screen.title
            BottomNavigationItem(selected = isSelected,
                selectedContentColor = selectedColor,
                unselectedContentColor = unSelectedColor,
                label = {
                    Text(
                        text = screen.title,
                        color = if (isSelected) selectedColor else unSelectedColor
                    )
                },
                onClick = {
                    navController.navigate(screen.title) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        tint = if (isSelected) selectedColor else unSelectedColor
                    )
                })

        }

    }
}