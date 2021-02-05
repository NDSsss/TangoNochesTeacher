import org.gradle.api.Plugin
import org.gradle.api.Project

open class BaseTangonochesAppPlugin(private val appType: TangonochesApp) : Plugin<Project> {
    override fun apply(target: Project) {
        target.configureAndroidPlugin(appType)
    }
}