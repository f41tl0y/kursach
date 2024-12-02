import java.io.File; //класс для работы с файлами
import java.io.IOException; // обработка исключений, связанных с вводом-выводом
import java.nio.file.Files; // работа с файлами (копирование, чтение)
import java.nio.file.Path; //  пути в файловой системе
import java.nio.file.StandardCopyOption; // параметры копирования
import java.util.Scanner; // ввод от пользователя

public class FileCopyProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу, который необходимо скопировать:");
        String sourcePathStr = scanner.nextLine();
        File sourceFile = new File(sourcePathStr);

        if (!sourceFile.exists() || !sourceFile.isFile()) {
            System.out.println("Файл не найден или указанный путь не является файлом.");
            return;
        }

        System.out.println("Введите путь к директории, куда скопировать файл:");
        String destDirPathStr = scanner.nextLine();
        File destDir = new File(destDirPathStr);

        if (!destDir.exists() || !destDir.isDirectory()) {
            System.out.println("Директория не найдена или указанный путь не является директорией.");
            return;
        }

        copyFile(sourceFile, destDir);
        directoryInfo(destDir);
    }

    private static void copyFile(File sourceFile, File destDir) {
        Path destinationPath = Path.of(destDir.getAbsolutePath(), sourceFile.getName());
        try {
            Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл успешно скопирован в " + destinationPath);
        } catch (IOException e) {
            System.out.println("Произошла ошибка при копировании файла: " + e.getMessage());
        }
    }

    private static void directoryInfo(File dir) {
        System.out.println("Файлы в директории " + dir.getAbsolutePath() + ":");
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    long fileSizeInBytes = file.length();
                    System.out.printf("%s: %d байт%n", file.getName(), fileSizeInBytes);
                }
            }
        } else {
            System.out.println("Не удалось получить список файлов.");
        }
    }
}
