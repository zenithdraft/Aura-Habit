#!/usr/bin/env bash

##############################################################################
##
##  Gradle wrapper
##
##############################################################################

# Determine the Java command to use to start the JVM.
if [ -n "" ] ; then
    JAVACMD=""
else
    if [ -n "$JAVA_HOME"  ] ; then
        JAVACMD="$JAVA_HOME/bin/java"
    else
        JAVACMD="java"
    fi
fi

# Determine the script directory.
SCRIPT_DIR=$(dirname "$0")

# Determine the wrapper jar file.
WRAPPER_JAR="$SCRIPT_DIR/gradle/wrapper/gradle-wrapper.jar"

# Execute the wrapper."exec" "$JAVACMD" "$DEFAULT_JVM_OPTS" "$JAVA_OPTS" "-jar" "$WRAPPER_JAR" "$@"

