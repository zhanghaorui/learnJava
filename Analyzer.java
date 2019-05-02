package test;




import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileWriter;

import java.io.IOException;

import java.io.Writer;

import java.util.ArrayList;

import java.util.Scanner;



public class Analyzer {

    private File inputFile;

    private File outputFile;

    private String fileContent;

    private ArrayList<Word> list = new ArrayList<>();



    public Analyzer(String input,String output) {

        inputFile = new File(input);

        outputFile = new File(output);

    }

    /**

     * 从指定的文件中读取源程序文件内容

     * @return

     */

    public String getContent() {

        StringBuilder stringBuilder = new StringBuilder();

        try(Scanner reader = new Scanner(inputFile)) {

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                stringBuilder.append(line + "\n");

            }

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return fileContent = stringBuilder.toString();

    }

    /**

     * 先将源程序中的注释和换行替换成空串

     * 然后扫描程序，在程序结束前将扫描到的词添加到list中

     * 最后把扫描结果保存到指定的文件中

     */

    public void analyze(String fileContent) {

        int over = 1;

        Word word = new Word();

        //fileContent = fileContent.replaceAll("//.*\n", "") // 去除字符串fileContent中所有的单行注释 与 换行

        //		.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", ""); // 去除字符串fileContent中所有的多行注释

        //现在不用正则表达式也可以识别换行和单行注释和多行注释了

        CodeScanner scanner = new CodeScanner(fileContent.toCharArray());

        while (over != 0) {

            word = scanner.scan();

//			System.out.println("(" + word.getTypenum() + " ," + word.getWord() + ")"); //在控制台输出结果

            list.add(word);

            over = word.getTypenum();

        }

        saveResult();

    }

    /**

     * 将结果写入到到指定文件中

     * 如果文件不存在，则创建一个新的文件

     * 用一个foreach循环将list中的项变成字符串写入到文件中

     */

    public void saveResult() {

        if (!outputFile.exists())

            try {

                outputFile.createNewFile();

            } catch (IOException e1) {

                // TODO Auto-generated catch block

                e1.printStackTrace();

            }

        try(Writer writer = new FileWriter(outputFile)){

            for (Word word : list) {

                writer.write("(" + word.getTypenum() + " ," + word.getWord() + ")\n");

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }





    public static void main(String[] args) {

        Analyzer analyzer = new Analyzer("C:\\Users\\10521\\IdeaProjects\\untitled\\src\\test\\input.txt","output.txt");//输入输出可自己修改，文件放在当前文件夹下，刷新项目就可以看到了

        analyzer.analyze(analyzer.getContent());

    }

}
