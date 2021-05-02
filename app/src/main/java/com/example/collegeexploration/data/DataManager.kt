package com.example.collegeexploration.data

import com.example.collegeexploration.R

class DataManager {

    // list of images
    var mediaImg: List<MediaItem> = mutableListOf(
        MediaItem(R.drawable.img_1, "Campus View", "Campus buildings view 1", true),
        MediaItem(R.drawable.img_2, "College Outer View", "College front look view 1", true),
        MediaItem(R.drawable.img_3, "Campus View", "Campus buildings view 2", true),
        MediaItem(R.drawable.img_4, "Placed Students Group Photo", "Placement head Mr. Avaig Kamal with placed students", true),
        MediaItem(R.drawable.img_5, "IT Lab", "Students programming on systems in lab", true),
        MediaItem(R.drawable.img_6, "Badminton Court Balcony View", "A shot of the badminton court from the balcony", true),
    )

    // list of 360 videos
    var mediaVid: List<MediaItem> = mutableListOf(
        MediaItem(R.raw.sample_vid, "Campus View", "Campus buildings view 1", false),
        MediaItem(R.raw.vid_1, "Campus View 2", "Campus buildings view 2", false)
    )

    fun getVRImgList(): List<MediaItem> {
        return mediaImg
    }

    fun getVRVidList(): List<MediaItem>{
        return mediaVid
    }
}