run-dist:
		./build/install/java-project-lvl2/bin/java-project-lvl2

install:
		./gradlew clean install

build:
		./gradlew clean build

run:
		./gradlew run
report:
		./gradlew jacocoTestReport
