package com.example.newproject.model.model

data class NetworkApiResponse(
    val status: Status,
    val data: LottoOriginalData?,
    val error: String?
) {
    companion object {
        fun loading(): NetworkApiResponse = NetworkApiResponse(Status.LOADING, null, null)

        fun success(lotto: LottoOriginalData): NetworkApiResponse =
            NetworkApiResponse(Status.SUCCESS, lotto, null)

        fun error(error: String): NetworkApiResponse =
            NetworkApiResponse(Status.ERROR, null, error)
    }
}