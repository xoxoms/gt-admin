import com.github.gradle.node.npm.task.NpmTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

dependencies {
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

node {
    // 프론트엔트 프로젝트의 node, npm 버전과 같게 합니다.
    val frontendNodeVersion = "23.3.0"
    val frontendNpmVersion = "9.7.1"

    val userHome = System.getProperty("user.home")
    // node 바이너리가 저장될 위치를 지정합니다.
    val nodeDir = file("$userHome/.node/$frontendNodeVersion")
    // npm 바이너리 저장될 위치를 지정합니다.
    val npmDir = file("$userHome/.npm/$frontendNpmVersion")
    // 빌드할 프로젝트의 위치를 지정합니다.
    val projectDir = file("${project.projectDir}/src/main/resources/static")

    version.set(frontendNodeVersion)
    npmVersion.set(frontendNpmVersion)
    download.set(true)
    workDir.set(nodeDir)
    npmWorkDir.set(npmDir)
    nodeProjectDir.set(projectDir)
}

val frontendTask =
    tasks.register<NpmTask>("frontendTask") {
        args.set(listOf("run", "build"))
    }

tasks.withType<KotlinCompile> {
//    dependsOn(frontendTask)
}
