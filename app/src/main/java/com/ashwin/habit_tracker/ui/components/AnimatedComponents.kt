package com.ashwin.habit_tracker.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCheckInButton(
    isCompleted: Boolean,
    currentStreak: Int,
    onCheckIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    
    val rotation by animateFloatAsState(
        targetValue = if (isCompleted) 360f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    
    Box(
        modifier = modifier
            .scale(scale)
            .graphicsLayer {
                rotationZ = rotation
            }
    ) {
        CheckInButton(
            isCompleted = isCompleted,
            currentStreak = currentStreak,
            onCheckIn = {
                isPressed = true
                onCheckIn()
                // Reset pressed state after animation
                isPressed = false
            }
        )
    }
}

@Composable
fun SlideInCard(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(
                durationMillis = 200,
                easing = FastOutLinearInEasing
            )
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = 200,
                easing = FastOutLinearInEasing
            )
        )
    ) {
        content()
    }
}

@Composable
fun FadeInContent(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutLinearInEasing
            )
        )
    ) {
        content()
    }
}

@Composable
fun PulsingIcon(
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Box(
        modifier = Modifier.scale(scale)
    ) {
        content()
    }
}

@Composable
fun ExpandableCard(
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                title()
            }
            
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutLinearInEasing
                    )
                ) + fadeOut()
            ) {
                Box(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                ) {
                    content()
                }
            }
        }
    }
}

