package models

data class DesktopFileData(
    var Version: Double? = null,
    var Terminal: Boolean? = null,
    var Type: String? = null,
    var Name: String? = null,
    var Exec: String? = null,
    var Icon: String? = null,
    var StartupWMClass: String? = null,
    var OnlyShowIn: String? = null,
) {
    
    fun toKeyValuePairs() : List<Pair<String, String>> =
        mutableListOf<Pair<String, String>>(
            "Version" to Version.toString(),
            "Terminal" to (if (Terminal == true) "True" else "False"),
            "Type" to Type.orEmpty(),
            "Name" to Name.orEmpty(),
            "Exec" to Exec.orEmpty(),
            "Icon" to Icon.orEmpty(),
            "StartupWMClass" to StartupWMClass.orEmpty(),
            "OnlyShowIn" to OnlyShowIn.orEmpty(),
        )
    
}
