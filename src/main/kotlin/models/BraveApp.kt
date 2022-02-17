package models

import java.io.File

data class BraveApp(
    val name: String,
    val file: File,
    val data: DesktopFileData,
) {
    
    val nameOrUnknown get () =
        if (!data.Name.isNullOrBlank())
            data.Name
        else
            if (name.isBlank()) "Unknown"
            else name
    
    fun toKeyValuePairs() : List<Pair<String, String>> =
        mutableListOf(
            "File" to file.name,
            "Version" to data.Version.toString(),
            "Terminal" to (if (data.Terminal == true) "True" else "False"),
            "Type" to data.Type.orEmpty(),
            "Name" to data.Name.orEmpty(),
            "Exec" to data.Exec.orEmpty(),
            "Icon" to data.Icon.orEmpty(),
            "StartupWMClass" to data.StartupWMClass.orEmpty(),
            "OnlyShowIn" to data.OnlyShowIn.orEmpty(),
        )
    
}
