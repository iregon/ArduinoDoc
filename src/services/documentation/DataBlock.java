package services.documentation;

import java.util.ArrayList;

public class DataBlock {
	private String text;
	
	private String description = "";
	private String methodName = "";
	private ArrayList<Parameter> params = new ArrayList<Parameter>();
	private String returns = "";

	public DataBlock(String text) {
		this.text = text;
		interpret();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}
	public ArrayList<Parameter> getParams() {
		return params;
	}

	public void setParams(ArrayList<Parameter> params) {
		this.params = params;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
	
	private void interpret()  {
		String[] lines = text.split("\n");
		
		description = getDescription(lines);
		
		methodName = getMethodName(lines);
		
		params = getParameters(lines);
		
		returns = getReturns(lines);
	}
	
	public String getTextDoc() {
		String doc = "";
		
		doc = methodName + "\n\n" + description;
		
		if(!params.isEmpty()) {
			doc += "\n\nParameters:";
			
			for (int i = 0; i < params.size(); i++) {
				doc += "\n" + params.get(i).getName() + " - " + params.get(i).getDescription();
			}
		}
		
		if(!returns.equals("")) {
			doc += "\n\nReturns:\n" + returns;
		}
		
		return doc;
	}
	
	public String getHTMLDoc() {
		StringBuilder doc = new StringBuilder();

		doc.append("<div><p>" + methodName + "</p><p>" + description + "<p>");
		
		if(!params.isEmpty()) {
			doc.append("<p>Parameters:</p>");
			
			for (int i = 0; i < params.size(); i++) {
				doc.append("<p>" + params.get(i).getName() + " - " + params.get(i).getDescription() + "</p>");
			}
		}
		
		if(!returns.equals("")) {
			doc.append("<p>Returns:</p><p>" + returns + "</p>");
		}

		doc.append("</div>");
		return doc.toString();
	}
	
	private String normalizeString(String line) {
		String doc = line;
		
		if(doc.contains("/* ")) doc = doc.replace("/* ", "");
		if(doc.contains(" * ")) doc = doc.replace(" * ", "");
		if(doc.contains("@param")) doc = doc.replace("@param ", "");
		if(doc.equals(" *")) doc = "";
		
		return doc;
	}
	
	private String getDescription(String[] lines) {
		String desc = "";
		for (int i = 0; i < lines.length; i++) {
			String line = normalizeString(lines[i]);
			
			if(!line.equals(" */") && !line.equals("")) desc += normalizeString(lines[i]) + " ";
			else break;
		}
		return desc;
	}
	
	private String getMethodName(String[] lines) {
		String name = "";
		
		for (int i = 0; i < lines.length; i++) {
			if(lines[i].contains("*/")) {
				name = lines[i+1].substring(0, lines[i+1].indexOf("{"));
				break;
			}
		}
		
		return name;
	}
	
	private ArrayList<Parameter> getParameters(String[] lines) {
		ArrayList<Parameter> p = new ArrayList<Parameter>();
		
		for (int i = 0; i < lines.length; i++) {
			if(lines[i].contains("@param")) {
				String line = normalizeString(lines[i]);
				int splitIndex = line.indexOf(" ");
				p.add(new Parameter(line.substring(0, splitIndex), line.substring(splitIndex + 1, line.length())));
			}
		}
		
		return p;
	}
	
	private String getReturns(String[] lines) {
		String r = "";
		
		for (int i = 0; i < lines.length; i++) {
			if(lines[i].contains("@return")) {
				String line = normalizeString(lines[i]);
				int splitIndex = line.indexOf(" ");
				r = line.substring(splitIndex + 1, line.length());
			}
		}
		
		return r;
	}
}
