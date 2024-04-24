plugins {
    alias(libs.plugins.androidApplication)

}

android {
    namespace = "com.xk.porject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xk.porject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["AROUTER_MODULE_NAME"] = project.name
            }
        }
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
    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
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
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    implementation(libs.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //okhttp
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //retrofit
    var dialogx_version = "0.0.50.beta11"
    implementation ("com.github.kongzue.DialogX:DialogX:${dialogx_version}")
    implementation ("com.github.kongzue.DialogXSample:AlbumDialog:0.0.13")
    implementation ("com.github.kongzue.DialogXSample:DatePicker:0.0.13")
    implementation ("com.github.gcacace:signature-pad:1.3.1")
    implementation(files("libs/BaiduLBS_Android.jar"))

    implementation(project(":adminproject"))
    implementation(project(":base"))
    implementation ("com.github.gcacace:signature-pad:1.3.1")
}