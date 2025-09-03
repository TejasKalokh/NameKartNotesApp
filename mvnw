
#!/bin/sh
MVNW_REPOURL=""
MVNW_VERBOSE=""
java -version >/dev/null 2>&1 || { echo >&2 "Java is required."; exit 1; }
exec mvn "$@"
