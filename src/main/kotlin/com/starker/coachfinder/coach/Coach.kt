package com.starker.coachfinder.coach

import com.starker.coachfinder.address.Address
import com.starker.coachfinder.address.PhoneNumber
import java.time.LocalDateTime

data class Coach(
        var id : String? = null,
        val name : String,
        val birthday : LocalDateTime? = null,
        val email : String,
        val phone : PhoneNumber,
        val instagramURL : String? = null,
        val youtubeURL : String? = null,
        val linkedInURL : String? = null,
        val site : String? = null,
        val shortDescription : String? = null,
        val description : String? = null,
        val address : Address? = null
)