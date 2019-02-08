package services.documentation.HTMLFormatter;

import services.dataStorage.DiskOperation;
import services.documentation.DataBlock;

import java.util.ArrayList;

public class DocumentationHTMLGenerator {

    private StringBuilder structure;
    private ArrayList<DataBlock> blocks;

    public DocumentationHTMLGenerator(ArrayList<DataBlock> blocks) {
        structure = new StringBuilder();
        this.blocks = blocks;
    }

    public String getHTML() {
        structure.append("<html>");
        structure.append(getHTMLHeadTag());
        structure.append("<body>");

        structure.append(getMethodDetailBlock());

        structure.append("</body></html>");
        return structure.toString();
    }

    private String getHTMLHeadTag() {
        StringBuilder data = new StringBuilder();
        data.append("<head><style>");
        data.append(getHTMLStyle());
        data.append("</style></head>");

        return data.toString();
    }

    private String getHTMLStyle() {
        String path = getClass().getResource("/resources/html/dataBlockHTML/MainStyle.css").getFile();
        String data = readData(path);
        return  data;
    }

    /*
    TODO: aggiungere un file di struttura per il blocco dei metodi dettagliati, fare riferimento a java api
     */
    private String getMethodDetailBlock() {
        StringBuilder block = new StringBuilder();

        for (DataBlock dataBlock : blocks) {
            block.append(new HTMLFormatter(dataBlock).getHTML());
        }

        return block.toString();
    }

    private String readData(String path) {
        String text = new DiskOperation().readData(path);
        return text;
    }


}
