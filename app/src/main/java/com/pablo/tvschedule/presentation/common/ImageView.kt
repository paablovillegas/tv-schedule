package com.pablo.tvschedule.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.pablo.tvschedule.R

@Composable
fun ImagePainter(
    modifier: Modifier = Modifier,
    contentDescription: String?,
    image: String?
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image ?: R.drawable.ic_launcher_background)
            .size(Size.ORIGINAL)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}