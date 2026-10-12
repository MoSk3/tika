@echo off
setlocal enabledelayedexpansion

rem ============================================================
rem  run-pit.bat - run PIT mutation testing on one class
rem
rem  Usage (run from the Tika ROOT folder):
rem    run-pit.bat                          (asks for the class name)
rem    run-pit.bat <ClassName> [TestClassPattern]
rem
rem  Examples:
rem    run-pit.bat org.apache.tika.parser.pdf.PDMetadataExtractor
rem    run-pit.bat PDMetadataExtractor
rem    run-pit.bat PDMetadataExtractor org.apache.tika.parser.pdf.PDFParserTest
rem
rem  Output is shown on screen AND saved to:
rem    pit-logs\pit_<ClassName>_<yyyy-MM-dd_HH-mm-ss>.log
rem ============================================================

rem ---- settings you may want to change ----
set "MODULE=:tika-parser-pdf-module"
set "LOGDIR=pit-logs"
set "SKIP_FAILING=true"
rem -----------------------------------------

if not exist "pom.xml" (
    echo ERROR: pom.xml not found. Run this script from the Tika root folder.
    exit /b 1
)

rem Use the command-line arguments if given, otherwise ask.
set "CLASS=%~1"
set "TESTS=%~2"

if "%CLASS%"=="" (
    echo Enter the class name to test. A simple name or a fully qualified name works.
    echo Example: PDMetadataExtractor
    echo Example: org.apache.tika.parser.pdf.PDMetadataExtractor
    echo.
    set /p "CLASS=Class name: "
    if "!CLASS!"=="" (
        echo No class name entered. Exiting.
        exit /b 1
    )
    set /p "TESTS=Test class pattern (optional, press Enter to skip): "
)

rem A simple name (no dot) gets a leading wildcard so PIT can match it.
echo %CLASS% | find "." >nul
if errorlevel 1 set "CLASS=*%CLASS%"

rem Optional test class filter
set "TESTS_ARG="
if not "%TESTS%"=="" set "TESTS_ARG='-DtargetTests=%TESTS%'"

rem Make a filesystem-safe name and a timestamp (locale independent)
set "PIT_CLASS=%CLASS%"
for /f "usebackq delims=" %%i in (`powershell -NoProfile -Command "$env:PIT_CLASS -replace '[^\w.-]','_'"`) do set "SAFE=%%i"
for /f "usebackq delims=" %%i in (`powershell -NoProfile -Command "Get-Date -Format yyyy-MM-dd_HH-mm-ss"`) do set "TS=%%i"

if not exist "%LOGDIR%" mkdir "%LOGDIR%"
set "LOG=%LOGDIR%\pit_%SAFE%_%TS%.log"

echo Running PIT on: %CLASS%
echo Module        : %MODULE%
echo Log file      : %LOG%
echo.

powershell -NoProfile -Command "mvn test-compile org.pitest:pitest-maven:mutationCoverage -pl %MODULE% '-DtargetClasses=%CLASS%' %TESTS_ARG% '-DskipFailingTests=%SKIP_FAILING%' 2>&1 | Tee-Object -FilePath '%LOG%'; exit $LASTEXITCODE"
set "RC=%ERRORLEVEL%"

echo.
if "%RC%"=="0" (
    echo PIT finished successfully.
) else (
    echo PIT / Maven exited with code %RC%. See the log for details.
)
echo Log saved to: %LOG%
echo HTML report : look under target\pit-reports in the module folder.

endlocal & exit /b %RC%