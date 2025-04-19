dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    // jpa 쓰기위해서는 코틀린 리플렉션 라이브러리 의존해야한다.
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // h2
    runtimeOnly("com.h2database:h2:2.3.232")

    testImplementation("io.mockk:mockk:1.13.13")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("io.kotest:kotest-framework-datatest:5.9.1")
    testImplementation("com.appmattus.fixture:fixture:1.2.0")
}