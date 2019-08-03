package BigWork1;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ParseXml {
    private Connection conn;
    private PreparedStatement pst;
    public  void parseXmlS(String filePath) throws Exception{
        SAXReader saxReader = new SAXReader();
        File file = new File(filePath);
        Document document;
        conn = new GetConnection().connect();
        System.out.println(conn);
        String sql = "insert into stu(id,name,gender,age,birth) values(?,?,?,?,?)";
        pst = conn.prepareStatement(sql);
        try {
            document = saxReader.read(file);
            Element rootElement = document.getRootElement();
            List<Element> list = rootElement.elements();
            for (int i = 0; i < list.size(); i++) {
                Element stuelements = list.get(i);
                String id = stuelements.attributeValue("id");
                pst.setLong(1,Long.parseLong(id));
                List<Element> elements = stuelements.elements();
                for (int j = 0; j < elements.size(); j++) {
                    Element element = elements.get(j);
                    String name = element.getName();
                    if ("name".equals(name)) {
                        String text = null;
                        text = element.getText();
                        System.out.println(text);
                        pst.setString(2,text);
                    }
                    if ("gender".equals(name)){
                        String gender = element.getText();
                        pst.setString(3,gender);
                    }
                    if ("age".equals(name)){
                        String age = element.getText();
                        pst.setInt(4,Integer.parseInt(age));
                    }
                    if ("birth".equals(name)){
                        String birth = element.getText();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        Date date = simpleDateFormat.parse(birth);
                        pst.setDate(5,new java.sql.Date(date.getTime()));
                    }

                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        pst.execute();
        System.out.println("Ö´ÐÐ³É¹¦");
        pst.close();
        conn.close();
                }
                public static void main (String[]args){
                    ParseXml parseXml = new ParseXml();
                    try {
                        parseXml.parseXmlS("J2SE/src/BigWork1/stu.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }