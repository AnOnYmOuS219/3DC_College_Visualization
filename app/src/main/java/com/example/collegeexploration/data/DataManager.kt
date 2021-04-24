package com.example.collegeexploration.data

import com.example.collegeexploration.R

class DataManager {

    var mediaImg: List<MediaItem> = mutableListOf(
        MediaItem(R.drawable.img_1, "Campus View", "Campus buildings view 1"),
        MediaItem(R.drawable.img_2, "College Outer View", "College front look view 1"),
        MediaItem(R.drawable.img_3, "Campus View", "Campus buildings view 2"),
        MediaItem(R.drawable.img_4, "Placed Students Group Photo", "Placement head Mr. Avaig Kamal with placed students"),
        MediaItem(R.drawable.img_5, "IT Lab", "Students programming on systems in lab"),
        MediaItem(R.drawable.img_6, "Badminton Court Balcony View", "A shot of the badminton court from the balcony"),
        )

    fun getVRImgList(): List<MediaItem> {
        return mediaImg
    }
}