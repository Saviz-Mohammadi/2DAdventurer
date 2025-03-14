cmake_minimum_required(VERSION 3.16)

project("2DAdventurer" LANGUAGES Java)

find_package(Java 11 REQUIRED)
include(UseJava)

set(SRC_FILES
        "./src/Main.java"
        "./src/Game.java"
        "./src/GameFrame.java"
        "./src/GamePanel.java"
        "./src/ResourceManager.java"
        "./src/Outcome.java"
        "./src/Direction.java"
        "./src/KeyboardInputs.java"
        "./src/MouseInputs.java"
        "./src/Entity.java"
        "./src/Player.java")

add_jar("${PROJECT_NAME}"
        SOURCES ${SRC_FILES}
        ENTRY_POINT Main
        OUTPUT_NAME "${PROJECT_NAME}")

# This segment manages resources:
add_custom_target(CopyResources ALL
        COMMAND ${CMAKE_COMMAND} -E copy_directory
        ${PROJECT_SOURCE_DIR}/resources
        ${PROJECT_BINARY_DIR}/resources
        COMMENT "Copying resources to binary location")

add_dependencies("${PROJECT_NAME}" CopyResources)
