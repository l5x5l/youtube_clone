package com.example.youtube.main.data

data class UserThumbnailSize(val url : String)

data class UserThumbnail(val high : UserThumbnailSize)

data class UserId(val channelId : String)

data class UserSnippet(val title : String, val resourceId : UserId, val thumbnails : UserThumbnail)

data class UserMeta(val snippet : UserSnippet)

data class Users(val items : List<UserMeta>)