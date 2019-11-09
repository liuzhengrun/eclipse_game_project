@echo off  
echo setting runtime variable  
set java_out_file=..\src\main\java
del /f /s /q  "%java_out_file%\*"

REM _protoSrc 是你的proto文件目录的位置  
set _protoSrc=proto
  
REM protoExe 是用于从proto生成java的protoc.exe程序的位置  
set protoExe=protoc.exe
  
REM java_out_file 存放生成的Java文件目录的位置  

mkdir %java_out_file%;
for /R "%_protoSrc%" %%i in (*.proto) do (
	echo "%%~nxi"
	
    if "%%~xi"  == ".proto" (
        %protoExe% -I=%_protoSrc% --java_out=%java_out_file% proto\%%~nxi
    )
)

pause