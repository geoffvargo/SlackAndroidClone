package dev.baseio.slackclone.uidashboard.nav

import androidx.navigation.*
import androidx.navigation.compose.*
import dev.baseio.slackclone.navigator.*
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.uichannels.createsearch.*
import dev.baseio.slackclone.uichat.newchat.*
import dev.baseio.slackclone.uidashboard.compose.*
import dev.baseio.slackclone.uidashboard.home.preferences.*

fun NavGraphBuilder.dashboardNavigation(composeNavigator: ComposeNavigator) {
	navigation(startDestination = SlackScreen.Dashboard.name,
	           route = SlackRoute.Dashboard.name) {
		composable(SlackScreen.Dashboard.name) {
			DashboardUI(composeNavigator)
		}
		composable(SlackScreen.CreateChannelsScreen.name) {
			SearchCreateChannelUI(composeNavigator = composeNavigator)
		}
		composable(SlackScreen.CreateNewChannel.name) {
			CreateNewChannelUI(composeNavigator)
		}
		composable(SlackScreen.CreateNewDM.name) {
			NewChatThreadScreen(composeNavigator)
		}
		composable(SlackScreen.SlackPreferences.name) {
			PreferencesUI(composeNavigator = composeNavigator)
		}
	}
}
