@echo off
setlocal
pushd "%~dp0"

where java >nul 2>&1
if errorlevel 1 (
    echo No se encontro Java. Instala el JDK 17 y vuelve a abrir PowerShell.
    popd
    exit /b 1
)

set "SPRING_PROFILES_ACTIVE=dev"
echo Compilando los tres servicios. La primera vez se descargan las dependencias.
call "%~dp0mvnw.cmd" -B -ntp package
if errorlevel 1 (
    echo La compilacion fallo. Revisa el mensaje anterior.
    popd
    exit /b 1
)

start "Cliente - 8081" cmd /k java -jar "cliente-service\target\cliente-service.jar"
start "Equipo - 8082" cmd /k java -jar "equipo-service\target\equipo-service.jar"
start "Orden de servicio - 8083" cmd /k java -jar "orden-service\target\orden-service.jar"

echo Espera a que las tres ventanas muestren Started antes de abrir Postman.
popd
