package com.example.assignment.ui.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.assignment.ui.utils.shimmerBackground
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

enum class ImageLoadingTypeEnum { Shimmer, Loader, None }

sealed class NetworkImageStateEnum {
    data object Loading : NetworkImageStateEnum()
    data object Error : NetworkImageStateEnum()
    data class Success(val painter: Painter) : NetworkImageStateEnum()
}

@Composable
private fun CoilImageLoader(url: Any): NetworkImageStateEnum {
    val painter = rememberAsyncImagePainter(url)
    val state by painter.state.collectAsState()
    return when (state) {
        is AsyncImagePainter.State.Empty,
        is AsyncImagePainter.State.Loading -> NetworkImageStateEnum.Loading
        is AsyncImagePainter.State.Error -> NetworkImageStateEnum.Error.also { logError(url, state) }
        is AsyncImagePainter.State.Success -> NetworkImageStateEnum.Success(painter)
    }
}

private fun logError(url: Any, state: AsyncImagePainter.State) {
    if (state is AsyncImagePainter.State.Error) {
        val errorMessage = state.result.throwable.message
        println("CoilImageLoader: Error while loading  url: $url, ErrorMessage: $errorMessage")
    }
}

@Composable
fun ReusableNetworkImage(
    url: Any,
    placeholder: DrawableResource? = null,
    contentScale: ContentScale = ContentScale.Crop,
    loadingType: ImageLoadingTypeEnum = ImageLoadingTypeEnum.Shimmer,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (val imageState = CoilImageLoader(url)) {
            is NetworkImageStateEnum.Loading -> {
                when (loadingType) {
                    ImageLoadingTypeEnum.Shimmer -> Box(Modifier.fillMaxSize().shimmerBackground())
                    ImageLoadingTypeEnum.Loader -> CircularProgressIndicator(Modifier.size(50.dp))
                    ImageLoadingTypeEnum.None -> Unit
                }
            }
            is NetworkImageStateEnum.Error -> {
                if (placeholder != null) {
                    Image(
                        painter = painterResource(placeholder),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = contentScale
                    )
                } else {
                    Image(
                        imageVector = Icons.Default.BrokenImage,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                }
            }
            is NetworkImageStateEnum.Success -> {
                Image(
                    painter = imageState.painter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = contentScale
                )
            }
        }
    }
}