package com.example.week17.response

data class ListResponse<T>(
        var msg: String,
        var status: Int,
        var data: List<T>
)

data class SingleResponse<T>(
        var msg: String,
        var status: Int,
        var data: T
)