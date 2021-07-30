package com.farahaniconsulting.flicko.ui.data

import com.farahanconsulting.flicko.R
import com.farahaniconsulting.flicko.ui.util.text.ResText
import com.farahaniconsulting.flicko.ui.util.text.Text

enum class PageErrorState(val message: Text) {
    NO_NETWORK(ResText(R.string.no_internet_error_msg)),
    SERVER_ERROR(ResText(R.string.generic_server_error)),
}