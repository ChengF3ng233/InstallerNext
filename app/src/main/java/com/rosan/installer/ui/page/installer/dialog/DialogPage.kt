package com.rosan.installer.ui.page.installer.dialog

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rosan.installer.data.installer.repo.InstallerRepo
import com.rosan.installer.ui.widget.dialog.PositionDialog
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DialogPage(
    installer: InstallerRepo, viewModel: DialogViewModel = getViewModel {
        parametersOf(installer)
    }
) {
    LaunchedEffect(installer.id) {
        viewModel.dispatch(DialogViewAction.CollectRepo(installer))
    }
    val params = DialogGenerateParams(installer, viewModel)
    PositionDialog(
        onDismissRequest = {
            viewModel.dispatch(DialogViewAction.Background)
        },
        shape = RoundedCornerShape(12.dp),
        containerColor = Color(245, 245, 220),
        modifier = Modifier.animateContentSize(
            spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessMedium)
        ),
        centerIcon = dialogInnerWidget(installer, params.icon),
        centerTitle = dialogInnerWidget(installer, params.title),
        centerSubtitle = dialogInnerWidget(installer, params.subtitle),
        centerText = dialogInnerWidget(installer, params.text),
        centerContent = dialogInnerWidget(installer, params.content),
        centerButton = dialogInnerWidget(installer, params.buttons)
    )
}