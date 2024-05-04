plugins {
    alias(libs.plugins.androidLibrary)
}

android {
    namespace = "com.xk.base"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
     
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    api ("com.orhanobut:logger:2.2.0")
    api ("com.squareup.okhttp3:okhttp:4.2.0")
    api ("com.squareup.retrofit2:retrofit:2.6.2")

    //使用gson解析json
    api ("com.google.code.gson:gson:2.8.5")

    //适配retrofit使用gson解析
    //版本要和retrofit一样
    api ("com.squareup.retrofit2:converter-gson:2.6.2")
    api ("com.squareup.okhttp3:logging-interceptor:4.7.2")
    //适配retrofit支持rxjava
    api ("com.squareup.retrofit2:adapter-rxjava2:2.6.2")
    api ("com.github.JessYanCoding:AndroidAutoSize:v1.2.1")
    // RxAndroid
    api ("io.reactivex.rxjava2:rxandroid:2.1.1")
    api ("com.guolindev.permissionx:permissionx:1.7.1")
    implementation ("org.conscrypt:conscrypt-openjdk-uber:1.1.3")
    api ("com.tencent:mmkv:1.3.5")
    var dialogx_version = "0.0.50.beta11"
    api ("com.github.kongzue.DialogX:DialogX:${dialogx_version}")
    api ("com.github.kongzue.DialogXSample:AlbumDialog:0.0.13")
    api ("com.github.kongzue.DialogXSample:DatePicker:0.0.13")
    api ("com.github.gcacace:signature-pad:1.3.1")
}