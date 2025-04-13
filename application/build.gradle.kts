dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    // jpa 쓰기위해서는 코틀린 리플렉션 라이브러리 의존해야한다.
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // h2
    runtimeOnly("com.h2database:h2:2.3.232")
}