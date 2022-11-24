package developer.behzod.mediaplayer.fragment

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import developer.behzod.mediaplayer.R
import developer.behzod.mediaplayer.`object`.MyData
import developer.behzod.mediaplayer.models.Music
import kotlinx.android.synthetic.main.fragment_media.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@Suppress("UNREACHABLE_CODE")
class MediaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var root: View
    lateinit var music: Music
    var position: Int = 0
    var mediaPlayer: MediaPlayer? = null
    lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_media, container, false)

        position = arguments?.getInt("position", 0)!!
        music = arguments?.getSerializable("music") as Music

        return root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onResume() {
        super.onResume()

        if (position != -1) {
            mediaPlayer = null
            mediaPlayer = MediaPlayer.create(context, Uri.parse(MyData.list[position].musicPath))
            mediaPlayer?.start()
            root.btn_pause.background = resources.getDrawable(R.drawable.ic_pause)
            root.seekbar.max = mediaPlayer?.duration!!
            handler = Handler(activity?.mainLooper!!)

            root.txt_all_music_size.text = MyData.list[position].author
            root.txt_music_name.text = MyData.list[position].title
            root.text_name.text = MyData.list[position].author
            root.txt_all_time_music.text = milliSecondsToTimer(mediaPlayer?.duration!!.toLong())

        }
    }

    private fun  milliSecondsToTimer(milliseconds: Long): String? {
        var finalTimerString = ""
        var secondsString = ""

        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
        if (hours > 0) {
            finalTimerString = "$hours:"
        }

        secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            "" + seconds
        }
        finalTimerString = "$finalTimerString$minutes:$secondsString"

        return finalTimerString
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MediaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}