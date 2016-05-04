set SERVICE_NAME=MYSERVER_VERSION_2

set PR_INSTALL=D:\caonimabi8\prunsrv.exe

set PROJECTPATH=D:\caonimabi8

set PRUNSRV = %PROJECTPATH%\prunsrv.exe

REM Service log configuration

set PR_LOGPREFIX=%SERVICE_NAME%

set PR_LOGPATH=D:\caonimabi8

set PR_STDOUTPUT=D:\caonimabi8\stdout.txt

set PR_STDERROR=D:\caonimabi8\stderr.txt

set PR_LOGLEVEL=Info



REM Path to java installation

set PR_JVM=F:\jdk\jre\bin\server\jvm.dll
set PR_CLASSPATH=D:\caonimabi8\server8_22_daemon.jar


REM Startup configuration

set PR_STARTUP=auto

set PR_STARTMODE=jvm

set PR_STARTCLASS=maintest.server_version_2

set PR_STARTMETHOD=start



REM Shutdown configuration

set PR_STOPMODE=jvm

set PR_STOPCLASS=maintest.server_version_2

set PR_STOPMETHOD=stop



REM JVM configuration

set PR_JVMMS=256

set PR_JVMMX=1024

set PR_JVMSS=4000

set PR_JVMOPTIONS=-Duser.language=DE;-Duser.region=de



REM Install service

%PR_INSTALL% //IS//%SERVICE_NAME%
REM %PR_INSTALL% //US//%SERVICE_NAME%