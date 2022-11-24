package developer.behzod.mediaplayer.models

import java.io.Serializable

data class Music(val id:Long, val title:String, val imagePath:String, val musicPath:String, val author:String):
    Serializable