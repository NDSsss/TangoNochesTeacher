import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

fun Project.configureAndroidPlugin(appType: TangonochesApp) {
    this.extensions.getByType<BaseAppModuleExtension>().run {
        compileSdkVersion(TangonochesConfigs.CompileSdkVersion)
        buildToolsVersion(TangonochesConfigs.BuildToolsVersion)

        defaultConfig {
            minSdkVersion(Dependencies.minSdkVersion)
            targetSdkVersion(Dependencies.targetSdkVersion)
            applicationId = appType.getFullAppId()
            versionCode = appType.versionCode
            versionName = appType.versionName
            testInstrumentationRunner = Dependencies.ANDROID_TEST_INSTRUMENTATION_RUNNER
            vectorDrawables.useSupportLibrary = true
            multiDexEnabled = true
        }
        compileOptions.setSourceCompatibility(JavaVersion.VERSION_1_8)
        compileOptions.setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}

fun Project.configureLibAndroidPlugin() {
    this.extensions.getByType<com.android.build.gradle.LibraryExtension>().run {
        compileSdkVersion(TangonochesConfigs.CompileSdkVersion)
        buildToolsVersion(TangonochesConfigs.BuildToolsVersion)

        defaultConfig {
            minSdkVersion(Dependencies.minSdkVersion)
            targetSdkVersion(Dependencies.targetSdkVersion)
//            applicationId = appType.getFullAppId()
//            versionCode = appType.versionCode
//            versionName = appType.versionName
            testInstrumentationRunner = Dependencies.ANDROID_TEST_INSTRUMENTATION_RUNNER
//            vectorDrawables.useSupportLibrary = true
//            multiDexEnabled = true
        }
        compileOptions.setSourceCompatibility(JavaVersion.VERSION_1_8)
        compileOptions.setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}