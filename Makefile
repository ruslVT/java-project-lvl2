run-dist:
		./build/install/java-project-lvl2/bin/java-project-lvl2

install:
		./gradlew clean install

run:
		./gradlew run

report:
		./gradlew jacocoTestReport

lint:
		./gradlew checkstyleMain checkstyleTest

test:
		./gradlew test

.PHONY: build

build:
		./gradlew build