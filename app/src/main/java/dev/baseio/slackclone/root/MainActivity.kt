package dev.baseio.slackclone.root

import android.os.*
import androidx.activity.compose.*
import androidx.appcompat.app.*
import androidx.compose.runtime.*
import androidx.core.view.*
import androidx.navigation.compose.*
import dagger.hilt.android.*
import dev.baseio.slackclone.R
import dev.baseio.slackclone.navigator.*
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.uidashboard.nav.*
import dev.baseio.slackclone.uionboarding.nav.*
import javax.inject.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	
	@Inject
	lateinit var composeNavigator: ComposeNavigator
	
	override fun onCreate(savedInstanceState: Bundle?) {
		setTheme(R.style.Theme_SlackJetpackCompose)
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window, false)
		
		setContent {
			val navController = rememberNavController()
			
			LaunchedEffect(Unit) {
				composeNavigator.handleNavigationCommands(navController)
			}
			
			NavHost(navController = navController,
			        startDestination = SlackRoute.OnBoarding.name) {
				onboardingNavigation(composeNavigator = composeNavigator)
				dashboardNavigation(composeNavigator = composeNavigator)
			}
			
			
		}
	}
}
