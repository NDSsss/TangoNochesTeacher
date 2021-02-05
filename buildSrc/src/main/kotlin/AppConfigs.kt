sealed class TangonochesApp {
    abstract val appSuffix: String
    abstract val versionCode: Int
    abstract val versionName: String

    object Teacher : TangonochesApp() {
        override val appSuffix: String = "teacher"
        override val versionCode: Int = 2
        override val versionName: String = "$versionCode"

    }
}

fun TangonochesApp.getFullAppId(): String =
    "${Dependencies.BASE_TANGONOCHES_ID}.${this.appSuffix}"

object TangonochesConfigs{
    const val CompileSdkVersion = 30
    const val BuildToolsVersion = "30.0.2"
}