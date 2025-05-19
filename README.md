# 설명
- 미용실에서 예약과 직원을 관리하는 용도의 어드민 토이 프로젝트입니다.
- 주말마다 기능을 조금씩 붙여보고 있습니다.

# 환경
- npm 9.7.1
- node 23.3.0
- java 21
- gradle 8.10.0
- h2 DB(MYSQL mode)

# 뷰 어드민 실행
1.1. cd {WORKSPACE}/gt-admin/api/src/main/resources/static
1.2. npm install
1.3. npm run dev

# 서버 실행
2.1. cd {WORKSPACE}/gt-admin
2.2. ./gradlew clean bootJar
2.3. java -jar ./api/build/libs/api-1.0-SNAPSHOT.jar