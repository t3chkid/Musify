package com.example.musify.data.dto

import com.example.musify.data.utils.getImageDtoForImageSize
import com.example.musify.domain.MusicSummary
import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

/**
 * A DTO that contains information related to a specific Playlist.
 */
data class PlaylistDTO(
    val id: String,
    val name: String,
    val images: List<ImageDTO>,
    @JsonProperty("owner") val ownerName: OwnerNameWrapper,
    @JsonProperty("followers") val numberOfFollowers: NumberOfFollowersWrapper,
    val tracks: Tracks
) {
    /**
     * A class that wraps a string that contains the name of the owner
     * associated with a playlist.
     */
    data class OwnerNameWrapper(@JsonProperty("display_name") val value: String)

    /**
     * A class that wraps a string that contains the number of followers
     * of a particular playlist.
     */
    data class NumberOfFollowersWrapper(@JsonProperty("total") val value: String)

    /**
     * A class that contains a list of [items] of type [TrackDTOWithAlbumMetadataWrapper].
     */
    data class Tracks(val items: List<TrackDTOWithAlbumMetadataWrapper>)

    /**
     * A wrapper class that wraps an instance of [TrackDTOWithAlbumMetadata]
     */
    data class TrackDTOWithAlbumMetadataWrapper(@JsonProperty("track") val track: TrackDTOWithAlbumMetadata)
}

/**
 * A mapper function used to map an instance of [PlaylistDTO] to
 * an instance of [MusicSummary.PlaylistSummary].
 *
 * Note:[getImageDtoForImageSize] cannot be used because playlists usually
 * contain only a single image. Therefore, the url of the first image
 * is mapped to [MusicSummary.PlaylistSummary.associatedImageUrl].
 */
fun PlaylistDTO.toPlayListSummary() = MusicSummary.PlaylistSummary(
    id = id,
    name = name,
    associatedImageUrl = URL(images.first().url)
)
