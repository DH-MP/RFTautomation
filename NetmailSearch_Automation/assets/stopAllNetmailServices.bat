
sc %1 stop "AWA_XMLV_SVC"
ping -n 11 -w 2 127.0.0.1 > nul


sc %1 stop "GWA_XMLV_SVC"
ping -n 11 -w 2 127.0.0.1 > nul


EXIT