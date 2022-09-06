package com.biometric.pdfviewer.entities

data class ChoiceModel(
    var name: String,
    var type: String,
    var title: String,
    var choices: List<String>,
    var hasNone: Boolean,
    var hasOther: Boolean,
    var isRequired: Boolean
) {
    constructor() : this(
        name = "",
        type = "",
        title = "",
        choices = listOf(),
        hasNone = true,
        hasOther = true,
        isRequired = true
    )
}
