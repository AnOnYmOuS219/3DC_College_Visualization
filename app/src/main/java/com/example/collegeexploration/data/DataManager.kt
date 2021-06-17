package com.example.collegeexploration.data

import com.example.collegeexploration.R

class DataManager {

    // list of images
    var mediaImg: List<MediaItem> = mutableListOf(
        MediaItem(R.drawable.img_20, "Administration Block", "Front view of admin block of NIEC", true),
        MediaItem(R.drawable.img_17, "Elevated view of Campus", "Bird view captured spanning all blocks of college", true),
        MediaItem(R.drawable.img_7, "Auditorium", "A carrier oriented session in NIEC spacious main hall.", true),
        MediaItem(R.drawable.img_9, "Scenic beauty of NIEC", "A captivating click encompassing the beauty of the college.", true),
        MediaItem(R.drawable.img_4, "Placed Students Group", "Placement head Mr. Avaig Kamal with placed students", true),
        MediaItem(R.drawable.img_21, "Campus Block-5", "Electronics and Electrical Department", true),
        MediaItem(R.drawable.img_8, "Canteen", "Students chit-chat outside college canteen", true),
        MediaItem(R.drawable.img_16, "Playground", "Students in the field.", true),
        MediaItem(R.drawable.img_14, "Badminton court", "Badminton court between Admin block and block-2", true),
        MediaItem(R.drawable.img_12, "Students in Auditorium", "Students in Auditorium", true),
        MediaItem(R.drawable.img_2, "External look", "Campus looks from outside", true),
        MediaItem(R.drawable.img_3, "Parking zone", "Parking zone at the college entrance.", true),
        MediaItem(R.drawable.img_6, "Badminton Court Balcony View", "A shot of the badminton court from the balcony", true),
        )

    // list of 360 videos
    var mediaVid: List<MediaItem> = mutableListOf(
        MediaItem(R.raw.sample_st_teresa_college, "Sample St. Teresa College", "360 view of St. Teresa college.", false),
        MediaItem(R.raw.sample_vancouver_college, "Sample Tour Vancover College", "Holistic view the campus locations.", false)
    )

    fun getVRImgList(): List<MediaItem> {
        return mediaImg
    }

    fun getVRVidList(): List<MediaItem>{
        return mediaVid
    }
}