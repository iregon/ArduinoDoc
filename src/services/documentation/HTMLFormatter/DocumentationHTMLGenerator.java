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
        String html = composeHTML();
        System.out.println(html);
        return html;
    }

    public String composeHTML() {
        String mainStructure = getMainStructure();
        String style = getHTMLStyle();
        String summary = getMethodSummaryBlock();
        String details = getMethodDetailBlock();

        mainStructure = mainStructure.replace("{{STYLE}}", style);
        mainStructure = mainStructure.replace("{{SUMMARY}}", summary);
        mainStructure = mainStructure.replace("{{DETAILS}}", details);

        return mainStructure;
    }

    public String getMainStructure() {
        String path = getClass().getResource("/resources/html/dataBlockHTML/MainStructure.html").getFile();
        String data = readData(path);
        return  data;
    }
    /*
    public String getHTML() {
        structure.append("<html>");
        structure.append(getHTMLHeadTag());
        structure.append("<body>");

        structure.append(getMethodSummaryBlock());
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
*/
    private String getHTMLStyle() {
        String path = getClass().getResource("/resources/html/dataBlockHTML/MainStyle.css").getFile();
        String data = readData(path);
        return  data;
    }

    //TODO: aggiungere un file di struttura per il blocco dei metodi dettagliati, fare riferimento a java api
    private String getMethodDetailBlock() {
        StringBuilder block = new StringBuilder();

        for (DataBlock dataBlock : blocks) {
            block.append(new HTMLFormatter(dataBlock).getHTML());
        }

        return block.toString();
    }

    //TODO: aggiungere un file di struttura per il blocco riassunto dei metodi, fare riferimento a java api
    private String getMethodSummaryBlock() {
        StringBuilder block = new StringBuilder();

        for (DataBlock dataBlock : blocks) {
            block.append(new HTMLFormatter(dataBlock).getHTMLOnlyMethodName());
        }

        return block.toString();
    }

    private String readData(String path) {
        String text = new DiskOperation().readData(path);
        return text;
    }

}
