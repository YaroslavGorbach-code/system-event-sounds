package koropapps.yaroslavgorbach.systemeventsounds.data

import androidx.fragment.app.Fragment
import koropapps.yaroslavgorbach.systemeventsounds.App

interface Repo {
    fun getEvents(): List<SystemEvent>
}

fun Fragment.getRepo(): Repo = (requireActivity().application as App).provideRepo()
