# ZipSecurity 
## Introduction
A zip bomb is a malicious archive file designed to crash or render useless the program or system reading it. This form of denial-of-service (DoS) attack aims to overwhelm the target application or system by exploiting the way compression algorithms work.

The basic idea behind a zip bomb involves creating a small, highly compressed file that, when decompressed, expands into an enormous file, consuming a significant amount of system resources. This can lead to performance degradation, system crashes, or even complete unavailability.

### Directory Traversal Attacks

Malicious actors may attempt to traverse directories using crafted file paths within ZIP entries.
Without proper validation, this can lead to unauthorized access or exposure of sensitive files.
### Zip Bomb Attacks

Zip bomb attacks involve creating ZIP files with intentionally compressed data that expands dramatically upon extraction.
If not detected, this can lead to denial-of-service attacks, consuming excessive resources during extraction.
How to Approach
To mitigate these risks, the ZipSecurity class employs the following key approaches:

1. ### Directory Validation
   Function: isInSubDirectory(File baseDir, File file)
   Description: Checks if a file is within a specified base directory, preventing directory traversal.
2. ### Entry Name Validation
   Function: isValidZipEntryName(String entryName)
   Description: Ensures that the name of each ZIP entry is valid, preventing path traversal attacks.
3. ### Zip Bomb Detection
   Function: isZipBomb(ZipEntry entry)
   Description: Identifies potential zip bomb attacks by comparing compressed and uncompressed sizes of ZIP entries.
4. ### Entry Validation
   Function: isValidZipEntry(ZipEntry entry)
   Description: Combines name validation and zip bomb detection to ensure the overall validity of ZIP entries.
5. ### Limited InputStream
   Function: getInputStreamForEntry(ZipFile zipFile, ZipEntry entry)
   Description: Provides a secure InputStream for zip entries, limiting the read size and preventing potential zip bomb attacks.
   Implementation
   Key Concepts
   Components:

## Workflow:

### Validation 
Validate each ZIP entry for directory traversal, valid entry name, and potential zip bomb attacks.
### Secure InputStream 
Use a secure input stream with limited size for processing each ZIP entry.
Custom Processing: Leverage customizable callbacks for processing valid ZIP entries based on specific use cases.
