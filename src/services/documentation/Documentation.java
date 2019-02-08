package services.documentation;

import services.documentation.HTMLFormatter.DocumentationHTMLGenerator;
import services.documentation.HTMLFormatter.HTMLFormatter;
import services.dataStorage.DiskOperation;

import java.util.ArrayList;

public class Documentation {

	ArrayList<DataBlock> blocks = new ArrayList<>();

	public Documentation() {}

	public void generate(String text) {
		String[] lines = text.split("\n");
		int start = 0;
		int end = 0;
		boolean state = true;
		String blockText = "";

		for (int i = 0; i < lines.length; i++) {
			if (state) {
				if (lines[i].contains("/* ")) {
					start = i;
					state = false;
				}
			} else {
				if (lines[i].contains("}")) {
					end = i;

				}
			}

			if ((start < end) && !state) {
				blockText = "";
				for (int j = start; j <= end; j++) {
					blockText += lines[j] + "\n";
				}
				blocks.add(new DataBlock(blockText));
				state = true;
			}
		}
	}
	//TODO StringBuilder
	public String getTextVersion() {
		String text = "";

		for (DataBlock dataBlock : blocks) {
			text += dataBlock.getTextDoc() + "\n----------------------\n";
		}

		return text;
	}

	public String getHTMLVersion() {
		DocumentationHTMLGenerator generator = new DocumentationHTMLGenerator(blocks);
		String html = generator.getHTML();
		return html;
	}





}
