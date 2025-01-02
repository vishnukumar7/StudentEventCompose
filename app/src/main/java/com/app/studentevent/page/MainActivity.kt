package com.app.studentevent.page

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.studentevent.R
import com.app.studentevent.model.Constants
import com.app.studentevent.ui.theme.StudentEventTheme
import com.app.studentevent.util.Routes
import com.app.studentevent.util.ViewModelFactoryClass
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val factory = ViewModelFactoryClass(application)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentEventTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreenMain(mainViewModel, this)
                }
            }
        }
    }
}

@Composable
fun ScreenMain(
    mainViewModel: MainViewModel,
    activity: MainActivity,
    start: String = Routes.HomeView.routes
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = start) {
        composable(route = Routes.SplashView.routes) {
            SplashView(navController)
        }

        composable(route = Routes.AdvertiseView.routes) {
            AdvertiseView(navController = navController, mainViewModel = mainViewModel)
        }

        composable(Routes.LoginView.routes) {
            LoginView(navController, mainViewModel)
        }

        composable(Routes.SignUpView.routes) {
            SignUpView(navController = navController, mainViewModel = mainViewModel)
        }

        composable(route = Routes.HomeView.routes) {
            HomePageView()
        }
    }
}


@Composable
fun SplashView(navController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Routes.AdvertiseView.routes)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_colors))
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Event ",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Center)
                .size(200.dp)
        )
        Text(
            text = "events",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(26.dp)
            // Adjust padding as needed
        )
    }
}

@Composable
fun AdvertiseView(navController: NavHostController, mainViewModel: MainViewModel) {
    val advertisePos by mainViewModel.advertisePo.observeAsState(initial = 0)
    AnimatedContent(targetState = advertisePos, transitionSpec = {
        slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
    }) { targetCounter ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = Constants.arrayListAd[advertisePos].eventImage),
                contentDescription = Constants.arrayListAd[advertisePos].eventTitle,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(200.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = Constants.arrayListAd[advertisePos].eventTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = Constants.arrayListAd[advertisePos].eventSubTitle,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.divider_2)
                    )
                    Row(modifier = Modifier.padding(top = 50.dp)) {
                        if (advertisePos != 3) {
                            TextButton(onClick = {
                                navController.navigate(Routes.HomeView)
                            }) {
                                Text(text = "SKIP", color = colorResource(id = R.color.divider_2))
                            }

                            Spacer(modifier = Modifier.width(50.dp))
                        }

                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(colorResource(id = if (advertisePos == 0) R.color.button_bg else R.color.divider_2))
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(colorResource(id = if (advertisePos == 1) R.color.button_bg else R.color.divider_2))
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(colorResource(id = if (advertisePos == 2) R.color.button_bg else R.color.divider_2))
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(colorResource(id = if (advertisePos == 3) R.color.button_bg else R.color.divider_2))
                                .align(Alignment.CenterVertically)
                        )

                        if (advertisePos != 3) {
                            Spacer(modifier = Modifier.width(50.dp))
                            TextButton(onClick = {
                                mainViewModel.nextPos()
                            }) {
                                Text(text = "NEXT", color = colorResource(id = R.color.button_bg))
                            }
                        }

                    }
                    if (advertisePos == 3) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = {
                                navController.navigate(Routes.LoginView.routes)
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp), colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(
                                    id = R.color.button_bg
                                )
                            )
                        ) {
                            Text(text = "GET START", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun LoginView(navController: NavHostController, mainViewModel: MainViewModel) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var revealPassword by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = "Login Page",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "User Name",
                fontSize = 18.sp,
                color = colorResource(id = R.color.divider_2)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "user")
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Password",
                fontSize = 18.sp,
                color = colorResource(id = R.color.divider_2)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if(revealPassword){
                    VisualTransformation.None
                }else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(onClick = { revealPassword=!revealPassword }) {
                        if(revealPassword){
                            Icon(imageVector = Icons.Filled.Visibility, contentDescription = "reveal")
                        }else{
                            Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = "not")
                        }
                    }
                },
                leadingIcon ={
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "lock")
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                navController.navigate(Routes.HomeView.routes)
            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = R.color.button_bg
                ))) {
                Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = buildAnnotatedString {
                append("Not Registered Yet? ")
                withLink(link = LinkAnnotation.Clickable("Register Here", linkInteractionListener = {
                    navController.navigate(Routes.SignUpView.routes)
                })){
                    withStyle(style = SpanStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)){
                        append("Register Here")
                    }
                }
            }, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
    }
}


@Composable
fun SignUpView(navController: NavHostController, mainViewModel: MainViewModel) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = "Register Page",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "First Name",
                fontSize = 18.sp,
                color = colorResource(id = R.color.divider_2)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "firstname")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Last Name",
                fontSize = 18.sp,
                color = colorResource(id = R.color.divider_2)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "lastName")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Mobile Number",
                fontSize = 18.sp,
                color = colorResource(id = R.color.divider_2)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "firstname")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "User Name",
                fontSize = 18.sp,
                color = colorResource(id = R.color.divider_2)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "firstname")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Password",
                fontSize = 18.sp,
                color = colorResource(id = R.color.divider_2)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon ={
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "lock")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Confirm Password",
                fontSize = 18.sp,
                color = colorResource(id = R.color.divider_2)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon ={
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "lock")
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                navController.navigate(Routes.HomeView.routes)
            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = R.color.button_bg
                ))) {
                Text(text = "Register", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = buildAnnotatedString {
                append("Already Registered? ")
                withLink(link = LinkAnnotation.Clickable("Login Here", linkInteractionListener = {
                    navController.navigate(Routes.SignUpView.routes)
                })){
                    withStyle(style = SpanStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)){
                        append("Login Here")
                    }
                }
            }, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
    }
}