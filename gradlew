#!/bin/sh
# Gradle wrapper script for Unix-based systems (used by GitHub Actions)

# Determine the script directory
APP_HOME=$(cd "$(dirname "$0")" && pwd -P)

# Find the java executable
JAVACMD="java"
if [ -n "$JAVA_HOME" ]; then
    JAVACMD="$JAVA_HOME/bin/java"
fi

# Run Gradle
exec "$JAVACMD" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS \
    -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
    org.gradle.wrapper.GradleWrapperMain "$@"
