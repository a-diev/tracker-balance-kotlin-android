package com.adiev.posananta.core.persentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adiev.posananta.balance.persentation.BalanceScreenCore
import com.adiev.posananta.core.persentation.ui.theme.TrackerBalanceTheme
import com.adiev.posananta.core.persentation.utils.Background
import com.adiev.posananta.core.persentation.utils.Screen
import com.adiev.posananta.spending_details.persentation.SpendingDetailScreenCore
import com.adiev.posananta.spending_overview.persentation.SpendingOverviewScreenCore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrackerBalanceTheme {
                Navigation(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()

    Background()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.SpendingOverview,
    ) {
        composable<Screen.SpendingOverview> {
            SpendingOverviewScreenCore(
                onBalanceClick = {
                    navController.navigate(Screen.Balance)
                },
                onAddSpendingClick = {
                    navController.navigate(Screen.SpendingDetails(-1))
                },
            )
        }

        composable<Screen.SpendingDetails> {
            SpendingDetailScreenCore(
                onSaveSpending = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screen.Balance> {
            BalanceScreenCore(
                onSaveClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrackerBalanceTheme {
        Navigation(modifier = Modifier.fillMaxSize())
    }
}