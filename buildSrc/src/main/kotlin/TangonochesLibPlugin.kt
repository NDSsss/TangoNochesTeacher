import org.gradle.api.Plugin
import org.gradle.api.Project

class TangonochesLibPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.configureLibAndroidPlugin()
    }
}