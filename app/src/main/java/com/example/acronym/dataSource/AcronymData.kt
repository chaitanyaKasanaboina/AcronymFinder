package com.example.acronym.dataSource

class AcronymData : ArrayList<AcronymDataItem>()

data class AcronymDataItem(
    val lfs: List<Lf>?,
    val sf: String?
)

data class Lf(
    val freq: Int?,
    val lf: String?,
    val since: Int?,
    val vars: List<Var>?
)

data class Var(
    val freq: Int?,
    val lf: String?,
    val since: Int?
)