package com.example.musify.ui.navigation

import com.example.musify.domain.SearchResult
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class MusifyNavigationDestinations(val route: String) {
    object SearchScreen :
        MusifyNavigationDestinations("MusifyNavigationDestinations.SearchScreen")

    object ArtistDetailScreen :
        MusifyNavigationDestinations("MusifyNavigationDestinations.ArtistDetailScreen/{artistId}/{artistName}?encodedUrlString={encodedImageUrlString}") {
        const val NAV_ARG_ARTIST_ID = "artistId"
        const val NAV_ARG_ARTIST_NAME = "artistName"
        const val NAV_ARG_ENCODED_IMAGE_URL_STRING = "encodedImageUrlString"

        fun buildRoute(artistSearchResult: SearchResult.ArtistSearchResult): String {
            val routeWithoutUrl =
                "MusifyNavigationDestinations.ArtistDetailScreen/${artistSearchResult.id}/${artistSearchResult.name}"
            if (artistSearchResult.imageUrlString == null) return routeWithoutUrl
            val encodedImageUrl = URLEncoder.encode(
                artistSearchResult.imageUrlString,
                StandardCharsets.UTF_8.toString()
            )
            return "$routeWithoutUrl?encodedUrlString=$encodedImageUrl"
        }

    }
}