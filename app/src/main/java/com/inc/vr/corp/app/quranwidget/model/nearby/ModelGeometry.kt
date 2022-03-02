package com.inc.vr.corp.app.quranwidget.model.nearby

import com.google.gson.annotations.SerializedName
import com.inc.vr.corp.app.quranwidget.model.nearby.ModelLocation

/**
 * Created by Azhar Rivaldi on 06-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class ModelGeometry {
    @SerializedName("location")
    lateinit var modelLocation: ModelLocation
}