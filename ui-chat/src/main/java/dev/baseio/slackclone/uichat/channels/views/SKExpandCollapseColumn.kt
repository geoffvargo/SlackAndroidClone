package dev.baseio.slackclone.uichat.channels.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.commonui.reusable.SlackListItem
import dev.baseio.slackclone.uichat.channels.data.ExpandCollapseModel
import dev.baseio.slackclone.uichat.models.ChatPresentation

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SKExpandCollapseColumn(
  expandCollapseModel: ExpandCollapseModel,
  onItemClick: (ChatPresentation.SlackChannel) -> Unit = {},
  onExpandCollapse: (isChecked: Boolean) -> Unit,
  channels: List<ChatPresentation.SlackChannel>,
  onClickAdd: () -> Unit
) {
  Column(
    Modifier
      .fillMaxWidth()
      .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
  ) {
    Row(
      Modifier
        .fillMaxWidth()
        .clickable {
          onExpandCollapse(!expandCollapseModel.isOpen)
        },
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = expandCollapseModel.title,
        style = SlackCloneTypography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
        modifier = Modifier.weight(1f)
      )
      AddButton(expandCollapseModel, onClickAdd)
      ToggleButton(expandCollapseModel, onExpandCollapse)
    }
    ChannelsList(expandCollapseModel, onItemClick, channels)
    Divider(color = SlackCloneColorProvider.colors.lineColor, thickness = 0.5.dp)
  }
}

@Composable
private fun ColumnScope.ChannelsList(
  expandCollapseModel: ExpandCollapseModel,
  onItemClick: (ChatPresentation.SlackChannel) -> Unit = {},
  channels: List<ChatPresentation.SlackChannel>
) {
  AnimatedVisibility(visible = expandCollapseModel.isOpen) {
    Column {
      repeat(channels.size) {
        val slackChannel = channels[it]
        SlackListItem(
          icon = if (slackChannel.isPrivate) Icons.Default.Lock else Icons.Default.MailOutline,
          title = "${slackChannel.name}",
          onItemClick = {
            onItemClick(slackChannel)
          }
        )
      }
    }
  }
}

@Composable
private fun AddButton(
  expandCollapseModel: ExpandCollapseModel,
  onClickAdd: () -> Unit
) {
  if (expandCollapseModel.needsPlusButton) {
    IconButton(onClick = onClickAdd) {
      Icon(
        imageVector = Icons.Default.Add,
        contentDescription = null,
        tint = SlackCloneColorProvider.colors.lineColor
      )
    }
  }

}

@Composable
private fun ToggleButton(
  expandCollapseModel: ExpandCollapseModel,
  onExpandCollapse: (isChecked: Boolean) -> Unit
) {
  IconToggleButton(checked = expandCollapseModel.isOpen, onCheckedChange = {
    onExpandCollapse(it)
  }) {
    Icon(
      imageVector = if (expandCollapseModel.isOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
      contentDescription = null,
      tint = SlackCloneColorProvider.colors.lineColor
    )
  }
}