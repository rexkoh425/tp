@echo off
cd /d "%~dp0"

cd ..
call gradlew.bat clean shadowJar

cd text-ui-test
type nul > data\carData.txt
type nul > data\customerData.txt
type nul > data\transactionData.txt

for /f "delims=" %%a in ('dir /b /a-d "..\build\libs\*.jar"') do (
    set "JAR_FILE=%%a"
    goto :found
)
:found

if not defined JAR_FILE (
    echo No JAR file found in ..\build\libs\.
    exit /b 1
)

java -jar "..\build\libs\%JAR_FILE%" < input.txt > ACTUAL.TXT

copy /Y EXPECTED.TXT EXPECTED-UNIX.TXT >nul

dos2unix.exe EXPECTED-UNIX.TXT ACTUAL.TXT

fc /b EXPECTED-UNIX.TXT ACTUAL.TXT >nul
if %errorlevel% equ 0 (
    echo Test passed!
    exit /b 0
) else (
    echo Test failed!
    exit /b 1
)