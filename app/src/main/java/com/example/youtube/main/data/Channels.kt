package com.example.youtube.main.data

data class ChannelThumbnailSize(val url : String)

data class ChannelThumbnail(val default : ChannelThumbnailSize)

data class ChannelSnippet(val thumbnails : ChannelThumbnail, val title : String)

data class ChannelMeta(val id : String, val snippet : ChannelSnippet)

data class Channels(val items : List<ChannelMeta>)