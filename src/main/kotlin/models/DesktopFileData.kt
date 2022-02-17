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
)
