package developer.behzod.mediaplayer.`object`

import developer.behzod.mediaplayer.models.Music

object MyObject {
    var list = ArrayList<Music>()
    lateinit var music: Music
    var position = -1
    var play = false
}