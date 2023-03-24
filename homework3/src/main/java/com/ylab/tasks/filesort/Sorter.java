package homework3.src.main.java.com.ylab.tasks.filesort;

import java.io.*;

public class Sorter {

    private String sortedFile = "sortedFile.txt";
    private String firstTmpFile = "firstTmpFile.txt";
    private String secondTmpFile = "secondTmpFile.txt";


    public File sortFile(File dataFile) throws IOException {
        // calculate count of lines
        int linesCount = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(dataFile.getName())))) {
            while (reader.readLine() != null) {
                linesCount++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // Dividing by two files
        int elementsCount = 1;
        splitFiles(dataFile.getName(), elementsCount, linesCount);

        // First sorting
        sort(elementsCount, linesCount);

        // Next sorting with increasing to pow(2, n) elements
        for (int n = 1; n < 30; n++) {
            elementsCount = power(2, n);
            splitFiles(sortedFile, elementsCount, linesCount);
            sort(elementsCount, linesCount);
        }

        return new File(sortedFile);
    }

    // splitting data from input file to two files
    public void splitFiles(String fileName, int elementsCount, int linesCount) {
        try (BufferedReader sourceFileReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)));
             FileWriter firstTmpFileWriter = new FileWriter(firstTmpFile, false);
             FileWriter secondTmpFileWriter = new FileWriter(secondTmpFile, false)
        ) {
            for (int i = 0; i < linesCount; ) {
                int firstFilePointer = 0;
                int secondFilePointer = 0;

                while (firstFilePointer < elementsCount) {
                    String currentLine = sourceFileReader.readLine();
                    if (currentLine == null) {
                        break;
                    }
                    firstTmpFileWriter.write(currentLine + "\n");
                    firstFilePointer++;
                    i++;
                }
                while (secondFilePointer < elementsCount) {
                    String currentLine = sourceFileReader.readLine();
                    if (currentLine == null) {
                        break;
                    }
                    secondTmpFileWriter.write(currentLine + "\n");
                    secondFilePointer++;
                    i++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sort(int num, int count) {
        try (BufferedReader firstTmpFileReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(secondTmpFile)));
             BufferedReader secondTmpFileReader = new BufferedReader(
                     new InputStreamReader(new FileInputStream(firstTmpFile)));
             FileWriter sortedFileWriter = new FileWriter(sortedFile, false)
        ) {
            String lineFromFirstFile = firstTmpFileReader.readLine();
            String lineFromSecondFile = secondTmpFileReader.readLine();

            for (int i = 0; i < count; ) {
                int j = 0;
                // merge files
                int first = 0;
                int second = 0;
                while (j < num * 2) {
                    if (lineFromSecondFile == null && lineFromFirstFile == null) {
                        i++;
                        break;
                    } else if (lineFromSecondFile == null) {
                        sortedFileWriter.write(lineFromFirstFile + "\n");
                        first++;
                        lineFromFirstFile = firstTmpFileReader.readLine();
                    } else if (lineFromFirstFile == null) {
                        sortedFileWriter.write(lineFromSecondFile + "\n");
                        second++;
                        lineFromSecondFile = secondTmpFileReader.readLine();
                    }
                    else if (Long.parseLong(lineFromFirstFile) < Long.parseLong(lineFromSecondFile)
                            && first < num
                    ) {
                        sortedFileWriter.write(lineFromFirstFile + "\n");
                        first++;
                        lineFromFirstFile = firstTmpFileReader.readLine();
                    }
                    else if (Long.parseLong(lineFromFirstFile) < Long.parseLong(lineFromSecondFile)
                            && !(first < num)
                    ) {
                        sortedFileWriter.write(lineFromSecondFile + "\n");
                        second++;
                        lineFromSecondFile = secondTmpFileReader.readLine();
                    }
                    else if (Long.parseLong(lineFromFirstFile) > Long.parseLong(lineFromSecondFile)
                            && second < num
                    ) {
                        sortedFileWriter.write(lineFromSecondFile + "\n");
                        second++;
                        lineFromSecondFile = secondTmpFileReader.readLine();
                    }
                    else if (Long.parseLong(lineFromFirstFile) > Long.parseLong(lineFromSecondFile)
                            && !(second < num)
                    ) {
                        sortedFileWriter.write(lineFromFirstFile + "\n");
                        first++;
                        lineFromFirstFile = firstTmpFileReader.readLine();
                    }
                    else if (Long.parseLong(lineFromFirstFile) == Long.parseLong(lineFromSecondFile)) {
                        sortedFileWriter.write(lineFromFirstFile + "\n");
                        sortedFileWriter.write(lineFromSecondFile + "\n");
                        first++;
                        second++;
                        lineFromFirstFile = firstTmpFileReader.readLine();
                        lineFromSecondFile = secondTmpFileReader.readLine();
                    }
                    j++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int power(int value, int powerValue) {
        if (powerValue == 1) {
            return value;
        } else {
            return value * power(value, powerValue - 1);
        }
    }
}
