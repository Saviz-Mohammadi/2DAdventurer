#!/bin/bash
# Exit immediately if a command exits with a non-zero status.
set -e

echo "Removing output directory..."
rm -rf output

# Step 1: Create the build directory and change into it.
echo "Creating ouput directory and navigating to it..."
mkdir -p output
cd output

# Step 2: Configure the project with CMake in Debug mode.
echo "Configuring project with CMake..."
cmake ../..

# Step 3: Build the project using parallel build.
echo "Building project & Running..."
cmake --build . --parallel

java -jar 2DAdventurer.jar
