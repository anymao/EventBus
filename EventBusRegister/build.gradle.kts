plugins {
    `kotlin-dsl`
    `maven`
}
buildscript {
    rootProject.file("gradle/publish.gradle")
}

val archivesBaseName = "eventbus-register"
val groupId = rootProject.ext.get("archivesGroup")
val versionName = rootProject.ext.get("archivesVersion")

repositories {
    google()
    jcenter()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:4.1.3")
    implementation("commons-io:commons-io:2.8.0")
    implementation("commons-codec:commons-codec:1.15")
}

tasks {
    "uploadArchives"(Upload::class) {
        repositories {
            withConvention(MavenRepositoryHandlerConvention::class) {
                mavenDeployer {
                    withGroovyBuilder {
                        "repository"("url" to mavenLocal().url)
                    }
                    pom.project {
                        withGroovyBuilder {
                            "groupId"(groupId)
                            "artifactId"(archivesBaseName)
                            "version"(versionName)
                        }
                        name = "EventBus-Gradle-Plugin"
                        description = "Help EventBus auto register Indexes for Android."
                    }
                }
            }
        }
    }
}


