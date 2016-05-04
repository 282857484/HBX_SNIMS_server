set SERVICE_NAME=MYSERVER_VERSION_3

set PR_INSTALL=D:\serverTCP\prunsrv.exe

set PROJECTPATH=D:\serverTCP

set PRUNSRV = %PROJECTPATH%\prunsrv.exe

REM Service log configuration

set PR_LOGPREFIX=%SERVICE_NAME%

set PR_LOGPATH=D:\serverTCP

set PR_STDOUTPUT=D:\serverTCP\stdout.txt

set PR_STDERROR=D:\serverTCP\stderr.txt

set PR_LOGLEVEL=Info



REM Path to java installation

REM set PR_JVM=C:\Program Files (x86)\java\jdk1.8.0_11\jre\bin\server\jvm.dll
set PR_JVM=C:\Program Files (x86)\java\jdk1.5\bin\server\jvm.dll
set PR_CLASSPATH=D:\serverTCP\server_TCP_1.jar


REM Startup configuration

set PR_STARTUP=auto

set PR_STARTMODE=jvm

set PR_STARTCLASS=maintest.server_version_3

set PR_STARTMETHOD=start



REM Shutdown configuration

set PR_STOPMODE=jvm

set PR_STOPCLASS=maintest.server_version_3

set PR_STOPMETHOD=stop



REM JVM configuration

set PR_JVMMS=256

set PR_JVMMX=1024

set PR_JVMSS=2000

set PR_JVMOPTIONS=-Duser.language=DE;-Duser.region=de



REM Install service

%PR_INSTALL% //IS//%SERVICE_NAME%
REM %PR_INSTALL% //US//%SERVICE_NAME%