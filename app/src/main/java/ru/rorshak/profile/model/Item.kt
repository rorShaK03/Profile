package ru.rorshak.profile.model

sealed interface Item {
    data class Header(val title: String) : Item
    data class withText(val text: String) : Item
    data class Photo(val name: String) : Item
    data class Desc(val name: String) : Item
    data class Skill(val name: String, val time: String) : Item
}
