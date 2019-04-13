# Bing-wallpaper

#### 运行

1. 运行

   mvn spring-boot:run --spring.profiles.active=live

2. 打包

   mvn clean compile package -DskipTests -Pprod
   
### 部署

1.部署脚本

```bat
@echo off & setlocal enabledelayedexpansion
set java_home=C:\Program Files\Java\jdk1.8.0_77&set path=%java_home%\bin;%path%

dir /b /on | findstr exe > tmp.txt

for /f "delims= tokens=1-5" %%a in (tmp.txt) do (
	set n=%%a
rem echo !n!
)
del tmp.txt /f

echo  echo !n! & pause

mode 180,60 

title bing-9080

rem java -jar -Dspring.datasource.maxActive=5 -Dspring.profiles.active=live -Dserver.port=9080 !n!

start "9080-bing live" /d %~dp0 mode 180,60 \
		& java -jar -Dspring.datasource.maxActive=5 -Dspring.profiles.active=live -Dserver.port=9080 !n!

```