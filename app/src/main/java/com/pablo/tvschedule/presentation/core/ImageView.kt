package com.pablo.tvschedule.presentation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.pablo.tvschedule.R

@Composable
fun ImagePainter(
    modifier: Modifier = Modifier,
    contentDescription: String?,
    image: String?
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .size(Size.ORIGINAL)
            .build(),
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}