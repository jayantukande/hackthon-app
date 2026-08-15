package com.signbridgecommunication.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.signbridgecommunication.app.ui.screens.HomeScreen
import com.signbridgecommunication.app.ui.screens.OnboardingScreen
import com.signbridgecommunication.app.ui.screens.SplashScreen
import com.signbridgecommunication.app.viewmodel.MainViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen {
                if (isOnboardingCompleted) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                } else {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            }
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen {
                viewModel.completeOnboarding()
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            }
        }

        composable(Screen.Home.route) {
            HomeScreen(viewModel = viewModel)
        }

        // Add more destinations as needed
    }
}
