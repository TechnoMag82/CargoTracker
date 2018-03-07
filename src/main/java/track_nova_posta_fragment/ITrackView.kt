package track_nova_posta_fragment

import android.content.Context

/**
 * Created by technomag on 21.02.18.
 */

interface ITrackView {

    fun setStatus(ttN: String?, from: String?, to: String?, address: String?, status: String?, cost: String?)
    fun setError(message: String)
    fun beginTrack()
    fun endTrack()
    fun getViewContext() : Context

}
