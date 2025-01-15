package com.wiseman.laredoutecodingchallenge.util.exception

sealed class BeeRaceExceptions {
    data class ApiError(val message: String) : BeeRaceExceptions()
    data class ReCaptchaError(val captchaUrl:String?):BeeRaceExceptions()
}


object ErrorMessages {
    const val NETWORK_ERROR = "Unable to connect to the server. Please check your internet connection and try again"
    const val API_ERROR = "A network error occurred."
    const val INVALID_RESPONSE = "Invalid API response."
}
