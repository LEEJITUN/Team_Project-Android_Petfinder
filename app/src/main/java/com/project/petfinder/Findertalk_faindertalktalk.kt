package com.project.petfinder

import android.app.TabActivity
import android.os.Bundle

@Suppress("deprecation")
class Findertalk_faindertalktalk : TabActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findertalk_findertalktalk)

        var tabHost = this.tabHost

        var tabSpecSong = tabHost.newTabSpec("SONG").setIndicator("핀더톡톡")
        tabSpecSong.setContent(R.id.findertalktalk)
        tabHost.addTab(tabSpecSong)

        var tabSpecArtist = tabHost.newTabSpec("ARTIST").setIndicator("가족 찾는 중")
        tabSpecArtist.setContent(R.id.findmypet)
        tabHost.addTab(tabSpecArtist)

        tabHost.currentTab = 0
    }
}