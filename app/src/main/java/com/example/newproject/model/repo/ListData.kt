package com.example.newproject.model.repo

data class ListData<T>(
    val content: ArrayList<T>,
    val links: ArrayList<ListDataLinkInfo>,
    val page: ListDatePageInfo
)