@ECHO ON
echo %4
IF "%4" EQU "" (
  ECHO No Password
  %1\WinRAR x %2 %3
) ELSE (
  ECHO Yes Password
  %1\WinRAR x %2 -p%3 %4
)

EXIT