package services.documentation.HTMLFormatter;

import services.dataStorage.DiskOperation;
import services.documentation.DataBlock;
import services.documentation.Parameter;

import java.util.ArrayList;

public class HTMLFormatter {
    DataBlock datablock;

    public HTMLFormatter(DataBlock datablock) {
        this.datablock = datablock;
    }

    public String getHTML() {
        return assemblyParts();
    }

    // TODO: aggiungere structure
    public String getHTMLOnlyMethodName() {
        return "<p>" + datablock.getMethodName() + "</p>";
    }

    private String assemblyParts() {
        String method =  generateMethodPart();
        String params = generateParametersPart();
        String returns = generateReturnsPart();

        method = method.replace("{{PARAMETERS}}", params);
        method = method.replace("{{RETURNS}}", returns);

        return method;
    }

    private String generateMethodPart() {
        String mainStructure = getMethodPartStructure();

        mainStructure = mainStructure.replace("{{METHOD_NAME}}", datablock.getMethodName());
        mainStructure = mainStructure.replace("{{METHOD_DESCRIPTION}}", datablock.getDescription());

        return mainStructure;
    }

    private String generateParametersPart() {
        StringBuilder params = new StringBuilder();
        String structure = getParametersStructure();
        ArrayList<Parameter> parameters = datablock.getParams();

        if(datablock.getParams().size() == 0) return "";

        for(int i = 0; i < parameters.size(); i++) {
            Parameter currentParam = parameters.get(i);
            String textToAppend = currentParam.getName() + " - " + currentParam.getDescription();

            if (i < parameters.size() - 1) textToAppend += "<br>";

            params.append(textToAppend);
        }

        structure = structure.replace("{{PARAMETERS}}", params.toString());

        return structure;
    }

    private String generateReturnsPart() {
        String structure = getReturnsStructure();

        if(datablock.getReturns().equals("")) return "";

        structure = structure.replace("{{RETURNS}}", datablock.getReturns());

        return structure;
    }

    private String getMethodPartStructure() {
        String path = getClass().getResource("/resources/html/dataBlockHTML/MethodStructure.html").getFile();
        String data = readData(path);
        return  data;
    }

    private String getParametersStructure() {
        String path = getClass().getResource("/resources/html/dataBlockHTML/ParametersStructure.html").getFile();
        String data = readData(path);
        return  data;
    }

    private String getReturnsStructure() {
        String path = getClass().getResource("/resources/html/dataBlockHTML/ReturnsStructure.html").getFile();
        String data = readData(path);
        return  data;
    }

    private String readData(String path) {
        String text = new DiskOperation().readData(path);
        return text;
    }
}
