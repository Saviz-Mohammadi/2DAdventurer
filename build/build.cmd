@echo off

echo Removing output directory...
rmdir /s /q output
if errorlevel 1 goto :error

echo Creating output directory and navigating to it...
mkdir output
if errorlevel 1 goto :error
cd output
if errorlevel 1 goto :error

echo Configuring project with CMake...
cmake ..\..\ 
if errorlevel 1 goto :error

echo Building project and Running...
cmake --build . --parallel
if errorlevel 1 goto :error

REM Note: Windows uses a semicolon as the classpath separator.
java -jar 2DAdventurer.jar
if errorlevel 1 goto :error

goto :eof

:error
echo An error occurred. Exiting.
exit /b 1
