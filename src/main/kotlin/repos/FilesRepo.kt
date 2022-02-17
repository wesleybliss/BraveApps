package repos

import GlobalState
import models.BraveApp
import models.DesktopFileData
import java.awt.Desktop
import java.io.File
import java.nio.charset.Charset

// @todo this is poorly organized
// - files should be in global state
// - all functions here should be pure
object FilesRepo {
    
    private fun filterBraveFiles(file: File) : Boolean =
        file.name.startsWith("brave-") &&
            file.name.endsWith(".desktop")
    
    private fun parseLine(line: String) : String {
        val parts = line.split("=").toMutableList()
        parts.removeAt(0)
        return parts.joinToString("=")
    }
    
    private fun getTrashDirectory() : File {
        val homeDir = File(System.getProperty("user.home"))
        val trashDir = homeDir.resolve(".local/share/Trash")
        val trashFilesDir = trashDir.resolve("files")
        if (!trashDir.exists())
            throw RuntimeException("Trash directory not found at $trashDir")
        if (!trashFilesDir.exists()) {
            println("Trash dir has no files subdirectory; creating... $trashFilesDir")
            try {
                trashFilesDir.mkdir()
            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException("Failed to create trash files subdirectory at $trashFilesDir")
            }
        }
        return trashFilesDir
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
        return desktopData
    }
    
    private fun getAppDetails(file: File) : BraveApp {
        val lines = file.readLines(charset = Charset.defaultCharset())
        val details = parseDesktopFile(lines)
        return BraveApp(file.name, file, details)
    }
    
    fun readFiles() {
        
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
        
        File("/tmp").walk().forEach {
            braveFiles.add(
                BraveApp(
                    name = it.name,
                    file = it,
                    data = DesktopFileData()
                )
            )
        }
    
        GlobalState.files = braveFiles.toList()
        
    }
    
    fun getFilteredFiles() =
        if (GlobalState.filterQuery.isEmpty()) GlobalState.files
        else GlobalState.files.filter {
            val name =
                if (it.data.Name.isNullOrBlank()) it.name
                else it.data.Name!!
            name.lowercase().contains(
                GlobalState.filterQuery.lowercase()
            )
        }
    
    fun deleteFile(file: File, permanent: Boolean = false) {
        
        if (permanent) {
            println("Permanently deleting ${file.name}")
            file.delete()
            return
        }
        
        try {
            
            // Try to delete the "correct" way first
            println("Attempting to trash ${file.name}")
            Desktop.getDesktop().moveToTrash(file)
            
        } catch (e: Exception) {
            
            try {
                
                val trashDir = getTrashDirectory()
                val trashFile = trashDir.resolve(file.name)
                
                println("Attempting to manually trash ${file.name} to $trashDir")
                file.copyTo(trashFile, overwrite = true)
                file.delete()
                
                println("Trashed file ${file.name} to ${trashDir.absolutePath}")
                
            } catch (e: Exception) {
    
                e.printStackTrace()
                println("Failed to trash ${file.name}")
                
                GlobalState.dialogFailedToDeleteAppOpen = true
                
            }
            
        }
        
    }
    
}
