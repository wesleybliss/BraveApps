package repos

import models.BraveApp
import models.DesktopFileData
import java.io.File
import java.nio.charset.Charset

class FilesRepo {
    
    private fun filterBraveFiles(file: File) : Boolean =
        file.name.startsWith("brave-") &&
            file.name.endsWith(".desktop")
    
    private fun parseLine(line: String) : String {
        println("@debug parsing line: $line")
        val parts = line.split("=").toMutableList()
        parts.removeAt(0)
        println("@debug line parts " + parts.joinToString("="))
        return parts.joinToString("=")
    }
    
    private fun parseDesktopFile(lines: List<String>) : DesktopFileData {
        val desktopData = DesktopFileData()
        /*
        Version=1.0
        Terminal=false
        Type=Application
        Name=Google Drive
        Exec=/opt/brave.com/brave/brave-browser --profile-directory=Default --app-id=aghbiahbpaijignceidepookljebhfak
        Icon=brave-aghbiahbpaijignceidepookljebhfak-Default
        StartupWMClass=crx_aghbiahbpaijignceidepookljebhfak
        OnlyShowIn=Old
        */
        println("@debug parsing ${lines.size} lines")
        lines.forEach { 
            if (it.startsWith("Version"))
                desktopData.Version = parseLine(it).toDoubleOrNull()
            else if (it.startsWith("Terminal"))
                desktopData.Terminal = parseLine(it) != "false"
            else if (it.startsWith("Type"))
                desktopData.Type = parseLine(it)
            else if (it.startsWith("Name"))
                desktopData.Name = parseLine(it)
            else if (it.startsWith("Exec"))
                desktopData.Exec = parseLine(it)
            else if (it.startsWith("Icon"))
                desktopData.Icon = parseLine(it)
            else if (it.startsWith("StartupWMClass"))
                desktopData.StartupWMClass = parseLine(it)
            else if (it.startsWith("OnlyShowIn"))
                desktopData.OnlyShowIn = parseLine(it)
        }
        println("@debug desktopData $desktopData")
        return desktopData
    }
    
    private fun getAppDetails(file: File) : BraveApp {
        val lines = file.readLines(charset = Charset.defaultCharset())
        val details = parseDesktopFile(lines)
        return BraveApp(file.name, file, details)
    }
    
    fun getFiles() : List<BraveApp> {
        
        val homeDir = File(System.getProperty("user.home"))
        val gnomeAppsDir = homeDir.resolve(".gnome/apps")
        val localShareApps = homeDir.resolve(".local/share/applications/")
        val braveFiles = mutableListOf<BraveApp>()
        
        gnomeAppsDir
            .walk()
            .filter(::filterBraveFiles)
            .forEach { braveFiles.add(getAppDetails(it)) }
        
        localShareApps
            .walk()
            .filter(::filterBraveFiles)
            .forEach { braveFiles.add(getAppDetails(it)) }
        
        return braveFiles.toList()
        
    }
    
}
